package ship.enums;

public enum FireModes {
	F("Single-shot"),
	G("Battery fire"),
	B("Burst fire"),
	A("Automatic fire");
	
	public String description;
	
	FireModes(String description){
		this.description=description;
	}
	

}
