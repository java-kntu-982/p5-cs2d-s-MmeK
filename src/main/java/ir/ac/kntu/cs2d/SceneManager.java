package ir.ac.kntu.cs2d;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static SceneManager sceneManagerInstance = null;
    private static Stage primaryStage;

    private SceneManager(Stage primaryStage) {
        SceneManager.primaryStage = primaryStage;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static SceneManager getInstance(Stage primaryStage) {
        if (sceneManagerInstance != null) return sceneManagerInstance;
        sceneManagerInstance = new SceneManager(primaryStage);
        return sceneManagerInstance;
    }

    public void switchScene(Scene scene) throws IOException {
//        Group root = new Group();
//        root.getChildren().add(FXMLLoader.load(getClass().getResource(sceneEnum.getFXMLPath())));
//        Scene scene = new Scene(root);
//        Scene scene = new MenuScene().initialize();
        primaryStage.setScene(scene);
    }
}
