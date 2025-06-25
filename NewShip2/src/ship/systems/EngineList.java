package ship.systems;


import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum EngineList implements SystemFactory<EngineList> {

	PlanetaryThruster(	"Planetary Thruster",	ProgressLevel.PL6, Tech.N, /* Pow */1.0, /* MinSize */1, Moneyz.money(200, "K"), Moneyz.money(50, "K"),
												/*5%*/0.1,/*10%*/0.25,/*15%*/0.5,/*20%*/1.0,/*30%*/0.0,/*40%*/0.0,/*50%*/0.00, /*Eff.*/ 10, Moneyz.money(10, "K")),
	PhotonSail(			"Photon Sail",			ProgressLevel.PL6, Tech.N, /* Pow */0.0, /* MinSize */5, Moneyz.money(500, "K"), Moneyz.money(50, "K"),
												/*5%*/0.0,/*10%*/0.02,/*15%*/0.05,/*20%*/0.1,/*30%*/0.15,/*40%*/0.2,/*50%*/0.25, /*Eff.*/ 0, Moneyz.money(0, "K")),
	FusionTorch(		"Fusion Torch",			ProgressLevel.PL6, Tech.N, /* Pow */0.33,/* MinSize */3, Moneyz.money(500, "K"), Moneyz.money(100, "K"),
												/*5%*/0.5,/*10%*/1.,/*15%*/1.5,/*20%*/2.0,/*30%*/3.0,/*40%*/4.0,/*50%*/5.0, /*Eff.*/ 200, Moneyz.money(1, "K")),
	IonEngine(			"Ion Engine",			ProgressLevel.PL6, Tech.S, /* Pow */0.5, /* MinSize */2, Moneyz.money(800, "K"), Moneyz.money(200, "K"),
												/*5%*/0.0,/*10%*/0.5,/*15%*/1.0,/*20%*/1.5,/*30%*/2.0,/*40%*/3.0,/*50%*/4.0, /*Eff.*/ 400, Moneyz.money(5, "K")),
	ParticleImpulse(	"Particle Impulse",		ProgressLevel.PL7, Tech.N, /* Pow */0.75,/* MinSize */4, Moneyz.money(500, "K"), Moneyz.money(300, "K"),
												/*5%*/0.5,/*10%*/1.0,/*15%*/1.5,/*20%*/2.0,/*30%*/2.5,/*40%*/3.0,/*50%*/4.0, /*Eff.*/ 0, Moneyz.money(0, "K")),
	InductionEngine(	"Induction Engine",		ProgressLevel.PL7, Tech.G, /* Pow */1.0, /* MinSize */2, Moneyz.money(1, "M"), Moneyz.money(500, "K"),
												/*5%*/1.0,/*10%*/2.0,/*15%*/3.0,/*20%*/4.0,/*30%*/5.0,/*40%*/6.0,/*50%*/8.0, /*Eff.*/ 0, Moneyz.money(0, "K")),
	InertialFluxEngine(	"Inertial Flux Engine",	ProgressLevel.PL8, Tech.X, /* Pow */1.0, /* MinSize */1, Moneyz.money(2, "M"), Moneyz.money(500, "K"),
												/*5%*/2.0,/*10%*/3.0,/*15%*/4.0,/*20%*/5.0,/*30%*/6.0,/*40%*/8.0,/*50%*/10.0, /*Eff.*/ 0, Moneyz.money(0, "K")),
	GraviticRedirector(	"Gravitic Redirector",	ProgressLevel.PL8, Tech.G, /* Pow */0.67,/* MinSize */3, Moneyz.money(3, "M"), Moneyz.money(1, "M"),
												/*5%*/2.0,/*10%*/4.0,/*15%*/6.0,/*20%*/8.0,/*30%*/10.0,/*40%*/12.0,/*50%*/16.0, /*Eff.*/ 0, Moneyz.money(0, "K")),
	SpatialCompressor(	"Spatial Compressor",	ProgressLevel.PL9, Tech.T, /* Pow */2.0, /* MinSize */4, Moneyz.money(1, "M")+Moneyz.money(500, "K"), Moneyz.money(200, "K"),
												/*5%*/3.0,/*10%*/6.0,/*15%*/9.0,/*20%*/12.0,/*30%*/15.0,/*40%*/18.0,/*50%*/20.0, /*Eff.*/ 0, Moneyz.money(0, "K"));

	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost;
	
	protected final int creditCost, minSize, costPerHull;
	
	protected final int fuelCost, fuelEfficiency;
	protected final boolean fuelRequired;
	
	protected final double arper5, arper10, arper15, arper20, arper30, arper40, arper50;

	
	EngineList(String name, ProgressLevel progressLevel, Tech tech, double power, int minSize, int baseCost, int costPerHull, double arper5, double arper10, double arper15, double arper20, double arper30, double arper40, double arper50, int fuelEfficiency, int fuelCost) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = power;
		this.creditCost = baseCost;
		this.minSize = minSize;
		this.costPerHull = costPerHull;
		this.fuelRequired = fuelCost > 0;
		this.fuelCost = fuelCost;
		this.fuelEfficiency = fuelEfficiency;
		this.arper5 = arper5;
		this.arper10 = arper10;
		this.arper15 = arper15;
		this.arper20 = arper20;
		this.arper30 = arper30;
		this.arper40 = arper40;
		this.arper50 = arper50;
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
	
	public static ArrayList<ShipSystem<EngineList>> getList() {
	    return SystemListUtilz.getAll(EngineList.class);
	}
	
	@Override
	public ShipSystem<EngineList> createInstance() {
		return new Engine(this);
	}
}
