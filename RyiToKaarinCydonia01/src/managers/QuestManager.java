package managers;

import java.util.ArrayList;

import quests.QST_Sign_The_Waiver;
import quests.Quest;

public class QuestManager {
	public ArrayList<Quest> quests = new ArrayList<>();
	
	public Quest sign_the_waiver = new QST_Sign_The_Waiver();
	
	public QuestManager() {
		loadQuests();
	}
	
	private void loadQuests() {
		
		quests.add(sign_the_waiver);
	}

}
