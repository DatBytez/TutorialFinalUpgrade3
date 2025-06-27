package ship.systems;

import java.util.ArrayList;

import ship.ShipCompartment;

public interface ShipSystem<T> {
	T getSystemData();
	String getName();
	String getDescription();
	ShipCompartment getCompartment();
	int getHullPoints();
	void setHullPoints(int points);
	double getPowerCost();
	int getBaseCost();
	boolean isResizeable();

	// SYSTEM-SPECIFIC
	ShipSystem<T> createNewInstanceFromSelf();
	ArrayList<Object> getProperties();
	int getCalculatedCost(Hull hull);
	int getCalculatedHullCost(Hull hull);

	// SYSTEM-SHARED
	void copySharedStateTo(ShipSystem<T> other);
	
	default double getCalculatedPowerCost() { return getPowerCost();	}

	default void incHullPoint() { setHullPoints(getHullPoints() + 1); }

	default void decHullPoint() { if (getHullPoints() > 1) setHullPoints(getHullPoints() - 1); }

	default ShipSystem<T> copy() {
		ShipSystem<T> copy = createNewInstanceFromSelf();
		copySharedStateTo(copy);
		return copy;
	}
	void setCompartment(ShipCompartment compartment);
}
