package ship;


import shipHelperz.Moneyz;

public enum supportSystems {

	LifeSupport(		"Life Support",			ProgressLevel.PL6, Tech.N, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(100, "K")),
	CrewBunkroom(		"Crew Bunkroom",		ProgressLevel.PL6, Tech.N, /*Hull*/ 3,/* Pow */0.0, Moneyz.money(40, "K")),
	Cabin(				"Cabin",				ProgressLevel.PL6, Tech.N, /*Hull*/ 1,/* Pow */0.0, Moneyz.money(20, "K")),
	CrewQuarters(		"Crew Quarters",		ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */0.0, Moneyz.money(20, "K")),
	SeatingDeck(		"Seating Deck",			ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */0.0, Moneyz.money(10, "K")),
	PassengersQuarters(	"Passengers Quarters",	ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */0.0, Moneyz.money(50, "K")),
	CryogenicsUnit(		"Cryogenics Unit",		ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */1.0, Moneyz.money(100, "K")),
	HydroponicsBay(		"Hydroponics Bay",		ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */1.0, Moneyz.money(75, "K")),
	RecyclerUnit(		"Recycler Unit",		ProgressLevel.PL6, Tech.N, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(300, "K")),
	DeepStores(			"Deep Stores",			ProgressLevel.PL6, Tech.N, /*Hull*/ 1,/* Pow */0.0, Moneyz.money(5, "K")),
	Autosupport(		"Autosupport",			ProgressLevel.PL7, Tech.N, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(200, "K")),
	LifeSupressionUnit(	"Life Supression Unit",	ProgressLevel.PL7, Tech.S, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(250, "K")),
	SymbioticHull(		"Symbiotic Hull",		ProgressLevel.PL8, Tech.P, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(250, "K"));//add tech M


	String name;
	ProgressLevel level;
	Tech tech;
	double power;
	int cost, hullCost;

	supportSystems(String name, ProgressLevel progressLevel, Tech tech, int hullCost, double power, int cost) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.power = power;
		this.cost = cost;
		this.hullCost = hullCost;
	}
	
//	Crew are randomly rolled or gain xp based upon success.
//
//	(Multiple methods)
//	#1 Your best crew represent your check score for that thing, when a crew dies, the best dies first. (Very Punishing)
//
//	#2 Your best crew represent your check score for that thing, when a crew dies, the worst die first (Very Forgiving)
//
//	#3 Your best crew represent your check score for that thing, when a crew dies, they die at random. (More forgiving for larger vessels)
//
//	#4 Your average crew represent your check score for that thing, when a crew dies, the best dies first. (Most Punishing)
//
//	#5 Your average crew represent your check score for that thing, when a crew dies, the worst dies first. (Most Unimpressive)
//
//	#6 Your average crew represent your check score for that thing, when a crew dies, they die at random. (Most Balanced?)
}
