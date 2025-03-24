package quests;

import java.util.ArrayList;

import entity.Entity;

public class Quest {
	
	private String name;
	private ArrayList<Boolean> steps_completed;
	private int xp_reward;
	private ArrayList<Entity> reward;
	
	public Quest() {

	}
	
	public void checkStepsCompleted() {
		
	}

	public ArrayList<Boolean> getSteps_completed() {
		return steps_completed;
	}
	
	public void setSteps_completed(int i, boolean completed) {
		steps_completed.set(i, completed);
		System.out.println("setSteps_completed "+ completed);
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
