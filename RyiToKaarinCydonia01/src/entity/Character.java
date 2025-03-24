package entity;

import static main.Game.TILE_SIZE;

import java.awt.Rectangle;

import scenes.Playing;

public class Character extends Entity{

	public Playing playing;
	public String muggleName;
	public int defaultSpeed = 1; //TODO:Should probably be made into a float

	public Character(Playing playing) {
		super(playing);
		this.playing = playing;
		this.direction = Direction.down;
		this.speed = defaultSpeed;
		setDefaultHitbox();
	}
	
	public Rectangle setDefaultHitbox() {

		hitbox.x = (int) (TILE_SIZE*0.6875);
		hitbox.y = TILE_SIZE;
		hitbox.width = (TILE_SIZE / 3)*2;
		hitbox.height = (int) (TILE_SIZE*0.9375);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;

		return hitbox;
	}
}
