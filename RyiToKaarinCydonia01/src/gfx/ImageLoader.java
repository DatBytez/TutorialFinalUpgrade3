package gfx;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		try {
			BufferedImage image;
			image = ImageIO.read(ImageLoader.class.getResource(path+".png"));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static BufferedImage scaleImage(BufferedImage original) {
		
		int tileSize = 64;

		BufferedImage scaledImage = new BufferedImage(tileSize, tileSize, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, tileSize, tileSize, null);
		g2.dispose();
		
		return scaledImage;
	}
}
