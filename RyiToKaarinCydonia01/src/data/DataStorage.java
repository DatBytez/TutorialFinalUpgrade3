package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
	
	// PLAYER STATS
	int level;
	int maxLife;
	int life;
	int maxMana;
	int mana;
	int strength;
	int dexterity;
	int exp;
	int nextLevelExp;
	int coin;
	
	// PLAYER INVENTORY
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmounts = new ArrayList<>();
	
	int currentWeaponSlot;
	int currentShieldSlot;
	
	// OBJECTS ON MAP
	ArrayList<String> mapObjectNames = new ArrayList<>();
	int mapObjectWorldX[][];
	int mapObjectWorldY[][];
	String mapObjectLootNames[][];
	boolean mapOjbectOpened[][];
	
}
