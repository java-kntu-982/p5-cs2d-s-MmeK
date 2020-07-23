package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PhysicsManager {
//    private static PhysicsManager instance;

    private ConcurrentMap<GameObject, Vector2D> moves = new ConcurrentHashMap<>();
    private List<List<RectangleCollider2D>> collider2DList = new ArrayList<List<RectangleCollider2D>>();

    public int addColliders(List<RectangleCollider2D> collider2Ds) {
        collider2DList.add(collider2Ds);
        return collider2DList.size() - 1;
    }

    public void move(GameObject gameObject, Vector2D movement) {
        moves.put(gameObject, new Vector2D(movement));
    }

    public void calculatePhysics() {
        calculateMoves();
        moves.forEach((g, m) -> {
            g.getTransform().translate(m);
        });
        moves.clear();
        for (int i = 0; i < collider2DList.size() - 1; i++) {
            List<RectangleCollider2D> list = collider2DList.get(i);
            for (Collider2D col1 :
                    list) {
                for (int j = i + 1; j < collider2DList.size(); j++) {
                    List<RectangleCollider2D> list2 = collider2DList.get(j);
                    for (Collider2D col2 :
                            list2) {
                        if (CollisionDetector.detectCollision(col1, col2, new Vector2D())) {
                            col1.collide(col2);
                            col2.collide(col1);
                        }
                    }
                }
            }
        }
    }

    public void removeColliders(int index) {
        collider2DList.remove(index);
    }

    public List<Collider2D> lineCast(double x1, double y1, double x2, double y2, List<String> layermasks) {
        List<Collider2D> hitColliders = new ArrayList<>();
        for (List<RectangleCollider2D> list : collider2DList) {
            for (Collider2D collider2D :
                    list) {
                if (CollisionDetector.lineRect(x1, y1, x2, y2,
                        ((RectangleCollider2D) collider2D).getTransform().getPosition().x,
                        ((RectangleCollider2D) collider2D).getTransform().getPosition().y,
                        ((RectangleCollider2D) collider2D).getWidth(),
                        ((RectangleCollider2D) collider2D).getHeight()
                )) {
                    boolean tempFlag = false;
                    for (String layer :
                            layermasks) {
                        if (layer.hashCode() == collider2D.getLayer().hashCode()) {
                            tempFlag = true;
                            break;
                        }
                    }
                    if (!tempFlag) {
                        hitColliders.add(collider2D);

                    }
                }
            }

        }
        hitColliders.sort((col1, col2) -> (int) (Vector2D.distance(x1, y1, col1.getTransform().getPosition().x, col1.getTransform().getPosition().y) -
                Vector2D.distance(x1, y1, col2.getTransform().getPosition().x, col2.getTransform().getPosition().y)));
        return hitColliders;
    }

    private void calculateMoves() {

        moves.forEach((g, m) -> {
            if (m.getMagnitude() == 0) moves.remove(g);
            for (List<RectangleCollider2D> list : collider2DList) {
                for (Collider2D collider2D :
                        list) {
                    if (!collider2D.isTrigger())
                        for (Collider2D gameObjectCollider : g.getRectangleCollider2d()) {
                            if (CollisionDetector.detectCollision(gameObjectCollider, collider2D, m)) {
                                m.lazyNormalize();
                                if (CollisionDetector.detectCollision(gameObjectCollider, collider2D, m)) {
                                    gameObjectCollider.collide(collider2D);
                                    collider2D.collide(gameObjectCollider);
                                    moves.remove(g);
                                }
                            }
                        }
                }
            }
        });
    }
}
