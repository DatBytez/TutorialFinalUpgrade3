package helpz;


import java.text.DecimalFormat;

public class Format {


	public static String getMoneyString(int value) {
	    float fvalue = value;
	    DecimalFormat noDecimal = new DecimalFormat("#");
	    DecimalFormat oneDecimal = new DecimalFormat("#.#");

	    if (fvalue < 1000) {
	        return "$" + noDecimal.format(fvalue) + " K";
	    } else if (fvalue < 1_000_000) {
	        float result = fvalue / 1000;
	        return "$" + (result % 1 == 0 ? noDecimal.format(result) : oneDecimal.format(result)) + " M";
	    } else {
	        float result = fvalue / 1_000_000;
	        return "$" + (result % 1 == 0 ? noDecimal.format(result) : oneDecimal.format(result)) + " B";
	    }
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
