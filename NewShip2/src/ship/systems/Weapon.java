package ship.systems;

import static ship.helpz.Format.*;

import java.util.ArrayList;

import actions.Rollz;
import ship.Tech;
import ship.enums.Damage;
import ship.enums.DamageSet;
import ship.enums.DamageType;
import ship.enums.FireRange;
import ship.enums.Firepower;
import ship.enums.ProgressLevel;
import ship.enums.Result;
import ship.enums.Severity;
import ship.enums.WeaponModes;

public class Weapon extends BaseSystem<WeaponList> {
	private int accuracy;
	private DamageType damageType;
	private FireRange range;
	private Firepower firepower;
	private WeaponModes modes;
	private DamageSet damageSet;
	private Damage damage;
	private WeaponType weaponType;
	
	public Weapon(WeaponList system) {
		super(system, system.name);
		this.name = system.name;
		this.weaponType = system.weaponType;
		this.level = system.level;
		this.tech = system.tech;
		this.hullCost = system.hullCost;
		this.powerCost = system.powerCost;
		this.creditCost = system.creditCost;
		this.accuracy = system.accuracy;
		this.damageType = system.damageType;
		this.range = system.range;
		this.firepower = system.firepower;
		this.modes = system.modes;
		this.damageSet = system.damageSet;
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
		properties.add(String.valueOf(hullCost));
		properties.add(String.valueOf(powerCost));
		properties.add(getMoneyString(creditCost));
		properties.add(getModifierString(accuracy));
		properties.add(range);
		properties.add(damageType + "/" + firepower);
		properties.add(damageSet);
		properties.add(modes);

		return properties;
	}

	public WeaponType getWeaponType() {
		return weaponType;
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

	@Override
	public int getCalculatedCost(Hull hull) {
		return creditCost;
	}

	@Override
	public int getCalculatedHullCost(Hull hull) {
		return hullCost;
	}

	@Override
	public ShipSystem<WeaponList> createNewInstanceFromSelf() {
	    return new Weapon(systemData);
	}
}