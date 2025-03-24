package managers;

import static main.Game.TILE_SIZE;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gfx.ImageLoader;
import scenes.Playing;
import tile.Tile;

public class TileManager {

	Playing playing;
	public int mapTileNum[][][];

	public Tile ERASE, GRASS, WATER, DIRT;
	public Tile GRASS_EDGE, GRASS_EDGE_IN, GRASS_EDGE_OUT;
	public Tile DIRT_EDGE, DIRT_EDGE_IN, DIRT_EDGE_OUT;
	public Tile COUNTER,COUNTER_EDGE, COUNTER_EDGE_IN, COUNTER_EDGE_OUT;
	public Tile CURTAIN,CURTAIN_EDGE, CURTAIN_EDGE_IN, CURTAIN_EDGE_OUT;

	public BufferedImage atlas;
	public BufferedImage sprite_sheet_counter;
	public ArrayList<Tile> tiles = new ArrayList<>();

	public ArrayList<Tile> erase = new ArrayList<>();
	public ArrayList<Tile> grass = new ArrayList<>();
	public ArrayList<Tile> water = new ArrayList<>();
	public ArrayList<Tile> dirt = new ArrayList<>();
	
	public ArrayList<Tile> grass_edge = new ArrayList<>();
	public ArrayList<Tile> grass_edge_out = new ArrayList<>();
	public ArrayList<Tile> grass_edge_in = new ArrayList<>();
	
	public ArrayList<Tile> dirt_edge = new ArrayList<>();
	public ArrayList<Tile> dirt_edge_in = new ArrayList<>();
	public ArrayList<Tile> dirt_edge_out = new ArrayList<>();
	
	public ArrayList<Tile> counter = new ArrayList<>();
	public ArrayList<Tile> counter_edge = new ArrayList<>();
	public ArrayList<Tile> counter_edge_in = new ArrayList<>();
	public ArrayList<Tile> counter_edge_out = new ArrayList<>();
	
	public ArrayList<Tile> curtain = new ArrayList<>();
	public ArrayList<Tile> curtain_edge = new ArrayList<>();
	public ArrayList<Tile> curtain_edge_in = new ArrayList<>();
	public ArrayList<Tile> curtain_edge_out = new ArrayList<>();
	
	public ArrayList<Tile> throne = new ArrayList<>();
	public ArrayList<Tile> bench = new ArrayList<>();
	
	public ArrayList<Tile> counter_g = new ArrayList<>();
	public ArrayList<Tile> counter_g_edge = new ArrayList<>();
	public ArrayList<Tile> counter_g_edge_in = new ArrayList<>();
	public ArrayList<Tile> counter_g_edge_out = new ArrayList<>();
	
	public ArrayList<Tile> chair_cushion = new ArrayList<>();
	public ArrayList<Tile> counter_bar = new ArrayList<>();
	public ArrayList<Tile> counter_bar2 = new ArrayList<>();
	public ArrayList<Tile> counter_bar3 = new ArrayList<>();
	
	public ArrayList<Tile> barrel = new ArrayList<>();
	public ArrayList<Tile> table = new ArrayList<>();
	public ArrayList<Tile> test = new ArrayList<>();
	
	private int id = 0;

	public TileManager(Playing playing) {
		this.playing = playing;
		loadAtlas();
		createTiles();
	}
	
	public TileManager() {

		loadAtlas();
		createTiles();
	}
	
	private void createTiles() {// TODO: Figure out why I can have multiple GRASS and if "Grass" is ever used.

		id = 0;
		erase.add(ERASE = new Tile(getSprite(8, 0), id++, "Erase", false));
		
		int xOff=0,yOff=0;	
		grass = addTileQuad(xOff,yOff,false); xOff += 2;
		grass_edge = addTileQuad(xOff,yOff,false); xOff += 2;
		grass_edge_in = addTileQuad(xOff,yOff,false); xOff += 2;
		grass_edge_out = addTileQuad(xOff,yOff,false); xOff = 0;
		
		yOff += 2;
		dirt = addTileQuad(xOff,yOff,false); xOff += 2;
		dirt_edge = addTileQuad(xOff,yOff,false); xOff += 2;
		dirt_edge_in = addTileQuad(xOff,yOff,false); xOff += 2;
		dirt_edge_out = addTileQuad(xOff,yOff,false); xOff = 0;
		
		yOff += 2;	
		water = addTileQuad(xOff,yOff,true);
		
		yOff += 2;	
		counter = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_edge = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_edge_in = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_edge_out = addTileQuad(xOff,yOff,true); xOff = 0;

		yOff += 2;	
		curtain = addTileQuad(xOff,yOff,true); xOff += 2;
		curtain_edge = addTileQuad(xOff,yOff,false); xOff += 2;
		curtain_edge_in = addTileQuad(xOff,yOff,false); xOff += 2;
		curtain_edge_out = addTileQuad(xOff,yOff,false); xOff = 0;
		
		yOff += 2;	
		throne = addTileQuad(xOff,yOff,false); xOff += 2;
		bench = addTileQuad(xOff,yOff,false); xOff += 2;
		counter_g_edge = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_g_edge_out = addTileQuad(xOff,yOff,true); xOff = 0;
		
		yOff += 2;	
		chair_cushion = addTileQuad(xOff,yOff,false); xOff += 2;
		counter_bar = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_bar2 = addTileQuad(xOff,yOff,true); xOff += 2;
		counter_bar3 = addTileQuad(xOff,yOff,true); xOff += 1;
		barrel = addTileQuad(xOff,yOff,false); xOff = 0;
		
		yOff += 2;	
		table = addTileBlock(xOff,yOff,false); xOff += 3;
		test = addTileSmallBlock(xOff,yOff,false); xOff += 2;

//		dirt_on_grass.add(DIRT_ON_GRASS = new Tile(ImgFix.buildImg(getImgs(5, 0, 3, 2)), id++, "Dirt_On_Grass"));

		tiles.addAll(erase);
		tiles.addAll(grass);
		tiles.addAll(grass_edge);
		tiles.addAll(grass_edge_in);
		tiles.addAll(grass_edge_out);
		
		tiles.addAll(dirt);
		tiles.addAll(dirt_edge);
		tiles.addAll(dirt_edge_in);
		tiles.addAll(dirt_edge_out);
		
		tiles.addAll(water);
		
		tiles.addAll(counter);
		tiles.addAll(counter_edge);
		tiles.addAll(counter_edge_in);
		tiles.addAll(counter_edge_out);
		
		tiles.addAll(curtain);
		tiles.addAll(curtain_edge);
		tiles.addAll(curtain_edge_in);
		tiles.addAll(curtain_edge_out);
		
		tiles.addAll(throne);
		tiles.addAll(bench);
		tiles.addAll(counter_g_edge);
		tiles.addAll(counter_g_edge_out);
		
		tiles.addAll(chair_cushion);
		tiles.addAll(counter_bar);
		tiles.addAll(counter_bar2);
		tiles.addAll(counter_bar3);
		
		tiles.addAll(barrel);
		tiles.addAll(table);
		tiles.addAll(test);
	}
	
	public ArrayList<Tile> getThrone() {
		return throne;
	}

	public ArrayList<Tile> getBench() {
		return bench;
	}

	public ArrayList<Tile> getCounter_g_edge() {
		return counter_g_edge;
	}

	public ArrayList<Tile> getCounter_g_edge_out() {
		return counter_g_edge_out;
	}

	private ArrayList<Tile> addTileQuad(int xOff, int yOff, boolean collision) {
		ArrayList<Tile> tileQuad = new ArrayList<>();
		tileQuad.add(new Tile(getSprite(xOff, yOff), id++, "tileQuad"+id,collision));
		tileQuad.add(new Tile(getSprite(xOff+1, yOff), id++, "tileQuad"+id,collision));
		tileQuad.add(new Tile(getSprite(xOff+1, yOff+1), id++, "tileQuad"+id,collision));
		tileQuad.add(new Tile(getSprite(xOff, yOff+1), id++, "tileQuad"+id,collision));
		return tileQuad;
	}
	
	private ArrayList<Tile> addTileSmallBlock(int xOff, int yOff, boolean collision) {
		
		ArrayList<Tile> tileBlock = new ArrayList<>();
		tileBlock.add(new Tile(getSprite( xOff+0.0,  yOff+0.0), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite( xOff+0.5,  yOff+0.0), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite( xOff+1.0,  yOff+0.0), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite( xOff+1.0,  yOff+0.5), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite( xOff+1.0,  yOff+1.0), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite( xOff+0.5,  yOff+1.0), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite( xOff+0.0,  yOff+1.0), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite( xOff+0.0,  yOff+0.5), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite( xOff+0.5,  yOff+0.5), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.0), (int) (yOff+0.0)), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.5), (int) (yOff+0.0)), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+1.0), (int) (yOff+0.0)), id++, "tileQuad"+id,collision));
//		
//		tileBlock.add(new Tile(getSprite((int) (xOff+1.0), (int) (yOff+0.5)), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+1.0), (int) (yOff+1.0)), id++, "tileQuad"+id,collision));
//		
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.5), (int) (yOff+1.0)), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.0), (int) (yOff+1.0)), id++, "tileQuad"+id,collision));
//		
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.0), (int) (yOff+0.5)), id++, "tileQuad"+id,collision));
//		tileBlock.add(new Tile(getSprite((int) (xOff+0.5), (int) (yOff+0.5)), id++, "tileQuad"+id,collision));

		return tileBlock;
	}
	
	private ArrayList<Tile> addTileBlock(int xOff, int yOff, boolean collision) {
		
		ArrayList<Tile> tileBlock = new ArrayList<>();
		tileBlock.add(new Tile(getSprite(xOff+0, yOff), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite(xOff+1, yOff), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite(xOff+2, yOff), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite(xOff+2, yOff+1), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite(xOff+2, yOff+2), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite(xOff+1, yOff+2), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite(xOff+0, yOff+2), id++, "tileQuad"+id,collision));
		
		tileBlock.add(new Tile(getSprite(xOff+0, yOff+1), id++, "tileQuad"+id,collision));
		tileBlock.add(new Tile(getSprite(xOff+1, yOff+1), id++, "tileQuad"+id,collision));

		return tileBlock;
	}
	
	private BufferedImage[] getImgs(int firstX, int firstY, int secondX, int secondY) {

		return new BufferedImage[] { getSprite(firstX, firstY), getSprite(secondX, secondY) };

	}

	public BufferedImage getSprite(int id) {
		return tiles.get(id).getImage();
	}

//	private BufferedImage getSprite(int xCord, int yCord) {
//		return atlas.getSubimage(xCord * TILE_SIZE, yCord * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//	}
	
	private BufferedImage getSprite(double xCord, double yCord, BufferedImage sprite_sheet) {
		return sprite_sheet.getSubimage((int) (xCord * TILE_SIZE), (int) (yCord * TILE_SIZE), TILE_SIZE, TILE_SIZE);
	}
	
	private BufferedImage getSprite(double xCord, double yCord) {
		return atlas.getSubimage((int) (xCord * TILE_SIZE), (int) (yCord * TILE_SIZE), TILE_SIZE, TILE_SIZE);
	}

	private void loadAtlas() { // TODO: Should either make one really big atlas, or create a way to switch
		// between them. Maybe pass an atlas in when you create a new tile manager?
		atlas = ImageLoader.loadImage("/textures/tileset_cydonia");
		sprite_sheet_counter = ImageLoader.loadImage("/textures/tileset_counter");
	}

	private void drawPath(Graphics2D g2) {
		if (playing.debugMode) {
			g2.setColor(new Color(255, 0, 0, 70));

			for (int i = 0; i < playing.pFinder.pathList.size(); i++) {
				int worldX = playing.pFinder.pathList.get(i).col * playing.tileSize;
				int worldY = playing.pFinder.pathList.get(i).row * playing.tileSize;
				int screenX = worldX - playing.player.worldX + playing.player.screenX;
				int screenY = worldY - playing.player.worldY + playing.player.screenY;

				g2.fillRect(screenX, screenY, playing.tileSize, playing.tileSize);
			}
		}
	}
	
	public ArrayList<Tile> getErase() {
		return erase;
	}

	public ArrayList<Tile> getGrass() {
		return grass;
	}

	public ArrayList<Tile> getWater() {
		return water;
	}

	public ArrayList<Tile> getRoad() {
		return dirt;
	}

	public ArrayList<Tile> getGrass_edge() {
		return grass_edge;
	}

	public ArrayList<Tile> getGrass_edge_out() {
		return grass_edge_out;
	}

	public ArrayList<Tile> getGrass_edge_in() {
		return grass_edge_in;
	}
	
	public ArrayList<Tile> getDirt_edge() {
		return dirt_edge;
	}

	public ArrayList<Tile> getDirt_edge_out() {
		return dirt_edge_out;
	}

	public ArrayList<Tile> getDirt_edge_in() {
		return dirt_edge_in;
	}
	
	public ArrayList<Tile> getCounter() {
		return counter;
	}

	public ArrayList<Tile> getCounter_edge() {
		return counter_edge;
	}

	public ArrayList<Tile> getCounter_edge_in() {
		return counter_edge_in;
	}

	public ArrayList<Tile> getCounter_edge_out() {
		return counter_edge_out;
	}

	public ArrayList<Tile> getCurtain() {
		return curtain;
	}

	public ArrayList<Tile> getCurtain_edge() {
		return curtain_edge;
	}

	public ArrayList<Tile> getCurtain_edge_in() {
		return curtain_edge_in;
	}

	public ArrayList<Tile> getCurtain_edge_out() {
		return curtain_edge_out;
	}

	public ArrayList<Tile> getChair_cushion() {
		return chair_cushion;
	}

	public ArrayList<Tile> getCounter_bar() {
		return counter_bar;
	}

	public ArrayList<Tile> getCounter_bar2() {
		return counter_bar2;
	}

	public ArrayList<Tile> getCounter_bar3() {
		return counter_bar3;
	}
	
	public ArrayList<Tile> getBarrel() {
		return barrel;
	}
	
	public ArrayList<Tile> getTable() {
		return table;
	}
	
	public ArrayList<Tile> getTest() {
		return test;
	}
	
}
