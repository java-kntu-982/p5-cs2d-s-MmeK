package ir.ac.kntu.cs2d;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private PhysicsManager physicsManager;
    private List<GameObject> gameObjects = new ArrayList<>();

    public GameManager(){
        this.physicsManager=new PhysicsManager();
    }

    public void addGameObject(GameObject gameObject){
        gameObjects.add(gameObject);
    }
    public void removeGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }


    public PhysicsManager getPhysicsManager() {
        return physicsManager;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
