package ui;

import static helpz.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.Artist;
import map.TileData;
import scenes.EditingScene;

/**
 * A tile selection bar that shows fixed buttons (for layer, save, menu)
 * and a grid of tile images for tile selection.
 */
public class TileSelectionBar extends Bar {
    // Fixed buttons.
    private MyButton bLayer;
    private MyButton bSave;
    private MyButton bMenu;
    // List of tile buttons in the grid.
    private List<MyButton> tileButtons;

    // Palette of tile data for the tile grid.
    private List<TileData> paletteTiles;
    // The currently selected tile index (for the grid portion).
    private int selectedTileIndex;

    // Layout variables.
    private int fixedWidth = 100; // Width reserved for fixed buttons.
    private int margin = 5;
    private int columns;  // Calculated for the tile grid area.

    // Reference to the EditingScene.
    private EditingScene editingScene;

    /**
     * Constructs a new TileSelectionBar.
     * 
     * @param x            x coordinate of the bar.
     * @param y            y coordinate of the bar.
     * @param width        total width of the bar.
     * @param height       total height of the bar.
     * @param paletteTiles list of TileData for the palette.
     * @param editingScene reference to the EditingScene.
     */
    public TileSelectionBar(int x, int y, int width, int height, List<TileData> paletteTiles, EditingScene editingScene) {
        super(x, y, width, height);
        this.paletteTiles = paletteTiles;
        this.editingScene = editingScene;
        this.selectedTileIndex = 0;
        // The grid area starts after the fixed column.
        int gridWidth = width - fixedWidth - margin;
        this.columns = (gridWidth - margin) / (TILE_SIZE + margin);
        initFixedButtons();
        initTileButtons();
    }

    /**
     * Initializes the fixed buttons for "Layer", "Save", and "Menu."
     */
    private void initFixedButtons() {
        int btnWidth = fixedWidth - 2 * margin;
        int btnHeight = (height - 4 * margin) / 3; // Three buttons in vertical stack.
        int btnX = x + margin;
        int btnY = y + margin;
        bLayer = new MyButton("Layer", btnX, btnY, btnWidth, btnHeight);
        btnY += btnHeight + margin;
        bSave = new MyButton("Save", btnX, btnY, btnWidth, btnHeight);
        btnY += btnHeight + margin;
        bMenu = new MyButton("Menu", btnX, btnY, btnWidth, btnHeight);
    }

    /**
     * Initializes tile grid buttons.
     */
    private void initTileButtons() {
        tileButtons = new ArrayList<>();
        // The tile grid area starts after the fixed column.
        int tileGridX = x + fixedWidth + margin;
        int tileGridY = y + margin;
        int gridWidth = width - fixedWidth - margin;
        // Calculate number of columns based on grid width.
        int gridColumns = (gridWidth - margin) / (TILE_SIZE + margin);
        // Create one button per palette tile.
        for (int i = 0; i < paletteTiles.size(); i++) {
            int col = i % gridColumns;
            int row = i / gridColumns;
            int btnX = tileGridX + col * (TILE_SIZE + margin);
            int btnY = tileGridY + row * (TILE_SIZE + margin);
            MyButton tileBtn = new MyButton("", btnX, btnY, TILE_SIZE, TILE_SIZE, i);
            tileButtons.add(tileBtn);
        }
    }

    /**
     * Renders the entire selection bar: fixed buttons on the left and tile grid on the right.
     * 
     * @param a          the Artist instance for rendering.
     * @param tileImages list of BufferedImages corresponding to the paletteTiles.
     */
    public void render(Artist a, List<BufferedImage> tileImages) {
        // Draw the background for the entire bar.
        a.setColor(Color.DARK_GRAY);
        a.fillRect(x, y, width, height);
        
        // Render fixed buttons.
        renderFixedButtons(a);
        
        // Render tile grid buttons.
        renderTileButtons(a, tileImages);
    }
    
    private void renderFixedButtons(Artist a) {
        // Draw each fixed button.
        bLayer.draw(a);
        bSave.draw(a);
        bMenu.draw(a);
    }
    
    private void renderTileButtons(Artist a, List<BufferedImage> tileImages) {
        // Render each tile button in the grid.
        for (MyButton btn : tileButtons) {
            int id = btn.getId();
            if (id >= 0 && id < tileImages.size()) {
                a.drawImage(tileImages.get(id), btn.x, btn.y, TILE_SIZE, TILE_SIZE);
            }
            btn.draw(a);
        }
        // Highlight the tile in the grid if it’s the selected one.
        for (MyButton btn : tileButtons) {
            if (btn.getId() == selectedTileIndex) {
                a.setColor(Color.YELLOW);
                a.drawRect(btn.x, btn.y, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    /**
     * Processes a mousePressed event. Checks first if the click is in the fixed button area;
     * if so, it performs the fixed button action. Otherwise, it checks the tile grid.
     * 
     * @param mouseX the x coordinate (virtual coordinates).
     * @param mouseY the y coordinate (virtual coordinates).
     */
    public void mousePressed(int mouseX, int mouseY) {
        // Check if the click is inside the fixed button area (x to x+fixedWidth).
        if (mouseX >= x && mouseX < x + fixedWidth) {
            // Determine which fixed button was clicked.
            if (bLayer.getBounds().contains(mouseX, mouseY)) {
                bLayer.setMousePressed(true);
                editingScene.toggleLayer();
            } else if (bSave.getBounds().contains(mouseX, mouseY)) {
                bSave.setMousePressed(true);
                editingScene.saveLevel();
            } else if (bMenu.getBounds().contains(mouseX, mouseY)) {
                bMenu.setMousePressed(true);
                editingScene.returnToMainMenu();
            }
        } else {
            // Otherwise, process the tile grid click.
            for (MyButton btn : tileButtons) {
                if (btn.getBounds().contains(mouseX, mouseY)) {
                    btn.setMousePressed(true);
                    selectedTileIndex = btn.getId();
                    break;
                }
            }
        }
    }
    
    public void mouseReleased(int mouseX, int mouseY) {
        bLayer.resetBooleans();
        bSave.resetBooleans();
        bMenu.resetBooleans();
        for (MyButton btn : tileButtons) {
            btn.resetBooleans();
        }
    }
    
    public void mouseMoved(int mouseX, int mouseY) {
        // Optional: update button hover state.
        bLayer.setMouseOver(false);
        bSave.setMouseOver(false);
        bMenu.setMouseOver(false);
        for (MyButton btn : tileButtons) {
            btn.setMouseOver(false);
        }
        if (mouseX >= x && mouseX < x + fixedWidth) {
            if (bLayer.getBounds().contains(mouseX, mouseY))
                bLayer.setMouseOver(true);
            else if (bSave.getBounds().contains(mouseX, mouseY))
                bSave.setMouseOver(true);
            else if (bMenu.getBounds().contains(mouseX, mouseY))
                bMenu.setMouseOver(true);
        } else {
            for (MyButton btn : tileButtons) {
                if (btn.getBounds().contains(mouseX, mouseY)) {
                    btn.setMouseOver(true);
                    break;
                }
            }
        }
    }
    
    /**
     * Returns the currently selected tile index (from the tile grid).
     * @return the selected tile index.
     */
    public int getSelectedTileIndex() {
        return selectedTileIndex;
    }
}
