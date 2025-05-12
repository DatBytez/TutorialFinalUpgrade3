package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import helpz.Debug;
import map.Level;
import managers.TileManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MapIO {

    /**
     * Returns the directory path where level data should be stored.
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
     * Returns the full file path for a given level file (without extension).
     */
    public static String getLevelDataFilePath(String fileName) {
        return getUserDataDirectory() + File.separator + fileName + ".json";
    }

    /**
     * Saves the provided Level object to a JSON file.
     *
     * @param level    The Level instance to save.
     * @param fileName The base file name (for example, "cydoniaLevel").
     */
    public static void saveLevel(Level level, String fileName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = getLevelDataFilePath(fileName);
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(level, writer);
            Debug.msg("Level saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a Level object from a JSON file.
     *
     * @param fileName    The base file name to load (for example, "cydoniaLevel").
     * @param tileManager The TileManager instance (needed to reattach tile resources).
     * @return The loaded Level, or null if loading fails.
     */
    public static Level loadLevel(String fileName, TileManager tileManager) {
        Gson gson = new Gson();
        String filePath = getLevelDataFilePath(fileName);
        try (FileReader reader = new FileReader(filePath)) {
            Level level = gson.fromJson(reader, Level.class);
            Debug.msg("Level loaded successfully from " + filePath);
            // Optionally, reassign tileManager (if needed) here.
            return level;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
