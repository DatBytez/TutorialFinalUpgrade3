package object;

import entity.Entity;
import scenes.Playing;

public class OBJ_Boots extends Entity {
	
	public static final String objName = "Boots";

	public OBJ_Boots(Playing playing) {
		super(playing);

		name = objName;
		down.add(setup("/objects/boots"));
		price = 200;
	}
}
