package shipEngine;


import shipHelperz.Moneyz;
import shipfight.ProgressLevel;
import shipfight.Tech;

public enum FTLList {

	JumpDrive(			"Jump Drive",			ProgressLevel.PL6, Tech.T, /* Pow */1.0, /* MinSize */5, Moneyz.money(4, "M"), Moneyz.money(1, "M")),
	WormholeScreen(		"Wormhole Screen",		ProgressLevel.PL6, Tech.M, /* Pow */2.0, /* MinSize */1, Moneyz.money(1, "M"), Moneyz.money(200, "K")),
	GateActivator(		"Gate Activator",		ProgressLevel.PL7, Tech.T, /* Pow */2.0, /* MinSize */1, Moneyz.money(500, "K"), Moneyz.money(100, "K")),
	Hyperdrive(			"Hyperdrive",			ProgressLevel.PL7, Tech.X, /* Pow */3.0, /* MinSize */4, Moneyz.money(5, "M"), Moneyz.money(2, "M")),
	Stardrive(			"Stardrive",			ProgressLevel.PL7, Tech.G, /* Pow */0.0, /* MinSize */3, Moneyz.money(2, "M"), Moneyz.money(1, "M")),
	Drivewave(			"Drivewave",			ProgressLevel.PL8, Tech.G, /* Pow */0.0, /* MinSize */2, Moneyz.money(3, "M"), Moneyz.money(1, "M")+Moneyz.money(500, "K")),
	SpacefoldDrive(		"SpacefoldDrive",		ProgressLevel.PL8, Tech.T, /* Pow */4.0, /* MinSize */4, Moneyz.money(8, "M"), Moneyz.money(2, "M")),
	PsychoportiveDrive(	"Psychoportive Drive",	ProgressLevel.PL8, Tech.P, /* Pow */1.0, /* MinSize */10, Moneyz.money(6, "M"), Moneyz.money(200, "K")),
	TranscendentDrive(	"Transcendent Drive",	ProgressLevel.PL9, Tech.P, /* Pow */1.0, /* MinSize */4, Moneyz.money(12, "M"), Moneyz.money(400, "K")),
	Warpdrive(			"Warpdrive",			ProgressLevel.PL9, Tech.X, /* Pow */2.0, /* MinSize */2, Moneyz.money(10, "M"), Moneyz.money(5, "M"));

	String name;
	ProgressLevel level;
	Tech tech;
	double power;
	int baseCost, costPerHull, minSize, fuelCost, fuelEfficiency;

	FTLList(String name, ProgressLevel progressLevel, Tech tech, double power, int minSize, int baseCost, int costPerHull) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.power = power;
		this.minSize = minSize;
		this.baseCost = baseCost;
		this.costPerHull = costPerHull;
		this.minSize = minSize;
	}
}
