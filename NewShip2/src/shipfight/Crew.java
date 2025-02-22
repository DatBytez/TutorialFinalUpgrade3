package shipfight;

public enum Crew {
MARGINAL("Green",10),
ORDINARY("Trained",12),
GOOD("Veteran",14),
AMAZING("Crack",16),
LEGENDARY("Legendary",18);
	
	public String description;
	public int score;
	
	Crew(String description, int score){
		this.description = description;
		this.score = score;
	}

}
