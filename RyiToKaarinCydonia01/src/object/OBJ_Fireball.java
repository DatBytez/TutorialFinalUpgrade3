package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import scenes.Playing;

public class OBJ_Fireball extends Projectile{
	
	public static final String objName = "Fireball";
	
	Playing gamePanel; //TODO: Try deleting this.

	public OBJ_Fireball(Playing playing) {
		super(playing);
		this.gamePanel = playing;
		
		name = objName;
		speed = 5;
		maxLife = 80;
		life = maxLife;
		attack = 5;
		knockBackPower = 10;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up.add(setup("/projectile/fireball_up_1"));
		up.add(setup("/projectile/fireball_up_2"));
		left.add(setup("/projectile/fireball_left_1"));
		left.add(setup("/projectile/fireball_left_2"));
		down.add(setup("/projectile/fireball_down_1"));
		down.add(setup("/projectile/fireball_down_2"));
		right.add(setup("/projectile/fireball_right_1"));
		right.add(setup("/projectile/fireball_right_2"));
		
		up.add(setup("/projectile/fireball_up_2"));
		left.add(setup("/projectile/fireball_left_2"));
		down.add(setup("/projectile/fireball_down_2"));
		right.add(setup("/projectile/fireball_right_2"));
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(240,50,0);
		return color;
	}
	
	public int getParticleSize() {
		int size = gamePanel.tileSize/8;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
