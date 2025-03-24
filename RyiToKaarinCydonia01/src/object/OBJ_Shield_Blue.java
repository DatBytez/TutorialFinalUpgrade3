package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Shield_Blue extends Entity{
	public static final String objName = "Blue Shield";
	
	public OBJ_Shield_Blue(Playing playing) {
		super(playing);
		
		type = EntityType.shield_blue;
		name = objName;
		down.add(setup("/objects/shield_blue"));
		down.add(setup("/objects/shield_blue", playing.tileSize/2,playing.tileSize/2));
		defenseValue = 2;
		description = "[" + name +"]\nA shiny blue shield.";
		price = 25;
	}
}
