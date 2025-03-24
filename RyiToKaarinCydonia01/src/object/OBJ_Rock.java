package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import gfx.ImageLoader;
import scenes.Playing;

public class OBJ_Rock extends Projectile{
	public static final String objName = "Rock";
	
	Playing playing; //TODO: Try deleting this.
	
		public OBJ_Rock(Playing playing) {
			super(playing);
			this.playing = playing;
			
			name = objName;
			speed = 8;
			maxLife = 60;
			life = maxLife;
			attack = 2;
			useCost = 1;
			alive = false;
			knockBackPower = 2;
			getImage();
		}
		
		public void getImage() {
			up.add(setup("/projectile/rock_down_1",playing.tileSize/2,playing.tileSize/2));
			left.add(setup("/projectile/rock_down_1",playing.tileSize/2,playing.tileSize/2));
			down.add(setup("/projectile/rock_down_1",playing.tileSize/2,playing.tileSize/2));
			right.add(setup("/projectile/rock_down_1",playing.tileSize/2,playing.tileSize/2));
		}
		
		public boolean haveResource(Entity user) {
			boolean haveResource = false;
			if(user.ammo >= useCost) {
				haveResource = true;
			}
			return haveResource;
		}
		
		public void subtractResource(Entity user) {
			user.ammo -= useCost;
		}
		
		public Color getParticleColor() {
			Color color = new Color(40,50,0);
			return color;
		}
		
		public int getParticleSize() {
			int size = playing.tileSize/8;
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



