package tile_interactive;

import java.awt.Color;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class IT_DryTree extends InteractiveTile{
	
	Playing playing;

	public IT_DryTree(Playing playing, int col, int row) {
		super(playing, col, row);
		this.playing = playing;
		
		this.worldX = playing.tileSize * col;
		this.worldY = playing.tileSize * row;
		
		down.add(setup("/tiles_interactive/drytree"));
		this.destructible = true;
		life = 3;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		
		if(entity.currentWeapon.type == EntityType.axe) {
			isCorrectItem = true;
		}
		
		return isCorrectItem;
	}
	
	public void playSoundEffect() {
		playing.playSoundEffect(11);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new IT_Trunk(playing, worldX/playing.tileSize, worldY/playing.tileSize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65,50,30);
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
	
	public boolean isDestructible() {
		return destructible;
	}

}
