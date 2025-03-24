package object;

import entity.Entity;
import entity.EntityType;

import scenes.Playing;

public class OBJ_Door extends Entity{
	
	public static final String objName = "Door";
	
	public OBJ_Door(Playing playing) {
		super(playing);
		this.playing = playing;

		type = EntityType.obstacle;
		name = objName;
		down.add(setup("/objects/door"));
		collision = true;
		
		hitbox = setHitbox(0,16,48,32);
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You need a key to open this,";
	}
	public void interact() {
		
		startDialogue(this,0);
	}
	
}
