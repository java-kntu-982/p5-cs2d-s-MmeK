package ir.ac.kntu.cs2d;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StaticSceneLoader {
    public static final String GAME_LAYOUT = "/layouts/game_layout.fxml";
    public static final String CLIENT_MAIN_MENU = "/layouts/client_main_menu.fxml";


    private static StackPane stackPane;
    private static Stage mainStage;

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        StaticSceneLoader.mainStage = mainStage;
    }

    public static void setScene(Scene scene) {
        getMainStage().setScene(scene);
    }

    public static void setStackPane(StackPane pane) {
        stackPane = pane;
    }

    public static StackPane getStackPane() {
        return stackPane;
    }

//    public static void setLayout(String string) throws IOException {
//        stackPane.getChildren().removeAll();
//        stackPane.getChildren().setAll((Node) FXMLLoader.load(StaticSceneLoader.class.getResource(string)));
//        System.out.println(stackPane.getChildren().toString());
//    }

    public static Scene newScene(String string, EventHandler<MouseEvent> mouseEvent, EventHandler<KeyEvent> keyEventEvent) throws IOException {
        Group root = new Group();
        root.getChildren().add(FXMLLoader.load(StaticSceneLoader.class.getResource(string)));
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(mouseEvent);
        scene.setOnKeyPressed(keyEventEvent);
        return scene;
    }
    public static Scene newScene(String string) throws IOException {
        Group root = new Group();
        root.getChildren().add(FXMLLoader.load(StaticSceneLoader.class.getResource(string)));
        Scene scene = new Scene(root);
        scene.setOnMouseClicked(null);
        scene.setOnKeyPressed(null);
        return scene;
    }
}
