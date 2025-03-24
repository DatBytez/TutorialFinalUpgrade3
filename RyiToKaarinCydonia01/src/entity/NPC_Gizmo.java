package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Gizmo extends Character {

	public NPC_Gizmo(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/gizmo_sheet");

		name = "Gizmo";
		muggleName = "Dennis";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Welcome to Wizard Party!";
		dialogues[1][0] = "I bought this magic potion from across the seas!";
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
