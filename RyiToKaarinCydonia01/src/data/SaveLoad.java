package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;
import scenes.Playing;

public class SaveLoad {
	
	Playing playing;
	
	public SaveLoad(Playing playing) {
		this.playing = playing;
	}
	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			DataStorage ds = new DataStorage();
			
			// SAVE PLAYER STATS
			ds.level = playing.player.level;
			ds.maxLife = playing.player.maxLife;
			ds.life = playing.player.life;
			ds.maxMana = playing.player.maxMana;
			ds.mana = playing.player.mana;
			ds.strength = playing.player.strength;
			ds.dexterity = playing.player.dexterity;
			ds.exp = playing.player.exp;
			ds.nextLevelExp = playing.player.nextLevelExp;
			ds.coin = playing.player.coin;
			
			// SAVE PLAYER INVENTORY
			for(int i = 0; i < playing.player.inventory.size(); i++) {
				ds.itemNames.add(playing.player.inventory.get(i).name);
				ds.itemAmounts.add(playing.player.inventory.get(i).amount);
			}
			
			// SAVE PLAYER EQUIPMENT
			ds.currentWeaponSlot = playing.player.getCurrentWeaponSlot();
			ds.currentShieldSlot = playing.player.getCurrentShieldSlot();
			
			// SAVE OBJECTS ON MAP  // TODO: Figure out why the list lengths don't adjust correctly when loading
			ds.mapObjectNames = new ArrayList<>();
			ds.mapObjectWorldX = new int[playing.maxMap][playing.objectList.size()+20];
//			System.out.println("mapObject: "+ds.mapObjectWorldX[0].length);
//			System.out.println("objList: "+gamePanel.objectList.size());
			ds.mapObjectWorldY = new int[playing.maxMap][playing.objectList.size()+20];
			ds.mapObjectLootNames = new String[playing.maxMap][playing.objectList.size()+20];
			ds.mapOjbectOpened = new boolean[playing.maxMap][playing.objectList.size()+20];
			
			for(int mapNum = 0; mapNum < playing.maxMap; mapNum++) {
				
				for(int i = 0; i < playing.objectList.size(); i++) {
					
					if(playing.objectList.get(i) == null) {
//						ds.mapObjectNames.set(i, "NA");
						ds.mapObjectNames.add("NA");
					}
					else {
						ds.mapObjectNames.add(playing.objectList.get(i).name);
						ds.mapObjectWorldX[mapNum][i] = playing.objectList.get(i).worldX;
						ds.mapObjectWorldY[mapNum][i] = playing.objectList.get(i).worldY;
						if(playing.objectList.get(i).loot != null) {
							ds.mapObjectLootNames[mapNum][i] = playing.objectList.get(i).loot.name;
						}
						ds.mapOjbectOpened[mapNum][i] = playing.objectList.get(i).opened;
					}
				}
			}
			
			// Write the DataStorage object
			oos.writeObject(ds);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			
			// Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();
			
			// LOAD PLAYER STATS
			playing.player.level = ds.level;
			playing.player.maxLife = ds.maxLife;
			playing.player.life = ds.life;
			playing.player.maxMana = ds.maxMana;
			playing.player.mana = ds.mana;
			playing.player.strength = ds.strength;
			playing.player.dexterity = ds.dexterity;
			playing.player.exp = ds.exp;
			playing.player.nextLevelExp = ds.nextLevelExp;
			playing.player.coin = ds.coin;
			
			// LOAD PLAYER INVENTORY
			playing.player.inventory.clear();
			for(int i = 0; i < ds.itemNames.size(); i++) {
				playing.player.inventory.add(playing.eGenerator.getObject(ds.itemNames.get(i)));
				playing.player.inventory.get(i).amount = ds.itemAmounts.get(i);
			}
			
			// LOAD PLAYER EQUIPMENT
			playing.player.currentWeapon = playing.player.inventory.get(ds.currentWeaponSlot);
			playing.player.currentShield = playing.player.inventory.get(ds.currentShieldSlot);
			playing.player.getAttack();
			playing.player.getDefense();
			playing.player.getAttackImage();
			playing.player.getGuardImage();
			
			// LOAD OBJEXTS ON MAP
			for(int mapNum = 0; mapNum < playing.maxMap; mapNum++) {
				
				for(int i = 0; i < playing.objectList.size(); i++) {
//					System.out.println("objectList.size: "+gamePanel.objectList.size());
//					System.out.println("objectNames.size: "+ds.mapObjectNames.size());
//					System.out.println("mapOpjects.size: "+ds.mapObjectWorldX[0].length);
					if(ds.mapObjectNames.get(i).equals("NA")) {
						playing.objectList.set(i, null);
					}
					else 
					{
						playing.objectList.set(i, playing.eGenerator.getObject(ds.mapObjectNames.get(i)));
						playing.objectList.get(i).worldX = ds.mapObjectWorldX[mapNum][i];
						playing.objectList.get(i).worldY = ds.mapObjectWorldY[mapNum][i];
						if(ds.mapObjectLootNames[mapNum][i] != null) {
							playing.objectList.get(i).loot = playing.eGenerator.getObject(ds.mapObjectLootNames[mapNum][i]);
							
						}
						playing.objectList.get(i).opened = ds.mapOjbectOpened[mapNum][i];
						if(playing.objectList.get(i).opened) {
							playing.objectList.get(i).down.set(0, playing.objectList.get(i).down.get(1));
						}
					}
				}
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
