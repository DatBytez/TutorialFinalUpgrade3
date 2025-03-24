package shipHull;

import java.util.ArrayList;

import helpz.MixedList;
import helpz.MyShipObject;

public class HullDescriptions {

	private String description;

	public HullDescriptions(HullList hull) {

	}

	private void getDescription(MyShipObject shipObject) {

		switch (shipObject.getName()) {
		case "Launch":
			description = "This is a boat, pinnace, or gig designed to simply move"
					+ "small amounts of people from one point to another. They’re" 
					+ "rarely armed or armored.";
			break;

		case "Courier":
			description = "The courier is a more robust ship capable of extended"
					+ "operation away from its base. Few are drive-capable.";
			break;

		case "Trader":
			description = "The smallest common commercial hull, the trader (or"
					+ "tradesman) generally carries a mix of small, high-value"
					+ "loads, personnel, and information or mail.";
			break;

		case "Fighter":
			description = "Designed for action against other small craft, fighters lack"
					+ "the punch to be very effective against large targets unless"
					+ "they’ve been designed to carry bombs or torpedoes.";
			break;

		case "Strike Fighter":
			description = "Also known as the heavy fighter, the strike fighter is usually"
					+ "armed with a bomb or torpedo capable of seriously damaging"
					+ "a large warship or tough ground target. It may or may"
					+ "not be equipped with weapons suitable for defending itself" 
					+ "against enemy fighters.";
			break;

		case "Cutter":
			description = "Many system patrol craft or police vessels fall into this category."
					+ "The cutter is rarely drive-capable. Note that a military"
					+ "ground assault ship might fall into this hull size.";
			break;

		case "Scout":
			description = "The largest small craft, a scout is designed to cover space and"
					+ "locate enemies without engaging in serious combat. A scoutship"
					+ "is 40 to 50 meters long and masses 100 to 200 tons.";
			break;

		case "Fast Freighter":
			description = "Fast freighters are a commercial hull generally employed"
					+ "in small, frequent runs, such as carrying supplies to"
					+ "small outposts and bases. The fast freighter usually carries" + "some minor defensive armament.";
			break;

		case "Fast Transport":
			description = "Designed to carry small amounts of high-bulk cargo"
					+ "such as heavy machinery or vehicles, the fast transport"
					+ "sees service in the same kind of work as the fast freighter.";
			break;

		case "Hauler":
			description = "The hauler is a spacegoing tug that drags heavy, nonpowered"
					+ "loads and modules. Most haulers are fitted with big"
					+ "power plants and huge engines, at the expense of cargo capacity" + "and crew quarters.";
			break;

		case "Industrial":
			description = "Industrial ships include a mining ship, mobile outpost, or"
					+ "similar vessel intended to carry very specialized machinery"
					+ "for a specific mission. Industrial hulls are rarely armed.";
			break;

		case "Escort":
			description = "Escorts are a long-endurance patrol craft employed for a"
					+ "variety of duties, including the protection of merchant shipping"
					+ "and remote bases. A typical escort ship is about 50 to"
					+ "70 meters long and weighs 1,000 tons. A crew of 30 to 40"
					+ "is normal, although a handful could operate the ship for a"
					+ "short time. Gunboats or missile boats could fall into this category.";
			break;

		case "Corvette":
			description = "Basically a larger version of the escort ship, the corvette"
					+ "serves as both a gunboat and fleet escort. These are the"
					+ "smallest military vessels expected to operate independently" + "in wartime.";
			break;

		case "Frigate":
			description = "A military vessel used for scouting and escort duties, the"
					+ "frigate is primarily intended to screen larger vessels"
					+ "against small craft attack. A frigate is roughly 100 to"
					+ "120 meters long and weighs about 2,000 to 3,000 tons,"
					+ "carrying a crew of about 100 men and women.";
			break;

		case "Destroyer":
			description = "Destroyers take their name from the torpedo-boat destroyers"
					+ "of the late 19th century. They’re integral to the"
					+ "defense of a task force, screening it against small craft"
					+ "and attack ships. Destroyers are often armed with a oneor"
					+ "two-shot weapons useful against much larger ships."
					+ "A destroyer is usually about 150 to 200 meters long"
					+ "and masses about 8,000 metric tons. It carries a crew of" + "150 to 200.";
		}
	}
}
