package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class GameScreen extends JPanel {

	private Game game;
	private Dimension size;
	public static int XSIZE = 1800;
	public static int YSIZE = 900;

	private MyMouseListener myMouseListener;
	private KeyboardListener keyboardListener;

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
		size = new Dimension(XSIZE, YSIZE);

		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.getRender().render(g);

	}

}