package entity;

import static main.Game.*;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import gfx.SpriteSheet;
import main.Util;
import scenes.GameState;
import scenes.Playing;
import tile_interactive.InteractiveTile;

public class Entity {

	public Playing playing;
	protected ArrayList<BufferedImage> up = new ArrayList<BufferedImage>();
	public ArrayList<BufferedImage> down = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> left = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> right = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> attackUp = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> attackDown = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> attackLeft = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> attackRight = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> guardUp = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> guardDown = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> guardLeft = new ArrayList<BufferedImage>();
	protected ArrayList<BufferedImage> guardRight = new ArrayList<BufferedImage>();
	protected BufferedImage texture;
	protected SpriteSheet sheet;
	public BufferedImage image, image2, image3, portrait;

	public Rectangle hitbox = new Rectangle(0, 0, TILE_SIZE, TILE_SIZE);
	public int hitboxDefaultX, hitboxDefaultY;
	public Rectangle attackHitbox = new Rectangle(0, 0, 0, 0);
	public boolean collision = false;
	public String dialogues[][] = new String[20][20];
	public Entity attacker;

	// STATE
	public int worldX, worldY;
	public Direction direction;
	public int walkSpriteNum = 0;
	private int attackSpriteNum = 1;
	protected int projectileSpriteNum = 1;
	public int dialogueSet = 0;
	public int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	private boolean hpBarOn = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public Direction knockBackDirection;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public Entity loot;
	public boolean opened = false;
	public boolean swinging = false;

	// COUNTERS
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	protected int walkSpriteCounter = 0;
	protected int attackSpriteCounter = 0;
	protected int projectileSpriteCounter = 0;
	public int shotAvailiableCounter = 0;
	private int dyingCounter = 0;
	private int hpBarCounter = 0;
	protected int knockBackCounter = 0;
	public int guardCounter = 0;
	int offBalanceCounter = 0;

	private int spriteNum = 1;
	private int spriteCounter = 0;

	// CHARACTER ATTRIBUTES
	public EntityType type;
	protected int focus = 120;
	public String name;
	public int defaultSpeed = 1;
	public int speed, xSpeed, ySpeed;
	public int maxLife;
	public int life;
	public int mana;
	public int maxMana;
	public int ammo;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1_duration;
	public int motion2_duration;
	public Entity currentWeapon; // TODO make a separate area where these are shown to be equipped
	public Entity currentShield;
	public Entity currentLight;
	public Projectile projectile;

	// ITEM ATTRIBUTES
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int value;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public boolean destructible;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadius;

	public Entity(Playing playing) {
		this.playing = playing;
		this.direction = Direction.down;
//		setDefaultHitbox();
	}

	public int getLeftX() {
		return worldX + hitbox.x;
	}

	public int getRightX() {
		return worldX + hitbox.x + hitbox.width;
	}

	public int getTopY() {
		return worldY + hitbox.y;
	}

	public int getBottomY() {
		return worldY + hitbox.y + hitbox.height;
	}

	public int getCol() {
		return (worldX + hitbox.x) / TILE_SIZE;
	}

	public int getRow() {
		return (worldY + hitbox.y) / TILE_SIZE;
	}

	public int getXdistance(Entity target) {
		return Math.abs(worldX - target.worldX);
	}

	public int getYdistance(Entity target) {
		return Math.abs(worldY - target.worldY);
	}

	public int getTileDistance(Entity target) {
		return (getXdistance(target) + getYdistance(target)) / TILE_SIZE;
	}

	public int getGoalCol(Entity target) {
		return (target.worldX + target.hitbox.x) / TILE_SIZE;
	}

	public int getGoalRow(Entity target) {
		return (target.worldY + target.hitbox.y) / TILE_SIZE;
	}

	public void resetCounter() {
		actionLockCounter = 0;
		invincibleCounter = 0;
		walkSpriteCounter = 0;
		attackSpriteCounter = 0;
		projectileSpriteCounter = 0;
		shotAvailiableCounter = 0;
		dyingCounter = 0;
		hpBarCounter = 0;
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;

		spriteNum = 1;
		spriteCounter = 0;
	}

	public void setLoot(Entity loot) {

	}

	public void setAction() {
	}

	public void damageReaction() {

	}

	public void speak() {

	}
	
	public void facePlayer() {
		switch (playing.player.direction) {
		case up:
			direction = Direction.down;
			break;
		case down:
			direction = Direction.up;
			break;
		case left:
			direction = Direction.right;
			break;
		case right:
			direction = Direction.left;
			break;
		default:
			break;
		}
	}
	
	public void setPortrait() {
		portrait = sheet.crop((int)(2.5 * TILE_SIZE),(int)(4.3 * TILE_SIZE), TILE_SIZE, TILE_SIZE);
	}
	
	public void startDialogue(Entity entity, int setNum) {
		dialogueSet = setNum;
		System.out.println("StartDialogue SetNum: " + setNum);
		playing.ui.npc = entity;
		System.out.println("StartDialogue npc.setdialogueSet: "+ playing.ui.npc.dialogueSet);
		playing.gameState = GameState.DIALOGUE;
		
		
	}

	public void interact() {
	}

	public boolean use(Entity entity) {
		return false;
	}

	public void checkDrop() {
	}

	public void dropItem(Entity droppedItem) {
		droppedItem.worldX = worldX;
		droppedItem.worldY = worldY;
		playing.objectList.add(droppedItem);
	}

	public Color getParticleColor() {
		Color color = null;
		return color;
	}

	public int getParticleSize() {
		int size = 0;
		return size;
	}

	public int getParticleSpeed() {
		int speed = 0;
		return speed;
	}

	public int getParticleMaxLife() {
		int maxLife = 0;
		return maxLife;
	}

	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getParticleMaxLife();

		Particle p1 = new Particle(playing, target, color, size, speed, maxLife, -2, -1); // final two numbers
																							// determine which
																							// directions the particles
																							// will fly.
		Particle p2 = new Particle(playing, target, color, size, speed, maxLife, +2, -1);
		Particle p3 = new Particle(playing, target, color, size, speed, maxLife, -2, +1);
		Particle p4 = new Particle(playing, target, color, size, speed, maxLife, +2, +1);
		Particle p5 = new Particle(playing, target, color, size, speed, maxLife, -1, -1);
		Particle p6 = new Particle(playing, target, color, size, speed, maxLife, +1, -1);
		Particle p7 = new Particle(playing, target, color, size, speed, maxLife, -1, +1);
		Particle p8 = new Particle(playing, target, color, size, speed, maxLife, +1, +1);
		playing.particleList.add(p1);
		playing.particleList.add(p2);
		playing.particleList.add(p3);
		playing.particleList.add(p4);
		playing.particleList.add(p5);
		playing.particleList.add(p6);
		playing.particleList.add(p7);
		playing.particleList.add(p8);
	}

	public void checkCollision() {
		collisionOn = false;
		playing.cChecker.checkTile(this);
		playing.cChecker.checkObject(this, false);
		playing.cChecker.checkEntity(this, playing.npcList);
		playing.cChecker.checkEntity(this, playing.monsterList);
		playing.cChecker.checkEntity(this, playing.iTile);
		boolean contactPlayer = playing.cChecker.checkPlayer(this);

		if (type == EntityType.monster && contactPlayer) {
			damagePlayer(attack);
		}
	}

	public void update() {

		if (knockBack) {
			checkCollision();
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
				this.speed = defaultSpeed;
				System.out.println("speed: "+speed);
				System.out.println("default speed: "+defaultSpeed);
			}
		} else if (attacking) {
			attacking();
		}

		else {
			setAction();
			checkCollision();

			if (collisionOn == false) {
				switch (direction) {
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

			spriteCounter++; // TODO I think this can be removed once I stop using his sprites
			if (spriteCounter > 24) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

			walkSpriteCounter(up);
		}

		if (invincible) {
			invincibleCounter++;
			if (invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}

		if (shotAvailiableCounter < 30) {
			shotAvailiableCounter++;
		}
		if (offBalance) {
			offBalanceCounter++;
			if (offBalanceCounter > 60) {
				offBalance = false;
				offBalanceCounter = 0;
			}
		}

	}

	public void checkAttackOrNot(int rate, int straight, int horizontal) {
		boolean targetInRange = false;
		int xDis = getXdistance(playing.player);
		int yDis = getYdistance(playing.player);

		switch (direction) {
		case up:
			if (playing.player.worldY < worldY && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case down:
			if (playing.player.worldY > worldY && yDis < straight && xDis < horizontal) {
				targetInRange = true;
			}
			break;
		case left:
			if (playing.player.worldX < worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		case right:
			if (playing.player.worldX > worldX && xDis < straight && yDis < horizontal) {
				targetInRange = true;
			}
			break;
		}

		if (targetInRange) {
			// Check if player is in range to start attack
			int i = new Random().nextInt(rate);
			if (i == 0) {
				attacking = true;
				spriteNum = 1;
				attackSpriteCounter = 0; // may need to be renamed spriteCounter
				spriteCounter = 0;
				shotAvailiableCounter = 0;
			}
		}
	}

	public void checkShootOrNot(int rate, int shotInterval) {
		int i = new Random().nextInt(rate);
		if (i == 0 && projectile.alive == false && shotAvailiableCounter == shotInterval) {
			projectile.set(worldX, worldY, direction, true, this);
			playing.projectileList.add(projectile);
			shotAvailiableCounter = 0;
		}
	}

	public void checkStartChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) < distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = true;
			}
		}
	}

	public void checkStopChasingOrNot(Entity target, int distance, int rate) {
		if (getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if (i == 0) {
				onPath = false;
			}
		}
	}

	public Direction getOppositeDirection(Direction direction) {
		Direction oppositeDirection = Direction.down;

		switch (direction) {
		case up:
			oppositeDirection = Direction.down;
			break;
		case down:
			oppositeDirection = Direction.up;
			break;
		case left:
			oppositeDirection = Direction.right;
			break;
		case right:
			oppositeDirection = Direction.left;
			break;
		default:
			break;
		}
		return oppositeDirection;
	}

	public void attacking() {

		int attackSpriteCounter = attackSpriteCounter(attackUp);
		
		if (attackSpriteNum == 2 && swinging == false) { // TODO: Messy but it works.
			playing.playSoundEffect(7);
			swinging = true;
		} else if(attackSpriteNum != 2) {
			swinging = false;
		}
		
		if (attackSpriteCounter < motion1_duration && attackSpriteNum == 1) {
		}else if (attackSpriteCounter > 2) { // TODO Addjust difficutly of hit timing
			// Save current worldX, worldY, hitbox
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int hitboxWidth = hitbox.width;
			int hitboxHeight = hitbox.height;

			// Adjust player's worldX/Y for the attackArea
			switch (direction) {
			case up:
				worldY -= hitbox.height;
				break;
			case down:
				worldY += hitbox.height;
				break;
			case left:
				worldX -= hitbox.width;
				break;
			case right:
				worldX += hitbox.width;
				break;
			default:
				break;
			}
			// attackHitbox becomes Hitbox
			hitbox.width = attackHitbox.width;
			hitbox.height = attackHitbox.height;

			if (type == EntityType.monster) {
				if (playing.cChecker.checkPlayer(this)) {
					damagePlayer(attack);
				}
			} else { // Player
				// Check monster collision with the updated worldX/Y/Hitbos
				int monsterIndex = playing.cChecker.checkEntity(this, playing.monsterList);
				playing.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

				int iTileIndex = playing.cChecker.checkEntity(this, playing.iTile);
				playing.player.damageInteractiveTile(iTileIndex);

				int projectileIndex = playing.cChecker.checkEntity(this, playing.projectileList);
				playing.player.damageProjectile(projectileIndex);
			}

			// After checking attack, return hitbox to original
			worldX = currentWorldX;
			worldY = currentWorldY;
			hitbox.width = hitboxWidth;
			hitbox.height = hitboxHeight;
		}
	}

	public void damagePlayer(int attack) {
		if (!playing.player.invincible) {

			int damage = attack - playing.player.defense;

			// Get an opposite direction of this attacker
			Direction canGuardDirection = getOppositeDirection(direction);

			if (playing.player.guarding && playing.player.direction.equals(canGuardDirection)) {
				// Parry
				if (playing.player.guardCounter < 10) {
					damage = 0;
					playing.playSoundEffect(16);
					setKnockBack(this, playing.player, knockBackPower);// TODO Set knockBackPower based on player or
																			// shield here
					offBalance = true;
					spriteCounter = -60; // TODO this may need to be tweeked, this should throw the person back into
											// their preattack animation
				} else {
					// Guard
					damage /= 3; // Divides damage by
					playing.playSoundEffect(15);
				}
			} else {
				playing.playSoundEffect(6);
				if (damage < 1) {
					damage = 1;
				}
			}

			if (damage != 0) {
				playing.player.transparent = true;
			} // TODO if you place set knockback above this line it will only happen if the
				// player takes damage
			setKnockBack(playing.player, this, knockBackPower);

			playing.player.life -= damage;
			playing.player.invincible = true;
		}
	}

	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {

		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}

	protected void walkSpriteCounter(ArrayList<BufferedImage> list) {
		int spriteNumMax = list.size() - 1;
		walkSpriteCounter++;
		if (walkSpriteCounter > 5) {
			if (walkSpriteNum < spriteNumMax) {
				walkSpriteNum++;
			} else if (walkSpriteNum == spriteNumMax) {
				attacking = false;
				walkSpriteNum = 0;
			}
			walkSpriteCounter = 0;
		}
	}

	protected int attackSpriteCounter(ArrayList<BufferedImage> list) {
		int spriteNumMax = list.size();
		attackSpriteCounter++;
		if (attackSpriteCounter < motion1_duration && attackSpriteNum == 1) {
		} else if (attackSpriteCounter > 5) {
			if (attackSpriteNum < spriteNumMax) {
				attackSpriteNum++;
			} else if (attackSpriteNum == spriteNumMax) {
				attacking = false;
				attackSpriteNum = 1;
			}
			attackSpriteCounter = 0;
		}
		return attackSpriteNum;
	}
	
	public void getImage(BufferedImage texture) {
		sheet = new SpriteSheet(texture);

		for (int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * TILE_SIZE*2, 8 * TILE_SIZE*2, TILE_SIZE*2, TILE_SIZE*2));
			left.add(
					sheet.crop(i * TILE_SIZE*2, 9 * TILE_SIZE*2, TILE_SIZE*2, TILE_SIZE*2));
			down.add(sheet.crop(i * TILE_SIZE*2, 10 * TILE_SIZE*2, TILE_SIZE*2,
					TILE_SIZE*2));
			right.add(sheet.crop(i * TILE_SIZE*2, 11 * TILE_SIZE*2, TILE_SIZE*2,
					TILE_SIZE*2));
		}
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - playing.player.worldX + playing.player.screenX;
		int screenY = worldY - playing.player.worldY + playing.player.screenY;

		if (worldX + TILE_SIZE > playing.player.worldX - playing.player.screenX
				&& worldX - TILE_SIZE < playing.player.worldX + playing.player.screenX
				&& worldY + TILE_SIZE > playing.player.worldY - playing.player.screenY
				&& worldY - TILE_SIZE < playing.player.worldY + playing.player.screenY) {

			int tempScreenX = screenX;
			int tempScreenY = screenY;

			// Monster HP Bar
			if (type == EntityType.monster && hpBarOn) {

				double oneScale = (double) hitbox.width / maxLife;
				double hpBarValue = oneScale * life;

				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - hitbox.height / 10 - 1, hitbox.width + 2, hitbox.height / 10 + 2);
				g2.setColor(new Color(255, 0, 30));
				g2.fillRect(screenX, screenY - hitbox.height / 10, (int) hpBarValue, hitbox.height / 10);

				hpBarCounter++;

				if (hpBarCounter > 400) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}

			if (invincible) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4F);
			}
			if (dying) {
				dyingAnimation(g2);
			}
			if (attacking) {
				tempScreenY = screenY - TILE_SIZE * 2;
				tempScreenX = screenX - TILE_SIZE * 2; //TODO: This is what was changed to fix attack animation looking strange.
				g2.drawImage(drawAttack(), tempScreenX, tempScreenY, null);
			} else if (!attacking && guarding) {
				g2.drawImage(drawGuard(), tempScreenX, tempScreenY, null);
			} else if (!attacking && !guarding) {
				g2.drawImage(drawWalk(), tempScreenX, tempScreenY, null);
			}

			changeAlpha(g2, 1F);
		}

		if (playing.debugMode) {
			g2.setColor(Color.red);
			g2.drawRect(screenX + hitbox.x, screenY + hitbox.y, hitbox.width, hitbox.height);
		}
	}

	protected BufferedImage drawWalk() {
		BufferedImage image = null;
		switch (direction) {
		case up:
			image = up.get(walkSpriteNum);
			break;
		case down:
			image = down.get(walkSpriteNum);
			break;
		case left:
			image = left.get(walkSpriteNum);
			break;
		case right:
			image = right.get(walkSpriteNum);
			break;
		default:
			break;
		}
		return image;
	}

	protected BufferedImage drawGuard() {
		BufferedImage image = null;
		switch (direction) {
		case up:
			image = guardUp.get(walkSpriteNum);
			break;
		case down:
			image = guardDown.get(walkSpriteNum);
			break;
		case left:
			image = guardLeft.get(walkSpriteNum);
			break;
		case right:
			image = guardRight.get(walkSpriteNum);
			break;
		default:
			break;
		}
		return image;
	}

	protected BufferedImage drawAttack() {
		BufferedImage image = null;
		switch (direction) {
		case up:
			image = attackUp.get(attackSpriteNum - 1);
			break;
		case down:
			image = attackDown.get(attackSpriteNum - 1);
			break;
		case left:
			image = attackLeft.get(attackSpriteNum - 1);
			break;
		case right:
			image = attackRight.get(attackSpriteNum - 1);
			break;
		default:
			break;
		}
		return image;
	}

	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;

		int i = 5;
		if (dyingCounter <= 5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i && dyingCounter <= i * 2) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
			changeAlpha(g2, 0f);
		}
		if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
			changeAlpha(g2, 1f);
		}
		if (dyingCounter > i * 8) {
			alive = false;
		}

	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public BufferedImage setup(String imagePath) {
		Util util = new Util();
		BufferedImage image = null;

		try {

			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = util.scaleImage(image, playing);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		Util util = new Util();
		BufferedImage image = null;

		try {

			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = util.scaleImage(image, width, height);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void move() {
		if (!collisionOn && !attacking) {
			worldX += xSpeed;
			worldY += ySpeed;
		}
	}

	protected void moveRandom() {
		actionLockCounter++;

		if (actionLockCounter == focus) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = Direction.up;
			}
			if (i > 25 && i <= 50) {
				direction = Direction.down;
			}
			if (i > 50 && i <= 75) {
				direction = Direction.left;
			}
			if (i > 75 && i <= 100) {
				direction = Direction.right;
			}

			actionLockCounter = 0;
		}
	}

	public Rectangle setHitbox(int x, int y, int width, int height) {
		Rectangle hitbox = new Rectangle(x, y, width, height);

		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = width;
		hitbox.height = height;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;

		return hitbox;
	}
	
	public Rectangle setDefaultHitbox() {
//		Rectangle hitbox = new Rectangle(x, y, width, height);

		hitbox.x = (int) (TILE_SIZE*0.6875);
		hitbox.y = TILE_SIZE;
		hitbox.width = (TILE_SIZE / 3)*2;
		hitbox.height = (int) (TILE_SIZE*0.9375);
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;

		return hitbox;
	}

	public boolean isDestructible() {
		return destructible;
	}

	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}

	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}

	public void playSoundEffect() {
	}

	public void searchPath(int goalCol, int goalRow) {

		int startCol = (worldX + hitbox.x) / TILE_SIZE;
		int startRow = (worldY + hitbox.y) / TILE_SIZE;

		playing.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

		if (playing.pFinder.search()) {

			// Next worldX & worldY
			int nextX = playing.pFinder.pathList.get(0).col * TILE_SIZE;
			int nextY = playing.pFinder.pathList.get(0).row * TILE_SIZE;

			// Entity's hitbox position
			int enLeftX = worldX + hitbox.x;
			int enRightX = worldX + hitbox.x + hitbox.width;
			int enTopY = worldY + hitbox.y;
			int enBottomY = worldY + hitbox.y + hitbox.height;

			if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + TILE_SIZE) {
				direction = Direction.up;
			} else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + TILE_SIZE) {
				direction = Direction.down;
			} else if (enTopY >= nextY && enBottomY < nextY + TILE_SIZE) {
				// left or right
				if (enLeftX > nextX) {
					direction = Direction.left;
				}
				if (enLeftX < nextX) {
					direction = Direction.right;
				}

			} else if (enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = Direction.up;
				checkCollision();
				if (collisionOn) {
					direction = Direction.left;
				}
			} else if (enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = Direction.up;
				checkCollision();
				if (collisionOn) {
					direction = Direction.right;
				}

			} else if (enTopY < nextY && enLeftX > nextX) {
				// down or left
				direction = Direction.down;
				checkCollision();
				if (collisionOn) {
					direction = Direction.left;
				}
			} else if (enTopY < nextY && enLeftX < nextX) {
				// down or right
				direction = Direction.down;
				checkCollision();
				if (collisionOn) {
					direction = Direction.right;
				}
			}

			// If goal reached, stop searching
//			int nextCol = gamePanel.pFinder.pathList.get(0).col;
//			int nextRow = gamePanel.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}

		}
	}

	public int getDetected(Entity user, ArrayList target, String targetName) {

		int index = 999;
		// Check the surrounding object
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch (user.direction) {
		case up:
			nextWorldY = user.getTopY() - playing.player.speed;
			break;
		case down:
			nextWorldY = user.getBottomY() + playing.player.speed;
			break;
		case left:
			nextWorldX = user.getLeftX() - playing.player.speed;
			break;
		case right:
			nextWorldX = user.getRightX() + playing.player.speed;
			break;
		}
		int col = nextWorldX / TILE_SIZE;
		int row = nextWorldY / TILE_SIZE;

		for (int i = 0; i < target.size(); i++) {
			if (target.get(i) != null) {
				if (((Entity) target.get(i)).getCol() == col && ((Entity) target.get(i)).getRow() == row
						&& ((Entity) target.get(i)).name.equals(targetName)) {

					index = i;
					break;

				}
			}
		}

		return index;

	}

	public BufferedImage getTexture() {
		return texture;
	}
	
	public boolean isQuestion(int dialogueSet, int dialogueIndex) {//TODO: Maybe set to require override.
		return false;
	}

}
