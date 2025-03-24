package entity;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import scenes.Playing;

public class NPC_Wrex extends Entity {

	public NPC_Wrex(Playing playing) {
		super(playing);

//		hitbox = setHitbox(playing.tileSize / 3,playing.tileSize / 2,playing.tileSize / 3,playing.tileSize / 2);
//		speed = 1;

		getImage();
		setDialogue();
	}

	public void getImage() {

		texture = ImageLoader.loadImage("/textures/wrex_sheet");
		sheet = new SpriteSheet(texture);

		for(int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * playing.tileSize, 8 * playing.tileSize, playing.tileSize, playing.tileSize));
			left.add(sheet.crop(i * playing.tileSize, 9 * playing.tileSize, playing.tileSize, playing.tileSize));
			down.add(sheet.crop(i * playing.tileSize, 10 * playing.tileSize, playing.tileSize, playing.tileSize));
			right.add(sheet.crop(i * playing.tileSize, 11 * playing.tileSize, playing.tileSize, playing.tileSize));
		}
	}

	public void setDialogue() {
		dialogues[0][0] = "Hey Sweetling!";
		dialogues[0][1] = "You are so beautiful today!";
		dialogues[0][2] = "Te Amo!";
		dialogues[0][3] = "Have you been drinking water?\nI hear the lake has healing properties!"; //TODO need to impliment actual text wrapping
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
