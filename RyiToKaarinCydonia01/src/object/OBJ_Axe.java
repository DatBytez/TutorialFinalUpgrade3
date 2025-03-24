package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Axe extends Entity{
	
	public static final String objName = "Woodcutter's Axe";
	
	public OBJ_Axe(Playing playing) {
		super(playing);
		
		type = EntityType.axe;
		name = objName;
		down.add(setup("/objects/axe"));
		down.add(setup("/objects/axe", playing.tileSize/2,playing.tileSize/2));
		attackValue = 2;
		attackHitbox.width = playing.tileSize/2 + playing.tileSize/4;
		attackHitbox.height = playing.tileSize/2 + playing.tileSize/4;;
		description = "["+ name +"]\nA simple axe.";
		price = 75;
		knockBackPower = 1;
		motion1_duration = 25;
	}

}
