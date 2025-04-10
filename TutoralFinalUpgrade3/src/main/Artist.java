package main;

import static helpz.Constants.TILE_SIZE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.AlphaComposite;

public class Artist {
	// The purpose of this method is to replicate the functionality of
	// Graphics and Graphics2D so that if I need to change graphic
	// output in the future, it can all be changed here.

	private BufferedImage offscreenImage;
	private Graphics2D g2d;

	private static BufferedImage spriteAtlas;

	public Artist(Dimension screenSize) {
		offscreenImage = new BufferedImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_ARGB);
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

	public void drawImage(BufferedImage bufferedImage, int x, int y) {
		g2d.drawImage(bufferedImage, x, y, null);
	}

	public void drawImage(BufferedImage bufferedImage, int x, int y, int width, int height) {
		g2d.drawImage(bufferedImage, x, y, width, height, null);
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
			System.err.println("SpriteAtlas loaded. [Artist]");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to load sprite atlas: /res/" + path);
		}
	}

	public static BufferedImage getSpriteAtlas() {
//		if (spriteAtlas == null) {
//			loadSpriteAtlas("spriteatlas.png");
//		}
		if (spriteAtlas == null) {
			loadSpriteAtlas("tileset_cydonia.png");
		}
		return spriteAtlas;
	}
}
