package ship.systems;

import static helpz.Format.getMoneyString;

import java.util.ArrayList;

import shipWeapons.MiscellaneousType;

public class Miscellaneous extends BaseSystem<MiscellaneousList> {
	private MiscellaneousType systemType;

	Miscellaneous(MiscellaneousList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.resizeable = true;
		this.systemType = system.systemType;
	}
	
	@Override
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add(tech);
		properties.add(hullCost);
		properties.add(powerCost);
		properties.add(getMoneyString(creditCost));
		properties.add(systemType);

		return properties;
	}
	
	@Override
	public int getCalculatedHullCost(Hull hull) {
		return hullCost;
	}

	@Override
	public int getCalculatedCost(Hull hull) {
		return creditCost;
	}
	
	@Override
	public void decHullPoint() {
		if(!(hullCost <= minSize))
		this.hullCost -= 1;
	}
	
	public double getCalculatedPowerCost() {
		return (int) Math.ceil(powerCost);
	}

	@Override
	public ShipSystem<MiscellaneousList> createNewInstanceFromSelf() {
		return new Miscellaneous(systemData);
	}
}
