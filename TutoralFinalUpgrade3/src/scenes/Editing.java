//package scenes;
//
//import static helpz.Constants.DEFAULT_LEVEL;
//import static helpz.Constants.SCREEN_HEIGHT;
//import static helpz.Constants.SCREEN_WIDTH;
//import static helpz.Constants.TILE_SIZE;
//import static helpz.Constants.TOOLBAR_HEIGHT;
//import static helpz.Constants.Tiles.ROAD_TILE;
//
//import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//
//import helpz.LoadSave;
//import main.Artist;
//import main.Game;
//import objects.PathPoint;
//import objects.Tile;
//import ui.Toolbar;
//
//
//public class Editing extends GameScene implements SceneTemplate {
//
//	private int[][] lvl;
////	private int[][] level;
//	private Tile selectedTile;
//	private int mouseX, mouseY;
//	private int lastTileX, lastTileY, lastTileId;
//	private boolean drawSelect;
//	private Toolbar toolbar;
//	private PathPoint start, end;
//
//	public Editing(Game game) {
//		super(game);
//		loadDefaultLevel();
//		toolbar = new Toolbar(0, SCREEN_HEIGHT-TOOLBAR_HEIGHT, SCREEN_WIDTH, TOOLBAR_HEIGHT, this);
//	}
//
//	private void loadDefaultLevel() {
//		lvl = LoadSave.GetLevelData(DEFAULT_LEVEL);
//		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints(DEFAULT_LEVEL);
//		start = points.get(0);
//		end = points.get(1);
//	}
//
//	public void update() {
//		updateTick();
//	}
//
//	@Override
//	public void render(Artist a) {
//		drawLevel(a);
//		toolbar.draw(a);
//		drawSelectedTile(a);
//		drawPathPoints(a);
//	}
//
//	private void drawPathPoints(Artist a) {
//		if (start != null)
//			a.drawImage(toolbar.getStartPathImg(), start.getxCord() * TILE_SIZE, start.getyCord() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//
//		if (end != null)
//			a.drawImage(toolbar.getEndPathImg(), end.getxCord() * TILE_SIZE, end.getyCord() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//
//	}
//
//	private void drawLevel(Artist a) {
//		for (int y = 0; y < lvl.length; y++) {
//			for (int x = 0; x < lvl[y].length; x++) {
//				int id = lvl[y][x];
//				if (isAnimation(id)) {
//					a.drawImage(getSprite(id, animationIndex), x * TILE_SIZE, y * TILE_SIZE);
//				} else
//					a.drawImage(getSprite(id), x * TILE_SIZE, y * TILE_SIZE);
//			}
//		}
//	}
//
//	private void drawSelectedTile(Artist a) {
//		if (selectedTile != null && drawSelect) {
//			a.drawImage(selectedTile.getSprite(), mouseX, mouseY, TILE_SIZE, TILE_SIZE);
//		}
//	}
//
//	public void saveLevel() {
//
//		LoadSave.SaveLevel(DEFAULT_LEVEL, lvl, start, end);
//		game.getPlaying().setLevel(lvl); //TODO: This should be specific to the scene that is running
//
//	}
//
//	public void setSelectedTile(Tile tile) {
//		this.selectedTile = tile;
//		drawSelect = true;
//	}
//
//	private void changeTile(int x, int y) {
//		if (selectedTile != null) {
//			int tileX = x / TILE_SIZE;
//			int tileY = y / TILE_SIZE;
//
//			if (selectedTile.getId() >= 0) {
//				if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
//					return;
//
//				lastTileX = tileX;
//				lastTileY = tileY;
//				lastTileId = selectedTile.getId();
//
//				lvl[tileY][tileX] = selectedTile.getId();
//			} else {
//				int id = lvl[tileY][tileX];
//				if (game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
//					if (selectedTile.getId() == -1)
//						start = new PathPoint(tileX, tileY);
//					else
//						end = new PathPoint(tileX, tileY);
//				}
//			}
//		}
//	}
//
//	@Override
//	public void mouseClicked(int x, int y) {
//		if (y >= 640) {
//			toolbar.mouseClicked(x, y);
//		} else {
//			changeTile(mouseX, mouseY);
//		}
//
//	}
//
//	@Override
//	public void mouseMoved(int x, int y) {
//
//		if (y >= 640) {
//			toolbar.mouseMoved(x, y);
//			drawSelect = false;
//		} else {
//			drawSelect = true;
//			mouseX = (x / TILE_SIZE) * TILE_SIZE;
//			mouseY = (y / TILE_SIZE) * TILE_SIZE;
//		}
//
//	}
//
//	@Override
//	public void mousePressed(int x, int y) {
//		if (y >= 640)
//			toolbar.mousePressed(x, y);
//
//	}
//
//	@Override
//	public void mouseReleased(int x, int y) {
//		toolbar.mouseReleased(x, y);
//
//	}
//
//	@Override
//	public void mouseDragged(int x, int y) {
//		if (y >= 640) {
//
//		} else {
//			changeTile(x, y);
//		}
//
//	}
//
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_R)
//			toolbar.rotateSprite();
//	}
//
//}
