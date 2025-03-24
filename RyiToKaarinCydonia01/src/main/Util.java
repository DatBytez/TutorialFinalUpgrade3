package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import scenes.Playing;

public class Util {

	public BufferedImage scaleImage(BufferedImage original, int width, int height) {

//		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		BufferedImage scaledImage = new BufferedImage(width, height, 2);
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}

	public BufferedImage scaleImage(BufferedImage original, Playing playing) {

		BufferedImage scaledImage = new BufferedImage(playing.tileSize, playing.tileSize, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, playing.tileSize, playing.tileSize, null);
		g2.dispose();
		
		return scaledImage;
	}

//	public BufferedImage scaleImage(BufferedImage original, GamePanel gamePanel, int width, int height) {
//		BufferedImage scaledImage = new BufferedImage(gamePanel.tileSize, gamePanel.tileSize, original.getType());
//		Graphics2D g2 = scaledImage.createGraphics();
//		g2.drawImage(original, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
//		g2.dispose();
//		
//		return scaledImage;
//	}

}
