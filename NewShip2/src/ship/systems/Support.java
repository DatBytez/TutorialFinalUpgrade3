package ship.systems;

import static ship.helpz.Format.*;

import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;

public class Support extends BaseSystem<SupportList> {

	Support(SupportList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.resizeable = true;
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
	public ShipSystem<SupportList> createNewInstanceFromSelf() {
		return new Support(systemData);
	}
}
