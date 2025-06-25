package ship.systems;

import static ship.helpz.Format.*;

import java.util.ArrayList;

import actions.Rollz;
import ship.enums.BlockSet;
import ship.enums.DamageType;

public class Armor extends BaseSystem<ArmorList>{
	private ArmorType armorType;
	private BlockSet blockSet;
	
	public Armor(ArmorList system) {
		super(system, system.name);
		this.name = system.name;
		this.armorType = system.armorType;
		this.level = system.level;
		this.tech = system.tech;
		this.blockSet = system.blockSet;
		this.creditCost = system.baseCost;
		this.resizeable = false;
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

	@Override
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add((tech));
		properties.add(blockSet);
		properties.add(getHullCost());
		properties.add(getMoneyString(creditCost));

		return properties;
	}

	@Override
	public int getCalculatedHullCost(Hull hull) {
		return (int) (hull.getBaseHullPoints() * (getHullCost() * 0.01));
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

	@Override
	public int getCalculatedCost(Hull hull) { 
		if (getCalculatedHullCost(hull) > 1)
			return (1 * creditCost);
		else
			return (int) (getCalculatedHullCost(hull) * creditCost);
	}

	@Override
	public ShipSystem<ArmorList> createNewInstanceFromSelf() {
	    return new Armor(systemData);
	}
}