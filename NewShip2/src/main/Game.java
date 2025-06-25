package main;

import javax.swing.JFrame;

import scenes.BuildScene;
import scenes.CombatScene;
import scenes.EditorScene;
import scenes.GameOverScene;
import scenes.MenuScene;
import ship.helpz.DescriptionLoader;

public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// Classes
	private Render render;
	private MenuScene menuScene;
	private BuildScene buildScene;
	private CombatScene combatScene;
	private EditorScene editing;
	private GameOverScene gameOver;

	public Game() {
		initClasses();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Alternity - Warships");
		add(gameScreen);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		DescriptionLoader.loadDescriptions();
	}

	private void initClasses() {
		render = new Render(this);
		gameScreen = new GameScreen(this);
		menuScene = new MenuScene(this);
		buildScene = new BuildScene(this);
		combatScene = new CombatScene(this);
		editing = new EditorScene(this);
		gameOver = new GameOverScene(this);
	}

	private void start() {
		gameThread = new Thread(this) {
		};

		gameThread.start();
	}

	private void updateGame() {
		switch (GameStates.gameState) {
		case EDIT_STATE:
			editing.update();
			break;
		case MENU_STATE:
			break;
		case BUILD_STATE:
			buildScene.update();
			break;
		case COMBAT_STATE:
			combatScene.update();
			break;
		default:
			break;
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.gameScreen.initInputs();
		game.start();

	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (true) {
			now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame = now;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate = now;
				updates++;
			}

//			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//				lastTimeCheck = System.currentTimeMillis();
//			}

		}

	}

	// Getters and setters
	public Render getRender() {
		return render;
	}

	public MenuScene getMenuScene() {
		return menuScene;
	}
	
	public BuildScene getBuildScene() {
		return buildScene;
	}

	public CombatScene getCombatScene() {
		return combatScene;
	}

	public EditorScene getEditorScene() {
		return editing;
	}

	public GameOverScene getGameOverScene() {
		return gameOver;
	}
}