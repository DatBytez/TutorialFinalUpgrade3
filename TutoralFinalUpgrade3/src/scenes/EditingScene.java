package scenes;

import static helpz.Constants.MAX_WORLD_COL;
import static helpz.Constants.MAX_WORLD_ROW;
import static helpz.Constants.SCREEN_HEIGHT;
import static helpz.Constants.SCREEN_WIDTH;
import static helpz.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helpz.Debug;
import main.Artist;
import map.TileData;
import map.TileMap;
import ui.TileSelectionBar;
import util.MapIO;
import managers.SceneManager;

public class EditingScene extends Scene {

    private static final int LAYER_COUNT = 5; //TODO: Move this to TileMap
    
    private TileMap tileMap;
    
    private List<TileData> paletteTiles = new ArrayList<>();
    private int transparentTileIndex = -1;
    
    private Map<String, BufferedImage> spriteAtlases = new HashMap<>();
    private static final String DEFAULT_ATLAS_ID = "main";
    
    private TileSelectionBar tileSelectionBar;

    public EditingScene() {
        Debug.msg("EditingScene Loaded");

        loadAtlases();
        loadPaletteFromAtlas(DEFAULT_ATLAS_ID); //TODO: Add more palettes to atlas
        // Initialize our tile map with empty layers and set active layer index to 0.
        tileMap = new TileMap(new TileData[LAYER_COUNT][MAX_WORLD_ROW][MAX_WORLD_COL], 0);
        initializeTileLayers();
        
        // Create the tile selection bar. It is positioned at the bottom of the screen.
        int barHeight = (TILE_SIZE + 20) * 2;
        int barY = SCREEN_HEIGHT - barHeight;
        tileSelectionBar = new TileSelectionBar(0, barY, SCREEN_WIDTH, barHeight, paletteTiles, TILE_SIZE, this);
    }

    // Loads atlas images into our spriteAtlases map using a key (the atlas ID).
    private void loadAtlases() {
        spriteAtlases.put(DEFAULT_ATLAS_ID, Artist.getSpriteAtlas());
    }

    // Loads the tile palette from the specified atlas image.
    // It calculates how many tiles the atlas contains, identifies the transparent tile,
    // and populates the paletteTiles list with TileData for each tile.
    private void loadPaletteFromAtlas(String atlasId) {
        BufferedImage atlas = spriteAtlases.get(atlasId);
        int cols = atlas.getWidth() / TILE_SIZE;
        int rows = atlas.getHeight() / TILE_SIZE;
        int totalTiles = cols * rows;
        transparentTileIndex = totalTiles - 1; // The last tile is set to be transparent.
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int index = y * cols + x;
                paletteTiles.add(new TileData(atlasId, index));
            }
        }
        Debug.msg("Palette loaded with " + paletteTiles.size() + " tiles. Transparent tile index: " + transparentTileIndex);
    }

    // Initializes each layer of the tile map.
    // For the base layer (layer 0), each tile is set to the default tile (index 0).
    // For all other layers, each tile is set to be transparent.
    private void initializeTileLayers() {
        TileData[][][] layers = tileMap.getTileMapLayers();
        for (int layer = 0; layer < LAYER_COUNT; layer++) {
            for (int row = 0; row < MAX_WORLD_ROW; row++) {
                for (int col = 0; col < MAX_WORLD_COL; col++) {
                    if (layer == 0) {
                        layers[layer][row][col] = new TileData(DEFAULT_ATLAS_ID, 0);
                    } else {
                        layers[layer][row][col] = new TileData(DEFAULT_ATLAS_ID, transparentTileIndex);
                    }
                }
            }
        }
    }
    
    // Given a TileData object, this method extracts and returns the correct subimage (tile)
    // from the atlas image based on the tileIndex.
    private BufferedImage getTileImage(TileData tile) {
        BufferedImage atlas = spriteAtlases.get(tile.getAtlasId());
        int tilesPerRow = atlas.getWidth() / TILE_SIZE;
        int atlasX = tile.getTileIndex() % tilesPerRow;
        int atlasY = tile.getTileIndex() / tilesPerRow;
        return atlas.getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    // Builds and returns a list of BufferedImages corresponding to each tile in the palette.
    // This is used to render the tile buttons in the selection bar.
    private List<BufferedImage> getPaletteTileImages() {
        List<BufferedImage> images = new ArrayList<>();
        for (TileData tile : paletteTiles) {
            images.add(getTileImage(tile));
        }
        return images;
    }
    
    // Paints a tile onto the map when the user clicks on the map area.
    // It calculates which cell of the map grid was clicked and applies the tile
    // corresponding to the currently selected tile (from the selection bar).
    private void paintTile(int x, int y) {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;
        // If the click is outside of the map grid, do nothing.
        if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
            return;
        // Get the tile index selected by the tileSelectionBar.
        int selectedTileIndex = tileSelectionBar.getSelectedTileIndex();
        // Update the tile in the active layer with a new TileData based on the selected tile.
        tileMap.getTileMapLayers()[tileMap.getActiveLayer()][row][col] = new TileData(paletteTiles.get(selectedTileIndex));
        Debug.msg("Painted tile at layer " + tileMap.getActiveLayer() + " position (" + row + ", " + col
                + ") with tileIndex " + selectedTileIndex);
    }
    
    // Renders the tile map by iterating over each layer, row, and column.
    // For each tile, it draws the corresponding image with appropriate transparency.
    private void renderTileMap(Artist a) {
        for (int layer = 0; layer < LAYER_COUNT; layer++) {
            float alpha = (layer == tileMap.getActiveLayer()) ? 1.0f : 0.5f;
            a.setAlphaComposite(alpha);
            for (int row = 0; row < MAX_WORLD_ROW; row++) {
                for (int col = 0; col < MAX_WORLD_COL; col++) {
                    TileData tile = tileMap.getTileMapLayers()[layer][row][col];
                    if (tile.getTileIndex() >= 0) { // Only draw if the tile index is valid.
                        BufferedImage img = getTileImage(tile);
                        a.drawImage(img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }
        a.resetComposite();
    }
    
    // The render method for the EditingScene.
    // It draws the tile map, renders the tile selection bar, and displays the current active layer.
    @Override
    public void render(Artist a) {
        renderTileMap(a);
        tileSelectionBar.render(a, getPaletteTileImages());
        a.setColor(Color.WHITE);
        a.drawString("Layer: " + (tileMap.getActiveLayer() + 1), 10, 20);
    }
    
    // Mouse events:
    // If a click occurs inside the selection bar, forward the event solely to the bar.
    // Otherwise, paint on the tile map.
    
    @Override
    public void mousePressed(int x, int y) {
        if (inSelectionBar(x, y)) {
            tileSelectionBar.handleMousePressed(x, y);
        } else {
            paintTile(x, y);
        }
    }
    
    @Override
    public void mouseDragged(int x, int y) {
        if (!inSelectionBar(x, y)) {
            paintTile(x, y);
        }
    }
    
    @Override
    public void mouseReleased(int x, int y) {
        if (inSelectionBar(x, y)) {
            tileSelectionBar.handleMouseReleased(x, y);
        }
    }
    
    // Determines whether a given x,y coordinate lies within the selection bar area. //TODO: Can this be moved to Bar so that it can be easily checked with other bars in other scenes
    private boolean inSelectionBar(int x, int y) {
        return (x >= tileSelectionBar.x && x < tileSelectionBar.x + tileSelectionBar.width
                && y >= tileSelectionBar.y && y < tileSelectionBar.y + tileSelectionBar.height);
    }
    
    // --- Helper methods that can be called by the TileSelectionBar ---
    
    // Toggles the active layer of the tile map.
    public void toggleLayer() {
        int currentLayer = tileMap.getActiveLayer();
        int newLayer = (currentLayer + 1) % LAYER_COUNT;
        tileMap.setActiveLayer(newLayer);
        Debug.msg("Switched to layer: " + (newLayer + 1));
    }

    // Saves the current tile map using MapIO.
    public void saveMap() {
        MapIO.saveMap(tileMap);
        Debug.msg("Map saved");
    }
    
    // Returns to the main menu by changing the scene.
    public void returnToMainMenu() {
        SceneManager.changeScene(SceneManager.SceneType.MENU);
        Debug.msg("Returning to main menu");
    }
    
    @Override
    public void update() { }
    
    // Key event handling:
    // Allows quick layer switching and map saving/loading via keyboard shortcuts.
    @Override
    public void keyPressed(int keyCode) {
        if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_5) {
            tileMap.setActiveLayer(keyCode - KeyEvent.VK_1);
            Debug.msg("Switched to Layer: " + (tileMap.getActiveLayer() + 1));
        }
//        if (keyCode == KeyEvent.VK_S) {
//            saveMap();
//        }
        if (keyCode == KeyEvent.VK_L) {
            TileMap loadedMap = MapIO.loadMap();
            if (loadedMap != null) {
                tileMap = loadedMap;
            }
            Debug.msg("Map loaded");
        }
    }
    
    @Override 
    public void keyReleased(int keyCode) { }
    
    @Override 
    public void keyTyped(char keyChar) { }

    @Override
    public void mouseClicked(int x, int y) {
        // Implementation can be added here if needed.
    }

    @Override
    public void mouseMoved(int x, int y) {
        // Implementation can be added here if needed.
    }
}
