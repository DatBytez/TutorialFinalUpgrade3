package monster;

import java.util.Random;

import entity.Entity;
import entity.EntityType;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import scenes.Playing;

public class MON_Orc extends Entity {

	public MON_Orc(Playing playing) {
		super(playing);

		type = EntityType.monster;
		name = "Orc";
		defaultSpeed = 1;
		speed = defaultSpeed;
		focus = 20;
		maxLife = 20;
		life = maxLife;
		attack = 1;
		defense = 2;
		exp = 10;
		knockBackPower = 5;

		hitbox = setHitbox(4, 4, 40, 44);

		attackHitbox.width = 48;
		attackHitbox.height = 48;
		motion1_duration = 40;
		motion2_duration = 85;

		getImage();
		getAttackImage();
	}

	public void getImage() {

		texture = ImageLoader.loadImage("/textures/nutmeg_sheet");
		sheet = new SpriteSheet(texture);

		for (int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * playing.tileSize, 8 * playing.tileSize, playing.tileSize, playing.tileSize));
			left.add(
					sheet.crop(i * playing.tileSize, 9 * playing.tileSize, playing.tileSize, playing.tileSize));
			down.add(sheet.crop(i * playing.tileSize, 10 * playing.tileSize, playing.tileSize,
					playing.tileSize));
			right.add(sheet.crop(i * playing.tileSize, 11 * playing.tileSize, playing.tileSize,
					playing.tileSize));
		}
	}

	public void getAttackImage() { // Get similar code from Player getPlayerAttackImage
		clearAttackImage();

		texture = ImageLoader.loadImage("/textures/nutmeg_attack");
		sheet = new SpriteSheet(texture);
		int size = playing.tileSize * 3;

		for (int i = 0; i < 6; i++) {
			attackUp.add(sheet.crop(i * size, 0 * size, size, size));
			attackLeft.add(sheet.crop(i * size, 1 * size, size, size));
			attackDown.add(sheet.crop(i * size, 2 * size, size, size));
			attackRight.add(sheet.crop(i * size, 3 * size, size, size));
		}
	}
	
	public void clearAttackImage() {
		attackUp.clear();
		attackLeft.clear();
		attackDown.clear();
		attackRight.clear();
	}
	
	

	public void setAction() {

		if (onPath) {
			// Check if enemy stops chasing
			checkStopChasingOrNot(playing.player, 15, 100);

			// Search the direction to go
			searchPath(getGoalCol(playing.player), getGoalRow(playing.player));

		} else {
			// Check if enemy starts chasing
			checkStartChasingOrNot(playing.player, 5, 50);

			// Get a random direction
			moveRandom();
		}

		// Check if enemy attacks
		if (!attacking) {
			checkAttackOrNot(30, playing.tileSize * 4, playing.tileSize);
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
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_Coin_Bronze(playing));
		}
		if (i >= 50 && i < 75) {
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_Heart(playing));

		}
		if (i >= 75 && i < 100) {
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_Coin_Bronze(playing));
			dropItem(new OBJ_ManaCrystal(playing));

		}
	}
}
