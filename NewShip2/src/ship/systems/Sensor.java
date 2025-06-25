package ship.systems;

import static ship.helpz.Format.getModifierString;
import static ship.helpz.Format.getMoneyString;

import java.util.ArrayList;

import ship.enums.FireRange;

public class Sensor extends BaseSystem<SensorList> {
	private SensorType sensorType;
	private FireRange range;
	private int arcs, targeting;

	Sensor(SensorList system) {
		super(system, system.name);
		this.name = system.name;
		this.level = system.level;
		this.tech = system.tech;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.resizeable = true;
		this.sensorType = system.sensorType;
		this.range = system.range;
		this.arcs = system.arcs;
		this.targeting = system.targeting;
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
		properties.add(sensorType);
		properties.add(range);
		properties.add(arcs);
		properties.add(getModifierString(targeting));

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
	public ShipSystem<SensorList> createNewInstanceFromSelf() {
		return new Sensor(systemData);
	}
}
