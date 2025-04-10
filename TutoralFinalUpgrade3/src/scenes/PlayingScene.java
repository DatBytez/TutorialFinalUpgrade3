package scenes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import helpz.Debug;
import static helpz.Constants.*;

import main.Artist;
import objects.Tile;

public class PlayingScene extends Scene {
	private ArrayList<Tile> tiles;
	private BufferedImage spriteAtlas;
	private BufferedImage altTileImage; // New image to switch to

	public PlayingScene() {
		Debug.msg("Loaded");
		tiles = new ArrayList<>();
		spriteAtlas = Artist.getSpriteAtlas();
		if (spriteAtlas == null) {
			System.err.println("Error: Sprite Atlas not loaded. [" + this.getClass().getSimpleName() + "]");
			return;
		}
		altTileImage = extractTileFromAtlas(9, 0); // Load the 10th tile in top row
		createTileGrid();
	}

	private void createTileGrid() {
		for (int y = 0; y < MAX_WORLD_ROW; y++) {
			for (int x = 0; x < MAX_WORLD_COL; x++) {
				BufferedImage tileImage = extractTileFromAtlas(0, 0); // Default tile
				Tile tile = new Tile(x * TILE_SIZE, y * TILE_SIZE, tileImage);
				tiles.add(tile);
			}
		}
	}

	private BufferedImage extractTileFromAtlas(int atlasX, int atlasY) {
		return spriteAtlas.getSubimage(atlasX * TILE_SIZE, atlasY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	}

	@Override
	public void update() {
		// Future game logic
	}

	@Override
	public void render(Artist a) {
		for (Tile tile : tiles) {
			a.drawImage(tile.getImage(), tile.getX(), tile.getY(), TILE_SIZE, TILE_SIZE);
		}
		a.setColor(Color.WHITE);
		a.drawCenteredString("Playing", 400, 300);
		a.setColor(Color.WHITE);
		a.drawCenteredString("Playing", 399, 299);
		a.setColor(Color.BLACK);
		a.drawCenteredString("Playing", 401, 301);
	}

	private void paintTile(int x, int y) {
		int tileX = x / TILE_SIZE;
		int tileY = y / TILE_SIZE;

		if (tileX < 0 || tileX >= MAX_WORLD_COL || tileY < 0 || tileY >= MAX_WORLD_ROW)
			return;

		int index = tileY * MAX_WORLD_COL + tileX;
		Tile clickedTile = tiles.get(index);
		clickedTile.setImage(altTileImage);
	}

	@Override
	public void mousePressed(int x, int y) {
		paintTile(x, y);
	}

	// Unused but required overrides
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
	public void mouseDragged(int x, int y) {
		paintTile(x, y);
	}

	@Override
	public void keyPressed(int keyCode) {
	}

	@Override
	public void keyReleased(int keyCode) {
	}

	@Override
	public void keyTyped(char keyChar) {
	}
}
