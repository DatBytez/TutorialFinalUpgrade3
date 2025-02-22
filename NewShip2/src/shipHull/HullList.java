package shipHull;

import java.awt.Font;
import java.util.ArrayList;

import helpz.MyShipObject;
import shipHelperz.Moneyz;

public enum HullList {
	
	// Civilian Hulls
	None(			"None", 			false, HullType.SMALL, 0, Toughness.GOOD,	0, 0,  0, Moneyz.money(0, "K"),"SELECT A HULL"),
	Launch(			"Launch", 			false, HullType.SMALL, 8, Toughness.GOOD,	3, 4,  2, Moneyz.money(300, "K"),"This\n is a boat,\n pinnace, or gig designed to simply move\n small amounts of people from one point to another. They’re\n rarely armed or armored."),
	Courier(		"Courier",			false, HullType.SMALL, 16, Toughness.GOOD,	2, 4,  4, Moneyz.money(400, "K"),"The courier is a more robust ship capable of extended\n operation away from its base. Few are drive-capable. "),
	Trader(			"Trader",			false, HullType.SMALL, 24, Toughness.GOOD,	2, 4,  6, Moneyz.money(500, "K"),""),
	FastFreighter(	"Fast Freighter",	false, HullType.SMALL, 32, Toughness.SMALL, 2, 4,  8, Moneyz.money(600, "K"),""),
	FastTransport(	"Fast Transport",	false, HullType.SMALL, 40, Toughness.SMALL, 2, 4, 10, Moneyz.money(800, "K"),""),
	Hauler(			"Hauler",			false, HullType.LIGHT, 72, Toughness.SMALL, 1, 3, 18, Moneyz.money(1, "M"),""),
	Industrial(		"Industrial",		false, HullType.LIGHT, 96, Toughness.SMALL, 1, 3, 24, Moneyz.money(2, "M"),""),
	MediumFreighter("Medium Freighter",	false, HullType.MEDIUM, 240, Toughness.LIGHT, 0, 2, 30, Moneyz.money(20, "M"),""),
	Clipper(		"Clipper",			false, HullType.MEDIUM, 360, Toughness.LIGHT, 0, 2, 360, Moneyz.money(40, "M"),""),
	MediumTransport("Medium Transport",	false, HullType.MEDIUM, 480, Toughness.LIGHT, -1, 2, 60, Moneyz.money(60, "M"),""),
	Tanker(			"Tanker",			false, HullType.HEAVY, 720, Toughness.MEDIUM, -1, 1, 90, Moneyz.money(100, "M"),""),
	Liner(			"Liner",			false, HullType.HEAVY, 840, Toughness.MEDIUM, -1, 1, 840, Moneyz.money(150, "M"),""), //Crew could be adjusted to 200
	HeavyTransport(	"Heavy Transport",	false, HullType.HEAVY, 1280, Toughness.MEDIUM, -2, 1, 160, Moneyz.money(200, "M"),""),
	SuperFreighter(	"Super-Freighter",	false, HullType.SUPERHEAVY, 2400, Toughness.HEAVY, -3, 0, 300, Moneyz.money(400, "M"),""),
	ColonyTransport("Colony Transport",	false, HullType.SUPERHEAVY, 3600, Toughness.HEAVY, -4, 0, 3600, Moneyz.money(1000, "M"),""),
	
	// Military Hulls
	Fighter(		"Fighter", 			true, HullType.SMALL, 10, Toughness.SMALL,	3, 4,  1, Moneyz.money(350, "K"),""),
	StrikeFighter(	"Strike Fighter",	true, HullType.SMALL, 15, Toughness.SMALL, 3, 4,  2, Moneyz.money(500, "K"),""),
	Cutter(			"Cutter",			true, HullType.SMALL, 20, Toughness.SMALL, 2, 4,  4, Moneyz.money(600, "K"),""),
	Scout(			"Scout",			true, HullType.SMALL, 30, Toughness.SMALL, 2, 4,  6, Moneyz.money(800, "K"),""),
	Escort(			"Escort",			true, HullType.SMALL, 40, Toughness.SMALL, 2, 4, 10, Moneyz.money(1, "M"),""),
	Corvette(		"Corvette",			true, HullType.LIGHT, 80, Toughness.LIGHT, 1, 3, 20, Moneyz.money(5, "M"),""),
	Frigate(		"Frigate",			true, HullType.LIGHT, 120, Toughness.LIGHT, 1, 3, 60, Moneyz.money(15, "M"),""),
	Destroyer(		"Destroyer",		true, HullType.LIGHT, 160, Toughness.LIGHT, 1, 3, 80, Moneyz.money(30, "M"),""),
	LightCruiser(	"Light Cruiser",	true, HullType.MEDIUM, 320, Toughness.MEDIUM, 0, 2, 240, Moneyz.money(50, "M"),""),
	HeavyCruiser(	"Heavy Cruiser",	true, HullType.MEDIUM, 400, Toughness.MEDIUM, 0, 2, 300, Moneyz.money(100, "M"),""),
	ArmoredCruiser(	"Armored Cruiser",	true, HullType.MEDIUM, 480, Toughness.MEDIUM, -1, 2, 360, Moneyz.money(200, "M"),""),
	Battlecruiser(	"Battlecruiser",	true, HullType.HEAVY, 960, Toughness.HEAVY, -2, 1, 960, Moneyz.money(500, "M"),""),
	Battleship(		"Battleship",		true, HullType.HEAVY, 1200, Toughness.HEAVY, -2, 1, 1200, Moneyz.money(1000, "M"),""),
	FleetCarrier(	"Fleet Carrier",	true, HullType.HEAVY, 1600, Toughness.HEAVY, -3, 1, 1600, Moneyz.money(1500, "M"),""),
	Dreadnought(	"Dreadnought",		true, HullType.SUPERHEAVY, 3200, Toughness.SUPERHEAVY, -3, 1, 3200, Moneyz.money(2000, "M"),""),
	SuperCarrier(	"Super-Carrier",	true, HullType.SUPERHEAVY, 4000, Toughness.SUPERHEAVY, -4, 1, 4000, Moneyz.money(4000, "M"),""),
	SuperDread(		"Super-Dreadnought",true, HullType.SUPERHEAVY, 6400, Toughness.SUPERHEAVY, -5, 1, 6400, Moneyz.money(10000, "M"),""),
	FortressShip(	"Fortress Ship",	true, HullType.SUPERHEAVY, 12000, Toughness.SUPERHEAVY, -5, 1, 12000, Moneyz.money(50000, "M"),"");
	
	
	String name, description;
	boolean military;
	HullType hullType;
	int hull;
	Toughness toughness;
	int target;
	int maneuverability;
	int crew;
	int cost;

	HullList(String name, boolean military, HullType hullType, int hull, Toughness toughness, int target, int maneuverability, int crew, int cost, String description){
		this.name=name;
		this.military=military;
		this.hullType=hullType;
		this.hull=hull;
		this.toughness=toughness;
		this.target=target;
		this.maneuverability=maneuverability;
		this.crew=crew;
		this.cost=cost;
		this.description=description;
	}
	
	public static ArrayList<MyShipObject> getCivilianHulls() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new Hull(Launch));
		fullList.add(new Hull(Courier));
		fullList.add(new Hull(Trader));
		fullList.add(new Hull(FastFreighter));
		fullList.add(new Hull(FastTransport));
		fullList.add(new Hull(Hauler));
		fullList.add(new Hull(Industrial));
		fullList.add(new Hull(MediumFreighter));
		fullList.add(new Hull(Tanker));
		fullList.add(new Hull(Liner));
		fullList.add(new Hull(HeavyTransport));
		fullList.add(new Hull(SuperFreighter));
		fullList.add(new Hull(ColonyTransport));
		
		return fullList;
	}

	public static ArrayList<MyShipObject> getMilitaryHulls() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new Hull(Fighter));
		fullList.add(new Hull(StrikeFighter));
		fullList.add(new Hull(Cutter));
		fullList.add(new Hull(Scout));
		fullList.add(new Hull(Escort));
		fullList.add(new Hull(Corvette));
		fullList.add(new Hull(Frigate));
		fullList.add(new Hull(Destroyer));
		fullList.add(new Hull(LightCruiser));
		fullList.add(new Hull(HeavyCruiser));
		fullList.add(new Hull(ArmoredCruiser));
		fullList.add(new Hull(Battlecruiser));
		fullList.add(new Hull(Battleship));
		fullList.add(new Hull(FleetCarrier));
		fullList.add(new Hull(Dreadnought));
		fullList.add(new Hull(SuperCarrier));
		fullList.add(new Hull(SuperDread));
		fullList.add(new Hull(FortressShip));
		
		return fullList;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> listTitles = new ArrayList<String>();
		listTitles.add("Name");
		listTitles.add("Pts.");
		listTitles.add("Tough");
		listTitles.add("Target");
		listTitles.add("Move");
		listTitles.add("Wound");
		listTitles.add("Mortal");
		listTitles.add("Critical");
		listTitles.add("Crew");
		listTitles.add("Cost");
		
		return listTitles;
	}
}
