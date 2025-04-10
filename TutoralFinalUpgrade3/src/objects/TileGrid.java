//package objects;
//
//import static helpz.Constants.*;
//
//public class TileGrid implements java.io.Serializable{
//	public Tile[][] map;
//	private int tilesWide = MAX_WORLD_COL;
//	private int tilesHigh = MAX_WORLD_ROW;
//	
//	public TileGrid(){
//		map = new Tile[tilesWide][tilesHigh];
//		for (int i = 0; i < map.length; i++){
//			for (int j = 0; j < map[i].length; j++){
//				map[i][j] = new Tile(i*TILE_SIZE,j*TILE_SIZE,TILE_SIZE,TILE_SIZE);
//			}
//		}
//	}
//	
//	public void setTile(int xCoord, int yCoord, TileType type){
//		if(xCoord < map.length && yCoord < map[0].length)
//		map[xCoord][yCoord] = new Tile(xCoord * TILE_SIZE, yCoord * TILE_SIZE, TILE_SIZE, TILE_SIZE,texture, type);
//	}
//	
//	public Tile getTile(int xPlace, int yPlace){
//		if (xPlace < tilesWide && yPlace < tilesHigh && xPlace > -1 && yPlace > -1)
//		return map[xPlace][yPlace];
//		else
//			return new Tile(0,0,0,0, texture,TileType.Null);
//	}
//	
//	public void draw() {
//		for (int i = 0; i < map.length; i++){
//			for (int j = 0; j < map[i].length; j++){
//				map[i][j].draw();
//			}
//		}
//	}
//
//	public int getTilesWide() {
//		return tilesWide;
//	}
//
//	public void setTilesWide(int tilesWide) {
//		this.tilesWide = tilesWide;
//	}
//
//	public int getTilesHigh() {
//		return tilesHigh;
//	}
//
//	public void setTilesHigh(int tilesHigh) {
//		this.tilesHigh = tilesHigh;
//	}
//}
