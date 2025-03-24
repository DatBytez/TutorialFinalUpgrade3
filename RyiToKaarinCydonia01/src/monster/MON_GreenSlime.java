package monster;

import java.awt.image.BufferedImage;
import java.util.Random;

import entity.Entity;
import entity.EntityType;
import gfx.ImageLoader;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import scenes.Playing;

public class MON_GreenSlime extends Entity {

	public MON_GreenSlime(Playing playing) {
		super(playing);

		type = EntityType.monster;
		name = "Green Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		focus = 20;
		maxLife = 4;
		life = maxLife;
		attack = 4;
		defense = 0;
		exp = 2;
		knockBackPower = 2;
		projectile = new OBJ_Rock(playing);

		hitbox = setHitbox(0, 0, 32, 32);

		getImage();
	}

	public void getImage() {

		BufferedImage image = ImageLoader.loadImage("/monster/greenslime_down_1");
		BufferedImage image2 = ImageLoader.loadImage("/monster/greenslime_down_2");

		up.add(image);
		up.add(image2);

		down.add(image);
		down.add(image2);

		left.add(image);
		left.add(image2);

		right.add(image);
		right.add(image2);
	}
	
	public void setAction() {

		if (onPath) {
			// Check if enemy stops chasing
			checkStopChasingOrNot(playing.player, 15, 100);

			// Search the direction to go
			searchPath(getGoalCol(playing.player), getGoalRow(playing.player));

			// Check if it shoots a projectile
			checkShootOrNot(100, 30);

		} else {
			// Check if enemy starts chasing
			checkStartChasingOrNot(playing.player, 5, 50);

			// Get a random direction
			moveRandom();
		}

	}

	public void damageReaction() {
		actionLockCounter = 0;
//		direction = gamePanel.player.direction;
		onPath = true;
	}

	public void checkDrop() {

		// ROLL FOR LOOT
		int i = new Random().nextInt(100) + 1;

		// SET THE MONSTER DROP
		if (i < 50) {
			dropItem(new OBJ_Coin_Bronze(playing));
		}
		if (i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(playing));
		}
		if (i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(playing));
		}
	}
}
