package ir.ac.kntu.cs2d.client;

import ir.ac.kntu.cs2d.SceneManager;
import ir.ac.kntu.cs2d.Scenes.MenuSceneController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        SceneManager sceneManager = SceneManager.getInstance(stage);
        try {
            sceneManager.switchScene(new MenuSceneController(stage).getScene());
        }catch (IOException e){
            e.printStackTrace();
        }
//        Group group = new Group();
//        StaticSceneLoader.setStackPane(new StackPane());
//        StaticSceneLoader.setMainStage(stage);
//        StaticSceneLoader.setLayout(StaticSceneLoader.CLIENT_MAIN_MENU);
//        group.getChildren().add(StaticSceneLoader.getStackPane());
//        Scene scene = StaticSceneLoader.newScene(StaticSceneLoader.CLIENT_MAIN_MENU);
        stage.setTitle("BS2D");
//        stage.setScene(scene);
        stage.show();
    }
}
