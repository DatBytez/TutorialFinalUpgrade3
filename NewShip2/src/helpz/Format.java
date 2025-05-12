package helpz;

public class Format {
	
	public static String getMoneyString(int value) {
		if (value < 1000)
			return ("$" + value + " K");
		else if (value < 1000000)
			return ("$" + (value / 1000) + " M");
		else
			return ("$" + (value / 1000000) + " B");
	}
	
	public static String getBooleanString(boolean value) {
		if(value)
			return "Yes";
		return "No";
	}
	
	public static String getDashedString(String inputString) {
		if (inputString.equals("$0 K") || inputString.equals("0"))
			return "-";
		return inputString;
	}
	
	public static String getDashedString(int inputInt) {
		String inputString = String.valueOf(inputInt);
		if (inputString.equals("$0 K") || inputString.equals("0"))
			return "-";
		return inputString;
	}
	
	public static String getModifierString(int modifier) {
		if (modifier > 0)
			return ("+" + modifier);
		else
			return ("" + modifier);
	}
}
