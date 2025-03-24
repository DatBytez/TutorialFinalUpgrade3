package entity;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import scenes.Playing;

public class NPC_PeterPants extends Character {

	public NPC_PeterPants(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/peterpants_sheet");

		name = "Peter Pants";
		muggleName = "Jonney";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Welcome to Wizard Party!";
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
