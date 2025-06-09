package ship.systems;

import static helpz.Format.getMoneyString;

import java.util.ArrayList;

public class Defense extends BaseSystem<DefenseList> {
	
	private int coverage;

	public Defense(DefenseList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.creditCost = system.creditCost;
		this.resizeable = false;
		this.coverage = system.coverage;
		this.hullCost = system.hullCost;
		this.powerCost = system.powerCost;
	}

	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add((tech));
		properties.add(powerCost);
		properties.add(hullCost);
		properties.add(getMoneyString(creditCost));
		properties.add(coverage);

		return properties;
	}

//	public double getHullCost() {
//		switch (armorType) {
//		case LIGHT:
//			return 2.5;
//		case MEDIUM:
//			return 5;
//		case HEAVY:
//			return 10;
//		case SUPERHEAVY:
//			return 20;
//		default:
//			return 0;
//		}
//	}

	public int getCalculatedHullCost(Hull hull) {
		return (hullCost);
	}

	public int getCalculatedCost(Hull hull) {
		return (creditCost);
	}

	@Override
	public ShipSystem<DefenseList> createNewInstanceFromSelf() {
		return new Defense(systemData);
	}
}