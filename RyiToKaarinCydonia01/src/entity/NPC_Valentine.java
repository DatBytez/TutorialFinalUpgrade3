package entity;

import gfx.ImageLoader;
import scenes.GameState;
import scenes.Playing;

public class NPC_Valentine extends Character {

	public NPC_Valentine(Playing playing) {
		super(playing);
		texture = ImageLoader.loadImage("/textures/valentine_sheet");
		
		dialogueSet =0;

		name = "Sir Valentine";
		getImage(texture);
		setPortrait();
		setDialogue();
	}

	public void setDialogue() {
		dialogues[0][0] = ""
				+ "  Welcome to Wizard Party! \n"
				+ "  I am Sir Valentine, Scroll Keeper and Loremaster.";
		
		dialogues[1][0] = ""
				+ "  The waiver? Sure! Just sign here!";

	  
		dialogues[2][0] = "So, Have you decided on a Wizard House yet?";
		dialogues[2][1] = ""
				+ "  [1] Yes, House Wiseaufang. [4] Yes, House Zylvandrake.\n"
				+ "  [2] Yes, House Gryznak.         [5] Yes, House Scuttlepuff.\n"
				+ "  [3] Yes, House Ruplemore.   [6] No, Please pick for me!\n"
				+ "  [7] No, Can you tell me more about the houses?";
		
		dialogues[3][0] = ""
				+ "  House Wiseaufang! What an excelent choice.\n"
				+ "  Here are a few [Kellans] to get you started.\n"
				+ "  Please take this [House Letter] to [Wanda Wiseaufang]\n"
				+ "  You may be able to find her lounging in the PPP to the South West.";
		
		dialogues[4][0] = ""
				+ "  House Gryznak! What an excelent choice.\n"
				+ "  Here are a few [Kellans] to get you started.\n"
				+ "  Please take this [House Letter] to [Glinda Gryznak]\n"
				+ "  You may be able to find her in The Great Hall Next Door.";
		
		dialogues[5][0] = ""
				+ "  House Ruplemore! What an excelent choice.\n"
				+ "  Here are a few [Kellans] to get you started.\n"
				+ "  Please take this [House Letter] to [Regina Ruplemoor]\n"
				+ "  You may be able to find her out by the Dance Floor.";
		
		dialogues[6][0] = ""
				+ "  House Zylvendrake! What an excelent choice.\n"
				+ "  Here are a few [Kellans] to get you started.\n"
				+ "  Please take this [House Letter] to [Xeniz Zylvandrake]\n"
				+ "  You may be able to find her in her Sky Ship to the South.";
		
		dialogues[7][0] = ""
				+ "  House Scuttlepuff! What an excelent choice.\n"
				+ "  Here are a few [Kellans] to get you started.\n"
				+ "  Please take this [House Letter] to [Claus Klaws]\n"
				+ "  You may be able to find him by the Goblin Hut to the East.";
		


	}

	public void setAction() {
		
		moveRandom();
	}
	
	public void speak() {
		facePlayer();
			
		System.out.println("Waver Signed: " + playing.questManager.sign_the_waiver.getSteps_completed().get(0));
		System.out.println("Dialogue Set: " + dialogueSet);
		if(dialogueSet == 1) {
//			if(!gamePanel.questManager.sign_the_waiver.getSteps_completed().get(0)) {
				playing.questManager.sign_the_waiver.setSteps_completed(0,true);
				playing.gameState = GameState.TRADE;
				
				System.out.println("Waver Signed: " + playing.questManager.sign_the_waiver.getSteps_completed().get(0));
//			}
		}

		if(dialogueSet >= 2) {
			playing.ui.npc = this;
		}
		System.out.println("Dialogue Set: " + dialogueSet);
		startDialogue(this, dialogueSet);

		dialogueSet++;
		if(dialogues[dialogueSet][0] == null) {//TODO: Could be turned into a trait for each npc that talks, and could be auto set to always repeat last unless changed.
//			dialogueSet = 0; // Start from first statement.
			dialogueSet--; // Repeat the last statement.
		}
	}
	
	public boolean isQuestion(int dialogueSet, int dialogueIndex) {//TODO: Must be changed to an object. Maybe Quests?
		if(dialogueSet == 0 && dialogueIndex == 1) {
			return true;
		}
		return false;
	}
	
}
