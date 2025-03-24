package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Potion_Red extends Entity{
	public static final String objName = "Red Potion";
	
	Playing gamePanel;
	
	public OBJ_Potion_Red(Playing playing) {
		super(playing);
		this.gamePanel = playing;
		
		
		type = EntityType.consumable;
		name = objName;
		value = 5;
		down.add(setup("/objects/potion_red", playing.tileSize/2, playing.tileSize/2));
		description = "[Red Potion]\nHeals your life by "+ value+".";
		price = 1;
		stackable = true;
		setDialogue();
	}
	
	public void setDialogue() {//This can be moved to all entities and here you just pass it a string or something
		dialogues[0][0] = "You drink the " + name + "!\n"
				+ "You regain "+value+" life.";
	}
	
	public boolean use(Entity entity) {
		startDialogue(this,0);
		entity.life += value;
		if(gamePanel.player.life > gamePanel.player.maxLife) {
			gamePanel.player.life = gamePanel.player.maxLife;
		}
		gamePanel.playSoundEffect(2);
		return true;
	}

}
