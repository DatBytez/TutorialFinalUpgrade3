package ship.systems;

public enum ArmorType {
	LIGHT(.00),
	MEDIUM(.05),
	HEAVY(.10),
	SUPERHEAVY(.20);
	
	public double value;
	
	ArmorType(double value){
		this.value=value;
	}
}
