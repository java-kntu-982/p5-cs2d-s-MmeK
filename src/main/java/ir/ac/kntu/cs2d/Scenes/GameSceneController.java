package ir.ac.kntu.cs2d.Scenes;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.ac.kntu.cs2d.*;
import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameSceneController extends SceneController {

    ResourcesLoader resourcesLoader;
    ParallelCamera parallelCamera;
    AnimationTimer animationTimer;
    GraphicsContext gc;
    List<GameObject> gameObjects = new ArrayList<>();

//    Canvas canvas;

    AnchorPane anchorPane;

    public GameSceneController() {

    }

    public GameSceneController(Stage stage) {
        super(stage);
//        canvas = new Canvas(800, 600);
    }


    @Override
    public Scene getScene() throws IOException {
        Scene scene = new Scene(root);
        this.scene = scene;
//        scene.setOnKeyPressed(e -> System.out.println(e.getCode()));

        initialize();
        return scene;
    }

    private double speed = 25;
    private Vector2D mousePos = new Vector2D();

    private void initialize() throws IOException {
        resourcesLoader = new ResourcesLoader();

//        gc.setFill(Paint.valueOf("red"));
//        gc.fillRect(0,0,1000,1000);


        ObjectMapper objectMapper = new ObjectMapper();

        String carJson =
                "{\n" +
                        "\t\"map_name\": \"dust\",\n" + "\"width\":400,\n" +
                        "\t\t\"height\":400," +
                        "\t\"walls\": [{\"x\":300,\"y\":200,\"width\":12,\"height\":12}],\n" +
                        "\t\"obstacles\": [{\"x\":300,\"y\":300,\"width\":50,\"height\":50}],\n" +
                        "\t\"ct_spawn\": {\"x\":1,\"y\":2,\"width\":12,\"height\":12},\n" +
                        "\t\"t_spawn\": {\"x\":1,\"y\":2,\"width\":12,\"height\":12},\n" +
                        "\t\"bomb_site\": {\"x\":1,\"y\":2,\"width\":12,\"height\":12}\n" +
                        "}";
        Level level = null;

        try {
            level = objectMapper.readValue(carJson, Level.class);
            System.out.println(level);
            System.out.println(objectMapper.writeValueAsString(level));
        } catch (IOException e) {
            e.printStackTrace();
        }


        GameManager gameManager = new GameManager(level,new Vector2D(300,300),);
        PhysicsManager physicsManager = gameManager.getPhysicsManager();
        Player player = gameManager.getPlayer();
        Camera camera = gameManager.getCamera();
        Pane pane = gameManager.getGamePane();



        gameManager.addGameObject(player);

        Player enemy = new Player("enemy", gameManager, TeamsEnum.Terrorist, resourcesLoader, new Vector2D(0, 0), "enemy");
        enemy.setTransform(new Transform(new Vector2D(200, 200), 0));
        gameManager.addGameObject(enemy);

        camera.follow(player, new Vector2D((double) camera.getWIDTH() / 2 - player.getSpriteRenderer().getSpriteSize().x / 2,
                (double) camera.getHEIGHT() / 2 - player.getSpriteRenderer().getSpriteSize().y / 2));



        Vector2D input = new Vector2D(0, 0);

        scene.setOnMouseClicked(e -> {
            rotatePlayer(player, pane, e);
            Vector2D playerCenter = new Vector2D(player.getTransform().getPosition().x +
                    player.getSpriteRenderer().getSpriteSize().x / 2,
                    player.getTransform().getPosition().y +
                            player.getSpriteRenderer().getSpriteSize().y / 2);
            player.useItem(playerCenter, mousePos);
        });


        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.S)
                input.y = 1;
            if (e.getCode() == KeyCode.W)
                input.y = -1;
            if (e.getCode() == KeyCode.A)
                input.x = -1;
            if (e.getCode() == KeyCode.D)
                input.x = 1;
            if (e.getCode() == KeyCode.DIGIT2) {
                player.getInventoryManager().switchToItem(2);
            }
            if (e.getCode() == KeyCode.DIGIT1)
                player.getInventoryManager().switchToItem(1);
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.S)
                if (input.y > 0)
                    input.y = 0;
            if (e.getCode() == KeyCode.W)
                if (input.y < 0)
                    input.y = 0;
            if (e.getCode() == KeyCode.A)
                if (input.x < 0)
                    input.x = 0;
            if (e.getCode() == KeyCode.D)
                if (input.x > 0)
                    input.x = 0;
        });

        scene.setOnMouseMoved(e -> {
            rotatePlayer(player, pane, e);
        });
        scene.setOnMouseDragged(e -> {
            rotatePlayer(player, pane, e);
        });



        root.getChildren().add(pane);
        animationTimer = new AnimationTimer() {

            long lastUpdate = System.nanoTime();

            @Override
            public void handle(long currentTimeNanoSeconds) {
                double deltaTime = (currentTimeNanoSeconds - lastUpdate) / 1e8;
                gameManager.renderGame();
                camera.update();


                if (!input.equals(new Vector2D()))
                    physicsManager.move(player, input.multiplyBy(speed * deltaTime));
                physicsManager.calculatePhysics();


                lastUpdate = currentTimeNanoSeconds;
            }
        };
        animationTimer.start();

    }

    private void rotatePlayer(Player player, Pane pane, MouseEvent e) {
        Vector2D playerSpriteSize = player.getSpriteRenderer().getSpriteSize();
        Vector2D relativeMousePos = new Vector2D(-e.getSceneX() + pane.getTranslateX() + player.getTransform().getPosition().x + playerSpriteSize.x / 2,
                -e.getSceneY() + pane.getTranslateY() + player.getTransform().getPosition().y + playerSpriteSize.y / 2);
        double angle = Math.toDegrees((Math.atan2(relativeMousePos.y, relativeMousePos.x) - Math.PI / 2));
        player.getTransform().setRotationZ(angle);
        mousePos = new Vector2D(e.getSceneX() - pane.getTranslateX(), e.getSceneY() - pane.getTranslateY());
    }
}




