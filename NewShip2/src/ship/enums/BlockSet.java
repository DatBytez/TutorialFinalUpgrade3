package ship.enums;

public class BlockSet {
	
	private int LiMin, LiMax, HiMin, HiMax, EnMin, EnMax;
	
	public BlockSet(int LiMin, int LiMax, int HiMin, int HiMax, int EnMin, int EnMax) {
		this.LiMin = LiMin;
		this.LiMax = LiMax;
		this.HiMin = HiMin;
		this.HiMax = HiMax;
		this.EnMin = EnMin;
		this.EnMax = EnMax;
	}

	public int getLiMin() {
		return LiMin;
	}

	public int getLiMax() {
		return LiMax;
	}

	public int getHiMin() {
		return HiMin;
	}

	public int getHiMax() {
		return HiMax;
	}

	public int getEnMin() {
		return EnMin;
	}

	public int getEnMax() {
		return EnMax;
	}
	
	@Override
	public String toString() {
	    return (LiMin + "-" + LiMax + " / " + HiMin + "-" + HiMax + " / " + EnMin + "-" + EnMax);
	}
}
