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
import java.awt.image.BufferedImage;

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

    // Artist draws to our temporary offscreen image.
    private Artist artist;
    private boolean fullscreen = false;  // Set to true to test fullscreen.

    // tempScreen is the offscreen image (at a fixed “native” resolution).
    private BufferedImage tempScreen;
    private int nativeWidth = SCREEN_WIDTH;
    private int nativeHeight = SCREEN_HEIGHT;
    
    // These fields will hold the current (physical) window dimensions.
    int screenWidth2 = SCREEN_WIDTH;
    int screenHeight2 = SCREEN_HEIGHT;
    
    // Singleton instance reference.
    public static Main instance;

    public Main() {
        instance = this;  // Save the instance for static access.

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
            // Set canvas preferred size to the full screen window's size.
            canvas.setPreferredSize(getSize());
            screenWidth2 = this.getWidth();
            screenHeight2 = this.getHeight();
        } else {
            setResizable(false);
            canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
            add(canvas);
            pack();
            setLocationRelativeTo(null);
        }

        // Create the temporary screen (render target) at native resolution.
        tempScreen = new BufferedImage(nativeWidth, nativeHeight, BufferedImage.TYPE_INT_ARGB);
        // Create an Artist that draws into tempScreen.
        artist = new Artist(new Dimension(nativeWidth, nativeHeight));

        setVisible(true);
        canvas.requestFocusInWindow();

        SceneManager.changeScene(SceneManager.SceneType.MENU);
        initInputs();
    }

    // Set up input listeners.
    private void initInputs() {
        MyMouseListener mouseListener = new MyMouseListener();
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);
        
        KeyboardListener keyboardListener = new KeyboardListener();
        canvas.addKeyListener(keyboardListener);
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

            while (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                drawToTempScreen();
                drawToScreen();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                frames = 0;
                updates = 0;
                lastCheck = System.currentTimeMillis();
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Renders the current scene onto the temporary screen.
    public void drawToTempScreen() {
        artist.drawBackground();
        SceneManager.render(artist);
    }
    
    // Draws (stretches) the temporary screen to the actual window.
    public void drawToScreen() {
        if (canvas.getBufferStrategy() == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        BufferStrategy bs = canvas.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        
        // Use the Artist's renderScaled method to scale the offscreen image to the window dimensions.
        artist.renderScaled(g, screenWidth2, screenHeight2);
        
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
    
    // Static utility methods for converting physical mouse coordinates to virtual coordinates.
    public static int getVirtualX(int physicalX) {
        return physicalX * SCREEN_WIDTH / instance.screenWidth2;
    }

    public static int getVirtualY(int physicalY) {
        return physicalY * SCREEN_HEIGHT / instance.screenHeight2;
    }
}
