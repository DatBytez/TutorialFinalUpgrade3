package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import helpz.Debug;

public class Artist {
    // Offscreen image and its graphics context.
    private BufferedImage offscreenImage;
    private Graphics2D g2d;
    
    // Static sprite atlas.
    private static BufferedImage spriteAtlas;
    
    // Constructor that creates a new offscreen image based on the given screen size.
    public Artist(Dimension screenSize) {
        offscreenImage = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_ARGB);
        g2d = offscreenImage.createGraphics();
    }
    
    // Overloaded constructor that uses an existing BufferedImage.
    public Artist(BufferedImage offscreenImage) {
        this.offscreenImage = offscreenImage;
        g2d = offscreenImage.createGraphics();
    }
    
    public void setColor(Color color) {
        g2d.setColor(color);
    }
    
    public void drawRect(int x, int y, int width, int height) {
        g2d.drawRect(x, y, width, height);
    }
    
    public int getStringWidth(String text) {
        return g2d.getFontMetrics().stringWidth(text);
    }
    
    public int getFontHeight() {
        return g2d.getFontMetrics().getHeight();
    }
    
    public void drawCenteredString(String text, int x, int y) {
        int textWidth = getStringWidth(text);
        int textHeight = getFontHeight();
        g2d.drawString(text, x - textWidth / 2, y + textHeight / 2);
    }
    
    public void render(Graphics g) {
        g.drawImage(offscreenImage, 0, 0, null);
    }
    
    public void drawBackground() {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, offscreenImage.getWidth(), offscreenImage.getHeight());
    }
    
    public void fillRect(int x, int y, int width, int height) {
        g2d.fillRect(x, y, width, height);
    }
    
    public void drawString(String text, int x, int y) {
        g2d.drawString(text, x, y);
    }
    
    // Draws the entire image at location (x,y).
    public void drawImage(BufferedImage bufferedImage, int x, int y) {
        g2d.drawImage(bufferedImage, x, y, null);
    }
    
    // Draws the entire image scaled to the given width and height.
    public void drawImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
        g2d.drawImage(bufferedImage, x, y, width, height, null);
    }
    
    // Overloaded drawImage: Draws a portion (source rectangle) of the image into the destination rectangle.
    // dx1,dy1,dx2,dy2 define the destination rectangle; sx1,sy1,sx2,sy2 the source rectangle.
    public void drawImage(BufferedImage image, int dx1, int dy1, int dx2, int dy2,
                          int sx1, int sy1, int sx2, int sy2) {
        g2d.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
    }
    
    public void setFont(Font font) {
        g2d.setFont(font);
    }
    
    public void drawOval(int x, int y, int width, int height) {
        g2d.drawOval(x, y, width, height);
    }
    
    public void setAlphaComposite(float alpha) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
    
    public void resetComposite() {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    
    public static void loadSpriteAtlas(String path) {
        try {
            spriteAtlas = ImageIO.read(Artist.class.getResourceAsStream("/res/" + path));
        } catch (IOException e) {
            e.printStackTrace();
            Debug.msg("Failed to load sprite atlas: /res/" + path);
        }
    }
    
    public static BufferedImage getSpriteAtlas() {
        if (spriteAtlas == null) {
            loadSpriteAtlas("tileset_cydonia.png");
        }
        return spriteAtlas;
    }
}
