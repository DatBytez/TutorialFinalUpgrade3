package map;

import static helpz.Constants.MAX_WORLD_COL;
import static helpz.Constants.MAX_WORLD_ROW;
import static helpz.Constants.TILE_SIZE;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import helpz.Debug;
import main.Artist;
import managers.TileManager;

public class Level {

    private String name;
    private TileManager tileManager;
    private ArrayList<Layer> layers = new ArrayList<>();

    public Level(String name, ArrayList<String> fileNames, TileManager tileManager) {
        this.name = name;
        this.tileManager = tileManager;
        // For each file name, create a new Layer.
        for (String file : fileNames) {
            layers.add(new Layer(file));
        }
    }

    // Draws each layer. (In this example, we draw layers and change the alpha if the layer is not the active one.)
    public void draw(Artist a, int obvWorldX, int obvWorldY, int obvScreenX, int obvScreenY) {
    	
        // For each layer, we simply call its draw() method.
        // You may add logic here to adjust alpha based on a currently selected layer if desired.
        for (Layer layer : layers) {
            layer.draw(a, obvWorldX, obvWorldY, obvScreenX, obvScreenY);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    // Inner class representing one layer in the level.
    public class Layer {
        private String fileName;
        private int[][] layer;  // The tile indices for this layer

        public Layer(String fileName) {
            this.fileName = fileName;
            this.layer = loadLayer(fileName);
        }

        // Loads the 2D array from a text file.
        public int[][] loadLayer(String filePath) {
            int[][] layer = new int[MAX_WORLD_COL][MAX_WORLD_ROW];
            try (InputStream is = getClass().getResourceAsStream(filePath)) {
                if (is == null) {
                    Debug.msg("Level file not found: " + filePath);
                    // Return an empty (zero-filled) level.
                    return layer;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                int col = 0;
                int row = 0;
                while (row < MAX_WORLD_ROW) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    String[] numbers = line.split("\\t");
                    for (col = 0; col < MAX_WORLD_COL; col++) {
                        layer[col][row] = Integer.parseInt(numbers[col]);
                    }
                    row++;
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return layer;
        }


        // Draws the visible portion of this layer using the provided Graphics2D context.
        public void draw(Artist a, int obvWorldX, int obvWorldY, int obvScreenX, int obvScreenY) {
            // Calculate visible range (with a buffer of 2 tiles on each side)
            int startCol = Math.max(0, (obvWorldX - obvScreenX) / TILE_SIZE - 2);
            int startRow = Math.max(0, (obvWorldY - obvScreenY) / TILE_SIZE - 2);
            int endCol = Math.min(MAX_WORLD_COL - 1, (obvWorldX + obvScreenX) / TILE_SIZE + 2);
            int endRow = Math.min(MAX_WORLD_ROW - 1, (obvWorldY + obvScreenY) / TILE_SIZE + 2);
            for (int col = startCol; col <= endCol; col++) {
                for (int row = startRow; row <= endRow; row++) {
                    int tileNum = layer[col][row];
                    // Compute world coordinates of this tile.
                    int worldX = col * TILE_SIZE;
                    int worldY = row * TILE_SIZE;
                    // Compute screen coordinates by subtracting the world offset and adding the screen offset.
                    int screenX = worldX - obvWorldX + obvScreenX;
                    int screenY = worldY - obvWorldY + obvScreenY;
                    // Draw the tile if the index is valid.
                    if (tileNum >= 0 && tileNum < tileManager.tiles.size()) {
                        a.drawImage(tileManager.tiles.get(tileNum).getImage(), screenX, screenY);
                    }
                }
            }
        }

        public String getFileName() {
            return fileName;
        }

        public int[][] getLayer() {
            return layer;
        }
    }
}


//package map;
//
//import static helpz.Constants.*;
//
//import java.awt.Graphics2D;
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import managers.TileManager;
//
//public class Level {
//
//	private String name;
//	private TileManager tileManager;
//	private ArrayList<Layer> layers = new ArrayList<>();
//	private ArrayList<String> fileNames = new ArrayList<>();
//
//	public int tileMapNum[][][];
//
//	public Level(String name, ArrayList<String> fileNames, TileManager tileManager) {
//		this.name = name;
//		this.tileManager = tileManager;
//
//		fileNames.forEach((n) -> layers.add(new Layer(n)));
//	}
//
//	public void draw(Graphics2D g2, int obvWorldX, int obvWorldY, int obvScreenX, int obvScreenY) {
//
//		layers.forEach((n) -> {
//			if (n != layers.get(5)) {
//				n.draw(g2, obvWorldX, obvWorldY, obvScreenX, obvScreenY);
//			}
//		});
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public ArrayList<Layer> getLayers() {
//		return layers;
//	}
//
//	public class Layer {
//
//		private String fileName;
//		private int[][] layer;
//
//		public Layer(String fileName) {
//			this.fileName = fileName;
//			this.layer = loadLayer(fileName);
//		}
//
//		public int[][] loadLayer(String filePath) {
//			int[][] layer = new int[MAX_WORLD_COL][MAX_WORLD_ROW];
//			try (InputStream inputStream = getClass().getResourceAsStream(filePath);
//				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
//
//				int col = 0;
//				int row = 0;
//
//				while (col < MAX_WORLD_COL && row < MAX_WORLD_ROW) {
//					String line = bufferedReader.readLine();
//
//					while (col < MAX_WORLD_COL) {
//						String numbers[] = line.split("\\t");
//
//						int num = Integer.parseInt(numbers[col]);
//
//						layer[col][row] = num;
//						col++;
//					}
//					if (col == MAX_WORLD_COL) {
//						col = 0;
//						row++;
//					}
//				}
//				bufferedReader.close();
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			return layer;
//		}
//
//		public void draw(Graphics2D g2, int obvWorldX, int obvWorldY, int obvScreenX, int obvScreenY) {
//		    // Calculate the starting and ending columns and rows, with an additional row and column for smoothing edges
//		    int startCol = Math.max(0, (obvWorldX - obvScreenX) / TILE_SIZE - 2);  // Start 1 column before the visible area
//		    int startRow = Math.max(0, (obvWorldY - obvScreenY) / TILE_SIZE - 2);  // Start 1 row before the visible area
//		    int endCol = Math.min(MAX_WORLD_COL - 1, (obvWorldX + obvScreenX) / TILE_SIZE + 2);  // End 1 column after the visible area
//		    int endRow = Math.min(MAX_WORLD_ROW - 1, (obvWorldY + obvScreenY) / TILE_SIZE + 2);  // End 1 row after the visible area
//
//		    // Loop through the adjusted range of visible tiles, including the additional rows/columns
//		    for (int worldCol = startCol; worldCol <= endCol; worldCol++) {
//		        for (int worldRow = startRow; worldRow <= endRow; worldRow++) {
//
//		            // Get the tile number from the layer
//		            int tileNum = layer[worldCol][worldRow];
//
//		            // Calculate world and screen coordinates for drawing
//		            int worldX = worldCol * TILE_SIZE;
//		            int worldY = worldRow * TILE_SIZE;
//		            int screenX = worldX - obvWorldX + obvScreenX;
//		            int screenY = worldY - obvWorldY + obvScreenY;
//
//		            // Draw the tile only if it's within the screen bounds
//		            g2.drawImage(tileManager.tiles.get(tileNum).getImage(), screenX, screenY, null);
//		        }
//		    }
//		}
//
//		public String getFileName() {
//			return fileName;
//		}
//
//		public int[][] getLayer() {
//			return layer;
//		}
//	}
//
//}
