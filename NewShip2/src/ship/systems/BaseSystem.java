package ship.systems;

import ship.ShipCompartment;
import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.DescriptionLoader;

public abstract class BaseSystem<T> implements ShipSystem<T> {
	protected T systemData;
	protected String name;
	protected String description;
	protected ShipCompartment compartment;
	protected int hullCost = 0;
	protected double powerCost = 0;
	protected int creditCost = 0;
	protected boolean resizeable = false;
	//
	protected ProgressLevel level;
	protected Tech tech;
	protected int costPerHull, minSize, fuelCost, fuelEfficiency;
	protected boolean fuelRequired;
	
	public BaseSystem(T systemData, String name) {
		this.systemData = systemData;
		this.description = DescriptionLoader.getDescription(name);
	}
	
	@Override public void setCompartment(ShipCompartment compartment) {
		this.compartment = compartment;
	}
	
//	public void addToCompartment(ShipCompartment compartment) { this.compartment = compartment;}

	@Override public T getSystemData() { return systemData; }
	@Override public String getName() { return name; }
	@Override public String getDescription() { return description; }
	@Override public ShipCompartment getCompartment() { return compartment; }
	@Override public int getHullPoints() { return hullCost; }
	@Override public void setHullPoints(int points) { this.hullCost = points; }
	@Override public double getPowerCost() { return powerCost; }
	@Override public int getBaseCost() { return creditCost; }
	@Override public boolean isResizeable() { return resizeable; }
	
	@Override
	public void copySharedStateTo(ShipSystem<T> other) {
		other.setHullPoints(this.hullCost);
	}
	
}
