package ir.ac.kntu.cs2d;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public enum ScenesEnum {
    MAIN_MENU{
        @Override
        public String getFXMLPath() {
            return "/layouts/client_main_menu.fxml";
        }
    },GAME_SCENE{
        @Override
        public String getFXMLPath() {
            return "/layouts/game_layout.fxml";
        }
    };
    public abstract String getFXMLPath();
}
