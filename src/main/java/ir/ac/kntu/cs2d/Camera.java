package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.MathUtil;
import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Rectangle;

public class Camera {
    private final int WIDTH, HEIGHT;
    private Transform offsetTransform;
    private GameObject followedGameObject;
    private boolean isFollowing = false;
    private Rectangle clipRectangle;
    private Vector2D followOffset;
    private Canvas canvas;

    public Camera(int width, int height, Canvas canvas) {
        this.canvas = canvas;
        WIDTH = width;
        HEIGHT = height;
        clipRectangle = new Rectangle(0,0, WIDTH, HEIGHT);
    }

    public void follow(GameObject centeredGameObject, Vector2D followOffset) {
        this.followedGameObject = centeredGameObject;
        this. offsetTransform = new Transform();
        this.followOffset=followOffset;
        clipRectangle = new Rectangle(offsetTransform.getPosition().x, offsetTransform.getPosition().y, WIDTH, HEIGHT);
        isFollowing = true;
    }

    public GameObject getFollowedGameObject() {
        return followedGameObject;
    }

    public void setFollowing(boolean following) {
        this.isFollowing = following;
    }

    public Transform getOffsetTransform() {
        return offsetTransform;
    }

    public Rectangle getClipRectangle() {
        return clipRectangle;
    }


    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void update() {
        if (isFollowing) {
            offsetTransform.setPosition(new Vector2D(-followOffset.x +  followedGameObject.getTransform().getPosition().x,
                   -followOffset.y+ followedGameObject.getTransform().getPosition().y));
        }
        clipRectangle.setTranslateX(MathUtil.clamp(offsetTransform.getPosition().x, 0, canvas.getWidth() - WIDTH));
        clipRectangle.setTranslateY(MathUtil.clamp(offsetTransform.getPosition().y, 0, canvas.getHeight() - HEIGHT));
    }
}
