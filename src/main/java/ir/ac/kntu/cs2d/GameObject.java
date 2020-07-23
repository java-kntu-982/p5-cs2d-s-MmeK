package ir.ac.kntu.cs2d;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class GameObject {
    private String name;
    private Transform transform;
    @JsonIgnore
    protected int colliderIndex;
    @JsonIgnore
    protected GameManager gameManager;
    @JsonIgnore
    protected List<RectangleCollider2D> rectangleCollider2DList = new ArrayList<>();
    @JsonIgnore
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

    public SpriteRenderer getSpriteRenderer() {
        return spriteRenderer;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public GameObject(String name, GameManager gameManager) {
        this.transform = new Transform();
        this.name = name;
        this.gameManager=gameManager;
    }

    public GameObject(String name, GameManager gameManager, Transform transform) {
        this.name = name;
        this.transform=transform;
        this.gameManager = gameManager;
    }

    public void setRectangleCollider2DList(List<RectangleCollider2D> rectangleCollider2DList) {
        this.rectangleCollider2DList = rectangleCollider2DList;
    }

    public void setSpriteRenderer(SpriteRenderer spriteRenderer) {
        this.spriteRenderer = spriteRenderer;
    }
}

