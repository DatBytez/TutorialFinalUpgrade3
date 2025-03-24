package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Lantern extends Entity{
	public static final String objName = "Lantern";
	
	public OBJ_Lantern(Playing playing) {
		super(playing);
		
		type = EntityType.light;
		name = objName;
		down.add(setup("/objects/lantern",32,32));
		description = "["+ name +"]\nIlluminates your \nsurroundings.";
		price = 200;
		lightRadius = 300;
	}

}
