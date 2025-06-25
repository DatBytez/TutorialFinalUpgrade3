package ship.systems;

import static ship.helpz.Format.*;

import java.util.ArrayList;

import ship.enums.Toughness;

public class Hull extends BaseSystem<HullList> {
	private HullType hullType;
	private Toughness toughness;
	private int hullProvided, target, maneuverability, crew;
	private int stun, wound, mortal, critical, compartmentCount;

	public Hull(HullList system) {
		super(system, system.name);
		this.creditCost = system.creditCost;
		this.name = system.name;
		this.hullType = system.hullType;
		this.hullProvided = system.hullProvided;
		this.toughness = system.toughness;
		this.target = system.target;
		this.maneuverability = system.maneuverability;
		this.crew = system.crew;
		setDamageTrack(system.hullProvided);
		setInitialHullPoints(system.hullProvided);
		setCompartmentCount(system.hullProvided);
		this.resizeable = false;
	}

	private void setDamageTrack(int hull) {
		switch (toughness) {
		case ORDINARY:
		case GOOD:
		case SMALL:
			this.stun = (int) Math.ceil((float) hull / 2);
			break;
		case LIGHT:
			this.stun = (int) Math.ceil((float) hull / 4);
			break;
		case MEDIUM:
			this.stun = (int) Math.ceil((float) hull / 8);
			break;
		case HEAVY:
			this.stun = (int) Math.ceil((float) hull / 16);
			break;
		case SUPERHEAVY:
			this.stun = (int) Math.ceil((float) hull / 32);
			break;
		}
		this.wound = stun;
		this.mortal = (int) Math.ceil((float) stun / 2);
		this.critical = (int) Math.ceil((float) mortal / 2);
	}

	private void setInitialHullPoints(int hull) {
		switch (hullType) {
		case SMALL:
			this.hullCost = hull;
			break;
		case LIGHT:
			this.hullCost = (int) Math.ceil((float) hull * 1.1);
			break;
		case MEDIUM:
			this.hullCost = (int) Math.ceil((float) hull * 1.2);
			break;
		case HEAVY:
			this.hullCost = (int) Math.ceil((float) hull * 1.3);
			break;
		case SUPERHEAVY:
			this.hullCost = (int) Math.ceil((float) hull * 1.5);
			break;
		}
	}

	private void setCompartmentCount(int hull) {
		switch (hullType) {
		case SMALL:
			if (hull <= 20)
				this.compartmentCount = 2;
			else
				this.compartmentCount = 4;
			break;
		case LIGHT:
			this.compartmentCount = 6;
			break;
		case MEDIUM:
			this.compartmentCount = 8;
			break;
		case HEAVY:
			this.compartmentCount = 12;
			break;
		case SUPERHEAVY:
			this.compartmentCount = 20;
			break;
		}
	}

	@Override
	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(hullCost));
		properties.add(toughness);
		properties.add(getModifierString(target));
		properties.add(String.valueOf(maneuverability));
		properties.add(String.valueOf(crew));
		properties.add(getMoneyString(creditCost));

		return properties;
	}

	@Override
	public int getCalculatedCost(Hull hull) {
		return creditCost;
	}

	@Override
	public int getCalculatedHullCost(Hull hull) {
		return hullCost * -1;
	}

	@Override
	public ShipSystem<HullList> createNewInstanceFromSelf() {
		return new Hull(systemData);
	}

	public int getBaseHullPoints() {
		return hullProvided;
	}

	public int getStun() {
		return stun;
	}

	public int getWound() {
		return wound;
	}

	public int getMortal() {
		return mortal;
	}

	public int getCritical() {
		return critical;
	}

	public Toughness getToughness() {
		return toughness;
	}

	public int getTarget() {
		return target;
	}

	public HullType getHullType() {
		return hullType;
	}
}
