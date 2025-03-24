package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Temp extends Character {

	public NPC_Temp(Playing	 playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/_sheet");

		name = "Temp";
		muggleName = "Temp";
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
