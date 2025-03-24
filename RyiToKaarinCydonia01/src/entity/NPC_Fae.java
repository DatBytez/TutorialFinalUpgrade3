package entity;

import gfx.ImageLoader;
import object.OBJ_Axe;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import scenes.GameState;
import scenes.Playing;

public class NPC_Fae extends Character {

	public NPC_Fae(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/fae_beard_sheet");

		name = "Fae";
		getImage(texture);
		setPortrait();
		setDialogue();
		setItems();
	}

	public void setDialogue() {
		dialogues[0][0] = "Arf! Arf! Arf! \n\nWelcome to the BeardedEye!";
		dialogues[1][0] = "Spite and hate keep you young.";
		dialogues[2][0] = "God damn knife ears! \n\nWhat do you want?";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Sword_Normal(playing));
		inventory.add(new OBJ_Shield_Wood(playing));
		inventory.add(new OBJ_Axe(playing));
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
		
		playing.gameState = GameState.TRADE;
		playing.ui.npc = this;
	}
}
