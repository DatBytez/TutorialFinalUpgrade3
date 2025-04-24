package shipArmor;

import java.util.ArrayList;

import helpz.MyShipObject;
import shipHelperz.Moneyz;
import shipfight.ProgressLevel;
import shipfight.Tech;

public enum ArmorList {

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
			
	String name, description;
	ArmorType armorType;
	ProgressLevel level;
	Tech tech;
	BlockSet blockSet;
	int cost;
	

	ArmorList(String name, ArmorType armorType, ProgressLevel progressLevel, Tech tech, BlockSet blockSet, int cost){
		this.name = name;
		this.armorType = armorType;
		this.level = progressLevel;
		this.tech = tech;
		this.blockSet = blockSet;
		this.cost = cost;
	}
	
	public static ArrayList<MyShipObject> getListArmors() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new Armor(PolymericLight));
		fullList.add(new Armor(ReflectiveLight));
		fullList.add(new Armor(CerametalLight));
		fullList.add(new Armor(CrystallisLight));
		fullList.add(new Armor(NanofluidicLight));
		fullList.add(new Armor(AlloyMedium));
		fullList.add(new Armor(PolymericMedium));
		fullList.add(new Armor(ReflectiveMedium));
		fullList.add(new Armor(CerametalMedium));
		fullList.add(new Armor(NeutroniteMedium));
		fullList.add(new Armor(ReactiveMedium));
		fullList.add(new Armor(CrystallisMedium));
		fullList.add(new Armor(NanofluidicMedium));
		fullList.add(new Armor(AlloyHeavy));
		fullList.add(new Armor(ReflectiveHeavy));
		fullList.add(new Armor(CerametalHeavy));
		fullList.add(new Armor(NeutroniteHeavy));
		fullList.add(new Armor(ReactiveHeavy));
		fullList.add(new Armor(NanofluidicHeavy));
		fullList.add(new Armor(AlloySuperHeavy));
		fullList.add(new Armor(NeutroniteSuperHeavy));
		fullList.add(new Armor(ReactiveSuperHeavy));
		fullList.add(new Armor(NanofluidicSuperHeavy));
		
		return fullList;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> listTitles = new ArrayList<String>();
		listTitles.add("Name");
		listTitles.add("PL");
		listTitles.add("Tech");
		listTitles.add("LI / HI / En");
		listTitles.add("Hull%");
		listTitles.add("Cost/Hull Pt.");
		
		return listTitles;
	}
}
