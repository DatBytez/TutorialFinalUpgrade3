package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.Artist;
import map.TileData;
import scenes.EditingScene;
import util.MapIO;

public class TileSelectionBar extends Bar {
    // Fixed buttons (vertical stack)
    private List<MyButton> fixedButtons;
    // Tile buttons arranged in a 2–row grid
    private List<MyButton> tileButtons;

    private List<TileData> paletteTiles;
    private int tileSize;
    // The currently selected tile index (updated by clicking a tile button)
    private int selectedTileIndex;
    
    // Layout variables for the fixed button column and margins.
    public int leftColumnWidth;
    private int margin = 5;
    private int fixedButtonHeight;
    private int fixedButtonWidth;
    
    // A reference to the scene so that the bar can perform scene–specific actions.
    private EditingScene editingScene;

    /**
     * Constructs a new TileSelectionBar.
     * 
     * @param x             x coordinate of the bar
     * @param y             y coordinate of the bar
     * @param width         total width of the bar
     * @param height        total height of the bar
     * @param paletteTiles  list of tile data (from the current atlas)
     * @param tileSize      size (width/height) of each tile button
     * @param editingScene  reference to the EditingScene using the bar
     */
    public TileSelectionBar(int x, int y, int width, int height,
                            List<TileData> paletteTiles, int tileSize, EditingScene editingScene) {
        super(x, y, width, height);
        this.paletteTiles = paletteTiles;
        this.tileSize = tileSize;
        this.editingScene = editingScene;
        this.selectedTileIndex = 0;
        
        // Fixed buttons: use a constant width.
        fixedButtonWidth = 80;
        // Stack three fixed buttons vertically with a margin between them.
        fixedButtonHeight = (height - margin * 4) / 3;
        leftColumnWidth = fixedButtonWidth + margin * 2;
        
        // Create fixed buttons (stacked vertically)
        fixedButtons = new ArrayList<>();
        int buttonX = x + margin;
        int buttonY = y + margin;
        fixedButtons.add(new MyButton("Layer", buttonX, buttonY, fixedButtonWidth, fixedButtonHeight));
        buttonY += fixedButtonHeight + margin;
        fixedButtons.add(new MyButton("Save", buttonX, buttonY, fixedButtonWidth, fixedButtonHeight));
        buttonY += fixedButtonHeight + margin;
        fixedButtons.add(new MyButton("Menu", buttonX, buttonY, fixedButtonWidth, fixedButtonHeight));
        
        // Create tile buttons arranged in a grid with 2 rows.
        tileButtons = new ArrayList<>();
        // The tile buttons will appear immediately to the right of the fixed buttons.
        int tileStartX = x + leftColumnWidth + margin;
        int tileStartY = y + margin;
        int numTileButtons = paletteTiles.size();
        for (int i = 0; i < numTileButtons; i++) {
            int row = i % 2; // Row 0 or 1.
            int col = i / 2;
            int btnX = tileStartX + col * (tileSize + margin);
            int btnY = tileStartY + row * (tileSize + margin);
            MyButton tileButton = new MyButton("", btnX, btnY, tileSize, tileSize, i);
            tileButtons.add(tileButton);
        }
    }
    
    /**
     * Renders the entire selection bar: a solid backdrop, fixed buttons, and tile buttons.
     *
     * @param a          the Artist instance used for rendering.
     * @param tileImages list of BufferedImages corresponding to the palette tiles.
     */
    public void render(Artist a, List<BufferedImage> tileImages) {
        // Draw a solid backdrop.
        a.setColor(Color.DARK_GRAY);
        a.fillRect(x, y, width, height);
        
        // Draw the fixed buttons.
        for (MyButton btn : fixedButtons) {
            btn.draw(a);
        }
        // Draw the tile buttons.
        for (MyButton btn : tileButtons) {
            btn.draw(a);
            int id = btn.getId();
            if (id >= 0 && id < tileImages.size()) {
                BufferedImage tileImg = tileImages.get(id);
                a.drawImage(tileImg, btn.x, btn.y, btn.width, btn.height);
            }
        }
    }
    
    /**
     * Handles mousePressed events within the bar. Fixed buttons call separate
     * methods on the associated EditingScene.
     */
    public void handleMousePressed(int mouseX, int mouseY) {
        // Check fixed buttons.
        for (int i = 0; i < fixedButtons.size(); i++) {
            MyButton btn = fixedButtons.get(i);
            if (btn.getBounds().contains(mouseX, mouseY)) {
                btn.setMousePressed(true);
                if (i == 0) {
                    editingScene.toggleLayer();
                } else if (i == 1) {
                    editingScene.saveMap();
                } else if (i == 2) {
                    editingScene.returnToMainMenu();
                }
                return;
            }
        }
        // Check tile buttons.
        for (MyButton btn : tileButtons) {
            if (btn.getBounds().contains(mouseX, mouseY)) {
                btn.setMousePressed(true);
                selectedTileIndex = btn.getId();
                return;
            }
        }
    }
    
    /**
     * Handles mouseReleased events within the bar. Resets button states.
     */
    public void handleMouseReleased(int mouseX, int mouseY) {
        for (MyButton btn : fixedButtons) {
            btn.resetBooleans();
        }
        for (MyButton btn : tileButtons) {
            btn.resetBooleans();
        }
    }
    
    public int getSelectedTileIndex() {
        return selectedTileIndex;
    }
}
