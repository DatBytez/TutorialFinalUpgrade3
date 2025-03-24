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

public class NPC_Rissa extends Character {

	public NPC_Rissa(Playing playing) {
		super(playing);

//		hitbox = setHitbox(playing.tileSize / 3,playing.tileSize / 2,playing.tileSize / 3,playing.tileSize / 2);
//		speed = 1; //TODO: Speed should be changed to a float. 1 is too fast to be the slowest speed.
		name = "Rissa";
		muggleName = "Chelle";

		getImage();
		setDialogue();
		setItems();
	}

	public void getImage() {

		texture = ImageLoader.loadImage("/textures/rissa_sheet");
		sheet = new SpriteSheet(texture);

		for(int i = 1; i <= 8; i++) {
			up.add(sheet.crop(i * playing.tileSize, 8 * playing.tileSize, playing.tileSize, playing.tileSize));
			left.add(sheet.crop(i * playing.tileSize, 9 * playing.tileSize, playing.tileSize, playing.tileSize));
			down.add(sheet.crop(i * playing.tileSize, 10 * playing.tileSize, playing.tileSize, playing.tileSize));
			right.add(sheet.crop(i * playing.tileSize, 11 * playing.tileSize, playing.tileSize, playing.tileSize));
		}
	}

	public void setDialogue() {//TODO: This could be put in a "seller" class and each npc just passes their name and their establishment
		dialogues[0][0] = "Hello! I'm Rissa Bakester\nWelcome to What the Fork! \nWhat can I interest you in?";
		dialogues[1][0] = "Please come again!";
		dialogues[2][0] = "You need more Kellans to buy that!";
		dialogues[3][0] = "You cannot carry any more!";
		dialogues[4][0] = "You cannot sell an equipped item!";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Potion_Red(playing));
		inventory.add(new OBJ_Key(playing));
		inventory.add(new OBJ_Shield_Wood(playing));
	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
		playing.gameState = GameState.TRADE;
		playing.ui.npc = this;
	}
}
