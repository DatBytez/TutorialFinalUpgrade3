package ship.enums;

public enum DamageType {
	LOWIMPACT, HIIMPACT, ENERGY, OTHER;

	@Override
	public String toString() {
		switch (this) {
		case LOWIMPACT:
			return "LI";
		case HIIMPACT:
			return "HI";
		case ENERGY:
			return "En";
		case OTHER:
			return "*";
		default:
			return super.toString(); // Fallback to default toString if needed
		}
	}
}
