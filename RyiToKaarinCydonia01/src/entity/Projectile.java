package entity;

import scenes.Playing;

public class Projectile extends Entity {

	Entity user;

	public Projectile(Playing gamePanel) {
		super(gamePanel);
	}

	public void set(int worldX, int worldY, Direction direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	public void update() {

		if (user == playing.player) {
			int monsterIndex = playing.cChecker.checkEntity(this, playing.monsterList);
			if (monsterIndex != 999) {
				playing.player.damageMonster(monsterIndex, this, attack, knockBackPower);
				generateParticle(user.projectile,playing.monsterList.get(monsterIndex));
				alive = false;
			}
		}
		if (user != playing.player) {
			boolean contactPlayer = playing.cChecker.checkPlayer(this);
			if (!playing.player.invincible && contactPlayer) {
				damagePlayer(attack);
				generateParticle(user.projectile,playing.player);
				alive = false;
			}
		}
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

		walkSpriteCounter(up);

		life--;
		if (life <= 0) {
			alive = false;
		}
	}

	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		return haveResource;
	}

	public void subtractResource(Entity user) {
	}

}
