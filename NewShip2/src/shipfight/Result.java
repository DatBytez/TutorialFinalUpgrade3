package shipfight;

public enum Result {
	AMAZING(3),
	GOOD(2),
	ORDINARY(1),
	FAILED(0);
	
	public int value;
	
	Result(int value){
		this.value=value;
	}
	

}
