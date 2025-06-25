package ship.systems;


import java.util.ArrayList;

import ship.Tech;
import ship.enums.FireRange;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum SensorList implements SystemFactory<SensorList>{

	AirSpaceRadar(		"Air/Space Radar",		ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */1.0, Moneyz.money( 20, "K"), SensorType.ACTIVE,  new FireRange( 5,10,20), 1, 0),
	EMDetector(			"EM Detector",			ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money( 20, "K"), SensorType.PASSIVE, new FireRange(10,20,60), 2, 3),
	HiResVideo(			"Hi-Res Video",			ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money( 10, "K"), SensorType.PASSIVE, new FireRange( 1, 2,10), 1, 2),
	IRDetector(			"IR Detector",			ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money( 20, "K"), SensorType.PASSIVE, new FireRange( 2, 4, 8), 1, 2),
	Ladar(				"Ladar",				ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(100, "K"), SensorType.ACTIVE,  new FireRange( 5,10,30), 1, 0),
	Probe(				"Probe",				ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(200, "K"), SensorType.REMOTE,  new FireRange( 0, 0, 0), 4, 0),

	MassDetector(		"Mass Detector",		ProgressLevel.PL7, Tech.G, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(100, "K"), SensorType.PASSIVE, new FireRange(10,30,50), 2, 1),
	MultibandRadar(		"Multiband Radar",		ProgressLevel.PL7, Tech.N, /*Hull*/ 0.5,/* Pow */1.0, Moneyz.money( 50, "K"), SensorType.ACTIVE,  new FireRange(10,20,40), 1, 0),
	Probeadvanced(		"Probe, Advanced",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(500, "K"), SensorType.REMOTE,  new FireRange( 0, 0, 0), 4, 0),
	RemoteNetwork(		"Remote Network",		ProgressLevel.PL7, Tech.C, /*Hull*/ 2.0,/* Pow */2.0, Moneyz.money(750, "K"), SensorType.REMOTE,  new FireRange( 0, 0, 0), 4, 0),
	Spectroanalyzer(	"Spectroanalyzer",		ProgressLevel.PL7, Tech.N, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(100, "K"), SensorType.ACTIVE,  new FireRange( 0, 0, 0), 1, 0),
	DriveDetectionArray("Drive Detection Array",ProgressLevel.PL7, Tech.G, /*Hull*/ 80.0,/* Pow */120.0, Moneyz.money(1000, "M"), SensorType.PASSIVE, new FireRange( 0, 0, 0), 4, 0),// ADD C Tech
	
	CEPassiveArray(		"CE Passive Array",		ProgressLevel.PL8, Tech.N, /*Hull*/ 2.0,/* Pow */1.0, Moneyz.money(  1, "M"), SensorType.PASSIVE,  new FireRange(30,60,100), 2, 0),
	DriveDetector(		"Drive Detector",		ProgressLevel.PL8, Tech.N, /*Hull*/ 4.0,/* Pow */4.0, Moneyz.money(500, "K"), SensorType.PASSIVE,  new FireRange( 0, 0, 0), 4, 0),
	MassRadar(			"Mass Radar",			ProgressLevel.PL8, Tech.G, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(200, "K"), SensorType.ACTIVE,   new FireRange(20,30,40), 1, 0),
	MultiphaseRadar(	"Multiphase Radar",		ProgressLevel.PL8, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(250, "K"), SensorType.ACTIVE,   new FireRange(20,40,80), 1, 0),
	OmniscienceSphere(	"Omniscience Sphere",	ProgressLevel.PL8, Tech.N, /*Hull*/ 3.0,/* Pow */1.0, Moneyz.money(  2, "M"), SensorType.PASSIVE,  new FireRange(50,50,50), 4, 0);


	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost, hullCost;
	protected final int creditCost;
	
	protected final SensorType sensorType;
	protected final FireRange range;
	protected final int arcs;
	protected final int targeting;

	SensorList(String name, ProgressLevel progressLevel, Tech tech, double hullCost, double powerCost, int creditCost, SensorType sensorType, FireRange range, int arcs, int targeting) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = powerCost;
		this.creditCost = creditCost;
		this.hullCost = hullCost;
		this.sensorType = sensorType;
		this.range = range;
		this.arcs = arcs;
		this.targeting = targeting;
	}
	
	public static ArrayList<String> getListTitles(){
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Hull");
        titles.add("Power");
        titles.add("Cost");
        titles.add("Type");
        titles.add("Range");
        titles.add("Arcs");
        titles.add("Targeting");
        return titles;
	}
	
	public static ArrayList<ShipSystem<SensorList>> getList() {
		return SystemListUtilz.getAll(SensorList.class);
	}

	@Override
	public ShipSystem<SensorList> createInstance() {
		return new Sensor(this);
	}
}
