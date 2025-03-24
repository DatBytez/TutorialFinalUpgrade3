package main;

import entity.NPC_Rissa;
import entity.NPC_Samol;
import entity.NPC_WizardKing;
import entity.NPC_Wrex;
import entity.NPC_Xeniz;
import monster.MON_GreenSlime;
import monster.MON_Orc;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Tent;
import scenes.Playing;
import tile_interactive.IT_DryTree;

public class AssetSetter {

	Playing playing;

	public AssetSetter(Playing playing) {
		this.playing = playing;
	}

	public void setObject() { // TODO Change this all to an array list\
		playing.objectList.clear();
		
		OBJ_Tent tent1 = new OBJ_Tent(playing);
		tent1.worldX = playing.tileSize * 38;
		tent1.worldY = playing.tileSize * 18;
		playing.objectListI.get(0).add(tent1);
		
		OBJ_Chest chest1 = new OBJ_Chest(playing);
		chest1.setLoot(new OBJ_Shield_Blue(playing));
		chest1.worldX = playing.tileSize * 30;
		chest1.worldY = playing.tileSize * 37;
		playing.objectListI.get(0).add(chest1);
		
		OBJ_Door door1 = new OBJ_Door(playing);
		door1.worldX = playing.tileSize * 14;
		door1.worldY = playing.tileSize * 28;
		playing.objectListI.get(0).add(door1);
		
		OBJ_Door door2 = new OBJ_Door(playing);
		door2.worldX = playing.tileSize * 10;
		door2.worldY = playing.tileSize * 12;
		playing.objectListI.get(0).add(door2);
		
		OBJ_Coin_Bronze coin6 = new OBJ_Coin_Bronze(playing);
		coin6.worldX = playing.tileSize * 27;
		coin6.worldY = playing.tileSize * 27;
		playing.objectListI.get(0).add(coin6);
		
		OBJ_Coin_Bronze coin8 = new OBJ_Coin_Bronze(playing);
		coin8.worldX = playing.tileSize * 12;
		coin8.worldY = playing.tileSize * 20;
		playing.objectListI.get(0).add(coin8);
		
		OBJ_Key key2 = new OBJ_Key(playing);
		key2.worldX = playing.tileSize * 12;
		key2.worldY = playing.tileSize * 9;
		playing.objectListI.get(1).add(key2);
		
		OBJ_Key key3 = new OBJ_Key(playing);
		key3.worldX = playing.tileSize * 8;
		key3.worldY = playing.tileSize * 9;
		playing.objectListI.get(1).add(key3);
		
		OBJ_Axe axe = new OBJ_Axe(playing);
		axe.worldX = playing.tileSize * 11;
		axe.worldY = playing.tileSize * 32;
		playing.objectListI.get(0).add(axe);
		
		OBJ_Shield_Blue shieldBlue = new OBJ_Shield_Blue(playing);
		shieldBlue.worldX = playing.tileSize * 35;
		shieldBlue.worldY = playing.tileSize * 21;
		playing.objectListI.get(0).add(shieldBlue);
		
		OBJ_Potion_Red potion = new OBJ_Potion_Red(playing);
		potion.worldX = playing.tileSize * 22;
		potion.worldY = playing.tileSize * 24;
		playing.objectListI.get(0).add(potion);
		
		OBJ_Heart heart = new OBJ_Heart(playing);
		heart.worldX = playing.tileSize * 22;
		heart.worldY = playing.tileSize * 29;
		playing.objectListI.get(0).add(heart);
		
		OBJ_ManaCrystal mana = new OBJ_ManaCrystal(playing);
		mana.worldX = playing.tileSize * 22;
		mana.worldY = playing.tileSize * 31;
		playing.objectListI.get(0).add(mana);
	}

	public void setNPC() {
		playing.npcList.clear();
		
		NPC_WizardKing wizardKing = new NPC_WizardKing(playing);
		wizardKing.worldX = playing.tileSize * 21;
		wizardKing.worldY = playing.tileSize * 21;
		playing.npcListI.get(0).add(wizardKing);
		
		NPC_Wrex wrex = new NPC_Wrex(playing);
		wrex.worldX = playing.tileSize * 22;
		wrex.worldY = playing.tileSize * 7;
		playing.npcListI.get(0).add(wrex);
		
		NPC_Rissa rissa = new NPC_Rissa(playing);
		rissa.worldX = playing.tileSize * 12;
		rissa.worldY = playing.tileSize * 6;
		playing.npcListI.get(1).add(rissa);
		
		NPC_Samol samol = new NPC_Samol(playing);
		samol.worldX = playing.tileSize * 38;
		samol.worldY = playing.tileSize * 9;
		playing.npcListI.get(0).add(samol);
		
		NPC_Xeniz Xeniz = new NPC_Xeniz(playing);
		Xeniz.worldX = playing.tileSize * 10;
		Xeniz.worldY = playing.tileSize * 8;
		playing.npcListI.get(0).add(Xeniz);
	}
	
	public void setMonster() {
		playing.monsterList.clear();
		
		MON_GreenSlime greenSlime = new MON_GreenSlime(playing);
		greenSlime.worldX = playing.tileSize * 23;
		greenSlime.worldY = playing.tileSize * 36;
		playing.monsterList.add(greenSlime);
		
		MON_GreenSlime greenSlime2 = new MON_GreenSlime(playing);
		greenSlime2.worldX = playing.tileSize * 25;
		greenSlime2.worldY = playing.tileSize * 39;
		playing.monsterList.add(greenSlime2);
		
		MON_GreenSlime greenSlime3 = new MON_GreenSlime(playing);
		greenSlime3.worldX = playing.tileSize * 21;
		greenSlime3.worldY = playing.tileSize * 38;
		playing.monsterList.add(greenSlime3);
		
		MON_GreenSlime greenSlime4 = new MON_GreenSlime(playing);
		greenSlime4.worldX = playing.tileSize * 35;
		greenSlime4.worldY = playing.tileSize * 42;
		playing.monsterList.add(greenSlime4);
		
		MON_GreenSlime greenSlime5 = new MON_GreenSlime(playing);
		greenSlime5.worldX = playing.tileSize * 34;
		greenSlime5.worldY = playing.tileSize * 40;
		playing.monsterList.add(greenSlime5);
		
		MON_GreenSlime greenSlime6 = new MON_GreenSlime(playing);
		greenSlime6.worldX = playing.tileSize * 38;
		greenSlime6.worldY = playing.tileSize * 42;
		playing.monsterList.add(greenSlime6);
		
		MON_Orc orc = new MON_Orc(playing);
		orc.worldX = playing.tileSize * 12;
		orc.worldY = playing.tileSize * 33;
		playing.monsterList.add(orc);
	}
	
	public void setInteractiveTile() {
		playing.iTile.clear();
		
		playing.iTile.add(new IT_DryTree(playing,26,7));
		playing.iTile.add(new IT_DryTree(playing,27,7));
		playing.iTile.add(new IT_DryTree(playing,28,7));
		playing.iTile.add(new IT_DryTree(playing,29,7));
		playing.iTile.add(new IT_DryTree(playing,30,7));
		playing.iTile.add(new IT_DryTree(playing,31,7));
		playing.iTile.add(new IT_DryTree(playing,32,7));
		playing.iTile.add(new IT_DryTree(playing,33,7));
		
		playing.iTile.add(new IT_DryTree(playing,38,13));
		playing.iTile.add(new IT_DryTree(playing,38,14));
		playing.iTile.add(new IT_DryTree(playing,38,15));
		playing.iTile.add(new IT_DryTree(playing,38,16));
		playing.iTile.add(new IT_DryTree(playing,38,17));
		
		playing.iTile.add(new IT_DryTree(playing,25,27));
		playing.iTile.add(new IT_DryTree(playing,26,27));
	}

}
