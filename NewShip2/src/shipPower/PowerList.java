package shipPower;

import java.util.ArrayList;

import helpz.MyShipObject;
import shipArmor.Armor;
import shipHelperz.Moneyz;
import shipfight.ProgressLevel;
import shipfight.Tech;

public enum PowerList {

	SolarCell(				"Solar Cell",				ProgressLevel.PL6, Tech.S, /* Pow */1.5, Moneyz.money(500, "K"), Moneyz.money(200, "K"), /* MinSize */4, false, Moneyz.money(0, "K"), /* Efficiency */0),
	FissionGenerator(		"Fission Generator",		ProgressLevel.PL6, Tech.N, /* Pow */1.5, Moneyz.money(1, "M"), Moneyz.money(100, "K"), /* MinSize */4, false, Moneyz.money(0, "K"), /* Efficiency */0),
	FusionGenerator(		"Fusion Generator",			ProgressLevel.PL6, Tech.F, /* Pow */2.0, Moneyz.money(1, "M"), Moneyz.money(200, "K"), /* MinSize */2, true, Moneyz.money(1, "K"), /* Efficiency */200),
	GravFusionCell(			"Grav-Fusion Cell",			ProgressLevel.PL6, Tech.G, /* Pow */2.5, Moneyz.money(2, "M"), Moneyz.money(200, "K"), /* MinSize */4, true, Moneyz.money(1, "K"), /* Efficiency */300),
	FuelTank(				"Fuel Tank",				ProgressLevel.PL6, Tech.N, /* Pow */0, Moneyz.money(50, "K"), Moneyz.money(10, "K"), /* MinSize */0, false, Moneyz.money(0, "K"), /* Efficiency */0),
	TachyonicCollider(		"Tachyonic Collider",		ProgressLevel.PL7, Tech.Q, /* Pow */2.5, Moneyz.money(1, "M"), Moneyz.money(100, "K"), /* MinSize */2, false, Moneyz.money(0, "K"), /* Efficiency */0),
	AntimatterReactor(		"Antimatter Reactor",		ProgressLevel.PL7, Tech.A, /* Pow */3.0, Moneyz.money(4, "M"), Moneyz.money(400, "K"), /* MinSize */3, false, Moneyz.money(0, "K"), /* Efficiency */0),
	MassReactor(			"Mass Reactor",				ProgressLevel.PL7, Tech.D, /* Pow */3.5, Moneyz.money(2, "M"), Moneyz.money(250, "K"), /* MinSize */2, false, Moneyz.money(0, "K"), /* Efficiency */0),
	DynamicMassReactor(		"Dynamic Mass Reactor",		ProgressLevel.PL8, Tech.D, /* Pow */4.0, Moneyz.money(3, "M"), Moneyz.money(200, "K"), /* MinSize */1, false, Moneyz.money(0, "K"), /* Efficiency */0),
	MatterConverter(		"Matter Converter",			ProgressLevel.PL8, Tech.M, /* Pow */4.5, Moneyz.money(4, "M"), Moneyz.money(200, "K"), /* MinSize */2, false, Moneyz.money(0, "K"), /* Efficiency */0),//Add X tech
	QuantumCell(			"Quantum Cell",				ProgressLevel.PL8, Tech.Q, /* Pow */5.0, Moneyz.money(5, "M"), Moneyz.money(400, "K"), /* MinSize */3, false, Moneyz.money(0, "K"), /* Efficiency */0),
	SingularityGenerator(	"Singularity Generator",	ProgressLevel.PL9, Tech.G, /* Pow */6.0, Moneyz.money(10, "M"), Moneyz.money(500, "K"), /* MinSize */20, false, Moneyz.money(0, "K"), /* Efficiency */0);

	String name;
	ProgressLevel level;
	Tech tech;
	double power;
	int baseCost, costPerHull, minSize, fuelCost, fuelEfficiency, cost;
	boolean fuelReq;

	PowerList(String name, ProgressLevel progressLevel, Tech tech, double power, int baseCost, int costPerHull,
			int minSize, boolean fuelReq, int fuelCost, int fuelEfficiency) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.power = power;
		this.baseCost = baseCost;
		this.costPerHull = costPerHull;
		this.minSize = minSize;
		this.fuelReq = fuelReq;
		this.fuelCost = fuelCost;
		this.fuelEfficiency = fuelEfficiency;
		calculateCost();
	}
	
	private void calculateCost() {
		cost = baseCost * costPerHull * minSize;
	}
	
	public static ArrayList<MyShipObject> getListPowerSystems() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new PowerSystem(SolarCell));
		fullList.add(new PowerSystem(FissionGenerator));
		fullList.add(new PowerSystem(FusionGenerator));
		fullList.add(new PowerSystem(GravFusionCell));
		fullList.add(new PowerSystem(FuelTank));
		fullList.add(new PowerSystem(TachyonicCollider));
		fullList.add(new PowerSystem(AntimatterReactor));
		fullList.add(new PowerSystem(MassReactor));
		fullList.add(new PowerSystem(DynamicMassReactor));
		fullList.add(new PowerSystem(MatterConverter));
		fullList.add(new PowerSystem(QuantumCell));
		fullList.add(new PowerSystem(SingularityGenerator));
		
		return fullList;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> listTitles = new ArrayList<String>();
		listTitles.add("Name");
		listTitles.add("PL");
		listTitles.add("Tech");
		listTitles.add("Base Cost");
		listTitles.add("Cost/Hull Pt.");
		listTitles.add("Min Size");
		listTitles.add("Fuel?");
		listTitles.add("Fuel Cost");
		listTitles.add("Fuel Eff");
		
		return listTitles;
	}
}
