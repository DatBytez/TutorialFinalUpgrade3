package ship.systems;


import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum MiscellaneousList implements SystemFactory<MiscellaneousList>{
	
	Airlock(			"Airlock",						ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 10, "K"), MiscellaneousType.HANGER),
	Brig(				"Brig",							ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money( 20, "K"), MiscellaneousType.ACCOM),
	CargoSpace(			"Cargo Space",					ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 10, "K"), MiscellaneousType.CARGO),
	CargoBay(			"Cargo Bay",					ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money( 20, "K"), MiscellaneousType.CARGO),
	CargoHold(			"Cargo Hold",					ProgressLevel.PL6, Tech.N, /*Hull*/ 3.0,/* Pow */0.0, Moneyz.money( 50, "K"), MiscellaneousType.CARGO),
	DockingClamps(		"Docking Clamps",				ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money( 50, "K"), MiscellaneousType.HANGER),
	EscapePod(			"Escape Pod",					ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 50, "K"), MiscellaneousType.HANGER),
	FuelCollectors(		"Fuel Collectors",				ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money(100, "K"), MiscellaneousType.FUEL),
	Hangar(				"Hangar",						ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(100, "K"), MiscellaneousType.HANGER),
	LabSection(			"Lab Section",					ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money(100, "K"), MiscellaneousType.ACCOM),
	Magazine(			"Magazine",						ProgressLevel.PL6, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 50, "K"), MiscellaneousType.MISC),
	ReentryCapsule(		"Reentry Capsule",				ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money(  5, "K"), MiscellaneousType.HANGER),
	SickBay(			"Sick Bay",						ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money(150, "K"), MiscellaneousType.MISC),
	Workshop(			"Workshop",						ProgressLevel.PL6, Tech.N, /*Hull*/ 2.0,/* Pow */1.0, Moneyz.money( 20, "K"), MiscellaneousType.MISC),
	Accumulator(		"Accumulator",					ProgressLevel.PL7, Tech.S, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 40, "K"), MiscellaneousType.POWER),
	Autocargo(			"Autocargo",					ProgressLevel.PL7, Tech.N, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money( 30, "K"), MiscellaneousType.CARGO),
	BoardingPod(		"Boarding Pod",					ProgressLevel.PL7, Tech.N, /*Hull*/ 2.0,/* Pow */0.0, Moneyz.money(200, "K"), MiscellaneousType.HANGER),
	EvacSystem(			"Evac System",					ProgressLevel.PL7, Tech.N, /*Hull*/ 4.0,/* Pow */0.0, Moneyz.money(250, "K"), MiscellaneousType.HANGER),
	Extrapods(			"Extra pods",					ProgressLevel.PL7, Tech.N, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money( 50, "K"), MiscellaneousType.HANGER),
	FabricationFacility("Fabrication Facility",			ProgressLevel.PL7, Tech.N, /*Hull*/ 4.0,/* Pow */2.0, Moneyz.money(200, "K"), MiscellaneousType.MISC),
	OrdnanceTransferSystem("Ordnance Transfer System",	ProgressLevel.PL7, Tech.N, /*Hull*/ 2.0,/* Pow */2.0, Moneyz.money(150, "K"), MiscellaneousType.HANGER),
	SecuritySuite(		"Security Suite",				ProgressLevel.PL7, Tech.N, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(200, "K"), MiscellaneousType.CMD),
	Stabilizer(			"Stabilizer",					ProgressLevel.PL7, Tech.G, /*Hull*/ 0.5,/* Pow */1.0, Moneyz.money(200, "K"), MiscellaneousType.ENGINE),// ADD X Tech
	HolofieldBay(		"Holofield Bay",				ProgressLevel.PL8, Tech.Q, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(100, "K"), MiscellaneousType.MISC),// ADD C Tech
	NanomanufactureBay(	"Nanomanufacture Bay",			ProgressLevel.PL8, Tech.S, /*Hull*/ 4.0,/* Pow */4.0, Moneyz.money(500, "K"), MiscellaneousType.MISC);// ADD C Tech


	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost, hullCost;
	protected final int creditCost;
	protected final MiscellaneousType systemType;

	MiscellaneousList(String name, ProgressLevel progressLevel, Tech tech, double hullCost, double powerCost, int creditCost, MiscellaneousType systemType) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = powerCost;
		this.creditCost = creditCost;
		this.hullCost = hullCost;
		this.systemType = systemType;
	}
	
	public static ArrayList<String> getListTitles(){
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Hull");
        titles.add("Power");
        titles.add("Cost");
        titles.add("Type");
        return titles;
	}
	
	public static ArrayList<ShipSystem<MiscellaneousList>> getList() {
		return SystemListUtilz.getAll(MiscellaneousList.class);
	}

	@Override
	public ShipSystem<MiscellaneousList> createInstance() {
		return new Miscellaneous(this);
	}
}
