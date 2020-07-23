package ir.ac.kntu.cs2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class RectangleCollider2D extends Collider2D {

    private final double width, height;

    public RectangleCollider2D(GameObject gameObject, Transform transform, double width, double height, Collision collision) {
        super(gameObject, transform, collision);
        this.width = width;
        this.height = height;
    }


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return  super.toString() +  "RectangleCollider2D{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public void drawDebug(GraphicsContext gc,int strokeWidth){
        Paint previousPaint = gc.getFill();
        gc.setFill(Paint.valueOf("green"));
        gc.fillRect(getTransform().getPosition().x-strokeWidth,getTransform().getPosition().y-strokeWidth,
                getWidth()+strokeWidth,getHeight()+strokeWidth);
        gc.setFill(previousPaint);
    }
}
