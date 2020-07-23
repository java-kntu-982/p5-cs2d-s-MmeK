package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class GameRenderer {
    private GraphicsContext gc;

    public GameRenderer(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawDebug(GameManager gameManager) {
//        GameObject player = gameObjects.get(0);
//        GameObject player2 = gameObjects.get(1);
//        GameObject level = gameObjects.get(2);
//        Vector2D playerCenter = new Vector2D(player.getTransform().getPosition().x +
//                player.getSpriteRenderer().getSpriteSize().x / 2,
//                player.getTransform().getPosition().y +
//                        player.getSpriteRenderer().getSpriteSize().y / 2);
//        Vector2D extendedMousePos = Vector2D.extendVector(playerCenter, mousePos, 100);
//
//        gc.strokeLine(playerCenter.x,
//                playerCenter.y, extendedMousePos.x, extendedMousePos.y);
//        player2.getRectangleCollider2d().get(0).drawDebug(gc, 2);
//        player.getRectangleCollider2d().get(0).drawDebug(gc, 2);

    }

    public void drawLevel(GameManager gameManager) {
        Level level = gameManager.getLevel();
        gc.setFill(Paint.valueOf(Color.ORANGE.toString()));
        gc.fillRect(0, 0, level.getWidth(), level.getHeight());
        gc.setFill(Paint.valueOf(Color.GRAY.toString()));
        for (ir.ac.kntu.cs2d.Rectangle wall : level.getWalls()) {
            gc.fillRect(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        }
        gc.setFill(Paint.valueOf(Color.BLUE.toString()));
        for (ir.ac.kntu.cs2d.Rectangle obstacle : level.getObstacles()) {
            gc.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
        }
    }

    public void draw(GameManager gameManager) {
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
