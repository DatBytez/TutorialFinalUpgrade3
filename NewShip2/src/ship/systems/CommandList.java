package ship.systems;


import java.util.ArrayList;

import helpz.SystemFactory;
import ship.ProgressLevel;
import ship.Tech;
import shipHelperz.Moneyz;
import ui.SystemListUtilz;

public enum CommandList implements SystemFactory<CommandList>{
	
	Cockpit(			"Cockpit",				ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */0.0, Moneyz.money(100, "K")),
	CommandDeck(		"Command Deck",			ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */0.0, Moneyz.money(300, "K")),
	FlagBridge(			"Flag Bridge",			ProgressLevel.PL6, Tech.N, /*Hull*/ 3,/* Pow */0.0, Moneyz.money(50, "K")),
	LaunchTower(		"Launch Tower",			ProgressLevel.PL6, Tech.N, /*Hull*/ 2,/* Pow */0.0, Moneyz.money(50, "K")),
	LaserTransceiver(	"Laser Transceiver",	ProgressLevel.PL6, Tech.N, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(50, "K")),
	RadioTransceiver(	"Radio Transceiver",	ProgressLevel.PL6, Tech.N, /*Hull*/ 0.5,/* Pow */1.0, Moneyz.money(25, "K")),
	MassTransceiver(	"Mass Transceiver",		ProgressLevel.PL7, Tech.G, /*Hull*/ 1,/* Pow */1.0, Moneyz.money(100, "K")),
	DrivesatCommArray(	"Drivesat Comm Array",	ProgressLevel.PL7, Tech.G, /*Hull*/ 150,/* Pow */300.0, Moneyz.money(2000, "M")),// ADD C tech
	DriveTransceiver(	"Drive Transceiver",	ProgressLevel.PL8, Tech.G, /*Hull*/ 2,/* Pow */2.0, Moneyz.money(200, "K")),
	PsionicTransceiver(	"Psionic Transceiver",	ProgressLevel.PL8, Tech.P, /*Hull*/ 1,/* Pow */2.0, Moneyz.money(150, "K")),
	Ansible(			"Ansible",				ProgressLevel.PL9, Tech.M, /*Hull*/ 4,/* Pow */4.0, Moneyz.money(1, "M"));


	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	protected final double powerCost, hullCost;
	protected final int creditCost;

	CommandList(String name, ProgressLevel progressLevel, Tech tech, double hullCost, double powerCost, int creditCost) {
		this.name = name;
		this.level = progressLevel;
		this.tech = tech;
		this.powerCost = powerCost;
		this.creditCost = creditCost;
		this.hullCost = hullCost;
	}
	
	public static ArrayList<String> getListTitles(){
        ArrayList<String> titles = SystemListUtilz.getListTitles();
        
        titles.add("Hull");
        titles.add("Power");
        titles.add("Cost");
        return titles;
	}
	
	public static ArrayList<ShipSystem<CommandList>> getList() {
		return SystemListUtilz.getAll(CommandList.class);
	}

	@Override
	public ShipSystem<CommandList> createInstance() {
		return new Command(this);
	}
}
