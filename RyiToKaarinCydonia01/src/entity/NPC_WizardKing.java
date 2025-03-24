package entity;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import scenes.Playing;

public class NPC_WizardKing extends Entity {

	public NPC_WizardKing(Playing playing) {
		super(playing);

//		hitbox = setHitbox(gamePanel.tileSize / 3,gamePanel.tileSize / 2,gamePanel.tileSize / 3,gamePanel.tileSize / 2);
//		speed = 1;
		
		dialogueSet = -1; //A little janky but I think it's the easiest solution.

		getImage();
		setDialogue();
	}

	public void getImage() {

		texture = ImageLoader.loadImage("/textures/wizard_king_sheet");
		sheet = new SpriteSheet(texture);

		for(int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * playing.tileSize, 8 * playing.tileSize, playing.tileSize, playing.tileSize));
			left.add(sheet.crop(i * playing.tileSize, 9 * playing.tileSize, playing.tileSize, playing.tileSize));
			down.add(sheet.crop(i * playing.tileSize, 10 * playing.tileSize, playing.tileSize, playing.tileSize));
			right.add(sheet.crop(i * playing.tileSize, 11 * playing.tileSize, playing.tileSize, playing.tileSize));
		}
	}

	public void setDialogue() {
		dialogues[0][0] = "Hello! and Welcome to Cydonia!";
		dialogues[0][1] = "Have you signed the waiver?";
		dialogues[0][2] = "Oh you have! Then please, Enjoy the libations! \nbut remember\nDon't be a dick!";
		
		dialogues[1][0] = "If you become tired, use a tent to sleep until the next day.";
		dialogues[1][1] = "You can also drink from the lake to recover your health.";
		dialogues[1][2] = "Be careful however!\nDoing so will make all of the monsters reappear.";
		
		dialogues [2][0] = "You will need a key to open the door!";
	}

	public void setAction() {
		
		if(onPath) {
//			int goalCol = 10;
//			int goalRow = 8;
			
			int goalCol = (playing.player.worldX + playing.player.hitbox.x)/playing.tileSize;
			int goalRow = (playing.player.worldY + playing.player.hitbox.y)/playing.tileSize;
			
			searchPath(goalCol,goalRow);
		}
		else {
			moveRandom();
		}
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {//TODO: Could be turned into a trait for each npc that talks, and could be auto set to always repeat last unless changed.
//			dialogueSet = 0; // Start from first statement.
			dialogueSet--; // Repeat the last statement.
		}
	}
}
