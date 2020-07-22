package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Models.Gun;
import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.image.Image;


public class Player extends GameObject {

    private TeamsEnum team;
    private InventoryManager inventoryManager;
    private HealthManager healthManager;

    public Player(String name, GameManager gameManager, TeamsEnum team, ResourcesLoader resourcesLoader, Vector2D position, String layer) {
        super(name, gameManager);
        PhysicsManager physicsManager=gameManager.getPhysicsManager();
        RectangleCollider2D playerCollider = new RectangleCollider2D(this,
                new Transform(this, position, 0), 30, 30,
                null);
        playerCollider.setLayer(layer);
        this.rectangleCollider2DList.add(playerCollider);
        colliderIndex = physicsManager.addColliders(rectangleCollider2DList);
        this.spriteRenderer = new SpriteRenderer(new Image[]{resourcesLoader.getImages().get(team.toString())});
        this.inventoryManager = new InventoryManager();
        inventoryManager.addItem(new Gun("AK-47", physicsManager, 200, 2000, 100, 22,
                30, resourcesLoader.getAudioClips().get("AK-47_Fire"), resourcesLoader.getAudioClips().get("AK-47_Reload"), getRectangleCollider2d().get(0).getLayer()));
        inventoryManager.addItem(new Gun("Glock", physicsManager, 100, 1000, 500, 10,
                20, resourcesLoader.getAudioClips().get("Glock_Fire"), resourcesLoader.getAudioClips().get("Glock_Reload"), getRectangleCollider2d().get(0).getLayer()));
        this.team = team;
        this.healthManager = new HealthManager(100, this);
    }

    public void useItem(Vector2D playerCenter, Vector2D mousePos) {
        getInventoryManager().getCurrentItem().onUsed(playerCenter, mousePos);
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public HealthManager getHealthManager() {
        return healthManager;
    }

    public TeamsEnum getTeam() {
        return team;
    }

    public void die() {
        gameManager.getPhysicsManager().removeColliders(colliderIndex);
        gameManager.removeGameObject(this);
    }
}
