package shipWeapons;

public class FireRange {
	
	int shortRange,mediumRange,longRange;
	
	public FireRange(int shortRange,int mediumRange,int longRange) {
		this.shortRange = shortRange;
		this.mediumRange = mediumRange;
		this.longRange = longRange;
	}
	
	@Override
	public String toString() {
	    return (shortRange + " / " + mediumRange + " / " + longRange);
	}
}
