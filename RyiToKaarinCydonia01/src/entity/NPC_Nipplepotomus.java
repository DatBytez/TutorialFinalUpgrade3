package entity;

import gfx.ImageLoader;
import scenes.Playing;

public class NPC_Nipplepotomus extends Character {

	public NPC_Nipplepotomus(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/susi_sheet");

		name = "Nipplepotomus";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = "Rawr!";
//		dialogues[0][1] = "You are so beautiful today!";
//		dialogues[0][2] = "Te Amo!";
//		dialogues[0][3] = "Have you been drinking water?\nI hear the lake has healing properties!"; //TODO need to impliment actual text wrapping
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
	}
}
