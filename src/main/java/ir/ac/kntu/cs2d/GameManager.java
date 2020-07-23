package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private PhysicsManager physicsManager;
    private List<GameObject> gameObjects = new ArrayList<>();
    private final Level level;
    private GameRenderer gameRenderer;
    private Camera camera;
    private Player player;
    private ResourcesLoader resourcesLoader;
    private Pane gamePane;

    public GameManager(Level level,Vector2D cameraSize,TeamsEnum teamsEnum,ResourcesLoader resourcesLoader) {
        this.physicsManager = new PhysicsManager();
        this.resourcesLoader = resourcesLoader;
        Canvas canvas = new Canvas(level.getWidth(), level.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        camera = new Camera((int)cameraSize.x,(int)cameraSize.y , canvas);
        this.gameRenderer= new GameRenderer(gc);
        this.level=level;
        gamePane = new Pane(canvas);
        javafx.scene.shape.Rectangle rectangle = camera.getClipRectangle();
        gamePane.setClip(rectangle);
        gamePane.translateXProperty().bind(rectangle.translateXProperty().multiply(-1));
        gamePane.translateYProperty().bind(rectangle.translateYProperty().multiply(-1));
        this.player= new Player("player", this,, TeamsEnum.Counter_Terrorist, resourcesLoader, new Vector2D(0, 0), "player");
        initializeLevel();
    }

    private void initializeLevel() {
        GameObject levelGameObject = new GameObject("level", this, new Transform());
        List<RectangleCollider2D> levelWalls = new ArrayList<>();
        for (Rectangle wall : this.getLevel().getWalls()) {
            RectangleCollider2D wallCollider = new RectangleCollider2D(levelGameObject, new Transform(new Vector2D(wall.getX(),
                    wall.getY()), 0), wall.getWidth(), wall.getHeight(), null);
            wallCollider.setLayer("wall");
            levelWalls.add(wallCollider);
        }
        physicsManager.addColliders(levelWalls);

        List<RectangleCollider2D> levelObstacles = new ArrayList<>();
        for (Rectangle wall : getLevel().getObstacles()) {
            RectangleCollider2D levelColliders = new RectangleCollider2D(levelGameObject, new Transform(new Vector2D(wall.getX(),
                    wall.getY()), 0), wall.getWidth(), wall.getHeight(), null);
            levelColliders.setLayer("obstacle");
            levelObstacles.add(levelColliders);
        }
        physicsManager.addColliders(levelObstacles);

    }

    public Level getLevel() {
        return level;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void renderGame(){
        gameRenderer.drawLevel(this);
        gameRenderer.draw(this);
    }

    public Camera getCamera() {
        return camera;
    }

    public Player getPlayer() {
        return player;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
