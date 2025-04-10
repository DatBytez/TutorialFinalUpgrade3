//package main;
//
//import static helpz.Constants.SCREEN_HEIGHT;
//import static helpz.Constants.SCREEN_WIDTH;
//
//import java.awt.Dimension;
//import java.awt.Graphics;
//
//import javax.swing.JPanel;
//
//import inputs.KeyboardListener;
//import inputs.MyMouseListener;
//
//public class GameScreen extends JPanel {
//
//	private Game game;
//	private Dimension size;
//
//	private MyMouseListener myMouseListener;
//	private KeyboardListener keyboardListener;
//	
//	public int SCREEN_WIDTH_FULL = SCREEN_WIDTH;
//	public int SCREEN_HEIGHT_FULL = SCREEN_HEIGHT;
//
//	public GameScreen(Game game) {
//		this.game = game;
//
//		setPanelSize();
//
//	}
//
//	public void initInputs() {
//		myMouseListener = new MyMouseListener(game);
//		keyboardListener = new KeyboardListener(game);
//
//		addMouseListener(myMouseListener);
//		addMouseMotionListener(myMouseListener);
//		addKeyListener(keyboardListener);
//
//		requestFocus();
//	}
//
//	private void setPanelSize() {
//		size = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
//
//		setMinimumSize(size);
//		setPreferredSize(size);
//		setMaximumSize(size);
//
//	}
//	
////	public void setFullScreen() {
////		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
////		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
////
////		graphicsDevice.setFullScreenWindow(game);
////
////		SCREEN_WIDTH_FULL = game.getWidth();
////		SCREEN_HEIGHT_FULL = game.getHeight();
////
////	}
//
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//
//		game.getRender().render(g);
//
//	}
//
//}