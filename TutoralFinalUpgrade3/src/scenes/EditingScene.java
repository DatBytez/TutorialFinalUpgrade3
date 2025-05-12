package scenes;

import static helpz.Constants.SCREEN_HEIGHT;
import static helpz.Constants.SCREEN_WIDTH;
import static helpz.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import helpz.Debug;
import main.Artist;
import managers.SceneManager;
import managers.TileManager;
import map.Level;
import util.MapIO;

public class EditingScene extends Scene {

    // Virtual view offsets.
    private int obvWorldX;
    private int obvWorldY;
    private int obvScreenX;
    private int obvScreenY;
    
    // Our Level instance that encapsulates the map.
    private Level level;
    // The active layer index (if you plan to support layer editing).
    private int activeLayer = 0;
    
    // Level name.
    private String levelName = "cydonia";
    
    // The TileManager provides the tile images.
    private TileManager tileManager;
    
    // The new TileSelectionBar.
    private ui.TileSelectionBar tileSelectionBar;
    
    public EditingScene() {
        Debug.msg("EditingScene Loaded (Using new Level)");
        
        // Set up view offsets (adjust as needed to center your view).
        obvScreenX = SCREEN_WIDTH / 2;
        obvScreenY = SCREEN_HEIGHT / 2;
        obvWorldX = TILE_SIZE * 24;
        obvWorldY = TILE_SIZE * 24;
        
        // Create the TileManager.
        tileManager = new TileManager();
        
        // Build a list of level file names for each layer.
        ArrayList<String> fileNames = new ArrayList<>();
        // For example, assume 6 layer files.
        for (int i = 0; i < 6; i++) {
            fileNames.add("/maps/" + levelName + "0" + i + ".txt");
        }
        // Create the Level object.
        level = new Level(levelName, fileNames, tileManager);
        activeLayer = 0;
        
     // Create the TileSelectionBar using the palette from the TileManager.
        tileSelectionBar = new ui.TileSelectionBar(
            0, 
            SCREEN_HEIGHT - (TILE_SIZE + 20) * 2, 
            SCREEN_WIDTH, 
            (TILE_SIZE + 20) * 2, 
            tileManager.getPalette(), // Return a List<TileData> from TileManager.
            this  // Pass this EditingScene so fixed buttons can call toggleLayer, saveMap, etc.
        );
    }
    
    @Override
    public void render(Artist a) {
        // Draw the Level (all layers) using view offsets.
        level.draw(a, obvWorldX, obvWorldY, obvScreenX, obvScreenY);
        // Render the tile selection bar on top.
        a.resetComposite();
        tileSelectionBar.render(a, getPaletteTileImages());
        // Overlay the active layer info.
        a.setColor(Color.WHITE);
        a.drawString("Layer: " + activeLayer, 10, 20);
    }
    
    /**
     * Converts the TileManager’s palette into a list of tile images.
     */
    private List<BufferedImage> getPaletteTileImages() {
        List<BufferedImage> images = new ArrayList<>();
        List<map.TileData> palette = tileManager.getPalette();
        int cols = Artist.getSpriteAtlas().getWidth() / TILE_SIZE;
        for (map.TileData td : palette) {
            int index = td.getTileIndex();
            int atlasX = index % cols;
            int atlasY = index / cols;
            BufferedImage img = Artist.getSpriteAtlas().getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            images.add(img);
        }
        return images;
    }
    
    public void toggleLayer() {
        activeLayer = (activeLayer + 1) % 5;
        Debug.msg("Switched to layer: " + (activeLayer + 1));
    }

    public void saveLevel() {
        // Save the current level using the level name (with an appended "Level" suffix, for example).
        util.MapIO.saveLevel(level, levelName + "Level");
        helpz.Debug.msg("Level saved");
    }

    public void returnToMainMenu() {
        SceneManager.changeScene(managers.SceneManager.SceneType.MENU);
        Debug.msg("Returning to main menu");
    }
    
    @Override
    public void update() {
        // Update view offsets or any animations.
    }
    
    // --- Input Handling ---
    
    @Override
    public void mousePressed(int x, int y) {
        // If click is in the tile selection bar area, delegate there.
        if (y >= tileSelectionBar.y && y < tileSelectionBar.y + tileSelectionBar.height) {
            tileSelectionBar.mousePressed(x, y);
        } else {
            changeTile(x, y);
        }
    }
    
    @Override
    public void mouseDragged(int x, int y) {
        changeTile(x, y);
    }
    
    @Override
    public void mouseReleased(int x, int y) {
        tileSelectionBar.mouseReleased(x, y);
    }
    
    @Override
    public void mouseClicked(int x, int y) { }
    
    @Override
    public void mouseMoved(int x, int y) {
        tileSelectionBar.mouseMoved(x, y);
    }
    
    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            obvWorldY -= TILE_SIZE;
        }
        if (keyCode == KeyEvent.VK_S) {
            obvWorldY += TILE_SIZE;
        }
        if (keyCode == KeyEvent.VK_A) {
            obvWorldX -= TILE_SIZE;
        }
        if (keyCode == KeyEvent.VK_D) {
            obvWorldX += TILE_SIZE;
        }
        if (keyCode == KeyEvent.VK_L) {
            Level loaded = MapIO.loadLevel(levelName + "Level", tileManager);
            if (loaded != null) {
                level = loaded;
            }
        }
    }
    
    @Override public void keyReleased(int keyCode) { }
    @Override public void keyTyped(char keyChar) { }
    
    @Override public void rightMousePressed(int x, int y) {
        // Optionally implement tile erasing.
    }
    
    /**
     * Changes the tile at the given screen coordinates.
     * Translates the screen coordinates into tile indices using view offsets.
     */
    private void changeTile(int x, int y) {
        int tileCol = (x + obvWorldX - obvScreenX) / TILE_SIZE;
        int tileRow = (y + obvWorldY - obvScreenY) / TILE_SIZE;
        int selectedId = tileSelectionBar.getSelectedTileIndex();
        int[][] currentLayerData = level.getLayers().get(activeLayer).getLayer();
        // Ensure indices are within bounds.
        if (tileCol >= 0 && tileCol < currentLayerData.length && tileRow >= 0 && tileRow < currentLayerData[0].length) {
            currentLayerData[tileCol][tileRow] = selectedId;
        }
    }
}
