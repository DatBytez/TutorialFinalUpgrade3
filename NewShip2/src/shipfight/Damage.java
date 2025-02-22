package shipfight;

import shipWeapons.DamageType;
import shipWeapons.Firepower;

public class Damage {
	int damage;
	Severity severity;
	DamageType type;
	Firepower firepower;
	int damageMultiplier = 1;
	
	public Damage(int damage, Severity severity, DamageType type, Firepower firepower) {
		this.damage=damage;
		this.severity=severity;
		this.type=type;
		this.firepower=firepower;
	}
	
	public void upgradeDamage() {
		switch (severity) {
		case MISS:
			break;
		case STUN:
			severity = Severity.WOUND;
			break;
		case WOUND:
			severity = Severity.MORTAL;
			break;
		case MORTAL:
			severity = Severity.CRITICAL;
			break;
		case CRITICAL:
			damage = damage/damageMultiplier;
			damageMultiplier++;
			damage = damage*damageMultiplier;
		}
		
		System.out.println("Damage upgraded to "+severity+" (x"+damageMultiplier+")");
	}
	
	public void downgradeDamage() {
		switch (severity) {
		case CRITICAL:
			severity = Severity.MORTAL;
			break;
		case MORTAL:
			severity = Severity.WOUND;
			break;
		case WOUND:
			severity = Severity.STUN;
			break;
		case STUN:
			damage = 0;
			break;
		case MISS:
			break;
		}
		
		System.out.println("Damage downgraded to "+severity);
	}

	public DamageType getType() {
		return type;
	}
}
