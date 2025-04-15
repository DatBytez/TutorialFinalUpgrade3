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
import managers.SceneManager;
import map.TileData;
import map.TileMap;
import ui.TileSelectionBar;
import util.MapIO;

public class EditingScene extends Scene {

	private static final int LAYER_COUNT = 5; // Total number of layers.

	// Our simple tile map is a 3D array:
	// [layer][row][column]
	private TileData[][][] tileMapLayers = new TileData[LAYER_COUNT][MAX_WORLD_ROW][MAX_WORLD_COL];

	// Active layer index.
	private int activeLayer = 0;

	// Palette of available tiles loaded from the atlas.
	private List<TileData> paletteTiles = new ArrayList<>();
	// Index in the atlas reserved for transparency (or you can choose to use -1).
	private int transparentTileIndex;

	// Atlas images indexed by atlas ID.
	private Map<String, BufferedImage> spriteAtlases = new HashMap<>();
	private static final String DEFAULT_ATLAS_ID = "main";

	// A UI element for choosing tiles.
	private TileSelectionBar tileSelectionBar;

	// Camera offset fields for scrolling.
	private int camX = 0;
	private int camY = 0;
	private int scrollSpeed = 16;

	public EditingScene() {
		Debug.msg("EditingScene Loaded");
		loadAtlases();
		loadPaletteFromAtlas(DEFAULT_ATLAS_ID);
		initializeTileLayers();

		// Create the tile selection bar at the bottom of the screen.
		int barHeight = (TILE_SIZE + 20) * 2;
		int barY = SCREEN_HEIGHT - barHeight;
		// (Assumes that your TileSelectionBar constructor is updated to the simple
		// version.)
		tileSelectionBar = new TileSelectionBar(0, barY, SCREEN_WIDTH, barHeight, paletteTiles, this);
	}

	// Loads atlas images into our spriteAtlases map.
	private void loadAtlases() {
		spriteAtlases.put(DEFAULT_ATLAS_ID, Artist.getSpriteAtlas());
	}

	// Loads the palette tiles from the atlas.
	private void loadPaletteFromAtlas(String atlasId) {
		BufferedImage atlas = spriteAtlases.get(atlasId);
		int cols = atlas.getWidth() / TILE_SIZE;
		int rows = atlas.getHeight() / TILE_SIZE;
		int totalTiles = cols * rows;
		// We reserve the last tile as "transparent."
		transparentTileIndex = totalTiles - 1;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				int index = y * cols + x;
				paletteTiles.add(new TileData(atlasId, index));
			}
		}
		Debug.msg("Palette loaded with " + paletteTiles.size() + " tiles. Transparent tile index: "
				+ transparentTileIndex);
	}

	// Initializes every cell of the tile map.
	// For the base (layer 0), assign the default tile (index 0); for other layers,
	// assign the transparent tile.
	private void initializeTileLayers() {
		for (int layer = 0; layer < LAYER_COUNT; layer++) {
			for (int row = 0; row < MAX_WORLD_ROW; row++) {
				for (int col = 0; col < MAX_WORLD_COL; col++) {
					if (layer == 0) {
						tileMapLayers[layer][row][col] = new TileData(DEFAULT_ATLAS_ID, 0);
					} else {
						tileMapLayers[layer][row][col] = new TileData(DEFAULT_ATLAS_ID, transparentTileIndex);
					}
				}
			}
		}
	}

	// Given a TileData object, extracts the corresponding subimage from the atlas.
	private BufferedImage getTileImage(TileData tile) {
		BufferedImage atlas = spriteAtlases.get(tile.getAtlasId());
		int tilesPerRow = atlas.getWidth() / TILE_SIZE;
		int atlasX = tile.getTileIndex() % tilesPerRow;
		int atlasY = tile.getTileIndex() / tilesPerRow;
		return atlas.getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

	// Builds a list of images for the palette tiles.
	private List<BufferedImage> getPaletteTileImages() {
		List<BufferedImage> images = new ArrayList<>();
		for (TileData tile : paletteTiles) {
			images.add(getTileImage(tile));
		}
		return images;
	}

	// Determines which cell is clicked (adding camera offset) and paints the tile.
	private void paintTile(int x, int y) {
		int col = (x + camX) / TILE_SIZE;
		int row = (y + camY) / TILE_SIZE;
		if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
			return;
		int selectedTileIndex = tileSelectionBar.getSelectedTileIndex();
		tileMapLayers[activeLayer][row][col] = new TileData(paletteTiles.get(selectedTileIndex));
		Debug.msg("Painted tile at layer " + activeLayer + " at (" + row + ", " + col + ") with tileIndex "
				+ selectedTileIndex);
	}

	// Erases a tile (right-click) by setting the tile's index to -1 (i.e.
	// transparent).
	private void eraseTile(int x, int y) {
		int col = (x + camX) / TILE_SIZE;
		int row = (y + camY) / TILE_SIZE;
		if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
			return;
		// Set the tile's index to -1 indicating it should not be drawn.
		tileMapLayers[activeLayer][row][col] = new TileData(paletteTiles.get(transparentTileIndex));
		Debug.msg("Erased tile at layer " + activeLayer + " at (" + row + ", " + col + ") with tileIndex "
				+ transparentTileIndex);
	}

	// Renders the tile map by directly iterating over all layers, rows, and
	// columns.
	// Only tiles with a nonnegative tileIndex are drawn.
	private void renderTileMap(Artist a) {
		// Use full opacity for the active layer and lower for others.
//		for (int layer = 0; layer < LAYER_COUNT; layer++) {
//			float alpha = (layer == activeLayer) ? 1.0f : 0.5f;
//			a.setAlphaComposite(alpha);
//			for (int row = 0; row < MAX_WORLD_ROW; row++) {
//				for (int col = 0; col < MAX_WORLD_COL; col++) {
//					TileData tile = tileMapLayers[layer][row][col];
//					// Only draw if tile index is >= 0 (i.e. not erased).
//					if (tile.getTileIndex() >= 0) {
//						BufferedImage img = getTileImage(tile);
//						// Subtract camera offset when drawing.
//						int drawX = col * TILE_SIZE - camX;
//						int drawY = row * TILE_SIZE - camY;
//						a.drawImage(img, drawX, drawY, TILE_SIZE, TILE_SIZE);
//					}
//				}
//			}
//		}
			for (int row = 0; row < MAX_WORLD_ROW; row++) {
				for (int col = 0; col < MAX_WORLD_COL; col++) {
					for (int layer = 0; layer < LAYER_COUNT; layer++) {
						float alpha = (layer == activeLayer) ? 1.0f : 0.5f;
						a.setAlphaComposite(alpha);
					TileData tile = tileMapLayers[layer][row][col];
					// Only draw if tile index is >= 0 (i.e. not erased).
					if (tile.getTileIndex() >= 0) {
						BufferedImage img = getTileImage(tile);
						// Subtract camera offset when drawing.
						int drawX = col * TILE_SIZE - camX;
						int drawY = row * TILE_SIZE - camY;
						a.drawImage(img, drawX, drawY, TILE_SIZE, TILE_SIZE);
					}
				}
			}
		}
		a.resetComposite();
	}

	// Main render method: draws the tile map and overlays the tile selection bar
	// (if any),
	// and displays the active layer.
	@Override
	public void render(Artist a) {
		renderTileMap(a);
		// If you still want to display a selection bar, call its render method.
		tileSelectionBar.render(a, getPaletteTileImages());
		a.setColor(Color.WHITE);
		a.drawString("Layer: " + (activeLayer + 1), 10, 20);
	}

	// --- Mouse Event Handling ---

	// Left-click: if the click is within the selection bar region, forward it;
	// otherwise, paint.
	@Override
	public void mousePressed(int x, int y) {
		if (inSelectionBar(x, y)) {
			tileSelectionBar.mousePressed(x, y);
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
			tileSelectionBar.mouseReleased(x, y);
		}
	}

	// Right-click: erase the tile.
	public void rightMousePressed(int x, int y) {
		if (!inSelectionBar(x, y)) {
			eraseTile(x, y);
		}
	}

	// Helper method: determines if (x,y) falls within the selection bar.
	private boolean inSelectionBar(int x, int y) {
		return (x >= tileSelectionBar.x && x < tileSelectionBar.x + tileSelectionBar.width && y >= tileSelectionBar.y
				&& y < tileSelectionBar.y + tileSelectionBar.height);
	}

	// --- Helper Methods for the TileSelectionBar ---

	public void toggleLayer() {
		activeLayer = (activeLayer + 1) % LAYER_COUNT;
		Debug.msg("Switched to layer: " + (activeLayer + 1));
	}

	public void saveMap() {
		MapIO.saveMap(new TileMap(tileMapLayers, activeLayer));
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
			activeLayer = keyCode - KeyEvent.VK_1;
			Debug.msg("Switched to layer: " + (activeLayer + 1));
		}
		// Camera scrolling: adjust camX and camY.
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
			// Load map functionality.
			TileMap loadedMap = MapIO.loadMap();
			if (loadedMap != null) {
				tileMapLayers = loadedMap.getTileMapLayers();
				activeLayer = loadedMap.getActiveLayer();
			}
			Debug.msg("Map loaded");
		}
	}

	@Override
	public void keyReleased(int keyCode) {
	}

	@Override
	public void keyTyped(char keyChar) {
	}

	@Override
	public void mouseClicked(int x, int y) {
	}

	@Override
	public void mouseMoved(int x, int y) {
	}

	@Override
	public void update() {
	}
}
