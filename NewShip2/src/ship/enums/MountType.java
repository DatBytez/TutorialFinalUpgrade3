package ship.enums;

public enum MountType {
	STANDARD(1.0, 1.0),FIXED(0.75, 0.75),SPONSON(1.0, 1.25),TURRET(1.25, 1.25),BANK(1.0, 1.25);
	
	public double hullCost, creditCost;
	
	MountType(double hullCost, double creditCost){
		this.hullCost = hullCost;
		this.creditCost = creditCost;
	}
}
