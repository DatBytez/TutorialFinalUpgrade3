package ship.enums;

public enum Toughness {
	ORDINARY(0,"Ordinary"), GOOD(1,"Good"), SMALL(2,"Small"), LIGHT(3,"Light"), MEDIUM(4,"Medium"), HEAVY(5,"Heavy"), SUPERHEAVY(6,"Super");

	public int value;
	private String description;
	
	private Toughness(int value, String description) {
		this.value=value;
		this.description=description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
