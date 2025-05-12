package helpz;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class DescriptionLoader {
    private static HashMap<String, String> descriptions = new HashMap<>();
    private static final String FILE_PATH = "src/main/resources/descriptions.json";

    public static void loadDescriptions() {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                createDefaultDescriptionFile(file);
            }

            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>() {}.getType();
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            descriptions = gson.fromJson(reader, type);
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
            descriptions = new HashMap<>();
        }
    }

    private static void createDefaultDescriptionFile(File file) {
        try {
            // Create parent directories if they don't exist
            file.getParentFile().mkdirs();

            // Default content
            HashMap<String, String> defaultDescriptions = new HashMap<>();
            defaultDescriptions.put("Launch", "Default description for a Launch. A small transport craft.");
            defaultDescriptions.put("Strike Fighter", "Default description for a Strike Fighter. A heavily armed small combat ship.");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonContent = gson.toJson(defaultDescriptions);

            try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
                writer.write(jsonContent);
            }

            System.out.println("Created default descriptions.json with initial entries.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDescription(String name) {
        return descriptions.getOrDefault(name, "");
    }
}


//package helpz;
//
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.lang.reflect.Type;
//
//public class DescriptionLoader {
//    private static HashMap<String, String> descriptions = new HashMap<>();
//
//    public static void loadDescriptions() {
//        try {
//            Gson gson = new Gson();
//            Type type = new TypeToken<HashMap<String, String>>() {}.getType();
//            InputStreamReader reader = new InputStreamReader(
//                DescriptionLoader.class.getResourceAsStream("descriptions.json"));
//            descriptions = gson.fromJson(reader, type);
//        } catch (Exception e) {
//            e.printStackTrace();
//            descriptions = new HashMap<>();
//        }
//    }
//
//    public static String getDescription(String name) {
//    	System.out.println(name);
//        return descriptions.getOrDefault(name, "");
//    }
//}
