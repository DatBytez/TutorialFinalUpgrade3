package shipfight;

public enum Severity {
	STUN, WOUND, MORTAL, CRITICAL, MISS;

	@Override
	public String toString() {
		switch (this) {
		case STUN:
			return "s";
		case WOUND:
			return "w";
		case MORTAL:
			return "m";
		case CRITICAL:
			return "c";
		case MISS:
			return "miss";
		default:
			return super.toString(); // Fallback to default toString if needed
		}

	}
}
