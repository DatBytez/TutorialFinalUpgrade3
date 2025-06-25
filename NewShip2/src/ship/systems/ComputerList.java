package ship.systems;


import java.util.ArrayList;

import ship.Tech;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum ComputerList implements SystemFactory<ComputerList>{

	ComputerCoreOrdinary(	"Computer Core, Ordinary",	ProgressLevel.PL6, Tech.C, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(500, "K"), 0),
	FireControlOrdinary(	"Fire Control, Ordinary",	ProgressLevel.PL6, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(200, "K"),-1),
	SensorControlOrdinary(	"Sensor Control, Ordinary",	ProgressLevel.PL6, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(200, "K"),-1),
	TacControlOrdinary(		"Tac Control, Ordinary",	ProgressLevel.PL6, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(100, "K"),-1),
	NavControlOrdinary(		"Nav Control, Ordinary",	ProgressLevel.PL6, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(500, "K"),-1),
	AttackComputer(			"Attack Computer",			ProgressLevel.PL7, Tech.C, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money(200, "K"),-1),
	ComputerCoreGood(		"Computer Core, Good",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(  1, "M"), 0),
	FireControlGood(		"Fire Control, Good",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(300, "K"),-2),
	SensorControlGood(		"Sensor Control, Good",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(300, "K"),-2),
	TacControlGood(			"Tac Control, Good",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(200, "K"),-2),
	NavControlGood(			"Nav Control, Good",		ProgressLevel.PL7, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(750, "K"),-2),
	ComputerCoreAmazing(	"Computer Core, Amazing",	ProgressLevel.PL8, Tech.C, /*Hull*/ 1.0,/* Pow */1.0, Moneyz.money(  2, "M"), 0),
	FireControlAmazing(		"Fire Control, Amazing",	ProgressLevel.PL8, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(300, "K"),-3),
	SensorControlAmazing(	"Sensor Control, Amazing",	ProgressLevel.PL8, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(300, "K"),-3),
	TacControlAmazing(		"Tac Control, Amazing",		ProgressLevel.PL8, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(400, "K"),-3),
	NavControlAmazing(		"Nav Control, Amazing",		ProgressLevel.PL8, Tech.C, /*Hull*/ 1.0,/* Pow */0.0, Moneyz.money(  1, "M"),-3);


	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost, hullCost;
	protected final int creditCost;
	
	protected final int modifier;

	ComputerList(String name, ProgressLevel progressLevel, Tech tech, double hullCost, double powerCost, int creditCost, int modifier) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = powerCost;
		this.creditCost = creditCost;
		this.hullCost = hullCost;
		this.modifier = modifier;
	}
	
	public static ArrayList<String> getListTitles(){
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Hull");
        titles.add("Power");
        titles.add("Cost");
        titles.add("Bonus");
        return titles;
	}
	
	public static ArrayList<ShipSystem<ComputerList>> getList() {
		return SystemListUtilz.getAll(ComputerList.class);
	}

	@Override
	public ShipSystem<ComputerList> createInstance() {
		return new Computer(this);
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
