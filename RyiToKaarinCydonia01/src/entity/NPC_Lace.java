package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Lace extends Character {

	public NPC_Lace(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/lace_sheet");

		name = "Lace";
		muggleName = "Laura";
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
