package objects;

import static helpz.Constants.TILE_SIZE;

import java.awt.image.BufferedImage;

import main.Artist;

public class Tile2 {

	private int x, y;
	private BufferedImage[] sprite;
	private int id, tileType;

	public Tile2(BufferedImage sprite, int id, int tileType) {
		this.sprite = new BufferedImage[1];
		this.sprite[0] = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public Tile2(BufferedImage[] sprite, int id, int tileType) {
		this.sprite = sprite;
		this.id = id;
		this.tileType = tileType;
	}

	public void draw(Artist a) {
		if (sprite.equals(null))
			a.drawImage(getSprite(id), x * TILE_SIZE, y * TILE_SIZE);

	}

	public int getTileType() {
		return tileType;
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
