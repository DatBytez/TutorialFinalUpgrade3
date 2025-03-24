package quests;

import java.util.ArrayList;

import entity.Entity;
import tile.Tile;

public class QST_Sign_The_Waiver extends Quest{
	
	private String name;
	private ArrayList<Boolean> steps_completed = new ArrayList<>();
	private int xp_reward;
	private ArrayList<Entity> reward;
	
	public QST_Sign_The_Waiver() {
		
		this.name = "Sign the Waiver";
		this.xp_reward = 20;
		this.steps_completed.add(Boolean.FALSE);
	}
	
	public void checkStepsCompleted() {
		
	}

	public ArrayList<Boolean> getSteps_completed() {
		return steps_completed;
	}
	
	public void setSteps_completed(int i, boolean completed) {
		steps_completed.set(i, completed);
	}

	public String getName() {
		return name;
	}

	public int getXp_reward() {
		return xp_reward;
	}

	public ArrayList<Entity> getReward() {
		return reward;
	}
	
	
	
	

}
