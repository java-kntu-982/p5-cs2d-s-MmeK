package ir.ac.kntu.cs2d.Scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class SceneController {
    public abstract Scene getScene() throws IOException;
    protected Scene scene;
    protected Stage stage;
    protected Group root;
    public SceneController(){

    }
    public SceneController(Stage stage){
        this.stage=stage;
        root=new Group();
    }
}
