package shipHull;

public enum HullType {
	
	SMALL("Small"),LIGHT("Light"),MEDIUM("Medium"),HEAVY("Heavy"),SUPERHEAVY("Super Heavy");
	
	private String description;
	
	HullType(String description){
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
