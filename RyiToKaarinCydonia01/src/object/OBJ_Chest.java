package object;

import entity.Entity;
import entity.EntityType;
import scenes.Playing;

public class OBJ_Chest extends Entity {
	
	public static final String objName = "Chest";

	public OBJ_Chest(Playing playing) {
		super(playing);
		this.playing = playing;

		type = EntityType.obstacle;
		name = objName;

		down.add(setup("/objects/chest", 32, 32));
		down.add(setup("/objects/chest_opened", 32, 32));
		collision = true;

		hitbox.x = 4;
		hitbox.y = 16;
		hitbox.width = 40;
		hitbox.height = 32;
		hitboxDefaultX = hitbox.x;
		hitboxDefaultY = hitbox.y;
	}

	public void setLoot(Entity loot) {
		this.loot = loot;
		setDialogue();
	}

	public void setDialogue() {// This can be moved to all entities and here you just pass it a string or
								// something
		dialogues[0][0] = "You open the chest and find a " + loot.name + "!\n... but you cannot carry any more!";
		dialogues[1][0] = "You open the chest and find a " + loot.name + "!\nYou obtain the " + loot.name + "\"!";
		dialogues[2][0] = "It's empty.";
	}

	public void interact() {

		if (!opened) {
			playing.playSoundEffect(3);

			if (!playing.player.canObtainItem(loot)) {
				startDialogue(this, 0);
			} else {
				startDialogue(this, 1);
				down.set(0, down.get(1));
				opened = true;
			}
		} else {
			startDialogue(this, 2);
		}
	}
}
