package ship.systems;

import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum PowerList implements SystemFactory<PowerList> {

	SolarCell(			 "Solar Cell",				ProgressLevel.PL6, Tech.S, /* Pow */1.5, /* MinSize */4, Moneyz.money(500, "K"), Moneyz.money(200, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	FissionGenerator(	 "Fission Generator",		ProgressLevel.PL6, Tech.N, /* Pow */1.5, /* MinSize */4, Moneyz.money(1, "M"), Moneyz.money(100, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	FusionGenerator(	 "Fusion Generator",		ProgressLevel.PL6, Tech.F, /* Pow */2.0, /* MinSize */2, Moneyz.money(1, "M"),	Moneyz.money(200, "K"), true, Moneyz.money(1, "K"), /* Efficiency */200),
	GravFusionCell(		 "Grav-Fusion Cell",		ProgressLevel.PL6, Tech.G, /* Pow */2.5, /* MinSize */4, Moneyz.money(2, "M"), Moneyz.money(200, "K"), true, Moneyz.money(1, "K"), /* Efficiency */300),
	FuelTank(			 "Fuel Tank",				ProgressLevel.PL6, Tech.N, /* Pow */0, /* MinSize */0, Moneyz.money(50, "K"), Moneyz.money(10, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	TachyonicCollider(	 "Tachyonic Collider",		ProgressLevel.PL7, Tech.Q, /* Pow */2.5, /* MinSize */2, Moneyz.money(1, "M"), Moneyz.money(100, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	AntimatterReactor(	 "Antimatter Reactor",		ProgressLevel.PL7, Tech.A, /* Pow */3.0, /* MinSize */3, Moneyz.money(4, "M"), Moneyz.money(400, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	MassReactor(		 "Mass Reactor",			ProgressLevel.PL7, Tech.D, /* Pow */3.5, /* MinSize */2, Moneyz.money(2, "M"), Moneyz.money(250, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	DynamicMassReactor(	 "Dynamic Mass Reactor",	ProgressLevel.PL8, Tech.D, /* Pow */4.0, /* MinSize */1, Moneyz.money(3, "M"), Moneyz.money(200, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	MatterConverter(	 "Matter Converter",		ProgressLevel.PL8, Tech.M, /* Pow */4.5, /* MinSize */2, Moneyz.money(4, "M"), Moneyz.money(200, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0), // Add X tech
	QuantumCell(		 "Quantum Cell",			ProgressLevel.PL8, Tech.Q, /* Pow */5.0, /* MinSize */3, Moneyz.money(5, "M"), Moneyz.money(400, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0),
	SingularityGenerator("Singularity Generator", 	ProgressLevel.PL9, Tech.G, /* Pow */6.0, /* MinSize */20, Moneyz.money(10, "M"), Moneyz.money(500, "K"), false, Moneyz.money(0, "K"), /* Efficiency */0);

	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double power;

	protected final int creditCost, minSize, costPerHull;
	
	protected final int fuelCost, fuelEfficiency;
	protected final boolean fuelRequired;

	PowerList(String name, ProgressLevel progressLevel, Tech tech, double power, int minSize, int baseCost, int costPerHull, boolean fuelRequired, int fuelCost, int fuelEfficiency) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.power = power;
		this.creditCost = baseCost;
		this.costPerHull = costPerHull;
		this.minSize = minSize;
		this.fuelRequired = fuelRequired;
		this.fuelCost = fuelCost;
		this.fuelEfficiency = fuelEfficiency;
	}
	
	public static ArrayList<String> getListTitles() {
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Power");
        titles.add("Min Size");
        titles.add("Base Cost");
        titles.add("Cost/Hull Pt.");
		titles.add("Fuel Cost");
		titles.add("Fuel Eff");
        return titles;
	}

	public static ArrayList<ShipSystem<PowerList>> getList() {
		return SystemListUtilz.getAll(PowerList.class);
	}

	@Override
	public ShipSystem<PowerList> createInstance() {
		return new Power(this);
	}
}
