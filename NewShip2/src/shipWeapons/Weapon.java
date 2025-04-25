package shipWeapons;

import java.util.ArrayList;

import ship.Damage;
import ship.ProgressLevel;
import ship.Result;
import ship.Severity;
import ship.ShipSystem;
import ship.Tech;
import shipHelperz.Rollz;

import static helpz.Format.*;

public class Weapon extends ShipSystem{
	private String name;
	private WeaponType weaponType;
	private ProgressLevel level;
	private Tech tech;
	private int hull, power, cost, accuracy;
	private DamageType damageType;
	private FireRange range;
	private Firepower firepower;
	private WeaponModes modes;
	private DamageSet damageSet;
	private Damage damage;
	
	public Weapon(WeaponList weapon) {
		super(weapon.name, weapon.cost);
		this.name = weapon.name;
		this.weaponType = weapon.weaponType;
		this.level = weapon.level;
		this.tech = weapon.tech;
		this.hull = weapon.hull;
		this.power = weapon.power;
		this.cost = weapon.cost;
		this.accuracy = weapon.accuracy;
		this.damageType = weapon.damageType;
		this.range = weapon.range;
		this.firepower = weapon.firepower;
		this.modes = weapon.modes;
		this.damageSet = weapon.damageSet;
	}
	
	public Damage getDamage(Result result) {
		int dmg = 0;
		Severity severity = Severity.MISS;
		DamageType type = this.damageType;
		
		switch(result) {
		case AMAZING:	
			dmg = Rollz.roll(damageSet.getaMin(), damageSet.getaMax());
			severity = damageSet.getaSev();
			break;
		case GOOD:	
			dmg = Rollz.roll(damageSet.getgMin(), damageSet.getgMax());
			severity = damageSet.getgSev();
			break;
		case ORDINARY:	
			dmg = Rollz.roll(damageSet.getoMin(), damageSet.getoMax());
			severity = damageSet.getoSev();
			break;
		case FAILED:	
			dmg = 0;
			severity = Severity.MISS;
			break;
		}
		Damage damage = new Damage(dmg,severity,type,firepower);
		
		if(severity==Severity.MISS)
			System.out.println(name+" Misses!");
		else
			System.out.println(name+" scores a "+result+" hit dealing "+dmg+" "+severity+" ("+firepower+" "+type+")");
		
		return damage;
	}
	
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(tech);
		properties.add(String.valueOf(hull));
		properties.add(String.valueOf(power));
		properties.add(getMoneyString(cost));
		properties.add(getModifierString(accuracy));
		properties.add(range);
		properties.add(damageType +"/" + firepower);
		properties.add(damageSet);
		properties.add(modes);

		return properties;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public ProgressLevel getLevel() {
		return level;
	}

	public Tech getTech() {
		return tech;
	}

	public int getHull() {
		return hull;
	}

	public int getPower() {
		return power;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public FireRange getRange() {
		return range;
	}

	public Firepower getFirepower() {
		return firepower;
	}

	public WeaponModes getModes() {
		return modes;
	}

	public DamageSet getDamageSet() {
		return damageSet;
	}

	public Damage getDamage() {
		return damage;
	}
	
	
}