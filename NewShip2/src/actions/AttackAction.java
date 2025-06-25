package actions;

import java.util.ArrayList;

import ship.Ship;
import ship.enums.Result;
import ship.systems.Weapon;

public class AttackAction {

//	public static void attack(Ship attacker, Ship target) {
//		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
//		weapons = attacker.getWeapons();
//		System.out.println(" -------------------------------- ");
//		System.out.println(attacker.getName()+ " is attacking!");
//		for (Weapon weapon : weapons) {
//			System.out.println("");
//			System.out.println("Firing "+weapon.getName()+"!");
//			fireWeapon(attacker.rollAttack(target, weapon),weapon, target);
//		}
//	}
	public static void fullAttack(Ship attacker, Ship target) {
		for (Weapon weapon : attacker.getWeapons()) {
			basicFire(attacker, weapon, target);
		}
	}

	public static void basicFire(Ship attacker, Weapon weapon, Ship target) {
		int step = attacker.getStatus().enemyBonus+target.getHull().getTarget()+weapon.getAccuracy();
		int roll = Rollz.skillRoll();
		int modifier = Rollz.modifier(step);
		Result check = Rollz.check(attacker.getCrew().score, roll+modifier);
		System.out.println(attacker.getName()+" scores a(n) "+check+" ( "+roll+"+"+modifier+" ["+step+"] )");
		target.takeDamage(weapon.getDamage(check));
	}
	
	public static void burstFire(Ship attacker, Weapon weapon, Ship target) {
		int step = attacker.getStatus().enemyBonus+target.getHull().getTarget()+weapon.getAccuracy()-1;
		int roll = Rollz.skillRoll();
		int modifier = Rollz.modifier(step);
		Result check = Rollz.check(attacker.getCrew().score, roll+modifier);
		System.out.println(attacker.getName()+" scores a(n) "+check+" ( "+roll+"+"+modifier+" ["+step+"] )");
		target.takeDamage(weapon.getDamage(check));
	}
	
	public static void autoFire(Ship attacker, Weapon weapon, Ship target) {
		int roll = Rollz.skillRoll();
		for(int i = 1;i<=3;i++) {
			int step = attacker.getStatus().enemyBonus+target.getHull().getTarget()+weapon.getAccuracy()+i;
			int modifier = Rollz.modifier(step);
			Result check = Rollz.check(attacker.getCrew().score, roll+modifier);
			System.out.println(attacker.getName()+" scores a(n) "+check+" ( "+roll+"+"+modifier+" ["+step+"] )");
			target.takeDamage(weapon.getDamage(check));
		}
	}
	
	public static void batteryFire(Ship attacker, Weapon weapon, Ship target) {
		int roll = Rollz.skillRoll();
		int count = 4;
		for(int i = 0;i<count;i++) {
			int step = attacker.getStatus().enemyBonus+target.getHull().getTarget()+weapon.getAccuracy()-i;
			int modifier = Rollz.modifier(step);
			Result check = Rollz.check(attacker.getCrew().score, roll+modifier);
			System.out.println(attacker.getName()+" scores a(n) "+check+" ( "+roll+"+"+modifier+" ["+step+"] )");
			target.takeDamage(weapon.getDamage(check));
		}
	}

}
