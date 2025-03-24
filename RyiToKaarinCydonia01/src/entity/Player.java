package entity;

import static main.Game.TILE_SIZE;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import scenes.GameState;
import scenes.Playing;

import static main.Game.*;

public class Player extends Entity {

	public final int screenX;
	public final int screenY;
	private int attackUpY, attackDownY, attackLeftY, attackRightY;
	private int attackUpX, attackDownX, attackLeftX, attackRightX;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;

	public Player(Playing playing) {
		super(playing);
		this.name = "Felina";

		screenX = SCREEN_WIDTH / 2 - TILE_SIZE / 2;
		screenY = SCREEN_HEIGHT / 2 - TILE_SIZE / 2;

		hitbox = new Rectangle();
		
		hitbox.x = (int) (TILE_SIZE*0.6875);
		hitbox.y = TILE_SIZE;
		hitbox.width = (TILE_SIZE / 3)*2;
		hitbox.height = (int) (TILE_SIZE*0.9375);

		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;

		attackHitbox.width = TILE_SIZE*4;
		attackHitbox.height = TILE_SIZE*4; // 48 is perfect
		
		texture = ImageLoader.loadImage("/textures/javi_sheet");

		setDefaultValues();
	}

	public void setDefaultValues() {
		worldX = playing.tileSize * 49;
		worldY = playing.tileSize * 26;
		defaultSpeed = 4;
		speed = defaultSpeed;

		// PLAYER STATUS
		level = 1;
		maxLife = 10;
		life = maxLife;
		maxMana = 7;
		mana = maxMana;
		ammo = 10; // TODO make it so you can pick this up and throw rocks!
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 200;
		currentWeapon = new OBJ_Sword_Normal(playing);
		currentShield = new OBJ_Shield_Wood(playing);
		currentLight = null;
		projectile = new OBJ_Fireball(playing);
//		projectile = new OBJ_Rock(gamePanel);
		attack = getAttack();
		defense = getDefense();
		
		getImage(texture);
		getAttackImage();
		getGuardImage();
		setItems();
		setDialogue();
	}

	public void setDefaultPositions() {
		worldX = TILE_SIZE * 23;
		worldY = TILE_SIZE * 21;
		direction = Direction.down;
		playing.playMusic(0);
	}

	public void setDialogue() {
		dialogues[0][0] = "Congratulations!\nYou reached level " + level + "!";
	}
	
	public void restoreStatus() {
		life = maxLife;
		mana = maxMana;
		speed = defaultSpeed;
		invincible = false;
		transparent = false;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}

	public void setItems() {

		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(playing));

	}

	public int getAttack() {
		attackHitbox = currentWeapon.attackHitbox;
		motion1_duration = currentWeapon.motion1_duration;
		return attack = strength * currentWeapon.attackValue;
	}

	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}

	public void clearSprites() {
		walkSpriteNum = 1;
		up.clear();
		left.clear();
		down.clear();
		right.clear();
	}

	public void getSleepingImage(BufferedImage image) {
		clearSprites();
		up.add(image);
		left.add(image);
		down.add(image);
		right.add(image);
		up.add(image);
		left.add(image);
		down.add(image);
		right.add(image);

	}

	public void getAttackImage() {
		clearAttackImage();

		if (currentWeapon.type == EntityType.sword) {
			texture = ImageLoader.loadImage("/textures/javi_sword");
		} else if (currentWeapon.type == EntityType.axe) {
			texture = ImageLoader.loadImage("/textures/javi_axe");
		}
		sheet = new SpriteSheet(texture);
		int size = TILE_SIZE * 4;

		for (int i = 1; i < 6; i++) {
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

	public void getGuardImage() {
		clearGuardImage();

		if (currentShield.type == EntityType.shield) {
			texture = ImageLoader.loadImage("/textures/javi_shield");
		}
		else if (currentShield.type == EntityType.shield_blue) {
			texture = ImageLoader.loadImage("/textures/javi_shield_blue");
		}
		sheet = new SpriteSheet(texture);
		int size = playing.tileSize;

		for (int i = 1; i <= 8; i++) {
			guardUp.add(sheet.crop(i * size, 8 * size, size, size));
			guardLeft.add(sheet.crop(i * size, 9 * size, size, size));
			guardDown.add(sheet.crop(i * size, 10 * size, size, size));
			guardRight.add(sheet.crop(i * size, 11 * size, size, size));
		}
	}

	public void clearGuardImage() { // TODO combine with attack clear to make clearPlayerImage
		guardUp.clear();
		guardLeft.clear();
		guardDown.clear();
		guardRight.clear();
	}

	private void getInput() {
		xSpeed = 0;
		ySpeed = 0;

		if (playing.upPressed) {
			ySpeed = -speed;
			direction = Direction.up;
		}
		if (playing.downPressed) {
			ySpeed = speed;
			direction = Direction.down;
		}
		if (playing.leftPressed) {
			xSpeed = -speed;
			direction = Direction.left;
		}
		if (playing.rightPressed) {
			xSpeed = speed;
			direction = Direction.right;
		}
	}

	public void update() {
		
		if (knockBack) { //TODO all of these collisions are redundant code that should be put into a method (maybe)
			
			
			collisionOn = false;
			playing.cChecker.checkTile(this);
			playing.cChecker.checkObject(this, true);
			playing.cChecker.checkEntity(this, playing.npcList);
			playing.cChecker.checkEntity(this, playing.monsterList);
			playing.cChecker.checkEntity(this, playing.iTile);
			
			if (collisionOn) {
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			} else if (!collisionOn) {
				switch (knockBackDirection) {
				case up:
					worldY -= speed;
					break;
				case down:
					worldY += speed;
					break;
				case left:
					worldX -= speed;
					break;
				case right:
					worldX += speed;
					break;
				default:
					break;
				}
			}
			knockBackCounter++;
			if (knockBackCounter == 10) { // TODO This 10 could increase as players get stronger
				knockBackCounter = 0;
				knockBack = false;
				speed = defaultSpeed;
			}
		}
		
		
		else if (attacking) {
			attacking();
		} else if (playing.guardKeyPressed) {
			guarding = true;
			guardCounter++;
			walkSpriteCounter(up);
		} else if (playing.upPressed || playing.downPressed || playing.leftPressed
				|| playing.rightPressed) {
			walkSpriteCounter(up);
		}

		getInput();
		move();

		// CHECK TILE COLLISION
		collisionOn = false;
		playing.cChecker.checkTile(this);

		// CHECK OBJECT COLLISION
		int objIndex = playing.cChecker.checkObject(this, true);
		pickUpObject(objIndex);

		// CHECK NPC COLLISION
		int npcIndex = playing.cChecker.checkEntity(this, playing.npcList);
		interactNPC(npcIndex);

		// CHECK MONSTER COLLISION
		int monsterIndex = playing.cChecker.checkEntity(this, playing.monsterList);
		interactMonster(monsterIndex);

		// CHECK INTERACTIVE TILE COLLISION
		playing.cChecker.checkEntity(this, playing.iTile);

		// CHECK EVENT
		playing.eventHandler.checkEvent();

		if (playing.enterPressed && !attackCanceled) {
			attacking = true;
			attackSpriteCounter = 0;
		}

		attackCanceled = false;
		playing.enterPressed = false;

		if (playing.shotKeyPressed /* && shotAvailiableCounter == 30 */ && projectile.haveResource(this)) {

			// SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY, direction, true, this);

			// SUBTRACT THE COST (MANA, AMMO, ETC.
			projectile.subtractResource(this);

			// ADD IT TO LIST
			playing.projectileList.add(projectile);

			shotAvailiableCounter = 0;

			playing.playSoundEffect(10);
		}
		playing.shotKeyPressed = false;

		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 60) {
				invincible = false;
				transparent = false;
				invincibleCounter = 0;
			}
		}
		if (shotAvailiableCounter < 30) {
			shotAvailiableCounter++;
		}
		if (life > maxLife) {
			life = maxLife;
		}
		if (mana > maxMana) {
			mana = maxMana;
		}
		if (life <= 0) {
			life = 0;
			playing.gameState = GameState.GAME_OVER;
			playing.stopMusic();
			playing.playSoundEffect(12);
		}
		if (!playing.guardKeyPressed) {
			guarding = false;
			guardCounter = 0;
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {

			// PICKUP ONLY ITEMS

			if (playing.objectList.get(i).type == EntityType.pickup) {

				playing.objectList.get(i).use(this);
				playing.objectList.remove(i);

			}
			// OBSTACLE
			else if (playing.objectList.get(i).type == EntityType.obstacle) {
				if (playing.enterPressed) {
					attackCanceled = true;
					playing.objectList.get(i).interact();
				}
			}
			// INVENTORY ITEMS
			else {

				// INVENTORY ITEMS
				String text;
				if (canObtainItem(playing.objectList.get(i))) {
					playing.playSoundEffect(1);
					text = "Picked up a " + playing.objectList.get(i).name + "!";
				} else {
					text = "You cannot carry any more.";
				}
				playing.ui.addMessage(text);
				playing.objectList.remove(i);
			}
		}
	}

	public void interactNPC(int i) {

		if (playing.enterPressed) {
			if (i != 999) {
				attackCanceled = true;
				playing.npcList.get(i).speak();
				System.out.println("SPEAK");
			}
		}
	}

	public void interactMonster(int i) {

		if (i != 999) {

			if (!invincible && !playing.monsterList.get(i).dying) {
				playing.playSoundEffect(6);

				int damage = playing.monsterList.get(i).attack - defense;
				if (damage < 1) {
					damage = 1;
				}
				life -= damage;
				invincible = true;
				transparent = true;
			}
		}
	}

	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		if (i != 999) {
			if (!playing.monsterList.get(i).invincible && !playing.monsterList.get(i).dying) {

				playing.playSoundEffect(5);

				if (knockBackPower > 0) {
					setKnockBack(playing.monsterList.get(i), attacker, knockBackPower);
				}
				
				if(playing.monsterList.get(i).offBalance) {
					attack *= 3;
				}

				int damage = attack - playing.monsterList.get(i).defense;
				if (damage < 0) {
					damage = 0;
				}

				playing.monsterList.get(i).life -= damage;
				playing.ui.addMessage(damage + " damage!");

				playing.monsterList.get(i).invincible = true;
				playing.monsterList.get(i).damageReaction();

				if (playing.monsterList.get(i).life <= 0) {
					playing.monsterList.get(i).dying = true;
					playing.ui.addMessage(playing.monsterList.get(i).name + " defeated!");
					exp += playing.monsterList.get(i).exp;
					playing.ui.addMessage("Exp +" + playing.monsterList.get(i).exp);
					checkLevelUp();
				}
			}
		}
	}

	public void damageInteractiveTile(int i) {
		if (i != 999) {
			if (playing.iTile.get(i).isDestructible() && playing.iTile.get(i).isCorrectItem(this)
					&& !playing.iTile.get(i).invincible) {
				playing.iTile.get(i).playSoundEffect();
				playing.iTile.get(i).life--;
				playing.iTile.get(i).invincible = true;

				generateParticle(playing.iTile.get(i), playing.iTile.get(i));

				if (playing.iTile.get(i).life <= 0) {
					playing.iTile.set(i, playing.iTile.get(i).getDestroyedForm());
				}
			}
		}
	}

	public void damageProjectile(int i) {
		if (i != 999) {
			Entity projectile = playing.projectileList.get(i);
			projectile.alive = false;
			generateParticle(projectile, projectile);
		}
	}

	public void checkLevelUp() {
		if (exp >= nextLevelExp) {
			level++;
			exp = 0;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();

			playing.playSoundEffect(8);
			playing.gameState = GameState.DIALOGUE;
			
			setDialogue();
			startDialogue(this,0);

		}
	}

	public void selectItem() {
		int itemIndex = playing.ui.getItemIndexOnSlot(playing.ui.playerSlotCol, playing.ui.playerSlotRow);

		if (itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);

			if (selectedItem.type == EntityType.sword || selectedItem.type == EntityType.axe) {

				currentWeapon = selectedItem;
				attack = getAttack();
				getAttackImage();
			}
			if (selectedItem.type == EntityType.shield || selectedItem.type == EntityType.shield_blue) {
				currentShield = selectedItem;
				defense = getDefense();
				getGuardImage();
			}
			if (selectedItem.type == EntityType.light) {
				if (currentLight == selectedItem) {
					currentLight = null;
				} else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			if (selectedItem.type == EntityType.consumable) {
				if (selectedItem.use(this)) {
					if (selectedItem.amount > 1) {
						selectedItem.amount--;
					} else {
						inventory.remove(itemIndex);
					}
				}
			}
		}
	}

	public int searchItemInInventory(String itemName) {

		int itemIndex = 999;

		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}

	public boolean canObtainItem(Entity item) {
		boolean canObtain = false;
		
		Entity newItem = playing.eGenerator.getObject(item.name);

		// CHECK IF STACKABLE
		if (newItem.stackable) {
			int index = searchItemInInventory(newItem.name);

			if (index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			} else {// NEW ITEM, CHECK INV SPACE
				if (inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					canObtain = true;
				}
			}
		} else {// NEW STACKABLE, CHECK INV SPACE
			if (inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				canObtain = true;
			}
		}
		return canObtain;
	}

	public void draw(Graphics2D g2) {
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		if (transparent) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		if (attacking) {
			tempScreenY = screenY - playing.tileSize / 2;
			tempScreenX = screenX - playing.tileSize / 2;
			g2.drawImage(drawAttack(), tempScreenX, tempScreenY, null);
		} else if (!attacking && guarding) {
			g2.drawImage(drawGuard(), screenX, screenY, null);
		} else if (!attacking && !guarding) {
			g2.drawImage(drawWalk(), screenX, screenY, null);
		}
		// Reset Alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		if (playing.debugMode) {
			g2.setColor(Color.red);
			g2.drawRect(screenX + hitbox.x, screenY + hitbox.y, hitbox.width, hitbox.height);

			// DEBUG
			tempScreenX = screenX + hitbox.x;
			tempScreenY = screenY + hitbox.y;
			switch (direction) {
			case up:
				tempScreenY = screenY + attackUpY;
				tempScreenX = screenX + attackUpX;
				break;
			case down:
				tempScreenY = screenY + attackDownY;
				tempScreenX = screenX + attackDownX;
				break;
			case left:
				tempScreenY = screenY + attackLeftY;
				tempScreenX = screenX + attackLeftX;
				break;
			case right:
				tempScreenY = screenY + attackRightY;
				tempScreenX = screenX + attackRightX;
				break;
			default:
				break;
			}
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(1));
			g2.drawRect(tempScreenX, tempScreenY, attackHitbox.width, attackHitbox.height);
		}
	}
	
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
}
