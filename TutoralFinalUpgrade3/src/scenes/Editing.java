//package scenes;
//
//import static helpz.Constants.SCREEN_HEIGHT;
//import static helpz.Constants.SCREEN_WIDTH;
//import static helpz.Constants.TILE_SIZE;
//
//import java.awt.AlphaComposite;
//import java.awt.Graphics2D;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//
//public class Editing extends GameScene implements SceneMethods {
//
//	public int obvScreenX;
//	public int obvScreenY;
//	public int speed, xSpeed, ySpeed;
//	public int obvWorldX, obvWorldY;
//	public int defaultSpeed;
//
//	private int currentLayer = 0;
//	private int maxLayer = 5;
//	private int[][] layer;
//	public String levelName = "cydonia";
//	private Level level;
//	private Tile selectedTile;
//	private int mouseX, mouseY;
//	private int lastTileX, lastTileY, lastTileId;
//	private boolean drawSelect;
//	private ToolBar toolBar;
//
//
//	public TileManager tileManager = new TileManager();
//
//	public Editing(Game game) {
//		super(game);
//		loadDefaultLevel();
//		toolBar = new ToolBar(0, 640, SCREEN_WIDTH, 200, this);
//
//		obvScreenX = SCREEN_WIDTH / 2;
//		obvScreenY = SCREEN_HEIGHT / 2;
//
//		obvWorldX = TILE_SIZE * 24;
//		obvWorldY = TILE_SIZE * 24;
//		defaultSpeed = 4;
//		speed = defaultSpeed;
//	}
//
//	private void loadDefaultLevel() {
//		ArrayList<String> fileNames = new ArrayList<>();
//		
//		int i = 0;
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;//TODO Should be a for loop
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;
//		fileNames.add("/maps/"+levelName+"0"+i+".txt"); i++;
//		
//		this.level = new Level(levelName, fileNames, tileManager);
//		layer = level.getLayers().get(currentLayer).getLayer();
//	}
//
//	@Override
//	public void render(Graphics2D g2) {
//
//		drawLevel(g2);
//		toolBar.draw(g2);
//		drawSelectedTile(g2);
//	}
//	
//	public void changeAlpha(Graphics2D g2, float alphaValue) {
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
//	}
//	
//	public void drawLevel(Graphics2D g2) {
//		level.getLayers().forEach((n) -> {
//			if(n != level.getLayers().get(currentLayer)) {
//				changeAlpha(g2, 0.5f);
//			}
//			n.draw(g2, obvWorldX, obvWorldY, obvScreenX, obvScreenY);
//			changeAlpha(g2, 1f);
//		}); 
//	}		
//
//	private void drawSelectedTile(Graphics2D g2) {
//		if (selectedTile != null && drawSelect) {
//			g2.drawImage(selectedTile.getImage(), mouseX, mouseY, TILE_SIZE, TILE_SIZE, null);
//		}
//	}
//
//	public void saveLevel() {
//		LoadSave.SaveLevel(levelName+"0"+currentLayer, layer);
//		game.getPlaying().setLevel(layer);
//	}
//	
//	public void changeLayer() {
//		if(currentLayer<maxLayer) {
//			currentLayer++;
//		} else {
//			currentLayer = 0;
//		}
//		layer = level.getLayers().get(currentLayer).getLayer();
//		toolBar.getbLayer().setText("Layer "+currentLayer);
//	}
//
//	public void setSelectedTile(Tile tile) {
//		this.selectedTile = tile;
//		drawSelect = true;
//	}
//
//	private void changeTile(int x, int y) {
//		if (selectedTile != null) {
//			int tileX = x / 32;
//			int tileY = y / 32;
//
//			if (lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) 
//				// this is to stop from redrawing tiles when you are clicking in one that's already changed
//				return;
//
//			lastTileX = tileX;
//			lastTileY = tileY;
//			lastTileId = selectedTile.getId();
//
//			layer[tileY][tileX] = selectedTile.getId();
//		}
//	}
//
//	@Override
//	public void mouseClicked(int x, int y) {
//		if (y >= toolBar.getY()) {
//			toolBar.mouseClicked(x, y);
//		} else {
//			changeTile(obvWorldY-obvScreenY+mouseY, obvWorldX-obvScreenX+mouseX);
//		}
//	}
//
//	@Override
//	public void mouseMoved(int x, int y) {
//		if (y >= toolBar.getY()) {
//			toolBar.mouseMoved(x, y);
//			drawSelect = false;
//		} else {
//			drawSelect = true;
//			mouseX = (x / TILE_SIZE) * TILE_SIZE;
//			mouseY = (y / TILE_SIZE) * TILE_SIZE;
//		}
//	}
//
//	@Override
//	public void mousePressed(int x, int y) {
//		if (y >= toolBar.getY()) {
//			toolBar.mousePressed(x, y);
//		} 
//	}
//
//	@Override
//	public void mouseReleased(int x, int y) {
//		toolBar.mouseReleased(x, y);
//	}
//
//	@Override
//	public void mouseDragged(int x, int y) {
//		if (y >= toolBar.getY()) {
//
//		} else {
//			changeTile(obvWorldY-obvScreenY+mouseY, obvWorldX-obvScreenX+mouseX);
//		}
//		
//		if (y >= toolBar.getY()) {
//			toolBar.mouseMoved(x, y);
//			drawSelect = false;
//		} else {
//			drawSelect = true;
//			mouseX = (x / TILE_SIZE) * TILE_SIZE;
//			mouseY = (y / TILE_SIZE) * TILE_SIZE;
//		}
//	}
//
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_R) {
//			toolBar.rotateSprite();
//		}
//
//		xSpeed = 0;
//		ySpeed = 0;
//
//		if (e.getKeyCode() == KeyEvent.VK_W) {
//			obvWorldY-=TILE_SIZE;
////			ySpeed = -speed;
//		}
//		if (e.getKeyCode() == KeyEvent.VK_S) {
//			obvWorldY+=TILE_SIZE;
////			ySpeed = speed;
//		}
//		if (e.getKeyCode() == KeyEvent.VK_A) {
//			obvWorldX-=TILE_SIZE;
////			xSpeed = -speed;
//		}
//		if (e.getKeyCode() == KeyEvent.VK_D) {
//			obvWorldX +=TILE_SIZE;
////			xSpeed = speed;
//		}
//	}
//}
