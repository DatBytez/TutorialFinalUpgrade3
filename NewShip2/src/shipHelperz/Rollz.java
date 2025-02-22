package shipHelperz;

import shipfight.Result;

public class Rollz {
	
	public static int roll(int min, int max) {
		return (min + (int)Math.floor(Math.random() * ((max - min) + 1)));
	}
	
	public static int skillRoll() {
		return roll(1, 20);
	}
	
	public static Result check(int skill, int check) {
		if (check <= (skill / 4))
			return Result.AMAZING;
		else if (check <= (skill / 2))
			return Result.GOOD;
		else if (check <= skill)
			return Result.ORDINARY;
		else
			return Result.FAILED;
	}
	
	public static int modifier(int modifier) {
		int stepModifier = modifier;

		if (modifier > 0) {
			modifier = roll(1, (modifier * 2) + 2);
//			System.out.println("+"+stepModifier+" step modifier adds " + modifier + " penalty to the roll.");
		} else if (modifier < 0) {
			modifier = -1 * roll(1, (Math.abs(modifier) * 2) + 2);
//			System.out.println(+stepModifier+" step modifier adds " + modifier + " bonus to the roll.");
		}
		return modifier;
	}

}
