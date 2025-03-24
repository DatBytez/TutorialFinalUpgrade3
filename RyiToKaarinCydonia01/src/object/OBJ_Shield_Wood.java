package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Shield_Wood extends Entity{
	public static final String objName = "Wood Shield";
	
	public OBJ_Shield_Wood(Playing playing) {
		super(playing);
		
		type = EntityType.shield;
		name = objName;
		down.add(setup("/objects/shield_wood"));
		down.add(setup("/objects/shield_wood", playing.tileSize/2,playing.tileSize/2));
		defenseValue = 1;
		description = "[" + name +"]\nMade of wood.";
		price = 5;
	}

}
