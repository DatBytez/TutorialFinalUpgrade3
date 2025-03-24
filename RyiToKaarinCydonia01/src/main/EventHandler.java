package main;

import entity.Entity;
import scenes.GameState;
import scenes.Playing;

public class EventHandler{ //TODO: Events should be a type of Entity and event handler should just be a list of them.
	Playing playing;
	EventRect eventRect[][][];
	Entity eventMaster;

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempRow, tempCol;

	public EventHandler(Playing playing) {
		this.playing = playing;
		
		eventMaster = new Entity(playing);

		eventRect = new EventRect[playing.maxMap][playing.maxWorldCol][playing.maxWorldRow];

		int map = 0;
		int col = 0;
		int row = 0;
		while (map < playing.maxMap && col < playing.maxWorldCol && row < playing.maxWorldRow) {

			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 6;
			eventRect[map][col][row].height = 6;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

			col++;
			if (col == playing.maxWorldCol) {
				col = 0;
				row++;

				if (row == playing.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		setDialogue();
	}
	
	public void setDialogue() { // TODO: If events become a subclass of entity, they will have their own dialogues instead of sharing a bunch like this.
		
		eventMaster.dialogues[0][0] = "You trip on a stick!";
		eventMaster.dialogues[1][0] = "You drink the water.\nYou have been rejuvenated!\n" + "(Game Progress Saved)";
		eventMaster.dialogues[1][1] = "That was very refreshing!";
	}

	public void checkEvent() {

		// Check if the player character is more than 1 tile away from the last event.
		int xDistance = Math.abs(playing.player.worldX - previousEventX);
		int yDistance = Math.abs(playing.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > playing.tileSize) {
			canTouchEvent = true;
		}

		if (canTouchEvent) {
			if (hit(0, 26, 16)) {
				damagePit(GameState.DIALOGUE);
			}
			else if (hit(0, 22, 20)) {
				damagePit(GameState.DIALOGUE);
			}
			else if (hit(0, 23, 7)) {
				healingPool(GameState.DIALOGUE);
			}
			else if (hit(0, 11, 21)) {
				teleport(0, 10, 10);
				playing.currentMap = 1;
				playing.switchArrays();
			}
			else if (hit(1, 10, 12)) {
				teleport(1, 12, 21);
				playing.currentMap = 0;
				playing.switchArrays();
			}
		}
	}

	public boolean hit(int map, int col, int row) {
		boolean hit = false;

		if (map == playing.currentMap) {
			playing.player.hitbox.x = playing.player.worldX + playing.player.hitbox.x;
			playing.player.hitbox.y = playing.player.worldY + playing.player.hitbox.y;

			eventRect[map][col][row].x = col * playing.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row * playing.tileSize + eventRect[map][col][row].y;

			if (playing.player.hitbox.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
				hit = true;

				previousEventX = playing.player.worldX;
				previousEventY = playing.player.worldY;
			}

			playing.player.hitbox.x = playing.player.hitboxDefaultX;
			playing.player.hitbox.y = playing.player.hitboxDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}

	public void teleport(int map, int col, int row) {
//		gamePanel.gameState = GameState.TRANSITION
		tempMap = map;
		tempCol = col;
		tempRow = row;
		

		playing.playSoundEffect(2);
		playing.player.worldX = playing.tileSize * col;
		playing.player.worldY = playing.tileSize * row;
		previousEventX = playing.player.worldX;
		previousEventY = playing.player.worldY;
		canTouchEvent = false;
	}
	
	public void speak(Entity entity) { //TODO isn't use because didn't care about talking through table at the time.
		if(playing.enterPressed) {
			playing.gameState = GameState.DIALOGUE;
			playing.player.attackCanceled = true;
			entity.speak();
		}
	}


	public void damagePit(GameState gameState) {
		playing.gameState = gameState;
		playing.playSoundEffect(6);
		eventMaster.startDialogue(eventMaster, 0);
		playing.player.life -= 1;
		canTouchEvent = false;
	}

	public void healingPool(GameState gameState) {
		if (playing.enterPressed) {
			playing.gameState = gameState;
			playing.player.attackCanceled = true;
			playing.playSoundEffect(2);
			eventMaster.startDialogue(eventMaster, 1);
			playing.player.life = playing.player.maxLife;
			playing.player.mana = playing.player.maxMana;

			playing.assetSetter.setMonster();
			
			playing.saveLoad.save();
		}
	}
}
