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

    private static final int LAYER_COUNT = 5; // TODO: Consider moving this to TileMap.
    
    private TileMap tileMap;
    private List<TileData> paletteTiles = new ArrayList<>();
    private int transparentTileIndex = -1;
    
    private Map<String, BufferedImage> spriteAtlases = new HashMap<>();
    private static final String DEFAULT_ATLAS_ID = "main";
    
    private TileSelectionBar tileSelectionBar;
    
    // Camera offset fields.
    private int camX = 0;
    private int camY = 0;
    private int scrollSpeed = 16;
    
    // Offscreen buffers: one per layer and a dirty flag per layer.
    private BufferedImage[] offscreenLayers;
    private boolean[] layerDirty;
    
    public EditingScene() {
        Debug.msg("EditingScene Loaded");
        loadAtlases();
        loadPaletteFromAtlas(DEFAULT_ATLAS_ID);
        tileMap = new TileMap(new TileData[LAYER_COUNT][MAX_WORLD_ROW][MAX_WORLD_COL], 0);
        
        // Initialize offscreen buffers before calling initializeTileLayers()
        initOffscreenBuffers();
        initializeTileLayers();
        
        // Create the tile selection bar at the bottom.
        int barHeight = (TILE_SIZE + 20) * 2;
        int barY = SCREEN_HEIGHT - barHeight;
        tileSelectionBar = new TileSelectionBar(0, barY, SCREEN_WIDTH, barHeight, paletteTiles, TILE_SIZE, this);
    }
    
    // Loads atlas images.
    private void loadAtlases() {
        spriteAtlases.put(DEFAULT_ATLAS_ID, Artist.getSpriteAtlas());
    }
    
    // Loads palette tiles from the atlas.
    private void loadPaletteFromAtlas(String atlasId) {
        BufferedImage atlas = spriteAtlases.get(atlasId);
        int cols = atlas.getWidth() / TILE_SIZE;
        int rows = atlas.getHeight() / TILE_SIZE;
        int totalTiles = cols * rows;
        transparentTileIndex = totalTiles - 1; // The last tile is reserved as transparent.
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int index = y * cols + x;
                paletteTiles.add(new TileData(atlasId, index));
            }
        }
        Debug.msg("Palette loaded with " + paletteTiles.size() + " tiles. Transparent tile index: " + transparentTileIndex);
    }
    
    // Initializes each layer of the tile map: layer 0 gets the default tile; all others get the transparent tile.
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
                    markLayerDirty(layer);
                }
            }
        }
    }
    
    // Initializes offscreen buffers and dirty flags for each layer.
    private void initOffscreenBuffers() {
        offscreenLayers = new BufferedImage[LAYER_COUNT];
        layerDirty = new boolean[LAYER_COUNT];
        int mapWidth = MAX_WORLD_COL * TILE_SIZE;
        int mapHeight = MAX_WORLD_ROW * TILE_SIZE;
        for (int layer = 0; layer < LAYER_COUNT; layer++) {
            offscreenLayers[layer] = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
            layerDirty[layer] = true;
        }
    }
    
    // Marks a layer as needing to be updated.
    public void markLayerDirty(int layer) {
        if (layer >= 0 && layer < LAYER_COUNT)
            layerDirty[layer] = true;
    }
    
    // Re-renders the entire layer into its offscreen buffer.
    private void updateLayer(int layer) {
        Artist offscreenArtist = new Artist(offscreenLayers[layer]);
        offscreenArtist.setColor(new Color(0, 0, 0, 0)); // Clear with transparency.
        offscreenArtist.fillRect(0, 0, offscreenLayers[layer].getWidth(), offscreenLayers[layer].getHeight());
        TileData[][] layerData = tileMap.getTileMapLayers()[layer];
        for (int row = 0; row < MAX_WORLD_ROW; row++) {
            for (int col = 0; col < MAX_WORLD_COL; col++) {
                TileData tile = layerData[row][col];
                if (tile.getTileIndex() >= 0) {
                    BufferedImage img = getTileImage(tile);
                    offscreenArtist.drawImage(img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
        offscreenArtist.resetComposite();
        layerDirty[layer] = false;
    }
    
    // Given a TileData, returns its subimage from the atlas.
    private BufferedImage getTileImage(TileData tile) {
        BufferedImage atlas = spriteAtlases.get(tile.getAtlasId());
        int tilesPerRow = atlas.getWidth() / TILE_SIZE;
        int atlasX = tile.getTileIndex() % tilesPerRow;
        int atlasY = tile.getTileIndex() / tilesPerRow;
        return atlas.getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    // Returns a list of images for the palette tiles.
    private List<BufferedImage> getPaletteTileImages() {
        List<BufferedImage> images = new ArrayList<>();
        for (TileData tile : paletteTiles) {
            images.add(getTileImage(tile));
        }
        return images;
    }
    
    // Left-click painting: calculates the map cell from the mouse coordinates (adjusted for camera offset)
    // and sets that cell to the currently selected tile.
    private void paintTile(int x, int y) {
        int col = (x + camX) / TILE_SIZE;
        int row = (y + camY) / TILE_SIZE;
        if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
            return;
        int selectedTileIndex = tileSelectionBar.getSelectedTileIndex();
        tileMap.getTileMapLayers()[tileMap.getActiveLayer()][row][col] = new TileData(paletteTiles.get(selectedTileIndex));
        Debug.msg("Painted tile at layer " + tileMap.getActiveLayer() + " position (" + row + ", " + col + ") with tileIndex " + selectedTileIndex);
    }
    
    // Renders the tile map using offscreen buffers.
    // Here we draw the region defined by the camera offset (i.e. what is visible on screen).
    private void renderTileMap(Artist a) {
        for (int layer = 0; layer < LAYER_COUNT; layer++) {
            if (layerDirty[layer])
                updateLayer(layer);
            float alpha = (layer == tileMap.getActiveLayer()) ? 1.0f : 0.5f;
            a.setAlphaComposite(alpha);
            // Destination covers the entire screen.
            int dx1 = 0;
            int dy1 = 0;
            int dx2 = SCREEN_WIDTH;
            int dy2 = SCREEN_HEIGHT;
            // Source is the region of the offscreen buffer corresponding to the camera offset.
            int sx1 = camX;
            int sy1 = camY;
            int sx2 = camX + SCREEN_WIDTH;
            int sy2 = camY + SCREEN_HEIGHT;
            a.drawImage(offscreenLayers[layer], dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2);
        }
        a.resetComposite();
    }
    
    // Main render method: draws the tile map, the tile selection bar, and the layer number.
    @Override
    public void render(Artist a) {
        renderTileMap(a);
        tileSelectionBar.render(a, getPaletteTileImages());
        a.setColor(Color.WHITE);
        a.drawString("Layer: " + (tileMap.getActiveLayer() + 1), 10, 20);
    }
    
    // --- Mouse Event Handling ---
    // For left-clicks (handled in mousePressed): delegate to tileSelectionBar if inside UI,
    // otherwise call paintTile.
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
    
 // New method: called when a right-click is detected.
    public void rightMousePressed(int x, int y) {
        // If the click occurred inside the selection bar, ignore it.
        if (inSelectionBar(x, y))
            return;
        // Convert screen coordinates to map cell coordinates, accounting for the camera offset.
        int col = (x + camX) / TILE_SIZE;
        int row = (y + camY) / TILE_SIZE;
        // Ensure the coordinates are within the map.
        if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
            return;
        // Set the tile's index to -1 to indicate it should not be drawn (transparent).
        tileMap.getTileMapLayers()[tileMap.getActiveLayer()][row][col] = new TileData(DEFAULT_ATLAS_ID, -1);
        Debug.msg("Right-click: Cleared tile at layer " + tileMap.getActiveLayer() +
                  " position (" + row + ", " + col + ")");
        markLayerDirty(tileMap.getActiveLayer());
    }
    
    // Returns true if (x,y) is within the tile selection bar area.
    private boolean inSelectionBar(int x, int y) {
        return (x >= tileSelectionBar.x && x < tileSelectionBar.x + tileSelectionBar.width &&
                y >= tileSelectionBar.y && y < tileSelectionBar.y + tileSelectionBar.height);
    }
    
    // --- Helper Methods for the TileSelectionBar ---
    public void toggleLayer() {
        int currentLayer = tileMap.getActiveLayer();
        int newLayer = (currentLayer + 1) % LAYER_COUNT;
        tileMap.setActiveLayer(newLayer);
        Debug.msg("Switched to layer: " + (newLayer + 1));
    }
    
    public void saveMap() {
        MapIO.saveMap(tileMap);
        Debug.msg("Map saved");
    }
    
    public void returnToMainMenu() {
        SceneManager.changeScene(SceneManager.SceneType.MENU);
        Debug.msg("Returning to main menu");
    }
    
    // --- Key Event Handling ---
    @Override
    public void keyPressed(int keyCode) {
        if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_5) {
            tileMap.setActiveLayer(keyCode - KeyEvent.VK_1);
            Debug.msg("Switched to layer: " + (tileMap.getActiveLayer() + 1));
        }
        // Scrolling using WASD and arrow keys.
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            camY = Math.min(camY + scrollSpeed, MAX_WORLD_ROW * TILE_SIZE - SCREEN_HEIGHT);
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            camY = Math.max(camY - scrollSpeed, 0);
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            camX = Math.min(camX + scrollSpeed, MAX_WORLD_COL * TILE_SIZE - SCREEN_WIDTH);
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            camX = Math.max(camX - scrollSpeed, 0);
        }
        if (keyCode == KeyEvent.VK_L) {
            TileMap loadedMap = MapIO.loadMap();
            if (loadedMap != null) {
                tileMap = loadedMap;
                for (int i = 0; i < LAYER_COUNT; i++) {
                    layerDirty[i] = true;
                }
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
        // Additional behavior if needed.
    }
    
    @Override
    public void mouseMoved(int x, int y) {
        // Additional behavior if needed.
    }
    
    @Override
    public void update() {
        // Update logic if required.
    }
}
