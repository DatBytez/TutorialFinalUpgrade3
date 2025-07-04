package ship.systems;

import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.enums.Toughness;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;

public enum HullList implements SystemFactory<HullList>{
	
	// Civilian Hulls
	None(			"None", 			false, HullType.SMALL, 0, Toughness.GOOD,	0, 0,  0, Moneyz.money(0, "K"),0),
	Launch(			"Launch", 			false, HullType.SMALL, 8, Toughness.GOOD,	3, 4,  2, Moneyz.money(300, "K"),5),
	Courier(		"Courier",			false, HullType.SMALL, 16, Toughness.GOOD,	2, 4,  4, Moneyz.money(400, "K"),10),
	Trader(			"Trader",			false, HullType.SMALL, 24, Toughness.GOOD,	2, 4,  6, Moneyz.money(500, "K"),8),
	FastFreighter(	"Fast Freighter",	false, HullType.SMALL, 32, Toughness.SMALL, 2, 4,  8, Moneyz.money(600, "K"),11),
	FastTransport(	"Fast Transport",	false, HullType.SMALL, 40, Toughness.SMALL, 2, 4, 10, Moneyz.money(800, "K"),14),
	Hauler(			"Hauler",			false, HullType.LIGHT, 72, Toughness.SMALL, 1, 3, 18, Moneyz.money(1, "M"),20),
	Industrial(		"Industrial",		false, HullType.LIGHT, 96, Toughness.SMALL, 1, 3, 24, Moneyz.money(2, "M"),27),
	MediumFreighter("Medium Freighter",	false, HullType.MEDIUM, 240, Toughness.LIGHT, 0, 2, 30, Moneyz.money(20, "M"),58),
	Clipper(		"Clipper",			false, HullType.MEDIUM, 360, Toughness.LIGHT, 0, 2, 360, Moneyz.money(40, "M"),87),
	MediumTransport("Medium Transport",	false, HullType.MEDIUM, 480, Toughness.LIGHT, -1, 2, 60, Moneyz.money(60, "M"),115),
	Tanker(			"Tanker",			false, HullType.HEAVY, 720, Toughness.MEDIUM, -1, 1, 90, Moneyz.money(100, "M"),117),
	Liner(			"Liner",			false, HullType.HEAVY, 840, Toughness.MEDIUM, -1, 1, 840, Moneyz.money(150, "M"),137), //Crew could be adjusted to 200
	HeavyTransport(	"Heavy Transport",	false, HullType.HEAVY, 1280, Toughness.MEDIUM, -2, 1, 160, Moneyz.money(200, "M"),208),
	SuperFreighter(	"Super-Freighter",	false, HullType.SUPERHEAVY, 2400, Toughness.HEAVY, -3, 0, 300, Moneyz.money(400, "M"),360),
	ColonyTransport("Colony Transport",	false, HullType.SUPERHEAVY, 3600, Toughness.HEAVY, -4, 0, 3600, Moneyz.money(1000, "M"),540),
	
	// Military Hulls
	Fighter(		"Fighter", 			true, HullType.SMALL, 10, Toughness.SMALL,	3, 4,  1, Moneyz.money(350, "K"),7),
	StrikeFighter(	"Strike Fighter",	true, HullType.SMALL, 15, Toughness.SMALL, 3, 4,  2, Moneyz.money(500, "K"),10),
	Cutter(			"Cutter",			true, HullType.SMALL, 20, Toughness.SMALL, 2, 4,  4, Moneyz.money(600, "K"),14),
	Scout(			"Scout",			true, HullType.SMALL, 30, Toughness.SMALL, 2, 4,  6, Moneyz.money(800, "K"),10),
	Escort(			"Escort",			true, HullType.SMALL, 40, Toughness.SMALL, 2, 4, 10, Moneyz.money(1, "M"),14),
	Corvette(		"Corvette",			true, HullType.LIGHT, 80, Toughness.LIGHT, 1, 3, 20, Moneyz.money(5, "M"),22),
	Frigate(		"Frigate",			true, HullType.LIGHT, 120, Toughness.LIGHT, 1, 3, 60, Moneyz.money(15, "M"),33),
	Destroyer(		"Destroyer",		true, HullType.LIGHT, 160, Toughness.LIGHT, 1, 3, 80, Moneyz.money(30, "M"),44),
	LightCruiser(	"Light Cruiser",	true, HullType.MEDIUM, 320, Toughness.MEDIUM, 0, 2, 240, Moneyz.money(50, "M"),75),
	HeavyCruiser(	"Heavy Cruiser",	true, HullType.MEDIUM, 400, Toughness.MEDIUM, 0, 2, 300, Moneyz.money(100, "M"),96),
	ArmoredCruiser(	"Armored Cruiser",	true, HullType.MEDIUM, 480, Toughness.MEDIUM, -1, 2, 360, Moneyz.money(200, "M"),115),
	Battlecruiser(	"Battlecruiser",	true, HullType.HEAVY, 960, Toughness.HEAVY, -2, 1, 960, Moneyz.money(500, "M"),156),
	Battleship(		"Battleship",		true, HullType.HEAVY, 1200, Toughness.HEAVY, -2, 1, 1200, Moneyz.money(1000, "M"),195),
	FleetCarrier(	"Fleet Carrier",	true, HullType.HEAVY, 1600, Toughness.HEAVY, -3, 1, 1600, Moneyz.money(1500, "M"),260),
	Dreadnought(	"Dreadnought",		true, HullType.SUPERHEAVY, 3200, Toughness.SUPERHEAVY, -3, 1, 3200, Moneyz.money(2000, "M"),480),
	SuperCarrier(	"Super-Carrier",	true, HullType.SUPERHEAVY, 4000, Toughness.SUPERHEAVY, -4, 1, 4000, Moneyz.money(4000, "M"),600),
	SuperDread(		"Super-Dreadnought",true, HullType.SUPERHEAVY, 6400, Toughness.SUPERHEAVY, -5, 1, 6400, Moneyz.money(10000, "M"),960),
	FortressShip(	"Fortress Ship",	true, HullType.SUPERHEAVY, 12000, Toughness.SUPERHEAVY, -5, 1, 12000, Moneyz.money(50000, "M"),1800);
	
	
	protected final String name;
	
	protected final int creditCost;
	
	protected final int hullProvided;
	protected final boolean military;
	protected final HullType hullType;
	protected final Toughness toughness;
	protected final int target, maneuverability, crew, zoneLimit;

	
	HullList(String name, boolean military, HullType hullType, int baseHullPoints, Toughness toughness, int target, int maneuverability, int crew, int baseCost, int zoneLimit){
		this.name=name;

		this.creditCost=baseCost;
		this.hullProvided=baseHullPoints;
		
		this.military=military;
		this.hullType=hullType;
		this.toughness=toughness;
		this.target=target;
		this.maneuverability=maneuverability;
		this.crew=crew;
		this.zoneLimit=zoneLimit;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Name");
		titles.add("Pts.");
		titles.add("Tough");
		titles.add("Target");
		titles.add("Move");
		titles.add("Crew");
		titles.add("Cost");
		
		return titles;
	}
	
	public static ArrayList<ShipSystem<HullList>> getCivilianHulls() {
		ArrayList<ShipSystem<HullList>> list = new ArrayList<>();
		for (HullList system : HullList.values()) {
			if (!system.military && system != HullList.None) {
				list.add(system.createInstance());
			}
		}
		return list;
	}

	public static ArrayList<ShipSystem<HullList>> getMilitaryHulls() {
		ArrayList<ShipSystem<HullList>> list = new ArrayList<>();
		for (HullList system : HullList.values()) {
			if (system.military) {
				list.add(system.createInstance());
			}
		}
		return list;
	}
	
	@Override
	public ShipSystem<HullList> createInstance() {
		return new Hull(this);
	}
}
