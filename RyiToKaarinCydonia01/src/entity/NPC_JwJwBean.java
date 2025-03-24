package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_JwJwBean extends Character {

	public NPC_JwJwBean(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/jwjwbean_sheet");

		name = "JwJw Bean";
		muggleName = "Julia";
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
