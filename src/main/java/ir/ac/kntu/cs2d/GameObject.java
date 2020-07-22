package ir.ac.kntu.cs2d;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private String name;
    private Transform transform;
    protected int colliderIndex;
    protected GameManager gameManager;
    protected List<RectangleCollider2D> rectangleCollider2DList = new ArrayList<>();
    protected SpriteRenderer spriteRenderer;


    public String getName() {
        return name;
    }

    public Transform getTransform() {
        return transform;
    }

    public List<RectangleCollider2D> getRectangleCollider2d() {
        return rectangleCollider2DList;
    }

    public SpriteRenderer getSpriteRenderer(){
        return spriteRenderer;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public GameObject(String name, SpriteRenderer renderer, List<RectangleCollider2D> collider2DList) {
        this.transform = new Transform(this);
        this.name = name;
        this.spriteRenderer=renderer;
        this.rectangleCollider2DList = collider2DList;
    }

    public GameObject(String name, GameManager gameManager) {
        this.name = name;
        this.gameManager = gameManager;
        this.transform = new Transform(this);
    }

    public void setRectangleCollider2DList(List<RectangleCollider2D> rectangleCollider2DList) {
        this.rectangleCollider2DList = rectangleCollider2DList;
    }

    public void setSpriteRenderer(SpriteRenderer spriteRenderer) {
        this.spriteRenderer = spriteRenderer;
    }
}

