package tile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import scenes.Playing;

public class TileManagerRy {
	
	Playing gamePanel;
	public Tile[] tile;
	public int mapTileNum[][][];

	public TileManagerRy(Playing playing) {
		this.gamePanel = playing;
		tile = new Tile[10];
		mapTileNum = new int[playing.maxMap][playing.maxWorldCol][playing.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/interior.txt",1);
	}
	
	public void getTileImage() {//TODO this should be handled with ENUMS
		
			setup(0, 2,0, false);
			setup(1, 1,0, true);
			setup(2, 0,2, true);
			setup(3, 1,2, true);
			setup(4, 2,2, true);
			setup(5, 0,0, false);
			setup(6, "hut", false);
			setup(7, "floor01", false);
			setup(8, "table01", true);
	}
	
	public void setup(int index, int x, int y, boolean collision) {
		BufferedImage texture = ImageLoader.loadImage("/textures/tileset_forest");
		SpriteSheet sheet = new SpriteSheet(texture);

			tile[index] = new Tile();
			tile[index].image = sheet.crop(x*gamePanel.tileSize,y*gamePanel.tileSize, gamePanel.tileSize, gamePanel.tileSize);
			tile[index].collision = collision;
	}
	
	public void setup(int index, String imageName, boolean collision) {

			tile[index] = new Tile();
			tile[index].image = ImageLoader.scaleImage(ImageLoader.loadImage("/tiles/"+ imageName));
			tile[index].collision = collision;
	}
	
	public void loadMap(String filePath, int map) {
		try {
			InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			int col = 0;
			int row = 0;
			
			while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = bufferedReader.readLine();
				
				while(col < gamePanel.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == gamePanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			bufferedReader.close();
			
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics g) {
		
		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			
			int tileNum = mapTileNum[gamePanel.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
			
			if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
			   worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
			   worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
			   worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
			
			g.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if(worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		drawPath(g);

	}
	
	private void drawPath(Graphics g) {
		if(gamePanel.debugMode) {
		g.setColor(new Color(255,0,0,70));
		
		for(int i = 0; i < gamePanel.pFinder.pathList.size(); i++) {
			int worldX = gamePanel.pFinder.pathList.get(i).col * gamePanel.tileSize;
			int worldY = gamePanel.pFinder.pathList.get(i).row * gamePanel.tileSize;
			int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
			
			g.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
		}
	}
	}
}
