package ir.ac.kntu.cs2d.Scenes;

import ir.ac.kntu.cs2d.*;
import ir.ac.kntu.cs2d.Util.ResourceHelper;
import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        Canvas canvas = new Canvas(800, 800);
        gc = canvas.getGraphicsContext2D();
        Camera camera = new Camera(500, 500, canvas);
//        gc.setFill(Paint.valueOf("red"));
//        gc.fillRect(0,0,1000,1000);
        GameManager gameManager = new GameManager();
        PhysicsManager physicsManager= gameManager.getPhysicsManager();
//        PhysicsManager physicsManager = new PhysicsManager();
//        List<GameComponent> levelComponent = new ArrayList<>();
//        GameObject level = new GameObject("level", levelComponent);
//        level.setTransform(new Transform(level, new Vector2D(0, 0), 0));
//        levelComponent.add(new SpriteRenderer(levelImage, level, gc));
//        levelComponent.add(new RectangleCollider2D(level, physicsManager, new Transform(level, new Vector2D(0, -100), 0), 800, 100, e -> System.out.println(e.getTransform().getGameObject())));
//        levelComponent.add(new RectangleCollider2D(level, physicsManager, new Transform(level, new Vector2D(-100, 0), 0), 100, 800, e -> System.out.println(e.getTransform().getGameObject())));
//        levelComponent.add(new RectangleCollider2D(level, physicsManager, new Transform(level, new Vector2D(800, 0), 0), 100, 800, e -> System.out.println(e.getTransform().getGameObject())));
//        levelComponent.add(new RectangleCollider2D(level, physicsManager, new Transform(level, new Vector2D(0, 800), 0), 800, 100, e -> System.out.println(e.getTransform().getGameObject())));

//        gameObjects.add(level);

//        List<GameComponent> playerComponents = new ArrayList<>();
//        GameObject player = new GameObject("player", playerComponents);
//        Transform playerTransform = new Transform(player, new Vector2D(100, 100), 0);
//        player.setTransform(playerTransform);
//        RectangleCollider2D playerCollider = new RectangleCollider2D(player, physicsManager, new Transform(player,new Vector2D(), 0), 32, 32, null);
//        playerCollider.setLayer("player");
//        playerComponents.add(playerCollider);
//        playerComponents.add(new SpriteRenderer(playerImage, player, gc));
//        player.start();

//
        Player player = new Player("player", gameManager, TeamsEnum.Counter_Terrorist, resourcesLoader, new Vector2D(0, 0), "player");
        player.setTransform(new Transform(player, new Vector2D(0, 0), 0));
        gameManager.addGameObject(player);
//        gameObjects.add(player);

        Player enemy = new Player("enemy", gameManager, TeamsEnum.Terrorist, resourcesLoader, new Vector2D(0, 0), "enemy");
        enemy.setTransform(new Transform(enemy, new Vector2D(200, 200), 0));
        gameManager.addGameObject(enemy);
//        gameObjects.add(enemy);

        //
//        Player player2 = new Player("player",TeamsEnum.Counter_Terrorist);
//        player2.setTransform(new Transform(player,new Vector2D(100,100),0));

//        List<GameComponent> player2Components = new ArrayList<>();
//        GameObject player2 = new GameObject("player2", player2Components);
//        player2.setTransform(new Transform(player, new Vector2D(200, 200), 0));
//        RectangleCollider2D player2Collider = new RectangleCollider2D(player2, physicsManager, new Transform(player2, new Vector2D(0, 0), 0), 32, 32, null);
//        player2Components.add(player2Collider);
//        player2Components.add(new SpriteRenderer(player2Image, player2, gc));
//        player2.start();

//        gameObjects.add(player2);


        camera.follow(player, new Vector2D((double) camera.getWIDTH() / 2 - player.getSpriteRenderer().getSpriteSize().x / 2,
                (double) camera.getHEIGHT() / 2 - player.getSpriteRenderer().getSpriteSize().y / 2));

        Pane pane = new Pane(canvas);
        Rectangle rectangle = camera.getClipRectangle();

        Vector2D input = new Vector2D(0, 0);

        scene.setOnScroll(e -> {
//            (int)e.getDeltaY()
        });

        scene.setOnMouseClicked(e -> {
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
            Vector2D playerSpriteSize = player.getSpriteRenderer().getSpriteSize();
            Vector2D relativeMousePos = new Vector2D(-e.getSceneX() + pane.getTranslateX() + player.getTransform().getPosition().x + playerSpriteSize.x / 2,
                    -e.getSceneY() + pane.getTranslateY() + player.getTransform().getPosition().y + playerSpriteSize.y / 2);
            double angle = Math.toDegrees((Math.atan2(relativeMousePos.y, relativeMousePos.x) - Math.PI / 2));
            player.getTransform().setRotationZ(angle);
            mousePos = new Vector2D(e.getSceneX() - pane.getTranslateX(), e.getSceneY() - pane.getTranslateY());


        });
        scene.setOnMouseDragged(e -> {
            Vector2D playerSpriteSize = player.getSpriteRenderer().getSpriteSize();
            Vector2D relativeMousePos = new Vector2D(-e.getSceneX() + pane.getTranslateX() + player.getTransform().getPosition().x + playerSpriteSize.x / 2,
                    -e.getSceneY() + pane.getTranslateY() + player.getTransform().getPosition().y + playerSpriteSize.y / 2);
            double angle = Math.toDegrees((Math.atan2(relativeMousePos.y, relativeMousePos.x) - Math.PI / 2));
            player.getTransform().setRotationZ(angle);
            mousePos = new Vector2D((e.getSceneX() - pane.getTranslateX()), (e.getSceneY() - pane.getTranslateY()));


        });
        pane.setClip(rectangle);

        pane.translateXProperty().bind(rectangle.translateXProperty().multiply(-1));
        pane.translateYProperty().bind(rectangle.translateYProperty().multiply(-1));

        root.getChildren().add(pane);
        animationTimer = new AnimationTimer() {

            long lastUpdate = System.nanoTime();

            @Override
            public void handle(long currentTimeNanoSeconds) {
                double deltaTime = (currentTimeNanoSeconds - lastUpdate) / 1e8;
                draw(gameManager);
//                drawDebug();
                camera.update();


                if (!input.equals(new Vector2D()))
                    physicsManager.move(player, input.multiplyBy(speed * deltaTime));
                physicsManager.calculatePhysics();


                lastUpdate = currentTimeNanoSeconds;
            }
        };
        animationTimer.start();

    }

    private void drawDebug(GameManager gameManager) {
        GameObject player = gameObjects.get(0);
        GameObject player2 = gameObjects.get(1);
        GameObject level = gameObjects.get(2);
        Vector2D playerCenter = new Vector2D(player.getTransform().getPosition().x +
                player.getSpriteRenderer().getSpriteSize().x / 2,
                player.getTransform().getPosition().y +
                        player.getSpriteRenderer().getSpriteSize().y / 2);
        Vector2D extendedMousePos = Vector2D.extendVector(playerCenter, mousePos, 100);

        gc.strokeLine(playerCenter.x,
                playerCenter.y, extendedMousePos.x, extendedMousePos.y);
        player2.getRectangleCollider2d().get(0).drawDebug(gc, 2);
        player.getRectangleCollider2d().get(0).drawDebug(gc, 2);

    }

    private void draw(GameManager gameManager) {
        for (GameObject gameObject : gameManager.getGameObjects()) {
            SpriteRenderer renderer = gameObject.getSpriteRenderer();
            if (renderer != null) {
                Transform gameObjectTransform = gameObject.getTransform();
                gc.save();
                gc.transform(new Affine(new Rotate(gameObjectTransform.getRotationZ(), gameObjectTransform.getPosition().x + renderer.getSpriteSize().x / 2, gameObjectTransform.getPosition().y + renderer.getSpriteSize().y / 2)));
                gc.drawImage(renderer.getSprites()[0], gameObjectTransform.getPosition().x, gameObjectTransform.getPosition().y);
                gc.restore();
            }
        }
    }


}




