package main;

import static helpz.Constants.SCREEN_HEIGHT;
import static helpz.Constants.SCREEN_WIDTH;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import helpz.Debug;
import inputs.KeyboardListener;
import inputs.MyMouseListener;
import managers.SceneManager;

public class Main extends JFrame implements Runnable {

	private Canvas canvas;
	private Thread gameThread;
	private boolean running;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private Artist artist;
	private boolean fullscreen = false;

	public Main() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Game");

		canvas = new Canvas();
		canvas.setFocusable(true);

		if (fullscreen) {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gd = ge.getDefaultScreenDevice();

			setUndecorated(true);
			add(canvas);
			gd.setFullScreenWindow(this);
			canvas.setPreferredSize(getSize());
		} else {
			setResizable(false);
			canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
			add(canvas);
			pack();
			setLocationRelativeTo(null);
		}

		initArtist();

		setVisible(true);
		canvas.requestFocusInWindow();

		SceneManager.changeScene(SceneManager.SceneType.MENU);
		initInputs();
	}

	private void initInputs() {
		MyMouseListener mouseListener = new MyMouseListener(); // Create mouse listener instance
		canvas.addMouseListener(mouseListener); // Add mouse listener to canvas
		canvas.addMouseMotionListener(mouseListener); // Add mouse motion listener

		KeyboardListener keyboardListener = new KeyboardListener();
		canvas.addKeyListener(keyboardListener); // Add key listener to canvas
	}

	private void initArtist() {
		Dimension screenSize = fullscreen ? Toolkit.getDefaultToolkit().getScreenSize()
				: new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

		artist = new Artist(screenSize);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	private void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		final double timePerFrame = 1_000_000_000.0 / FPS_SET;
		final double timePerUpdate = 1_000_000_000.0 / UPS_SET;

		long previousTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		int frames = 0;
		int updates = 0;

		running = true;

		while (running) {
			long currentTime = System.nanoTime();
			double elapsedTime = currentTime - previousTime;
			previousTime = currentTime;

			deltaU += elapsedTime / timePerUpdate;
			deltaF += elapsedTime / timePerFrame;

			// Update logic
			while (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			// Render logic
			if (deltaF >= 1) {
				render();
				frames++;
				deltaF--;
			}

			// Print FPS/UPS every second
			if (System.currentTimeMillis() - lastCheck >= 1000) {
//				Debug.msg("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastCheck = System.currentTimeMillis();
			}

			try {
				Thread.sleep(1); // Let CPU breathe, avoids 100% usage
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void render() {
		if (canvas.getBufferStrategy() == null) {
			canvas.createBufferStrategy(3); // This should happen once during initialization.
			return;
		}

		BufferStrategy bs = canvas.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		artist.drawBackground();
		SceneManager.render(artist);
		artist.render(g);

		g.dispose();
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}

	private void update() {
		SceneManager.update();
	}

	public void stop() {
		running = false;
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);

			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
