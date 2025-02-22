package shipfight;

public enum Status {
	ACTIVE(0,0,0),
	SHAKEN(1,0,0),
	DISABLED(2,-1,-1),
	CRIPPLED(3,-2,-2),
	DESTROYED(0,0,0);
	
	public int stepPenalty, movePenalty, enemyBonus;
	
	Status(int stepPenalty, int movePenalty, int enemyBonus){
		this.stepPenalty = stepPenalty;
		this.movePenalty = movePenalty;
		this.enemyBonus = enemyBonus;
	}
	
	

}
