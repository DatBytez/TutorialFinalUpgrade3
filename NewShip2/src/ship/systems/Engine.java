package ship.systems;

import static ship.helpz.Format.*;

import java.util.ArrayList;

public class Engine extends BaseSystem<EngineList> {
	
	Engine(EngineList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.costPerHull = system.costPerHull;
		this.minSize = system.minSize;
		this.fuelRequired = system.fuelRequired;
		this.fuelCost = system.fuelCost;
		this.fuelEfficiency = system.fuelEfficiency;
		this.resizeable = true;
		this.hullCost = system.minSize;
	}
	
	@Override
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add(tech);
		properties.add(powerCost);
		properties.add(String.valueOf(minSize));
		properties.add(getMoneyString(creditCost));
		properties.add(getMoneyString(costPerHull));
		properties.add(getDashedString(getMoneyString(fuelCost)));
		properties.add(getDashedString(String.valueOf(fuelEfficiency)));

		return properties;
	}
	
	@Override
	public int getCalculatedHullCost(Hull hull) {
		return hullCost;
	}

	@Override
	public int getCalculatedCost(Hull hull) {
		return (int) ((hullCost * costPerHull) + creditCost);
	}
	
	@Override
	public void decHullPoint() {
		if(!(hullCost <= minSize))
		this.hullCost -= 1;
	}
	
	public double getCalculatedPowerCost() {
		return (int) Math.ceil(powerCost * hullCost);
	}

	@Override
	public ShipSystem<EngineList> createNewInstanceFromSelf() {
		return new Engine(systemData);
	}
}
