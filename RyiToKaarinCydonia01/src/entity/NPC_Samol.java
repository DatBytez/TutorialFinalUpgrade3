package entity;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import scenes.GameState;
import scenes.Playing;

public class NPC_Samol extends Character {

	public NPC_Samol(Playing playing) {
		super(playing);

//		hitbox = setHitbox(playing.tileSize / 3,playing.tileSize / 2,playing.tileSize / 3,playing.tileSize / 2);
//		speed = 1;
		name = "Samol";
		muggleName = "Sam";

		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {

		texture = ImageLoader.loadImage("/textures/samol_sheet");
		sheet = new SpriteSheet(texture);

		for(int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * playing.tileSize, 8 * playing.tileSize, playing.tileSize, playing.tileSize));
			left.add(sheet.crop(i * playing.tileSize, 9 * playing.tileSize, playing.tileSize, playing.tileSize));
			down.add(sheet.crop(i * playing.tileSize, 10 * playing.tileSize, playing.tileSize, playing.tileSize));
			right.add(sheet.crop(i * playing.tileSize, 11 * playing.tileSize, playing.tileSize, playing.tileSize));
		}
	}

	public void setDialogue() {
		dialogues[0][0] = "Well hello! \nWelcome to the tent of the Order!";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Potion_Red(playing));
		inventory.add(new OBJ_Key(playing));
		inventory.add(new OBJ_Shield_Blue(playing));
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
