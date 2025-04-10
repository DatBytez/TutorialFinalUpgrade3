package shipfight;

import java.util.ArrayList;

import helpz.MyShipObject;

public class ShipCompartment {
	private ArrayList<MyShipObject> ShipObjects = new ArrayList<MyShipObject>();
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

	public ArrayList<MyShipObject> getShipObjects() {
		return ShipObjects;
	}

}
