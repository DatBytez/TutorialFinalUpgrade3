package main;

import java.util.ArrayList;

import entity.Direction;
import entity.Entity;
import scenes.Playing;

public class CollisionChecker {

	Playing gamePanel;

	public CollisionChecker(Playing playing) {
		this.gamePanel = playing;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.hitbox.x;
		int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
		int entityTopWorldY = entity.worldY + entity.hitbox.y;
		int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

		int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
		int entityRightCol = entityRightWorldX / gamePanel.tileSize;
		int entityTopRow = entityTopWorldY / gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

		int tileNum1, tileNum2;
		
		// Use a temporal direction when it's being knocked back
		Direction direction = entity.direction;
		if(entity.knockBack) {
			direction = entity.knockBackDirection;
		}

		switch (direction) {
		case up:
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case down:
			entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case left:
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case right:
			entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gamePanel.tileM.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
			if (gamePanel.tileM.tile[tileNum1].collision == true || gamePanel.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		default:
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) { // TODO Lots of repeated code here. Could probably use
															// another method added in.

		int index = 999;

		for (int i = 0; i < gamePanel.objectList.size(); i++) {

			if (gamePanel.objectList.get(i) != null) {

				// Get entity's solid area position
				entity.hitbox.x = entity.worldX + entity.hitbox.x;
				entity.hitbox.y = entity.worldY + entity.hitbox.y;

				// Get object's solid area position
				gamePanel.objectList.get(i).hitbox.x = gamePanel.objectList.get(i).worldX
						+ gamePanel.objectList.get(i).hitbox.x;
				gamePanel.objectList.get(i).hitbox.y = gamePanel.objectList.get(i).worldY
						+ gamePanel.objectList.get(i).hitbox.y;

				switch (entity.direction) {
				case up:
					entity.hitbox.y -= entity.speed;
					break;
				case down:
					entity.hitbox.y += entity.speed;
					break;
				case left:
					entity.hitbox.x -= entity.speed;
					break;
				case right:
					entity.hitbox.x += entity.speed;
					break;
				default:
					break;
				}
				if (entity.hitbox.intersects(gamePanel.objectList.get(i).hitbox)) {
					if (gamePanel.objectList.get(i).collision == true) {
						entity.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				gamePanel.objectList.get(i).hitbox.x = gamePanel.objectList.get(i).hitboxDefaultX;
				gamePanel.objectList.get(i).hitbox.y = gamePanel.objectList.get(i).hitboxDefaultY;
			}
		}
		return index;
	}

	// NPC OR MONSTER
	public int checkEntity(Entity entity, ArrayList<Entity> target) {
		
		int index = 999;
		
		// Use a temporal direction when it's being knocked back //TODO double coded
		Direction direction = entity.direction;
		if(entity.knockBack) {
			direction = entity.knockBackDirection;
		}

		for (int i = 0; i < target.size(); i++) {

			if (target.get(i) != null && !target.get(i).dying) {

				// Get entity's solid area position
				entity.hitbox.x = entity.worldX + entity.hitbox.x;
				entity.hitbox.y = entity.worldY + entity.hitbox.y;

				// Get object's solid area position
				target.get(i).hitbox.x = target.get(i).worldX + target.get(i).hitbox.x;
				target.get(i).hitbox.y = target.get(i).worldY + target.get(i).hitbox.y;

				switch (direction) {
				case up:
					entity.hitbox.y -= entity.speed;
					break;
				case down:
					entity.hitbox.y += entity.speed;
					break;
				case left:
					entity.hitbox.x -= entity.speed;
					break;
				case right:
					entity.hitbox.x += entity.speed;
					break;
				default:
					break;
				}
				if (entity.hitbox.intersects(target.get(i).hitbox)) {
					if (target.get(i) != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				entity.hitbox.x = entity.hitboxDefaultX;
				entity.hitbox.y = entity.hitboxDefaultY;
				target.get(i).hitbox.x = target.get(i).hitboxDefaultX;
				target.get(i).hitbox.y = target.get(i).hitboxDefaultY;
			}
		}
		return index;
	}

	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;

		// Get entity's solid area position
		entity.hitbox.x = entity.worldX + entity.hitbox.x;
		entity.hitbox.y = entity.worldY + entity.hitbox.y;

		// Get object's solid area position
		gamePanel.player.hitbox.x = gamePanel.player.worldX + gamePanel.player.hitbox.x;
		gamePanel.player.hitbox.y = gamePanel.player.worldY + gamePanel.player.hitbox.y;

		switch (entity.direction) {
		case up: entity.hitbox.y -= entity.speed; break;
		case down: entity.hitbox.y += entity.speed;	break;
		case left: entity.hitbox.x -= entity.speed;	break;
		case right: entity.hitbox.x += entity.speed; break;
		default:
			break;
		}
		
		if (entity.hitbox.intersects(gamePanel.player.hitbox)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		entity.hitbox.x = entity.hitboxDefaultX;
		entity.hitbox.y = entity.hitboxDefaultY;
		gamePanel.player.hitbox.x = gamePanel.player.hitboxDefaultX;
		gamePanel.player.hitbox.y = gamePanel.player.hitboxDefaultY;
		
		return contactPlayer;
	}
}
