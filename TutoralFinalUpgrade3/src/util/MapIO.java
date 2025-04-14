package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpz.Debug;
import map.TileMap;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapIO {

    /**
     * Returns the path to the directory where user data will be stored.
     * This folder (e.g. ".mygame") will be created in the user's home directory.
     */
    public static String getUserDataDirectory() {
        String userHome = System.getProperty("user.home");
        String dataDirPath = userHome + File.separator + ".mygame";
        File dataDir = new File(dataDirPath);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        return dataDirPath;
    }

    /**
     * Returns the full file path for the map data file.
     */
    public static String getMapDataFilePath() {
        return getUserDataDirectory() + File.separator + "mapData.json";
    }

    /**
     * Saves the given TileMap object to a JSON file in the user data directory.
     */
    public static void saveMap(TileMap tileMap) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = getMapDataFilePath();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(tileMap, writer);
            Debug.msg("Map saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a TileMap object from a JSON file in the user data directory.
     */
    public static TileMap loadMap() {
        Gson gson = new Gson();
        String filePath = getMapDataFilePath();
        try (FileReader reader = new FileReader(filePath)) {
            TileMap tileMap = gson.fromJson(reader, TileMap.class);
            Debug.msg("Map loaded successfully from " + filePath);
            return tileMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
