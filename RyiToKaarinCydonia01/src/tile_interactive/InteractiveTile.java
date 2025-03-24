package tile_interactive;

import entity.Entity;
import scenes.Playing;

public class InteractiveTile extends Entity{
	
	Playing playing;
	public boolean destructible = false;
	
	public InteractiveTile(Playing playing, int col, int row) {
		super(playing);
		this.playing = playing;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	
	public void playSoundEffect() {
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}
	
	public void update() {
	
		if(invincible) {
			invincibleCounter++;
			if(invincibleCounter > 30) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		
	}


	public boolean isDestructible() {
		return destructible;
	}

}
