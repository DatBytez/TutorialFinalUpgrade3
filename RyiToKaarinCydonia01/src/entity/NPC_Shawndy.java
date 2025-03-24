package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Shawndy extends Character {

	public NPC_Shawndy(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/shawndy_sheet");

		name = "Shawndy";
		muggleName = "Shawndy";
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
