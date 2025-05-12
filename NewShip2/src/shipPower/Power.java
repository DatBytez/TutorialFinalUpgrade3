package shipPower;

import java.util.ArrayList;

import ship.ProgressLevel;
import ship.ShipSystem;
import ship.Tech;

import static helpz.Format.*;

public class Power extends ShipSystem {
	
	String name;
	ProgressLevel level;
	Tech tech;
	double power;
	int baseCost, costPerHull, minSize, fuelCost, fuelEfficiency;
	boolean fuelReq;
	
	Power(PowerList powerSystem) {
		super(powerSystem.name, powerSystem.cost);
		this.name = powerSystem.name;
		this.level = powerSystem.level;
		this.tech = powerSystem.tech;
		this.power = powerSystem.power;
		this.baseCost = powerSystem.baseCost;
		this.costPerHull = powerSystem.costPerHull;
		this.minSize = powerSystem.minSize;
		this.fuelReq = powerSystem.fuelReq;
		this.fuelCost = powerSystem.fuelCost;
		this.fuelEfficiency = powerSystem.fuelEfficiency;
	}
	
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(level));
		properties.add((tech));
		properties.add(getMoneyString(baseCost));
		properties.add(getMoneyString(costPerHull));
		properties.add(String.valueOf(minSize));
		properties.add(getBooleanString(fuelReq));
		properties.add(getDashedString(getMoneyString(fuelCost)));
		properties.add(getDashedString(String.valueOf(fuelEfficiency)));

		return properties;
	}
}
