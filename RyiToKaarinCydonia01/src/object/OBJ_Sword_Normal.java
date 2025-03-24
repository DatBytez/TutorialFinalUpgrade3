package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Sword_Normal extends Entity {
	public static final String objName = "Normal Sword";
	
	public OBJ_Sword_Normal(Playing playing) {
		super(playing);
		
		type = EntityType.sword;
		name = objName;
		down.add(setup("/objects/sword_normal"));
		down.add(setup("/objects/sword_normal", playing.tileSize/2,playing.tileSize/2));
		attackValue = 1;
		attackHitbox.width = playing.tileSize;
		attackHitbox.height = playing.tileSize;
		description = "[" + name +"]\nAn old sword.";
		price = 5;
		knockBackPower = 2;
		motion1_duration = 0;
	}

}
