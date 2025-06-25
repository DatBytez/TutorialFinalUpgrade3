package ship.systems;


import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum FTLList implements SystemFactory<FTLList>{

	JumpDrive(			"Jump Drive",			ProgressLevel.PL6, Tech.T, /* Pow */1.0, /* MinSize */5, Moneyz.money(4, "M"), Moneyz.money(1, "M")),
	WormholeScreen(		"Wormhole Screen",		ProgressLevel.PL6, Tech.M, /* Pow */2.0, /* MinSize */1, Moneyz.money(1, "M"), Moneyz.money(200, "K")),
	GateActivator(		"Gate Activator",		ProgressLevel.PL7, Tech.T, /* Pow */2.0, /* MinSize */1, Moneyz.money(500, "K"), Moneyz.money(100, "K")),
	Hyperdrive(			"Hyperdrive",			ProgressLevel.PL7, Tech.X, /* Pow */3.0, /* MinSize */4, Moneyz.money(5, "M"), Moneyz.money(2, "M")),
	Stardrive(			"Stardrive",			ProgressLevel.PL7, Tech.G, /* Pow */0.0, /* MinSize */3, Moneyz.money(2, "M"), Moneyz.money(1, "M")),
	Drivewave(			"Drivewave",			ProgressLevel.PL8, Tech.G, /* Pow */0.0, /* MinSize */2, Moneyz.money(3, "M"), Moneyz.money(1, "M")+Moneyz.money(500, "K")),
	SpacefoldDrive(		"Spacefold Drive",		ProgressLevel.PL8, Tech.T, /* Pow */4.0, /* MinSize */4, Moneyz.money(8, "M"), Moneyz.money(2, "M")),
	PsychoportiveDrive(	"Psychoportive Drive",	ProgressLevel.PL8, Tech.P, /* Pow */1.0, /* MinSize */10, Moneyz.money(6, "M"), Moneyz.money(200, "K")),
	TranscendentDrive(	"Transcendent Drive",	ProgressLevel.PL9, Tech.P, /* Pow */1.0, /* MinSize */4, Moneyz.money(12, "M"), Moneyz.money(400, "K")),
	Warpdrive(			"Warpdrive",			ProgressLevel.PL9, Tech.X, /* Pow */2.0, /* MinSize */2, Moneyz.money(10, "M"), Moneyz.money(5, "M"));

	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost;
	
	protected final int creditCost, minSize, costPerHull;

	FTLList(String name, ProgressLevel progressLevel, Tech tech, double power, int minSize, int baseCost, int costPerHull) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = power;
		this.minSize = minSize;
		this.creditCost = baseCost;
		this.costPerHull = costPerHull;
	}
	
	public static ArrayList<String> getListTitles(){
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Power");
        titles.add("Min Size");
        titles.add("Base Cost");
        titles.add("Cost/Hull Pt.");
        return titles;
	}
	
	public static ArrayList<ShipSystem<FTLList>> getList() {
		return SystemListUtilz.getAll(FTLList.class);
	}
	
	@Override
	public ShipSystem<FTLList> createInstance() {
		return new FTL(this);
	}
}
