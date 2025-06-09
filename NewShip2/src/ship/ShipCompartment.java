package ship;

import java.util.ArrayList;

import ship.systems.ShipSystem;

public class ShipCompartment {
	private ArrayList<ShipSystem> ShipObjects = new ArrayList<ShipSystem>();
	private String name;
	
	public ShipCompartment(Ship ship,String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ShipSystem> getShipObjects() {
		return ShipObjects;
	}

}
