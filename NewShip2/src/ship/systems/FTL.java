package ship.systems;

import java.util.ArrayList;

import ship.ProgressLevel;
import ship.Tech;

import static helpz.Format.*;

public class FTL extends BaseSystem<FTLList> {

	FTL(FTLList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.costPerHull = system.costPerHull;
		this.minSize = system.minSize;
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
		properties.add(getMoneyString(creditCost));
		properties.add(getMoneyString(costPerHull));
		properties.add(String.valueOf(minSize));

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
	public ShipSystem<FTLList> createNewInstanceFromSelf() {
		return new FTL(systemData);
	}
}
