package managers;

import main.Artist;
import scenes.Scene;
import scenes.EditingScene;
import scenes.MenuScene;
import scenes.PlayingScene;
// import scenes.EditingScene;
// import scenes.SettingsScene;

public class SceneManager {

    // Enum representing all possible scenes in the game
    public enum SceneType {
        // Each enum constant holds a reference to the class of the Scene
        MENU(MenuScene.class),
        PLAYING(PlayingScene.class),
        EDITING(EditingScene.class);
        // SETTINGS(SettingsScene.class);

        private final Class<? extends Scene> sceneClass; // The class of the Scene
        private Scene sceneInstance; // Lazy-loaded Scene instance

        // Constructor stores the class type
        SceneType(Class<? extends Scene> sceneClass) {
            this.sceneClass = sceneClass;
        }

        // Lazily creates the scene instance the first time it's accessed
        public Scene getScene() {
            if (sceneInstance == null) {
                try {
                    sceneInstance = sceneClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException("Could not create scene: " + sceneClass.getSimpleName());
                }
            }
            return sceneInstance;
        }
    }

    // The currently active scene
    private static SceneType currentScene = null;

    // Changes the current scene
    public static void changeScene(SceneType newScene) {
        currentScene = newScene;
        System.out.println("Switched to scene: " + newScene);
    }

    // Calls update on the current scene (game logic)
    public static void update() {
        if (currentScene != null)
            currentScene.getScene().update();
    }

    // Calls render on the current scene (draw to screen)
    public static void render(Artist a) {
        if (currentScene != null)
            currentScene.getScene().render(a);
    }

    // Returns the current scene enum (useful for checking state)
    public static SceneType getCurrentScene() {
        return currentScene;
    }
}