package shipWeapons;

public class WeaponModes {
	
	boolean singleShot,batteryFire,burstFire,automaticFire;
	
	public WeaponModes(boolean singleShot,boolean batteryFire,boolean burstFire, boolean automaticFire) {
		this.singleShot = singleShot;
		this.batteryFire = batteryFire;
		this.burstFire = burstFire;
		this.automaticFire = automaticFire;
	}
	
	@Override
	public String toString() {
		String modes = "";
		
		if(singleShot)
			modes += "F";
		if(batteryFire)
			modes += "G";
		if(burstFire)
			modes += "B";
		if(automaticFire)
			modes += "A";
			
		return modes;
	}
	

}
