package shipWeapons;

import ship.Severity;

public class DamageSet {
	
	private int aMin, aMax, gMin, gMax, oMin, oMax;
	private Severity aSev, gSev, oSev;
	
	public DamageSet(int oMin, int oMax, Severity oSev, int gMin, int gMax, Severity gSev, int aMin, int aMax, Severity aSev) {
		this.oMin = oMin;
		this.oMax = oMax;
		this.gMin = gMin;
		this.gMax = gMax;
		this.aMin = aMin;
		this.aMax = aMax;
		this.oSev = oSev;
		this.gSev = gSev;
		this.aSev = aSev;
	}

	public int getaMin() {
		return aMin;
	}

	public int getaMax() {
		return aMax;
	}

	public int getgMin() {
		return gMin;
	}

	public int getgMax() {
		return gMax;
	}

	public int getoMin() {
		return oMin;
	}

	public int getoMax() {
		return oMax;
	}

	public Severity getaSev() {
		return aSev;
	}

	public Severity getgSev() {
		return gSev;
	}

	public Severity getoSev() {
		return oSev;
	}
	
	@Override
	public String toString() {
//		int oMin, int oMax, Severity oSev, int gMin, int gMax, Severity gSev, int aMin, int aMax, Severity aSev
	    return (oMin + "-" + oMax + oSev + " / " + gMin + "-" + gMax + gSev + " / " + aMin + "-" + aMax + aSev);
	}

}
