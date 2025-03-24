package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Pamplemousse extends Character {

	public NPC_Pamplemousse(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/pamplemousse_sheet");

		name = "Pamplemousse";
		//name = "??";
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
