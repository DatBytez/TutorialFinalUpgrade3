package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import enviroment.EnvironmentManager;
import inputs.KeyboardListener;
import main.AssetSetter;
import main.CollisionChecker;
import main.Config;
import main.EntityGenerator;
import main.EventHandler;
import main.Game;
import main.MenuSelection;
import main.OptionsSelection;
import main.Sound;
import main.UI;
import managers.QuestManager;
import tile.Map;
import tile.TileManagerRy;

public class Playing extends GameScene implements SceneMethods {
	// ADDED BY KAARIN?
	boolean gamePaused = false;

	// Probably needs to be moved down a layer to Game?
	public GameState gameState = GameState.TITLE;

	// SCREEN SETTINGS
	final int originalTileSize = 64;
	final int scale = 1;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int maxMap = 10;
	public int currentMap = 0; // change to 0 for main map

	// FOR FULL SCREEN
	int screenWidth2 = screenWidth;
	int screenHeight2 = screenHeight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;

	// FPS
	int FPS = 60;
	public int drawCount = 0;

	// SYSTEM
	public boolean debugMode;
	public TileManagerRy tileM = new TileManagerRy(this);
	public QuestManager questManager = new QuestManager();
	public KeyboardListener keyboardListener;
	public Sound music = new Sound();
	public Sound soundEffect = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eventHandler = new EventHandler(this);
	public Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	public EnvironmentManager eManager = new EnvironmentManager(this);
	Map map = new Map(this);
	public SaveLoad saveLoad = new SaveLoad(this);
	public EntityGenerator eGenerator = new EntityGenerator(this);
	Thread gameThread;

	// ENTITY AND OBJECTS
	public Player player;
	public ArrayList<Entity> objectList = new ArrayList<>();
	public ArrayList<Entity> npcList = new ArrayList<>();
	public ArrayList<Entity> monsterList = new ArrayList<>();
	public ArrayList<Entity> iTile = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	public ArrayList<Entity> entityList = new ArrayList<>();

	public ArrayList<ArrayList> objectListI = new ArrayList<>();
	public ArrayList<ArrayList> npcListI = new ArrayList<>();
	public ArrayList<ArrayList> monsterListI = new ArrayList<>();
	public ArrayList<ArrayList> iTileI = new ArrayList<>();

	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, shotKeyPressed,
			guardKeyPressed;

	public Playing(Game game) {
		super(game);

		this.player = new Player(this);
		setupGame();
	}

	public void setupGame() {
		while (objectListI.size() <= maxMap)
			objectListI.add(new ArrayList<>());
		while (npcListI.size() <= maxMap)
			npcListI.add(new ArrayList<>());
		while (monsterListI.size() <= maxMap)
			monsterListI.add(new ArrayList<>());
		while (iTileI.size() <= maxMap)
			iTileI.add(new ArrayList<>());

		eManager.setup();
		objectListI.set(currentMap, objectList);
		npcListI.set(currentMap, npcList);
		monsterListI.set(currentMap, monsterList);
		iTileI.set(currentMap, iTile);

		assetSetter.setObject();
		assetSetter.setNPC();
		assetSetter.setMonster();
		assetSetter.setInteractiveTile();

		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); // TODO: Should be
																								// linked to GameScreen
//		g2 = (Graphics2D) tempScreen.getGraphics();

		if (fullScreenOn) {
			game.getGameScreen().setFullScreen(); // TODO: Needs to be linked to GameScreen before this will work
		}
	}

	public void resetGame(boolean restart) {
		player.setDefaultPositions();
		player.restoreStatus();
		player.resetCounter();
		assetSetter.setNPC();
		assetSetter.setMonster();

		if (restart) {
			player.setDefaultValues();
			assetSetter.setObject();
			assetSetter.setInteractiveTile();
			eManager.lighting.resetDay();
		}
	}

////	public void setFullScreen() {
//	
//	/* Moved to GameScreen
//	 * screenWidth/screenHeight changed to SCREEN_WIDTH/SCREEN_HEIGHT
//	 * screenWidth2/screenHeight2 changed to SCREEN_WIDTH_FULL/SCREEN_HEIGHT_FULL
//	 */
//	
////		// GET LOCAL SCREEN DEVICE
////		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
////		GraphicsDevice gd = ge.getDefaultScreenDevice();
////		gd.setFullScreenWindow(Main.window);
////
////		// GET FULL SCREEN WIDTH AND HEIGHT
////		screenWidth2 = Main.window.getWidth();
////		screenHeight2 = Main.window.getHeight();
////	}
//	
////	public void startGameThread() {
////		gameThread = new Thread(this);
////		gameThread.start();
////	}

	public void switchArrays() {
		objectList = objectListI.get(currentMap);

		npcList = npcListI.get(currentMap);

		monsterList = monsterListI.get(currentMap);

		iTile = iTileI.get(currentMap);
	}

	public void update() {
		if (gameState == GameState.PLAY) {
			// PLAYER
			player.update();

			// NPC
			npcList.forEach((n) -> n.update());

			// MONSTER
			for (int i = 0; i < monsterList.size(); i++) {
				if (monsterList.get(i) != null) {
					if (monsterList.get(i).alive && !monsterList.get(i).dying) {
						monsterList.get(i).update();
					} else if (!monsterList.get(i).alive) {
						monsterList.get(i).checkDrop();
						monsterList.remove(i);
					}
				}
			}

			// PROJECTILE
			for (int i = 0; i < projectileList.size(); i++) {
				if (projectileList.get(i) != null) {
					if (projectileList.get(i).alive) {
						projectileList.get(i).update();
					} else if (!projectileList.get(i).alive) {
						projectileList.remove(i);
					}
				}
			}

			// PARTICLE
			for (int i = 0; i < particleList.size(); i++) {
				if (particleList.get(i) != null) {
					if (particleList.get(i).alive) {
						particleList.get(i).update();
					} else if (!particleList.get(i).alive) {
						particleList.remove(i);
					}
				}
			}

			iTile.forEach((n) -> n.update());
		}
		eManager.update();
		if (gameState == GameState.PAUSE) {
			// Nothing
		}
	}

	@Override
	public void render(Graphics2D g2) {

		drawToTempScreen(g2);
		drawToScreen(g2);
	}

	public void drawToTempScreen(Graphics2D g2) {
		// TITLE SCREEN
		if (gameState == GameState.TITLE) {
			ui.draw(g2);
		} else if (gameState == GameState.MAP) {
			map.drawFullMapScreen(g2);
		}
		// OTHERS
		else {

			tileM.draw(g2);

			iTile.forEach((n) -> n.draw(g2));

			objectList.forEach((n) -> entityList.add(n));

			entityList.add(player);

			npcList.forEach((n) -> entityList.add(n));

			monsterList.forEach((n) -> entityList.add(n));

			projectileList.forEach((n) -> entityList.add(n));

			particleList.forEach((n) -> entityList.add(n));

			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});

			// DRAW ENTITIES
			entityList.forEach((n) -> n.draw(g2));
			entityList.clear();

			// ENVIRONMENT
			eManager.draw(g2);

			// MINI MAP
			map.drawMiniMap(g2);

			// UI
			ui.draw(g2);
			// DEBUG

			if (debugMode) {
				g2.setFont(new Font("Arial", Font.PLAIN, 20));
				g2.setColor(Color.white);
				int x = tileSize * 14;
				int y = tileSize * 11;
				int lineHeight = 20;

				g2.drawString("WorldX" + player.worldX, x, y);
				y += lineHeight;
				g2.drawString("WorldY" + player.worldY, x, y);
				y += lineHeight;
				g2.drawString("Col" + (player.worldX + player.hitbox.x) / tileSize, x, y);
				y += lineHeight;
				g2.drawString("Row" + (player.worldY + player.hitbox.y) / tileSize, x, y);
				y += lineHeight;
			}
		}
	}

	public void drawToScreen(Graphics g2) {
		g2.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
	}

	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	public void stopMusic() {
		music.stop();
	}

	public void playSoundEffect(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}

	public void mouseClicked(int x, int y) {
	}

	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	public void keyPressed(KeyEvent e) {

		// PLAY STATE
		if (gameState == GameState.PLAY) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
				upPressed = true;
			else
				upPressed = false;

			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
				downPressed = true;
			else
				downPressed = false;

			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
				leftPressed = true;
			else
				leftPressed = false;

			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				rightPressed = true;
			else
				rightPressed = false;
		}

		// TITLE STATE
		if (gameState == GameState.TITLE) {
			titleState(e);
		}
		// PLAY STATE
		else if (gameState == GameState.PLAY) {
			playState(e);
		}
		// PAUSE STATE
		else if (gameState == GameState.PAUSE) {
			pauseState(e);
		}
		// DIALOGUE STATE
		else if (gameState == GameState.DIALOGUE) {
			dialogueState(e);
		}
		// CHARACTER STATE
		else if (gameState == GameState.CHARACTER) {
			characterState(e);
		}
		// OPTIONS STATE
		else if (gameState == GameState.OPTIONS) {
			optionsState(e);
		}
		// GAME OVER STATE
		else if (gameState == GameState.GAME_OVER) {
			gameOverState(e);
		}
		// TRADE STATE
		else if (gameState == GameState.TRADE) {
			tradeState(e);
		}
		// MAP STATE
		else if (gameState == GameState.MAP) {
			mapState(e);
		}
		// DEBUG
		if (e.getKeyCode() == KeyEvent.VK_T) {
			if (debugMode)
				debugMode = false;
			else
				debugMode = true;
		}
		// REFRESH MAP
		if (e.getKeyCode() == KeyEvent.VK_R) {
			switch (currentMap) {
			case 0:
				tileM.loadMap("/maps/world01.txt", 0);
				break;
			case 1:
				tileM.loadMap("/maps/interior.txt", 1);
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP)
			upPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER)
			enterPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			guardKeyPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_F)
			shotKeyPressed = false;
	}

	// ALL OF THIS SHOULD PROBABLY BE IN A DIFFERENT CLASS

	public void tradeState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		if (ui.subState == 0) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				ui.commandNum--;
				if (ui.commandNum < 0) {
					ui.commandNum = 2;
				}
				playSoundEffect(9);
			}
			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				ui.commandNum++;
				if (ui.commandNum > 2) {
					ui.commandNum = 0;
				}
				playSoundEffect(9);
			}
		}
		if (ui.subState == 1) {
			npcInventory(e);
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				ui.subState = 0;
			}
		}
		if (ui.subState == 2) {
			playerInventory(e);
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				ui.subState = 0;
			}
		}
	}

	public void mapState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = GameState.PLAY;
		}
	}

	public void playerInventory(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			if (ui.playerSlotRow != 0) {
				ui.playerSlotRow--;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ui.playerSlotCol != 0) {
				ui.playerSlotCol--;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (ui.playerSlotRow != 3) {
				ui.playerSlotRow++;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (ui.playerSlotCol != 4) {
				ui.playerSlotCol++;
				playSoundEffect(9);
			}
		}
	}

	public void npcInventory(KeyEvent e) { // This should be combined with playerInventory into one method that does
											// both.

		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			if (ui.npcSlotRow != 0) {
				ui.npcSlotRow--;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ui.npcSlotCol != 0) {
				ui.npcSlotCol--;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (ui.npcSlotRow != 3) {
				ui.npcSlotRow++;
				playSoundEffect(9);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (ui.npcSlotCol != 4) {
				ui.npcSlotCol++;
				playSoundEffect(9);
			}
		}
	}

	public void titleState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			switch (ui.menuSelection) {

			case NEW_GAME:
				ui.menuSelection = MenuSelection.QUIT;
				break;
			case LOAD_GAME:
				ui.menuSelection = MenuSelection.NEW_GAME;
				break;
			case QUIT:
				ui.menuSelection = MenuSelection.LOAD_GAME;
				break;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			switch (ui.menuSelection) {

			case NEW_GAME:
				ui.menuSelection = MenuSelection.LOAD_GAME;
				break;
			case LOAD_GAME:
				ui.menuSelection = MenuSelection.QUIT;
				break;
			case QUIT:
				ui.menuSelection = MenuSelection.NEW_GAME;
				break;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			switch (ui.menuSelection) {

			case NEW_GAME:
				gameState = GameState.PLAY;
//				playMusic(0);
				break;
			case LOAD_GAME:
				saveLoad.load();
				gameState = GameState.PLAY;
				playMusic(0);
				break;
			case QUIT:
				System.exit(0);
				break;
			}
		}
	}

	public void playState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {
			gameState = GameState.PAUSE;
		}
		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_CONTROL) {
			gameState = GameState.CHARACTER;
		}
		if (e.getKeyCode() == KeyEvent.VK_F) {
			shotKeyPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			guardKeyPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = GameState.OPTIONS;
		}
		if (e.getKeyCode() == KeyEvent.VK_M) {
			gameState = GameState.MAP;
		}
		if (e.getKeyCode() == KeyEvent.VK_N) {
			if (map.miniMapOn == false) {
				map.miniMapOn = true;
			} else {
				map.miniMapOn = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_I) {
			if (currentMap == 0)
				currentMap = 1;
			else
				currentMap = 0;
		}
	}

	public void pauseState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {
			gameState = GameState.PLAY;
		}
	}

	public void dialogueState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {
			gameState = GameState.PAUSE;
		}
		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
	}

	public void characterState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_C || e.getKeyCode() == KeyEvent.VK_SPACE) {
			gameState = GameState.PLAY;
			playSoundEffect(9);
		}

		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			player.selectItem();
		}
		playerInventory(e);
	}

	public void optionsState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = GameState.PLAY;
			ui.subState = 0;
		}

		if (e.getKeyCode() == KeyEvent.VK_E || e.getKeyCode() == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			playSoundEffect(9);
			switch (ui.optionsSelection) {

			case FULL_SCREEN:
				ui.optionsSelection = OptionsSelection.BACK;
				break;
			case MUSIC:
				ui.optionsSelection = OptionsSelection.FULL_SCREEN;
				break;
			case SOUND_EFFECTS:
				ui.optionsSelection = OptionsSelection.MUSIC;
				break;
			case CONTROL:
				ui.optionsSelection = OptionsSelection.SOUND_EFFECTS;
				break;
			case END_GAME:
				ui.optionsSelection = OptionsSelection.CONTROL;
				break;
			case BACK:
				if (ui.subState == 3)
					ui.optionsSelection = OptionsSelection.FULL_SCREEN;
				else if (ui.subState == 0)
					ui.optionsSelection = OptionsSelection.END_GAME;
				break;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			playSoundEffect(9);
			switch (ui.optionsSelection) {

			case FULL_SCREEN:
				if (ui.subState == 3)
					ui.optionsSelection = OptionsSelection.BACK;
				else
					ui.optionsSelection = OptionsSelection.MUSIC;
				break;
			case MUSIC:
				ui.optionsSelection = OptionsSelection.SOUND_EFFECTS;
				break;
			case SOUND_EFFECTS:
				ui.optionsSelection = OptionsSelection.CONTROL;
				break;
			case CONTROL:
				ui.optionsSelection = OptionsSelection.END_GAME;
				break;
			case END_GAME:
				ui.optionsSelection = OptionsSelection.BACK;
				break;
			case BACK:
				if (ui.subState == 0 || ui.subState == 3)
					ui.optionsSelection = OptionsSelection.FULL_SCREEN;
				break;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ui.subState == 0) {
				if (ui.optionsSelection == OptionsSelection.MUSIC && music.volumeScale > 0) {
					music.volumeScale--;
					music.checkVolume();
					playSoundEffect(9);
				}
				if (ui.optionsSelection == OptionsSelection.SOUND_EFFECTS && soundEffect.volumeScale > 0) {
					soundEffect.volumeScale--;
					playSoundEffect(9);
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (ui.subState == 0) {
				if (ui.optionsSelection == OptionsSelection.MUSIC && music.volumeScale < 10) {
					music.volumeScale++;
					music.checkVolume();
					playSoundEffect(9);
				}
				if (ui.optionsSelection == OptionsSelection.SOUND_EFFECTS && soundEffect.volumeScale < 10) {
					soundEffect.volumeScale++;
					playSoundEffect(9);
				}
			}
		}
	}

	public void gameOverState(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
			ui.commandNum--;
			if (ui.commandNum < 0) {
				ui.commandNum = 1;
			}
			playSoundEffect(9);
		}

		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
			ui.commandNum++;
			if (ui.commandNum > 1) {
				ui.commandNum = 0;
			}
			playSoundEffect(9);
		}

		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ui.commandNum == 0) {
				gameState = GameState.PLAY;
				resetGame(false);
			} else if (ui.commandNum == 1) {
				gameState = GameState.TITLE;
				resetGame(true);
			}
			playSoundEffect(9);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
	}

	@Override
	public void mousePressed(int x, int y) {
	}

	@Override
	public void mouseReleased(int x, int y) {
	}

	@Override
	public void mouseDragged(int x, int y) {

	}

	public boolean isGamePaused() {
		return gamePaused;
	}

	public void resetEverything() {
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
}