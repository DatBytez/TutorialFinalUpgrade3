package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import scenes.Playing;

public class Map extends TileManagerRy {

	Playing playing;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;

	public Map(Playing playing) {
		super(playing);
		this.playing = playing;
		createWorldMap();
	}

	public void createWorldMap() {
		worldMap = new BufferedImage[playing.maxMap];
		int worldMapWidth = playing.tileSize * playing.maxWorldCol;
		int worldMapHeight = playing.tileSize * playing.maxWorldRow;

		for (int i = 0; i < playing.maxMap; i++) {
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D) worldMap[i].createGraphics();

			int col = 0;
			int row = 0;

			while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

				int tileNum = mapTileNum[i][col][row];
				int x = playing.tileSize * col;
				int y = playing.tileSize * row;
				g2.drawImage(tile[tileNum].image, x, y, null);
				
				col++;
				if(col == playing.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			g2.dispose(); 
		}
	}
	
	public void drawFullMapScreen(Graphics2D g2) {
		
		// Background Color
		g2.setColor(Color.black);
		g2.fillRect(0, 0, playing.screenWidth, playing.screenHeight);
		
		// Draw Map
		int width = 500;
		int height = 500;
		int x = playing.screenWidth/2 - width/2;
		int y = playing.screenHeight/2 - height/2;
		g2.drawImage(worldMap[playing.currentMap],x, y,width, height,null);
		
		// Draw Player
		double scale = (double)(playing.tileSize* playing.maxWorldCol)/width;
		int playerX = (int)(x + playing.player.worldX/scale);
		int playerY = (int)(y + playing.player.worldY/scale);
		int playerSize = (int)(playing.tileSize/scale);
		g2.drawImage(playing.player.down.get(0), playerX-playerSize-playerSize/2, playerY-playerSize*2, playerSize*4, playerSize*4, null);
		
		g2.setFont(playing.ui.bungee.deriveFont(32f));
		g2.setColor(Color.white);
		g2.drawString("Press M to close", 485, 620);
	}
	
	public void drawMiniMap(Graphics2D g2) {
		if(miniMapOn) {
			
			// Draw Minimap
			int width = 200;
			int height = 200;
			int x = playing.screenWidth - width - 50;
			int y = 50;
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			g2.drawImage(worldMap[playing.currentMap], x, y, width, height,null);
			
			// Draw Minimap Player
			double scale = (double)(playing.tileSize* playing.maxWorldCol)/width;
			int playerX = (int)(x + playing.player.worldX/scale);
			int playerY = (int)(y + playing.player.worldY/scale);
			int playerSize = (int)(playing.tileSize/scale);
			g2.drawImage(playing.player.down.get(0), playerX-playerSize-playerSize/2, playerY-playerSize*2, playerSize*4, playerSize*4, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
}
