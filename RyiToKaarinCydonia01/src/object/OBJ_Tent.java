package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Tent extends Entity{
	public static final String objName = "Tent";
	Playing gamePanel; //TODO:Try deleting this
	
	public OBJ_Tent(Playing playing) {
		super(playing);
		this.gamePanel = playing;
		
		type = EntityType.consumable;
		name = objName;
		down.add(setup("/objects/tent"));
		description = "[" + name +"]\nYou can sleep until\nnext morning.";
		price = 10;
		stackable = true;
	}

	public boolean use(Entity entity) {
//		gamePanel.gameState = GameState.SLEEP;
		gamePanel.playSoundEffect(14);
		gamePanel.player.life = gamePanel.player.maxLife;
		gamePanel.player.mana = gamePanel.player.maxMana;
		gamePanel.player.getSleepingImage(down.get(0));
		return false;
	}
}
