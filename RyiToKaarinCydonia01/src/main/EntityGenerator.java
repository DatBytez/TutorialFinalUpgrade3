package main;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Rock;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import scenes.Playing;

public class EntityGenerator {

	Playing playing; // TODO: Probably don't need this

	public EntityGenerator(Playing playing) {
		this.playing = playing;
	}

	public Entity getObject(String itemName) {
		Entity obj = null;

		switch (itemName) {// TODO: can you call OBJ_Axe.getName() as a case? if so, can we do this in a
							// loop for every item the player owns?
		case OBJ_Axe.objName:
			obj = new OBJ_Axe(playing);
			break;
		case OBJ_Boots.objName:
			obj = new OBJ_Boots(playing);
			break;
		case OBJ_Chest.objName:
			obj = new OBJ_Chest(playing);
			break;
		case OBJ_Coin_Bronze.objName:
			obj = new OBJ_Coin_Bronze(playing);
			break;
		case OBJ_Door.objName:
			obj = new OBJ_Door(playing);
			break;
		case OBJ_Fireball.objName:
			obj = new OBJ_Fireball(playing);
			break;
		case OBJ_Heart.objName:
			obj = new OBJ_Heart(playing);
			break;
		case OBJ_Key.objName:
			obj = new OBJ_Key(playing);
			break;
		case OBJ_Lantern.objName:
			obj = new OBJ_Lantern(playing);
			break;
		case OBJ_ManaCrystal.objName:
			obj = new OBJ_ManaCrystal(playing);
			break;
		case OBJ_Potion_Red.objName:
			obj = new OBJ_Potion_Red(playing);
			break;
		case OBJ_Rock.objName:
			obj = new OBJ_Rock(playing);
			break;
		case OBJ_Shield_Blue.objName:
			obj = new OBJ_Shield_Blue(playing);
			break;
		case OBJ_Shield_Wood.objName:
			obj = new OBJ_Shield_Wood(playing);
			break;
		case OBJ_Sword_Normal.objName:
			obj = new OBJ_Sword_Normal(playing);
			break;
		case OBJ_Tent.objName:
			obj = new OBJ_Tent(playing);
			break;
		}
		return obj;
	}

}
