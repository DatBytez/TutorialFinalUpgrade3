package tile;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage[] sprite;
	private int id, tileType;
	public BufferedImage image;
	public boolean collision = false;
	
	public Tile() {
	}
	
	public Tile(BufferedImage image, int id, String name, boolean collision) {
		this.image = image;
		this.id = id;
//		this.name = name;
		this.collision = collision;
	}

	public Tile(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}
	
	public Tile(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public int getTileType() {
		return tileType;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public BufferedImage getSprite(int animationIndex) {
		return sprite[animationIndex];
	}

	public BufferedImage getSprite() {
		return sprite[0];
	}

	public boolean isAnimation() {
		return sprite.length > 1;
	}

	public int getId() {
		return id;
	}

}
