package shipHull;

import java.util.ArrayList;

import helpz.MixedList;
import helpz.ShipSystem;
import static helpz.Format.*;

public class Hull extends ShipSystem {

	private String name;
	private HullType hullType;
	private Toughness toughness;
	private int hull, hullPoints, target, maneuverability, crew;
	private int stun, wound, mortal, critical, compartmentCount;

	public Hull(HullList hull) {
		super(hull.name, hull.cost);
		this.name = hull.name;
		this.hullType = hull.hullType;
		this.hull = hull.hullPoints;
		this.toughness = hull.toughness;
		this.target = hull.target;
		this.maneuverability = hull.maneuverability;
		this.crew = hull.crew;
		setDamageTrack(hull.hullPoints);
		setHullPoints(hull.hullPoints);
		setCompartmentCount(hull.hullPoints);
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

	private void setHullPoints(int hull) {
		switch (hullType) {
		case SMALL:
			this.hullPoints = hull;
			break;
		case LIGHT:
			this.hullPoints = (int) Math.ceil((float) hull * 1.1);
			break;
		case MEDIUM:
			this.hullPoints = (int) Math.ceil((float) hull * 1.2);
			break;
		case HEAVY:
			this.hullPoints = (int) Math.ceil((float) hull * 1.3);
			break;
		case SUPERHEAVY:
			this.hullPoints = (int) Math.ceil((float) hull * 1.5);
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

	public ArrayList<Object> getProperties() {

		ArrayList<Object> properties = new ArrayList<Object>();

		properties.add(name);
		properties.add(String.valueOf(hullPoints));
		properties.add(toughness);
		properties.add(getModifierString(target));
		properties.add(String.valueOf(maneuverability));
		properties.add(String.valueOf(wound));
		properties.add(String.valueOf(mortal));
		properties.add(String.valueOf(critical));
		properties.add(String.valueOf(crew));
		properties.add(getMoneyString(cost));

		return properties;
	}

	public HullType getHullType() {
		return hullType;
	}

	public int getHull() {
		return hull;
	}

	public int getHullPoints() {
		return hullPoints;
	}

	public Toughness getToughness() {
		return toughness;
	}

	public int getTarget() {
		return target;
	}

	public int getManeuverability() {
		return maneuverability;
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

	public int getCrew() {
		return crew;
	}
	
	public int getCompartmentCount() {
		return compartmentCount;
	}
}
