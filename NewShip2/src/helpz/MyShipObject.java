package helpz;

import java.util.ArrayList;

public class MyShipObject {
	private ArrayList<Object> properties = new ArrayList<Object>();
	private String name;
	private boolean military = false;
	private int cost;
	private String description = "";
	
	public MyShipObject(String name,int cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public ArrayList<Object> getProperties(){
		return properties;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMilitary() {
		return military;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getCostString() {
		if(cost < 1000)
			return ("$" + cost + " K");
		else if(cost < 1000000)
			return ("$" + (cost/1000) + " M");
		else
			return ("$" + (cost/1000000) + " B");
	}
	
	public String getModifierString(int modifier) {
		if(modifier > 0)
			return ("+" + modifier);
		else
			return ("" + modifier);
	}
}
