package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_ShilohCrowe extends Character {

	public NPC_ShilohCrowe(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/shilohcrowe_sheet");

		name = "Shiloh Crowe";
		//name = "Amber";
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
