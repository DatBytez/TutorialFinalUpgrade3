package ship.helpz;

public final class Moneyz {
	
	public static int money(int value, String multiplier) {
		if(multiplier == "K")
			return value*1;
		else if(multiplier == "M")
			return value*1000;
		else if(multiplier == "B")
			return value*1000000;
		else if(multiplier == "T")
			return value*1000000000;
		else {
			System.out.println("Incorrect Moneyz multiplier");
			return 0;
		}
	}
	
	public String money(int value) {
		if(value > 1000000000)
			return ""+value/1000000000+"T";
		else if(value > 1000000)
			return ""+value/1000000+"B";
		else if(value > 1000)
			return ""+value/1000+"M";
		else
			return ""+value+"K";
	}

}
