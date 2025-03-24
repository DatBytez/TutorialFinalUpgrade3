package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel {

	private Game game;
	private Dimension size;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;
	
	// SCREEN SETTINGS
	final static int originalTileSize = 64;
	final static int scale = 1;

	public final static int tileSize = originalTileSize * scale;
	public final static int maxScreenCol = 20;
	public final static int maxScreenRow = 12;
	public final static int SCREEN_WIDTH = tileSize * maxScreenCol;
	public final static int SCREEN_HEIGHT = tileSize * maxScreenRow;
	public static int SCREEN_WIDTH_FULL = SCREEN_WIDTH;
	public static int SCREEN_HEIGHT_FULL = SCREEN_HEIGHT;

	public GameScreen(Game game) {
		this.game = game;
		setPanelSize();
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(game);
		keyboardListener = new KeyboardListener(game);

		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	private void setPanelSize() {
		size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

		setMinimumSize(size);
		setPreferredSize(size);
		setBackground(Color.black);
		setMaximumSize(size);

		this.setDoubleBuffered(true);
		this.setFocusable(true); // TODO: This may be unnecessary
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		game.getRender().render(g2);
	}

	public void setFullScreen() {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

		graphicsDevice.setFullScreenWindow(game);

		int SCREEN_WIDTH_FULL = game.getWidth();
		int SCREEN_HEIGHT_FULL = game.getHeight();

	}
	
	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}
}