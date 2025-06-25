package ship.systems;

import java.util.ArrayList;

import ship.Tech;
import ship.enums.BlockSet;
import ship.enums.ProgressLevel;
import ship.helpz.Moneyz;
import ship.helpz.SystemFactory;
import ui.SystemListUtilz;

public enum ArmorList implements SystemFactory<ArmorList> {

	PolymericLight(		"Polymeric, Light", 	ArmorType.LIGHT, ProgressLevel.PL6, Tech.N, new BlockSet(0,3,0,3,-1,2), Moneyz.money(50, "K")),
	ReflectiveLight(	"Reflective, Light", 	ArmorType.LIGHT, ProgressLevel.PL6, Tech.N, new BlockSet(-2,1,-1,2,0,5), Moneyz.money(50, "K")),
	CerametalLight(		"Cerametal, Light", 	ArmorType.LIGHT, ProgressLevel.PL7, Tech.N, new BlockSet(0,5,0,5,0,5), Moneyz.money(100, "K")),
	CrystallisLight(	"Crystallis, Light", 	ArmorType.LIGHT, ProgressLevel.PL8, Tech.P, new BlockSet(0,5,1,6,3,8), Moneyz.money(250, "K")), //Add Tech.X
	NanofluidicLight(	"Nanofluidic, Light", 	ArmorType.LIGHT, ProgressLevel.PL8, Tech.S, new BlockSet(0,7,0,7,1,8), Moneyz.money(500, "K")), //Add Tech.C
	
	AlloyMedium(		"Alloy, Medium",	 	ArmorType.MEDIUM, ProgressLevel.PL6, Tech.N, new BlockSet(2,5,2,5,1,4), Moneyz.money(150, "K")),
	PolymericMedium(	"Polymeric, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL6, Tech.N, new BlockSet(1,4,1,4,0,3), Moneyz.money(100, "K")),
	ReflectiveMedium(	"Reflective, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL6, Tech.N, new BlockSet(-1,2,0,3,1,6), Moneyz.money(100, "K")),
	CerametalMedium(	"Cerametal, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL7, Tech.N, new BlockSet(2,5,2,5,2,5), Moneyz.money(200, "K")),
	NeutroniteMedium(	"Neutronite, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL7, Tech.S, new BlockSet(2,7,2,7,2,7), Moneyz.money(500, "K")),
	ReactiveMedium(		"Reactive, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL7, Tech.N, new BlockSet(3,6,1,6,1,4), Moneyz.money(150, "K")),
	CrystallisMedium(	"Crystallis, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL8, Tech.P, new BlockSet(1,6,2,7,3,9), Moneyz.money(500, "K")), //Add Tech.X
	NanofluidicMedium(	"Nanofluidic, Medium", 	ArmorType.MEDIUM, ProgressLevel.PL8, Tech.S, new BlockSet(2,7,2,7,2,7), Moneyz.money(1, "M")), //Add Tech.C
	
	AlloyHeavy(			"Alloy, Heavy", 		ArmorType.HEAVY, ProgressLevel.PL6, Tech.N, new BlockSet(2,7,2,7,1,6), Moneyz.money(300, "K")),
	ReflectiveHeavy(	"Reflective, Heavy",	ArmorType.HEAVY, ProgressLevel.PL6, Tech.N, new BlockSet(1,4,1,4,2,9), Moneyz.money(200, "K")),
	CerametalHeavy(		"Cerametal, Heavy", 	ArmorType.HEAVY, ProgressLevel.PL7, Tech.N, new BlockSet(1,8,1,8,1,8), Moneyz.money(400, "K")),
	NeutroniteHeavy(	"Neutronite, Heavy", 	ArmorType.HEAVY, ProgressLevel.PL7, Tech.S, new BlockSet(2,9,2,9,2,9), Moneyz.money(1, "M")),
	ReactiveHeavy(		"Reactive, Heavy", 		ArmorType.HEAVY, ProgressLevel.PL7, Tech.N, new BlockSet(3,9,1,8,2,5), Moneyz.money(300, "K")),
	NanofluidicHeavy(	"Nanofluidic, Heavy",	ArmorType.HEAVY, ProgressLevel.PL8, Tech.S, new BlockSet(3,9,4,10,3,9), Moneyz.money(2, "M")), //Add Tech.C
	
	AlloySuperHeavy(		"Alloy, Super-Heavy",		ArmorType.SUPERHEAVY, ProgressLevel.PL6, Tech.N, new BlockSet(4,9,4,9,3,8), Moneyz.money(600, "K")),
	NeutroniteSuperHeavy(	"Neutronite, Super-Heavy",	ArmorType.SUPERHEAVY, ProgressLevel.PL7, Tech.S, new BlockSet(4,11,4,11,4,11), Moneyz.money(2, "M")),
	ReactiveSuperHeavy(		"Reactive, Super-Heavy",	ArmorType.SUPERHEAVY, ProgressLevel.PL7, Tech.N, new BlockSet(5,11,3,10,4,7), Moneyz.money(600, "K")),
	NanofluidicSuperHeavy(	"Nanofluidic, Super-Heavy",	ArmorType.SUPERHEAVY, ProgressLevel.PL8, Tech.S, new BlockSet(5,11,6,12,5,11), Moneyz.money(4, "M")); //Add Tech.C
			
	protected final String name;
	protected final ProgressLevel level;
	protected final Tech tech;
	
	protected final int baseCost;
	
	protected final ArmorType armorType;
	protected final BlockSet blockSet;

	
	ArmorList(String name, ArmorType armorType, ProgressLevel progressLevel, Tech tech, BlockSet blockSet, int baseCost){
		this.name = name;
		this.armorType = armorType;
		this.level = progressLevel;
		this.tech = tech;
		this.blockSet = blockSet;
		this.baseCost = baseCost;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("Name");
		titles.add("PL");
		titles.add("Tech");
		titles.add("LI / HI / En");
		titles.add("Hull%");
		titles.add("Cost/Hull Pt.");
		
		return titles;
	}
	
	public static ArrayList<ShipSystem<ArmorList>> getList() {
		return SystemListUtilz.getAll(ArmorList.class);
	}
	
	@Override
	public ShipSystem<ArmorList> createInstance() {
		return new Armor(this);
	}
}
