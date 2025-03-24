package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Key extends Entity {
	public static final String objName = "Key";
	
	public OBJ_Key(Playing playing) {
		super(playing);
		this.playing = playing;

		type = EntityType.consumable;
		name = objName;
		down.add(setup("/objects/key", playing.tileSize/2, playing.tileSize/2));
		description = "[" + name +"]\nIt opens a door.";
		price = 10;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You use the " + name + " and open the door";
		
		dialogues[1][0] = "There is nothing to use the " + name + " on.";
	}
	public boolean use(Entity entity) {//TODO: I think interact in npc class could be changed to use and combined into one function
		
		int objIndex = getDetected(entity, playing.objectList, "Door");
		
		if(objIndex != 999) {
			startDialogue(this,0);
			playing.playSoundEffect(3);
			playing.objectList.remove(objIndex);
			return true;
		}
		else {
			startDialogue(this,1);
			return false;
		}
	}
}
