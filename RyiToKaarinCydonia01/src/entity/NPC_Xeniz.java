package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Xeniz extends Character {

	public NPC_Xeniz(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/xeniz_sheet");

		name = "Xeniz";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Give me Dialoge";
		dialogues[0][1] = "Give me Dialoge2";
		dialogues[0][2] = "Give me Dialoge3";
		dialogues[0][3] = "Give me Dialoge4";
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
