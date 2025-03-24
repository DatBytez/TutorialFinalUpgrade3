package gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {

	private BufferedImage sheet;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	public void loadSprites() {//TODO: This should have sheet size paramaters and tile size parameters
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				sprites.add(sheet.getSubimage(x*32, y*32, 32, 32));
			}
		}
	}
}