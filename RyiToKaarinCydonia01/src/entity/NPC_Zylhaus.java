package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Zylhaus extends Character {

	public NPC_Zylhaus(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/zylhaus_sheet");

		name = "Zylhaus";
		muggleName = "Josh";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Hey!";
		dialogues[0][1] = "Welcome to Wizard Party!";
		dialogues[0][2] = "I'm from house Zylvendrake. What house are you from?";
		dialogues[0][3] = "Check with Josh Wash! He will get you started."; //TODO need to impliment actual text wrapping
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
