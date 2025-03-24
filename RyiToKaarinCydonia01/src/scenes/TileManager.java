package scenes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import gfx.ImageLoader;
import gfx.SpriteSheet;
import tile.Tile;

public class TileManager {
	
	Playing playing;
	public Tile[] tile;
	public int mapTileNum[][][];

	public TileManager(Playing playing) {
		this.playing = playing;
		tile = new Tile[10];
		mapTileNum = new int[playing.maxMap][playing.maxWorldCol][playing.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt",0);
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
			tile[index].image = sheet.crop(x*playing.tileSize,y*playing.tileSize, playing.tileSize, playing.tileSize);
//			tile[index].image = util.scaleImage(tile[index].image,gamePanel);
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
			
			while(col < playing.maxWorldCol && row < playing.maxWorldRow) {
				String line = bufferedReader.readLine();
				
				while(col < playing.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[map][col][row] = num;
					col++;
				}
				if(col == playing.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			bufferedReader.close();
			
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < playing.maxWorldCol && worldRow < playing.maxWorldRow) {
			
			int tileNum = mapTileNum[playing.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * playing.tileSize;
			int worldY = worldRow * playing.tileSize;
			int screenX = worldX - playing.player.worldX + playing.player.screenX;
			int screenY = worldY - playing.player.worldY + playing.player.screenY;
			
			if(worldX + playing.tileSize > playing.player.worldX - playing.player.screenX &&
			   worldX - playing.tileSize < playing.player.worldX + playing.player.screenX &&
			   worldY + playing.tileSize > playing.player.worldY - playing.player.screenY &&
			   worldY - playing.tileSize < playing.player.worldY + playing.player.screenY) {
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if(worldCol == playing.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
		drawPath(g2);

	}
	
	private void drawPath(Graphics2D g2) {
		if(playing.debugMode) {
		g2.setColor(new Color(255,0,0,70));
		
		for(int i = 0; i < playing.pFinder.pathList.size(); i++) {
			int worldX = playing.pFinder.pathList.get(i).col * playing.tileSize;
			int worldY = playing.pFinder.pathList.get(i).row * playing.tileSize;
			int screenX = worldX - playing.player.worldX + playing.player.screenX;
			int screenY = worldY - playing.player.worldY + playing.player.screenY;
			
			g2.fillRect(screenX, screenY, playing.tileSize, playing.tileSize);
		}
	}
	}
}

