package helpz;

import java.util.ArrayList;

public class MyShipObject {
	private ArrayList<Object> properties = new ArrayList<Object>();
	private String name;
	private boolean military = false;
	private int cost;
	private String description = "";

	public MyShipObject(String name, int cost) {
		this.name = name;
		this.cost = cost;
		setDescription();
	}

	public ArrayList<Object> getProperties() {
		return properties;
	}

	public String getName() {
		return name;
	}

	public boolean isMilitary() {
		return military;
	}

	public int getCost() {
		return cost;
	}

	public String getDescription() {
		return description;
	}

	public String getCostString() {
		if (cost < 1000)
			return ("$" + cost + " K");
		else if (cost < 1000000)
			return ("$" + (cost / 1000) + " M");
		else
			return ("$" + (cost / 1000000) + " B");
	}

	public String getModifierString(int modifier) {
		if (modifier > 0)
			return ("+" + modifier);
		else
			return ("" + modifier);
	}

	private void setDescription() {

		switch (this.name) {
		
		// ================ //
		// ===== HULL ===== //
		
		case "Launch":
			description = "This is a boat, pinnace, or gig designed to simply move "
					+ "small amounts of people from one point to another. They’re " + "rarely armed or armored. ";
			break;

		case "Courier":
			description = "The courier is a more robust ship capable of extended "
					+ "operation away from its base. Few are drive-capable. ";
			break;

		case "Trader":
			description = "The smallest common commercial hull, the trader (or "
					+ "tradesman) generally carries a mix of small, high-value "
					+ "loads, personnel, and information or mail. ";
			break;

		case "Fighter":
			description = "Designed for action against other small craft, fighters lack "
					+ "the punch to be very effective against large targets unless "
					+ "they’ve been designed to carry bombs or torpedoes. ";
			break;

		case "Strike Fighter":
			description = "Also known as the heavy fighter, the strike fighter is usually "
					+ "armed with a bomb or torpedo capable of seriously damaging "
					+ "a large warship or tough ground target. It may or may "
					+ "not be equipped with weapons suitable for defending itself " + "against enemy fighters. ";
			break;

		case "Cutter":
			description = "Many system patrol craft or police vessels fall into this category. "
					+ "The cutter is rarely drive-capable. Note that a military "
					+ "ground assault ship might fall into this hull size. ";
			break;

		case "Scout":
			description = "The largest small craft, a scout is designed to cover space and "
					+ "locate enemies without engaging in serious combat. A scoutship "
					+ "is 40 to 50 meters long and masses 100 to 200 tons. ";
			break;

		case "Fast Freighter":
			description = "Fast freighters are a commercial hull generally employed "
					+ "in small, frequent runs, such as carrying supplies to "
					+ "small outposts and bases. The fast freighter usually carries "
					+ "some minor defensive armament. ";
			break;

		case "Fast Transport":
			description = "Designed to carry small amounts of high-bulk cargo "
					+ "such as heavy machinery or vehicles, the fast transport "
					+ "sees service in the same kind of work as the fast freighter. ";
			break;

		case "Hauler":
			description = "The hauler is a spacegoing tug that drags heavy, nonpowered "
					+ "loads and modules. Most haulers are fitted with big "
					+ "power plants and huge engines, at the expense of cargo capacity " + "and crew quarters. ";
			break;

		case "Industrial":
			description = "Industrial ships include a mining ship, mobile outpost, or "
					+ "similar vessel intended to carry very specialized machinery "
					+ "for a specific mission. Industrial hulls are rarely armed. ";
			break;

		case "Escort":
			description = "Escorts are a long-endurance patrol craft employed for a "
					+ "variety of duties, including the protection of merchant shipping "
					+ "and remote bases. A typical escort ship is about 50 to "
					+ "70 meters long and weighs 1,000 tons. A crew of 30 to 40 "
					+ "is normal, although a handful could operate the ship for a "
					+ "short time. Gunboats or missile boats could fall into this category. ";
			break;

		case "Corvette":
			description = "Basically a larger version of the escort ship, the corvette "
					+ "serves as both a gunboat and fleet escort. These are the "
					+ "smallest military vessels expected to operate independently " + "in wartime. ";
			break;

		case "Frigate":
			description = "A military vessel used for scouting and escort duties, the "
					+ "frigate is primarily intended to screen larger vessels "
					+ "against small craft attack. A frigate is roughly 100 to "
					+ "120 meters long and weighs about 2,000 to 3,000 tons, "
					+ "carrying a crew of about 100 men and women. ";
			break;

		case "Destroyer":
			description = "Destroyers take their name from the torpedo-boat destroyers "
					+ "of the late 19th century. They’re integral to the "
					+ "defense of a task force, screening it against small craft "
					+ "and attack ships. Destroyers are often armed with a oneor "
					+ "two-shot weapons useful against much larger ships. "
					+ "A destroyer is usually about 150 to 200 meters long "
					+ "and masses about 8,000 metric tons. It carries a crew of " + "150 to 200. ";

		case "Medium Freighter":
			description = "The medium freighter usually carries containerized cargo. "
					+ "Most operate in regular runs between densely populated " + "systems. ";
			break;
		case "Clipper":
			description = "This is a small liner or personnel transport intended for "
					+ "passenger use, not heavy cargo. Many clippers are fitted "
					+ "with top-notch engines for the best possible speed. ";
			break;
		case "Medium Transport":
			description = "Medium transports are similar to medium freighters, but "
					+ "they are fitted for specialized cargoes such as vehicle "
					+ "decks, liquid or gas tanks, or bulk holds. ";
			break;
		case "Light Cruiser":
			description = "The light cruiser is a warship that serves several roles. It "
					+ "may be part of a task force, escorting capital ships; it may "
					+ "operate independently as a scout and raider; and finally, it "
					+ "may serve in diplomatic and scientific tasks. Light cruisers "
					+ "frequently have an outstanding endurance and can operate "
					+ "with little or no base support for months on end. An escort "
					+ "carrier or assault transport could be built on a light cruiser " + "hull. ";
			break;
		case "Heavy Cruiser":
			description = "Generally considered the smallest capital ship, a heavy cruiser "
					+ "is a serious warship. It can outfight anything it can catch, "
					+ "and outrun anything it can’t outfight. A typical heavy cruiser "
					+ "is about 250 to 300 meters in length and masses about "
					+ "50,000 metric tons. It carries a crew of 500 to 1,000. ";
			break;
		case "Armored Cruiser":
			description = "These vessels often serve as the centerpiece of a raiding or "
					+ "patrolling task force, especially if heavier warships are in "
					+ "short supply. Many armored cruisers are configured for "
					+ "task force command functions; the command cruiser is a "
					+ "fairly common variant of this hull type. A light carrier or "
					+ "assault carrier could easily fit into a ship of this hull type. ";
			break;
		case "Tanker":
			description = "A large civilian hull intended for the transport of large "
					+ "quantities of gases or liquids. While other forms of cargo "
					+ "storage may be installed, most ships of this size carry water, "
					+ "hydrogen, petrochemicals, or oxygen. ";
			break;
		case "Liner":
			description = "This is a full-sized passenger ship. While any vessel this "
					+ "size must carry some cargo, the liner specializes in moving "
					+ "a large number of passengers, usually in some degree of "
					+ "luxury. A liner is about 400 meters in length and weighs in "
					+ "at 60,000 to 90,000 tons. The crew numbers about 200, "
					+ "not counting the hotel and restaurant staff, which might be " + "as many as 1,000 more. ";
			break;
		case "Heavy Transport":
			description = "The heavy transport is designed to move a huge amount of "
					+ "specialized cargo such as bulk freight, containerized freight, "
					+ "or roll-on/roll-off vehicle storage. ";
			break;
		case "Battlecruiser":
			description = "The battlecruiser is a formidable warship that sacrifices "
					+ "weight of armor in exchange for speed. Its heavy weapons "
					+ "can make short work of any smaller vessel, and it can usually "
					+ "outrun anything big enough to stand up to it in a fair " + "fight. ";
			break;
		case "Battleship":
			description = "The mainstay of many stellar navies, the battleship is heavily "
					+ "armed and armored, although not very maneuverable. "
					+ "Battleships serve as the backbone of any battle fleet. The "
					+ "average battleship is roughly 400 to 500 meters in length "
					+ "and masses about 150,000 tons, with a crew of about " + "2,000 men and women. ";
			break;
		case "Fleet Carrier":
			description = "Designed to carry large numbers of strike craft and interceptors, "
					+ "the fleet carrier has the ability to launch devastating "
					+ "attacks from millions of kilometers away. Most are very "
					+ "lightly armed, relying on their escorting vessels to defend " + "them against attack. ";
			break;
		case "Super-Freighter":
			description = "The largest ships built for routine commercial purposes, the "
					+ "super-freighter can be fitted with tanks or other specialized "
					+ "cargo facilities to make it into a super-tanker or supertransport. ";
			break;
		case "Colony Transport":
			description = "This colossal ship is designed to haul everything a new "
					+ "colony needs to a new star system. Thousands upon thousands "
					+ "of colonists with heavy machinery, prefabricated "
					+ "buildings, equipment, and supplies can fit within its cavernous " + "storage decks. ";
			break;
		case "Dreadnought":
			description = "The mightiest ships found in the battle lines of a stellar "
					+ "navy, dreadnoughts are titans armed with awesome firepower "
					+ "and impregnable defenses. A dreadnought is around "
					+ "800 to 1,200 meters long and weighs about 600,000 to "
					+ "1,000,000 tons. Typically, a crew of 4,000 to 5,000 is required. ";
			break;
		case "Super-Carrier":
			description = "Like the fleet carrier, the super-carrier is designed specifically "
					+ "to operate large numbers of small craft. A super-carrier "
					+ "might have an air wing of 200 to 300 interceptors and " + "strike craft. ";
			break;
		case "Super-Dreadnought":
			description = "Sometimes found serving as fleet command ships or flagships, "
					+ "the super-dreadnought is roughly twice the size of its " + "smaller namesake. ";
			break;
		case "Fortress Ship":
			description = "The fortress ship combines the best features of the dreadnought "
					+ "and the super-carrier. It is armed with an impressive "
					+ "main battery, and it embarks hundreds (or maybe even "
					+ "thousands) of small craft, too. A fortress ship might be "
					+ "2,500 to 4,000 meters in length and weigh as much as "
					+ "3–5 million metric tons. Crew and passengers could easily "
					+ "approach 100,000 men and women. Like any super-heavy "
					+ "military ships, fortress ships are national assets that serve "
					+ "as the centerpieces of sector defense fleets; they’re almost "
					+ "always accompanied by an escort of dozens of cruisers and " + "destroyers. ";
			break;
			
			// ================= //
			// ===== ARMOR ===== //
			
		case "Alloy, Medium":
		case "Alloy, Heavy":
		case "Alloy, Super-Heavy":
			description = "Composed of carefully fitted belts or plates of very tough alloys "
					+ "such as vanadium steel, alloy armor is reasonably "
					+ "cheap and effective.";
			break;
			
		case "Polymeric, Light":
		case "Polymeric, Medium":
			description = "Polymeric armor is made up of advanced polymers, like carbon "
					+ "fiber and high-grade fiberglass. It is relatively cheap and "
					+ "light, but doesn’t offer tremendous protection.";
			break;
			
		case "Reflective, Light":
		case "Reflective, Medium":
		case "Reflective, Heavy":
			description = "Consisting of dense, highly polished plates of metal, reflective "
					+ "armor is effective against simple energy weapons. It is "
					+ "quickly defeated by more advanced weaponry.";
			break;
			
		case "Cerametal, Light":
		case "Cerametal, Medium":
		case "Cerametal, Heavy":	
			description = "Combining the heat-resistant qualities of tough ceramics "
					+ "with the ductile strength of metal, cerametal armor offers a "
					+ "good compromise between protection and economy.";
			break;
			
		case "Neutronite, Medium":
		case "Neutronite, Heavy":
		case "Neutronite, Super-Heavy":
		description = "Advanced materials technologies lead to the creation of artificially "
				+ "dense materials that can withstand enormous forces. "
				+ "Neutronite is a tough steel alloy into which a “weave” of free "
				+ "neutrons has been pressed. It is incredibly massive, weighing "
				+ "about five times more than a similar volume of lead.";
			break;
			
		case "Reactive, Medium":
		case "Reactive, Heavy":
		case "Reactive, Super":
		description = "A forerunner of the more advanced nanofluidic armor, reactive "
				+ "armor consists of layers of insulating gel or compressed "
			+ "gas between cerametal sheets. High-velocity impacts "
			+ "and energy beams may burn through the armor before "
			+ "it can dissipate the impact or heat of an intense blow.";
			break;
			
		case "Crystallis, Light":
		case "Crystallis, Medium":
		description = "This sophisticated armor consists of a crystalline lattice that "
				+ "absorbs phenomenal amounts of energy. While it is not "
			+ "particularly effective against projectiles and other massive "
			+ "weapons, it is the finest energy protection available.";
			break;
			
		case "Nanofluidic, Light":
		case "Nanofluidic, Medium":
		case "Nanofluidic, Heavy":
		case "Nanofluidic, Super":
		description = "Consisting of a thick layer of gel-like fluid sandwiched in a "
				+ "neutronite structure, nanofluidic armor is “smart” armor—it "
			+ "concentrates at the point of impact to blunt physical blows, "
			+ "and circulates around heat sources to dissipate energy. It is "
			+ "the most effective armor available. ";
			break;
		}
	}
}
