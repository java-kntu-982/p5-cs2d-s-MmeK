package ir.ac.kntu.cs2d;

import ir.ac.kntu.cs2d.Util.Vector2D;

public class CollisionDetector {
    public static boolean detectCollision(Collider2D col1, Collider2D col2, Vector2D offset) {
        if (col1 == col2)
            return false;
        if (col1 instanceof RectangleCollider2D && col2 instanceof RectangleCollider2D) {
//            if (col1.X1 < RectB.X2 && RectA.X2 > RectB.X1 &&
//                    RectA.Y1 < RectB.Y2 && RectA.Y2 > RectB.Y1)
            return col1.getTransform().getPosition().x + offset.x < col2.getTransform().getPosition().x + ((RectangleCollider2D) col2).getWidth() &&
                    col1.getTransform().getPosition().x + offset.x + ((RectangleCollider2D) col1).getWidth() > col2.getTransform().getPosition().x &&
                    col1.getTransform().getPosition().y + offset.y < col2.getTransform().getPosition().y + ((RectangleCollider2D) col2).getHeight() &&
                    col1.getTransform().getPosition().y + offset.y + ((RectangleCollider2D) col1).getHeight() > col2.getTransform().getPosition().y;
        }
        return false;
    }

    public static boolean lineRect(double x1, double y1, double x2, double y2, double rx, double ry, double rw, double rh) {

        // check if the line has hit any of the rectangle's sides
        // uses the Line/Line function below
        boolean left = lineLine(x1, y1, x2, y2, rx, ry, rx, ry + rh);
        boolean right = lineLine(x1, y1, x2, y2, rx + rw, ry, rx + rw, ry + rh);
        boolean top = lineLine(x1, y1, x2, y2, rx, ry, rx + rw, ry);
        boolean bottom = lineLine(x1, y1, x2, y2, rx, ry + rh, rx + rw, ry + rh);

        // if ANY of the above are true, the line
        // has hit the rectangle
        if (left || right || top || bottom) {
            return true;
        }
        return false;
    }

    public static boolean lineLine(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {

        // calculate the direction of the lines
        double uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));
        double uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

//        double intersectionX = x1 + (uA * (x2-x1));
//        double intersectionY = y1 + (uA * (y2-y1));

        // if uA and uB are between 0-1, lines are colliding
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            return true;
        }
        return false;
    }

}
