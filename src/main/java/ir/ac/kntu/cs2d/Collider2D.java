package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;

public abstract class Collider2D {

    private boolean isTrigger = false;
    private Transform transform;
    private Collision collision=null;
    private GameObject gameObject;
    private String layer = "default";


    public void collide(Collider2D other) {
        if (collision != null)
            collision.collide(other);
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Transform getTransform() {
        return new Transform(new Vector2D(transform.getPosition().x + gameObject.getTransform().getPosition().x,
                        transform.getPosition().y + gameObject.getTransform().getPosition().y),
                transform.getRotationZ() + gameObject.getTransform().getRotationZ());
    }

    public Collider2D(GameObject gameObject, Transform transform, Collision collision) {
        this.transform = transform;
        this.gameObject = gameObject;
        this.collision = collision;
    }

    public Collider2D(GameObject gameObject, Transform transform, boolean isTrigger, Collision collision) {
        this.transform = transform;
        this.gameObject = gameObject;
        this.collision = collision;
        this.isTrigger = isTrigger;
    }

    public Collider2D(GameObject gameObject, Transform transform, String layer, Collision collision) {
        this.transform = transform;
        this.gameObject = gameObject;
        this.collision = collision;
        this.layer = layer;
    }

    public Collider2D(GameObject gameObject, Transform transform, boolean isTrigger, String layer, Collision collision) {
        this.transform = transform;
        this.gameObject = gameObject;
        this.collision = collision;
        this.layer = layer;
        this.isTrigger = isTrigger;
    }


    public void setOnCollision(Collision collision) {
        this.collision = collision;
    }

    public String getLayer() {
        return layer;
    }

    @Override
    public String toString() {
        return "Collider2D{" +
                ", transform=" + getTransform() +
                ", layer="+ getLayer()+
                '}';
    }
}
