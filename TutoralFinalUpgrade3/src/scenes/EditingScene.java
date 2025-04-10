package scenes;

import static helpz.Constants.MAX_WORLD_COL;
import static helpz.Constants.MAX_WORLD_ROW;
import static helpz.Constants.SCREEN_HEIGHT;
import static helpz.Constants.SCREEN_WIDTH;
import static helpz.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import helpz.Debug;
import main.Artist;

public class EditingScene extends Scene {

	private static final int LAYER_COUNT = 5;
	private TileData[][][] tileMapLayers = new TileData[LAYER_COUNT][MAX_WORLD_ROW][MAX_WORLD_COL];
	private int activeLayer = 0;
	// Declaration for palette selection.
	private int selectedTileIndex = 0;

	private List<TileData> paletteTiles = new ArrayList<>();
	// Transparent tile is the last tile in the atlas.
	private int transparentTileIndex = -1;

	private Map<String, BufferedImage> spriteAtlases = new HashMap<>();
	private static final String DEFAULT_ATLAS_ID = "main";

	public EditingScene() {
		Debug.msg("EditingScene Loaded");

		loadAtlases();
		loadPaletteFromAtlas(DEFAULT_ATLAS_ID);
		initializeTileLayers();
	}

	private void loadAtlases() {
		spriteAtlases.put(DEFAULT_ATLAS_ID, Artist.getSpriteAtlas());
		// Add more atlases here if needed.
	}

	/**
	 * Loads all the tiles from the specified atlas into the palette. The last tile
	 * (index = totalTiles - 1) is reserved as the transparent tile.
	 */
	private void loadPaletteFromAtlas(String atlasId) {
		BufferedImage atlas = spriteAtlases.get(atlasId);
		int cols = atlas.getWidth() / TILE_SIZE;
		int rows = atlas.getHeight() / TILE_SIZE;
		int totalTiles = cols * rows;
		// Save transparent tile index as the last tile in the atlas.
		transparentTileIndex = totalTiles - 1;

		// Populate the palette with all tiles from the atlas.
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				int index = y * cols + x;
				paletteTiles.add(new TileData(atlasId, index));
			}
		}
		Debug.msg("Palette loaded with " + paletteTiles.size() + " tiles. Transparent tile index: "
				+ transparentTileIndex);
	}

	/**
	 * Initializes each layer. The base layer (layer 0) uses the default tile (index
	 * 0), while all other layers are initialized with the transparent tile.
	 */
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

	private BufferedImage getTileImage(TileData tile) {
		BufferedImage atlas = spriteAtlases.get(tile.atlasId);
		int tilesPerRow = atlas.getWidth() / TILE_SIZE;
		int atlasX = tile.tileIndex % tilesPerRow;
		int atlasY = tile.tileIndex / tilesPerRow;
		return atlas.getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

	private void paintTile(int x, int y) {
		int col = x / TILE_SIZE;
		int row = y / TILE_SIZE;

		if (col < 0 || col >= MAX_WORLD_COL || row < 0 || row >= MAX_WORLD_ROW)
			return;

		tileMapLayers[activeLayer][row][col] = new TileData(paletteTiles.get(selectedTileIndex));
		Debug.msg("Painted tile at layer " + activeLayer + " position (" + row + ", " + col + ") with tileIndex "
				+ selectedTileIndex);
	}

	private void renderTileMap(Artist a) {
		// Draw each layer with appropriate transparency.
		for (int layer = 0; layer < LAYER_COUNT; layer++) {
			float alpha = (layer == activeLayer) ? 1.0f : 1.0f;// 0.6f
			a.setAlphaComposite(alpha);

			for (int row = 0; row < MAX_WORLD_ROW; row++) {
				for (int col = 0; col < MAX_WORLD_COL; col++) {
					TileData tile = tileMapLayers[layer][row][col];
					if (tile.tileIndex >= 0) {
						BufferedImage img = getTileImage(tile);
						a.drawImage(img, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					}
				}
			}
		}
		a.resetComposite();
	}

	private void renderPalette(Artist a) {
		int tilesPerRow = SCREEN_WIDTH / (TILE_SIZE + 4);
		int startX = 10;
		int startY = SCREEN_HEIGHT - (TILE_SIZE + 10) * ((paletteTiles.size() + tilesPerRow - 1) / tilesPerRow);

		for (int i = 0; i < paletteTiles.size(); i++) {
			int row = i / tilesPerRow;
			int col = i % tilesPerRow;
			int x = startX + col * (TILE_SIZE + 4);
			int y = startY + row * (TILE_SIZE + 4);

			BufferedImage img = getTileImage(paletteTiles.get(i));
			a.drawImage(img, x, y, TILE_SIZE, TILE_SIZE);

			if (i == selectedTileIndex) {
				a.setColor(Color.YELLOW);
				a.drawRect(x, y, TILE_SIZE, TILE_SIZE);
			}
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Artist a) {
		renderTileMap(a);
		renderPalette(a);

		a.setColor(Color.WHITE);
		a.drawString("Layer: " + (activeLayer + 1), 10, 20);
	}

	@Override
	public void mousePressed(int x, int y) {
		if (!clickInPalette(x, y)) {
			paintTile(x, y);
		}
	}

	@Override
	public void mouseDragged(int x, int y) {
		paintTile(x, y);
	}

	@Override
	public void mouseClicked(int x, int y) {
	}

	@Override
	public void mouseMoved(int x, int y) {
	}

	@Override
	public void mouseReleased(int x, int y) {
	}

	@Override
	public void keyPressed(int keyCode) {
		if (keyCode >= java.awt.event.KeyEvent.VK_1 && keyCode <= java.awt.event.KeyEvent.VK_5) {
			activeLayer = keyCode - java.awt.event.KeyEvent.VK_1;
			Debug.msg("Switched to Layer: " + (activeLayer + 1));
		}
		if (keyCode == KeyEvent.VK_S) {
//        	 saveMap("src/res/mapData.json");
			saveMap();
			Debug.msg("S key pressed");
		}
		if (keyCode == KeyEvent.VK_L) {
//       	 loadMap("src/res/mapData.json");
			loadMap();
			Debug.msg("L key pressed");
		}
	}

	@Override
	public void keyReleased(int keyCode) {
	}

	@Override
	public void keyTyped(char keyChar) {
	}

	// --- TileData class for storing tile properties ---
	public static class TileData {
		public String atlasId;
		public int tileIndex;
		public boolean isAnimated;
		public boolean isCollidable;
		public int animationFrame;

		public TileData(String atlasId, int tileIndex) {
			this.atlasId = atlasId;
			this.tileIndex = tileIndex;
			this.isAnimated = false;
			this.isCollidable = false;
			this.animationFrame = 0;
		}

		public TileData(TileData other) {
			this.atlasId = other.atlasId;
			this.tileIndex = other.tileIndex;
			this.isAnimated = other.isAnimated;
			this.isCollidable = other.isCollidable;
			this.animationFrame = other.animationFrame;
		}
	}

	/**
	 * Checks whether a mouse click falls within the palette. If so, it selects the
	 * corresponding tile.
	 */
	private boolean clickInPalette(int x, int y) {
		int tilesPerRow = SCREEN_WIDTH / (TILE_SIZE + 4);
		int startX = 10;
		int startY = SCREEN_HEIGHT - (TILE_SIZE + 10) * ((paletteTiles.size() + tilesPerRow - 1) / tilesPerRow);

		for (int i = 0; i < paletteTiles.size(); i++) {
			int row = i / tilesPerRow;
			int col = i % tilesPerRow;
			int tileX = startX + col * (TILE_SIZE + 4);
			int tileY = startY + row * (TILE_SIZE + 4);

			if (x >= tileX && x < tileX + TILE_SIZE && y >= tileY && y < tileY + TILE_SIZE) {
				selectedTileIndex = i;
				Debug.msg("Selected tile index: " + selectedTileIndex);
				return true;
			}
		}
		return false;
	}

	////////////////////////////////////////////////////////////////////////////////
	// SAVE/LOAD IMPLEMENTATION //
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * A helper container class to hold map data for saving and loading.
	 */
	private static class MapData {
		public TileData[][][] tileMapLayers;
		public int activeLayer;
		// You could add additional metadata here as needed.
	}

	/**
	 * Returns the path to the directory where user data will be stored. This folder
	 * will be created in the user's home directory.
	 */
	private String getUserDataDirectory() {
		// For example, use a hidden folder named ".mygame" inside the user's home
		// directory.
		String userHome = System.getProperty("user.home");
		String dataDirPath = userHome + File.separator + ".mygame";
		File dataDir = new File(dataDirPath);
		if (!dataDir.exists()) {
			dataDir.mkdirs();
		}
		return dataDirPath;
	}

	/**
	 * Returns the full file path for the map data file.
	 */
	private String getMapDataFilePath() {
		return getUserDataDirectory() + File.separator + "mapData.json";
	}

	/**
	 * Saves the current map state to a JSON file in the user data directory.
	 */
	public void saveMap() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		MapData mapData = new MapData();
		mapData.tileMapLayers = this.tileMapLayers;
		mapData.activeLayer = this.activeLayer;
		String filePath = getMapDataFilePath();
		try (FileWriter writer = new FileWriter(filePath)) {
			gson.toJson(mapData, writer);
			Debug.msg("Map saved successfully to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a map state from a JSON file in the user data directory.
	 */
	public void loadMap() {
		Gson gson = new Gson();
		String filePath = getMapDataFilePath();
		try (FileReader reader = new FileReader(filePath)) {
			MapData mapData = gson.fromJson(reader, MapData.class);
			this.tileMapLayers = mapData.tileMapLayers;
			this.activeLayer = mapData.activeLayer;
			Debug.msg("Map loaded successfully from " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
