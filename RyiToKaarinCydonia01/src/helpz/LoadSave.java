package helpz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import gfx.ImageLoader;
import static main.Game.*;

public class LoadSave {

	public static BufferedImage getSpriteAtlas() {

		return ImageLoader.loadImage("/textures/spriteatlas");
	}

	public static void CreateLevel(String name, int[] idArr) {

		File newLevel = new File("res/maps/" + name + ".txt");

		if (newLevel.exists()) {
			System.out.println("File " + name + " already exists!");
			return;
		} else {
			try {
				newLevel.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			WriteToFileRy(newLevel, idArr);
		}
	}
	
	private static void WriteToFileRy(File file, int[] idArr) { //TODO: We need to combine one of these two eventually
		int ii = 0;
		try {
			PrintWriter pw = new PrintWriter(file);

			for (Integer i : idArr) {
				pw.print(i + "\t");
				ii++;

				if (ii >= 100) {
					pw.println("");
					System.out.println(ii);
					ii = 0;
				}
			}

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void SaveLevel(String name, int[][] idArr) {
		File lvlFile = new File("res/maps/" + name + ".txt");

		if (lvlFile.exists()) {
			WriteToFileRy(lvlFile, Utilz.TwoDto1dintArr(idArr));
		} else {
			System.out.println("File: " + name + " does not exist!");
			return;
		}
	}
	
	public static int[][] GetLevelData(String name) {
		File lvlFile = new File("res/maps/" + name + ".txt");

		if (lvlFile.exists()) {
			return ReadFromFile(lvlFile);
		} else {
			System.out.println("File: " + name + " does not exist!");
			return null;
		}
	}

	public static int[][] ReadFromFile(File file) {
		int[][] list = new int[MAX_WORLD_COL][MAX_WORLD_ROW];
		
		try {
			Scanner sc = new Scanner(file);

			int col = 0;
			int row = 0;

			while (col < MAX_WORLD_COL && row < MAX_WORLD_ROW) {
				String line = sc.nextLine();

				while (col < MAX_WORLD_COL) {
					String numbers[] = line.split("\\t");

					int num = Integer.parseInt(numbers[col]);

					list[row][col] = num;
					col++;
				}
				if (col == MAX_WORLD_COL) {
					col = 0;
					row++;
				}
			}
			sc.close();

		} catch (Exception e) {

		}
		return list;
	}
}
