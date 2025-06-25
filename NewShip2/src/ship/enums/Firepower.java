package ship.enums;

public enum Firepower {
	ORDINARY(0), GOOD(1), SMALL(2), LIGHT(3), MEDIUM(4), HEAVY(5), SUPERHEAVY(6);

	public int value;

	private Firepower(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		switch (this) {
		case ORDINARY:
			return "O";
		case GOOD:
			return "G";
		case SMALL:
			return "S";
		case LIGHT:
			return "L";
		case MEDIUM:
			return "M";
		case HEAVY:
			return "H";
		case SUPERHEAVY:
			return "SH";
		default:
			return super.toString(); // Fallback to default toString if needed
		}
	}
}
