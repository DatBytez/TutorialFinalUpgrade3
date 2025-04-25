package ship;

import java.util.ArrayList;

import shipArmor.Armor;
import shipHelperz.Rollz;
import shipHull.Hull;
import shipHull.HullList;
import shipWeapons.Weapon;

public class Ship {
	// Established During Ship Creation
	private int cost = 0;
	private String name = "New Ship";
	private Hull hull;
//	private Hull hull = new Hull(HullList.None);
	private Armor armor;
	private Crew crew = Crew.MARGINAL;
	private ArrayList<ShipCompartment> compartments = new ArrayList<ShipCompartment>();
	private ArrayList<ShipSystem> systemList = new ArrayList<ShipSystem>();

	// Change During Combat
	private int stun, wound, mortal, critical;
	private boolean edge;
	private Status status = Status.ACTIVE;;

	public Ship() {
	}

	//TODO: Only used in ship fight for quick ship creation, can be removed when other ship creations methods are finished
	public Ship(String name, Hull hull, Crew crew) {
		this.name = name;
		this.hull = hull;
		this.crew = crew;
		initShip();
	}
	
	public void initShip() {
		if(hull != null) {
		stun = hull.getStun();
		wound = hull.getWound();
		mortal = hull.getMortal();
		critical = hull.getCritical();
		buildCompartments();
		}
		else {
			stun = wound = mortal = critical = 0;
		}
	}

	public void statusUpdate() {
		if (this.critical <= 0) {
			status = Status.DESTROYED;
		} else if (this.mortal <= 0) {
			status = Status.CRIPPLED;
		} else if (this.wound <= 0) {
			status = Status.DISABLED;
		} else if (this.stun <= 0) {
			status = Status.SHAKEN;
		} else {
			status = Status.ACTIVE;
		}
	}

	private Damage adjustForToughness(Damage damage) {
		int step = 0;
		do {
			if(damage.firepower.value > hull.getToughness().value) {
				damage.upgradeDamage();
				step++;
			}
			
			if(damage.firepower.value < hull.getToughness().value) {
				damage.downgradeDamage();
				step--;
			}

		} while (damage.firepower.value != hull.getToughness().value + step);
		return damage;
	}

	public void takeDamage(Damage damage) {
		damage = adjustForToughness(damage);
		int block = armor.getBlock(damage.getType());
		int mitigatedDamage = damage.damage - block; // Mitigated Damage
		if (mitigatedDamage < 0)
			mitigatedDamage = 0;

		if (damage.severity == Severity.STUN)
			takeStun(mitigatedDamage); // Primary Damage
		else if (damage.severity == Severity.WOUND) {
			takeWound(mitigatedDamage); // Primary Damage
			takeStun((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
		} else if (damage.severity == Severity.MORTAL) {
			takeMortal(mitigatedDamage); // Primary Damage
			takeStun((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
			takeWound((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
		} else if (damage.severity == Severity.CRITICAL) {
			takeCritical(mitigatedDamage); // Primary Damage
			takeStun((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
			takeWound((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
			takeMortal((int) Math.ceil((float) damage.damage / 2)); // Secondary Damage
		}
	}

	public void takeStun(int damage) {
		System.out.println(this.name + " takes " + damage + " STUN Damage");

		if (stun >= damage) {
			stun = stun - damage;
		} else {
			damage = damage - stun;
			stun = 0;
			System.out.println(" Upgrading to WOUND Damage");
			takeWound((int) Math.ceil((float) damage / 2));
		}
	}

	public void takeWound(int damage) {
		System.out.println(this.name + " takes " + damage + " WOUND Damage");

		if (wound >= damage) {
			wound = wound - damage;
		} else {
			damage = damage - wound;
			wound = 0;
			System.out.println(" Upgrading to MORTAL Damage");
			takeMortal((int) Math.ceil((float) damage / 2));
		}
	}

	public void takeMortal(int damage) {
		System.out.println(this.name + " takes " + damage + " MORTAL Damage");

		if (mortal >= damage) {
			mortal = mortal - damage;
		} else {
			damage = damage - mortal;
			mortal = 0;
			System.out.println(" Upgrading to CRITICAL Damage");
			takeCritical((int) Math.ceil((float) damage / 2));
		}
	}

	public void takeCritical(int damage) {
		System.out.println(this.name + " takes " + damage + " CRITICAL Damage");

		if (critical >= damage) {
			critical = critical - damage;
		} else {
			critical = 0;
			System.out.println("== " + this.name + " has been DESTROYED! ==");
		}
	}

	public void update() {
		statusUpdate();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public void setEdge(boolean edge) {
		this.edge = edge;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public String getName() {
		return name;
	}

	public Crew getCrew() {
		return crew;
	}

	public int getStun() {
		return stun;
	}

	public int getWound() {
		return wound;
	}

	public int getCritical() {
		return critical;
	}

	public int getMortal() {
		return mortal;
	}

	public ArrayList<Weapon> getWeapons() {
		ArrayList<Weapon> weapons = new ArrayList<>();
		for (ShipSystem system : systemList) {
		    if (system instanceof Weapon) {
		        weapons.add((Weapon) system);
		    }
		}
		return weapons;
	}

	public Armor getArmor() {
		return armor;
	}

	public Status getStatus() {
		return status;
	}

	public Hull getHull() {
		return hull;
	}

	public boolean getShipEdge() {
		return edge;
	}

	public Result rollEdgeCheck() {
		int step = status.stepPenalty;
		int roll = Rollz.skillRoll();
		int modifier = Rollz.modifier(step);
		Result check = Rollz.check(crew.score, roll+modifier);
		System.out.println(this.name+" scores a(n) "+check+" ( "+roll+"+"+modifier+" ["+step+"] )");
		return check;
	}

	public Result rollAttack(Ship target, Weapon weapon) {
		int roll = Rollz.skillRoll();
		int modifier = Rollz.modifier(target.getStatus().enemyBonus + target.getHull().getTarget() + weapon.getAccuracy());
		return Rollz.check(crew.score, roll+modifier);
	}
	
	public void setHull(Hull hull) {
		this.hull = hull;
		initShip();
	}
	
	public int getCost() {
		return cost;
	}

	public void addCost(int cost) {
		this.cost += cost;
	}
	
	private void buildCompartments() {
		compartments.clear();
		
    		switch (this.getHull().getHullType()) {
    		case SUPERHEAVY:
    			compartments.add(new ShipCompartment(this,"FFP"));
    			compartments.add(new ShipCompartment(this,"FFC")); 
    			compartments.add(new ShipCompartment(this,"FFS")); 
    			compartments.add(new ShipCompartment(this,"AAP"));
    			compartments.add(new ShipCompartment(this,"AAC")); 
    			compartments.add(new ShipCompartment(this,"AAS")); 
    			compartments.add(new ShipCompartment(this,"PC"));
    			compartments.add(new ShipCompartment(this,"SC"));
    		case HEAVY:
    			compartments.add(new ShipCompartment(this,"CA"));
    			compartments.add(new ShipCompartment(this,"CF")); 
    			compartments.add(new ShipCompartment(this,"AP"));
    			compartments.add(new ShipCompartment(this,"AS")); 			
    		case MEDIUM:
    			compartments.add(new ShipCompartment(this,"FP"));
    			compartments.add(new ShipCompartment(this,"FS"));
    		case LIGHT:
    			compartments.add(new ShipCompartment(this,"P"));
    			compartments.add(new ShipCompartment(this,"S"));
    		case SMALL:
    			compartments.add(new ShipCompartment(this,"F"));
    			compartments.add(new ShipCompartment(this,"A"));
    			if (this.getHull().getHull() <= 20) {
    				break;
    			}
    			else { // May not need this
        			compartments.add(new ShipCompartment(this,"FC"));//
        			compartments.add(new ShipCompartment(this,"AC"));//
    			}
    		}
	}

	public ArrayList<ShipCompartment> getCompartments() {
		return compartments;
	}
	
	public void addSystem(ShipSystem newSystem) {
		this.systemList.add(newSystem);
	}

	public ArrayList<ShipSystem> getSystemList() {
		return systemList;
	}

	
}