package shipArmor;

import java.util.ArrayList;

import helpz.ShipSystem;
import ship.ProgressLevel;
import ship.Tech;
import shipHelperz.Rollz;
import shipWeapons.DamageType;

import static helpz.Format.*;

public class Armor extends ShipSystem {
	String name, description;
	ArmorType armorType;
	ProgressLevel level;
	Tech tech;
	BlockSet blockSet;
	int cost;

	public Armor(ArmorList armor) {
		super(armor.name, armor.cost);
		this.name = armor.name;
		this.armorType = armor.armorType;
		this.level = armor.level;
		this.tech = armor.tech;
		this.blockSet = armor.blockSet;
		this.cost = armor.cost;
		this.description = armor.description;
	}

	public int getBlock(DamageType damageType) {
		int block = 0;

		switch (damageType) {
		case LOWIMPACT:
			block = Rollz.roll(blockSet.getLiMin(), blockSet.getLiMax());
			break;
		case HIIMPACT:
			block = Rollz.roll(blockSet.getHiMin(), blockSet.getHiMax());
			break;
		case ENERGY:
			block = Rollz.roll(blockSet.getEnMin(), blockSet.getEnMax());
			break;
		case OTHER:
			block = 0;
			break;
		}

		System.out.println(name + " blocks " + block + " " + damageType + " damage.");
		return block;
	}

	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add((tech));
		properties.add(blockSet);
		properties.add(getHullCost());
		properties.add(getMoneyString(cost));

		return properties;
	}

	public double getHullCost() {
		switch (armorType) {
		case LIGHT:
			return 2.5;
		case MEDIUM:
			return 5;
		case HEAVY:
			return 10;
		case SUPERHEAVY:
			return 20;
		default:
			return 0;
		}
	}
}