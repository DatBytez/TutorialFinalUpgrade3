package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_DreDay extends Character {

	public NPC_DreDay(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/dreday_sheet");

		name = "DreDay";
		muggleName = "Andre";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Welcome to Wizard Party!";	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
