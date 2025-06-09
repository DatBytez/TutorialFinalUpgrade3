package ship.systems;

import java.util.ArrayList;

import ship.ProgressLevel;
import ship.Tech;

import static helpz.Format.*;

public class Computer extends BaseSystem<ComputerList> {
	private int modifier;

	Computer(ComputerList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.resizeable = true;
		this.modifier = system.modifier;
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
		properties.add(getModifierString(modifier));

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
	public ShipSystem<ComputerList> createNewInstanceFromSelf() {
		return new Computer(systemData);
	}
}
