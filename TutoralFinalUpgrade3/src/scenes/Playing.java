//package scenes;
//
//import static helpz.Constants.DEFAULT_LEVEL;
//import static helpz.Constants.Tiles.GRASS_TILE;
//import static helpz.Constants.*;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.event.KeyEvent;
//import java.util.ArrayList;
//
//import enemies.Enemy;
//import helpz.LoadSave;
//import main.Artist;
//import main.Game;
//import managers.EnemyManager;
//import managers.ProjectileManager;
//import managers.TowerManager;
//import managers.WaveManager;
//import objects.PathPoint;
//import objects.Tower;
//import ui.ActionBar;
//
//public class Playing extends GameScene implements SceneTemplate {
//
//	private int[][] lvl;
//
//	private ActionBar actionBar;
//	private int mouseX, mouseY;
//	private EnemyManager enemyManager;
//	private TowerManager towerManager;
//	private ProjectileManager projManager;
//	private WaveManager waveManager;
//	private PathPoint start, end;
//	private Tower selectedTower;
//	private int goldTick;
//	private boolean gamePaused;
//
//	public Playing(Game game) {
//		super(game);
//		loadDefaultLevel();
//
//		actionBar = new ActionBar(0, 640, 640, 160, this);
//		enemyManager = new EnemyManager(this, start, end);
//		towerManager = new TowerManager(this);
//		projManager = new ProjectileManager(this);
//		waveManager = new WaveManager(this);
//	}
//
//	private void loadDefaultLevel() {
//		lvl = LoadSave.GetLevelData(DEFAULT_LEVEL);
//		ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints(DEFAULT_LEVEL);
//		start = points.get(0);
//		end = points.get(1);
//	}
//
//	public void setLevel(int[][] lvl) {
//		this.lvl = lvl;
//	}
//
//	public void update() {
//		if (!gamePaused) {
//			updateTick();
//			waveManager.update();
//
//			// Gold tick
//			goldTick++;
//			if (goldTick % (60 * 3) == 0)
//				actionBar.addGold(1);
//
//			if (isAllEnemiesDead()) {
//				if (isThereMoreWaves()) {
//					waveManager.startWaveTimer();
//					if (isWaveTimerOver()) {
//						waveManager.increaseWaveIndex();
//						enemyManager.getEnemies().clear();
//						waveManager.resetEnemyIndex();
//
//					}
//				}
//			}
//
//			if (isTimeForNewEnemy()) {
//				if (!waveManager.isWaveTimerOver())
//					spawnEnemy();
//			}
//
//			enemyManager.update();
//			towerManager.update();
//			projManager.update();
//		}
//
//	}
//
//	private boolean isWaveTimerOver() {
//
//		return waveManager.isWaveTimerOver();
//	}
//
//	private boolean isThereMoreWaves() {
//		return waveManager.isThereMoreWaves();
//
//	}
//
//	private boolean isAllEnemiesDead() {
//
//		if (waveManager.isThereMoreEnemiesInWave())
//			return false;
//
//		for (Enemy e : enemyManager.getEnemies())
//			if (e.isAlive())
//				return false;
//
//		return true;
//	}
//
//	private void spawnEnemy() {
//		enemyManager.spawnEnemy(waveManager.getNextEnemy());
//	}
//
//	private boolean isTimeForNewEnemy() {
//		if (waveManager.isTimeForNewEnemy()) {
//			if (waveManager.isThereMoreEnemiesInWave())
//				return true;
//		}
//
//		return false;
//	}
//
//	public void setSelectedTower(Tower selectedTower) {
//		this.selectedTower = selectedTower;
//	}
//
//	@Override
//	public void render(Artist artist) {
//
//		drawLevel(artist);
////		actionBar.draw(artist);
////		enemyManager.draw(artist);
////		towerManager.draw(artist);
////		projManager.draw(artist);
//
//		drawSelectedTower(artist);
//		drawHighlight(artist);
//
//	}
//
//	private void drawHighlight(Artist artist) {
//		artist.setColor(Color.WHITE);
//		artist.drawRect(mouseX, mouseY, TILE_SIZE, TILE_SIZE);
//
//	}
//
//	private void drawSelectedTower(Artist artist) {
//		if (selectedTower != null)
//			artist.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY);
//	}
//
//	private void drawLevel(Artist artist) {
//
//		for (int y = 0; y < lvl.length; y++) {
//			for (int x = 0; x < lvl[y].length; x++) {
//				int id = lvl[y][x];
//				if (isAnimation(id)) {
//					artist.drawImage(getSprite(id, animationIndex), x * TILE_SIZE, y * TILE_SIZE);
//				} else
//					artist.drawImage(getSprite(id), x * TILE_SIZE, y * TILE_SIZE);
//			}
//		}
//	}
//
//	public int getTileType(int x, int y) {
//		int xCord = x / TILE_SIZE;
//		int yCord = y / TILE_SIZE;
//
//		if (xCord < 0 || xCord > 19)
//			return 0;
//		if (yCord < 0 || yCord > 19)
//			return 0;
//
//		int id = lvl[y / TILE_SIZE][x / TILE_SIZE];
//		return game.getTileManager().getTile(id).getTileType();
//	}
//
//	@Override
//	public void mouseClicked(int x, int y) {
//		// Below 640y
//		if (y >= 640)
//			actionBar.mouseClicked(x, y);
//		else {
//			// Above 640y
//			if (selectedTower != null) {
//				// Trying to place a tower
//				if (isTileGrass(mouseX, mouseY)) {
//					if (getTowerAt(mouseX, mouseY) == null) {
//						towerManager.addTower(selectedTower, mouseX, mouseY);
//
//						removeGold(selectedTower.getTowerType());
//
//						selectedTower = null;
//
//					}
//				}
//			} else {
//				// Not trying to place a tower
//				// Checking if a tower exists at x,y
//				Tower t = getTowerAt(mouseX, mouseY);
//				actionBar.displayTower(t);
//			}
//		}
//	}
//
//	private void removeGold(int towerType) {
//		actionBar.payForTower(towerType);
//
//	}
//
//	public void upgradeTower(Tower displayedTower) {
//		towerManager.upgradeTower(displayedTower);
//
//	}
//
//	public void removeTower(Tower displayedTower) {
//		towerManager.removeTower(displayedTower);
//	}
//
//	private Tower getTowerAt(int x, int y) {
//		return towerManager.getTowerAt(x, y);
//	}
//
//	private boolean isTileGrass(int x, int y) {
//		int id = lvl[y / TILE_SIZE][x / TILE_SIZE];
//		int tileType = game.getTileManager().getTile(id).getTileType();
//		return tileType == GRASS_TILE;
//	}
//
//	public void shootEnemy(Tower t, Enemy e) {
//		projManager.newProjectile(t, e);
//
//	}
//
//	public void setGamePaused(boolean gamePaused) {
//		this.gamePaused = gamePaused;
//	}
//
//	public void keyPressed(KeyEvent e) {
//		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//			selectedTower = null;
//		}
//	}
//
//	@Override
//	public void mouseMoved(int x, int y) {
//		if (y >= 640)
//			actionBar.mouseMoved(x, y);
//		else {
//			mouseX = (x / TILE_SIZE) * TILE_SIZE;
//			mouseY = (y / TILE_SIZE) * TILE_SIZE;
//		}
//	}
//
//	@Override
//	public void mousePressed(int x, int y) {
//		if (y >= 640)
//			actionBar.mousePressed(x, y);
//
//	}
//
//	@Override
//	public void mouseReleased(int x, int y) {
//		actionBar.mouseReleased(x, y);
//	}
//
//	@Override
//	public void mouseDragged(int x, int y) {
//
//	}
//
//	public void rewardPlayer(int enemyType) {
//		actionBar.addGold(helpz.Constants.Enemies.GetReward(enemyType));
//	}
//
//	public TowerManager getTowerManager() {
//		return towerManager;
//	}
//
//	public EnemyManager getEnemyManger() {
//		return enemyManager;
//	}
//
//	public WaveManager getWaveManager() {
//		return waveManager;
//	}
//
//	public boolean isGamePaused() {
//		return gamePaused;
//	}
//
//	public void removeOneLife() {
//		actionBar.removeOneLife();
//	}
//
//	public void resetEverything() {
//
//		actionBar.resetEverything();
//
//		// managers
//		enemyManager.reset();
//		towerManager.reset();
//		projManager.reset();
//		waveManager.reset();
//
//		mouseX = 0;
//		mouseY = 0;
//
//		selectedTower = null;
//		goldTick = 0;
//		gamePaused = false;
//
//	}
//
//}