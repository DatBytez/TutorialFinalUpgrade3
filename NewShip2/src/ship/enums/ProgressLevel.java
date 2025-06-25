package ship.enums;

public enum ProgressLevel {
PL6("Fusion Age",6),
PL7("Gravity Age",7),
PL8("Energy Age",8),
PL9("Matter Age",9);
	
	public String description;
	public int value;
	
	ProgressLevel(String description, int value){
		this.description = description;
		this.value = value;
	}

}
