package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Scenes.GameSceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FxControllerClientMainMenu {

    @FXML
    Button button1;

    public FxControllerClientMainMenu() {

    }

//    @FXML
//    private void initialize() {
//        button1.setOnMouseClicked((e) -> {
//            try {
//                SceneManager sceneManager = SceneManager.getInstance(SceneManager.getPrimaryStage());
//                sceneManager.switchScene(new GameSceneController().getScene());
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        });
//    }
}
