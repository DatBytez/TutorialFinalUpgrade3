package ui;

import java.util.ArrayList;

import ship.helpz.SystemFactory;
import ship.systems.ArmorList;
import ship.systems.CommandList;
import ship.systems.ComputerList;
import ship.systems.DefenseList;
import ship.systems.EngineList;
import ship.systems.FTLList;
import ship.systems.HullList;
import ship.systems.MiscellaneousList;
import ship.systems.PowerList;
import ship.systems.SensorList;
import ship.systems.ShipSystem;
import ship.systems.SupportList;
import ship.systems.WeaponList;

public class SystemListUtilz {

	public static MyButtonList<?> createList(String title, int x, int y, int width) {
		switch (title) {
		case "CIVILIAN":
			return new MyButtonList<>("CIVILIAN HULLS", HullList.getCivilianHulls(), HullList.getListTitles(), x, y, width);
		case "MILITARY":
			return new MyButtonList<>("MILITARY HULLS", HullList.getMilitaryHulls(), HullList.getListTitles(), x, y, width);
		case "ARMOR":
			return new MyButtonList<>("ARMOR", ArmorList.getList(), ArmorList.getListTitles(), x, y, width);
		case "SUBSPACE":
			return new MyButtonList<>("SUBSPACE", EngineList.getList(), EngineList.getListTitles(), x, y, width);
		case "FTL":
			return new MyButtonList<>("FTL", FTLList.getList(), FTLList.getListTitles(), x, y, width);
		case "BEAMS":
			return new MyButtonList<>("BEAMS", WeaponList.getListBeams(), WeaponList.getListTitles(), x, y, width);
		case "PROJECTILES":
			return new MyButtonList<>("PROJECTILES", WeaponList.getListProjectiles(), WeaponList.getListTitles(), x, y, width);
		case "ORDINANCES":
			return new MyButtonList<>("ORDINANCES", WeaponList.getListOrdinances(), WeaponList.getListTitles(), x, y, width);
		case "POWER":
			return new MyButtonList<>("POWER", PowerList.getList(), PowerList.getListTitles(), x, y, width);
		case "DEFENSES":
			return new MyButtonList<>("DEFENSES", DefenseList.getList(), DefenseList.getListTitles(), x, y, width);
		case "SUPPORT":
			return new MyButtonList<>("SUPPORT", SupportList.getList(), SupportList.getListTitles(), x, y, width);
		case "SPECIAL":
			return new MyButtonList<>("SPECIAL", WeaponList.getListSpecial(), WeaponList.getListTitles(), x, y, width);
		case "SENSORS":
			return new MyButtonList<>("SENSORS", SensorList.getList(), SensorList.getListTitles(), x, y, width);
		case "MISCELLANEOUS":
			return new MyButtonList<>("MISCELLANEOUS", MiscellaneousList.getList(), MiscellaneousList.getListTitles(), x, y, width);
		case "COMMAND":
			return new MyButtonList<>("COMMAND", CommandList.getList(), CommandList.getListTitles(), x, y, width);
		case "COMPUTERS":
			return new MyButtonList<>("COMPUTERS", ComputerList.getList(), ComputerList.getListTitles(), x, y, width);
		default:
			return null;
		}
	}

	public static <T extends Enum<T> & SystemFactory<T>> ArrayList<ShipSystem<T>> getAll(Class<T> enumClass) {
		ArrayList<ShipSystem<T>> list = new ArrayList<>();
		for (T system : enumClass.getEnumConstants()) {
			list.add(system.createInstance());
		}
		return list;
	}
	
    public static ArrayList<String> getListTitles() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Name");
        titles.add("PL");
        titles.add("Tech");

        return titles;
    }
}