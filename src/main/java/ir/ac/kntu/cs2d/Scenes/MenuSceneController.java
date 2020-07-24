package ir.ac.kntu.cs2d.Scenes;

import ir.ac.kntu.cs2d.SceneManager;
import ir.ac.kntu.cs2d.ScenesEnum;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuSceneController extends SceneController {

    @FXML
    Button button1;


    public MenuSceneController(){

    }

    public MenuSceneController(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() throws IOException {
        Group root = new Group();
        root.getChildren().add(FXMLLoader.load(getClass().getResource(ScenesEnum.MAIN_MENU.getFXMLPath())));
        Scene scene = new Scene(root);
        this.scene = scene;
        return scene;
    }

    @FXML
    private void initialize() {
        button1.setOnMouseClicked((e) -> {
            try {
                SceneManager sceneManager = SceneManager.getInstance(SceneManager.getPrimaryStage());
                sceneManager.switchScene(new GameSceneController(stage).getScene());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
