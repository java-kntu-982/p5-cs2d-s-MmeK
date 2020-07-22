package ir.ac.kntu.cs2d.Util;


public class Vector2D {

    public double x;
    public double y;

    public Vector2D(double var1, double var3) {
        this.x = var1;
        this.y = var3;
    }

    public Vector2D(){
        this.x=0;
        this.y=0;
    }

    public Vector2D(Vector2D var1) {
        this.set(var1);
    }


    public void set(Vector2D var1) {
        this.x = (double) var1.x;
        this.y = (double) var1.y;
    }

    public void set(double var1, double var3) {
        this.x = var1;
        this.y = var3;
    }

    public static double distanceSq(double var0, double var2, double var4, double var6) {
        var0 -= var4;
        var2 -= var6;
        return var0 * var0 + var2 * var2;
    }

    public static double distance(double var0, double var2, double var4, double var6) {
        var0 -= var4;
        var2 -= var6;
        return Math.sqrt(var0 * var0 + var2 * var2);
    }

    public double distanceSq(double var1, double var3) {
        var1 -= this.x;
        var3 -= this.y;
        return var1 * var1 + var3 * var3;
    }


    public double distance(double var1, double var3) {
        var1 -= this.x;
        var3 -= this.y;
        return Math.sqrt(var1 * var1 + var3 * var3);
    }

    public double distance(Vector2D var1) {
        double var2 = var1.x - this.x;
        double var4 = var1.y - this.y;
        return Math.sqrt(var2 * var2 + var4 * var4);
    }

    public Vector2D multiplyBy(double factor) {
        return new Vector2D(x * factor, y * factor);
    }

    public void lazyNormalize() {
        if (this.x != 0) {
            if (this.x > 1) this.x = 1;
            else if (this.x < -1) this.x = -1;
        }
        if (this.y != 0) {
            if (this.y > 1) this.y = 1;
            else if (this.y < -1) this.y = -1;
        }
    }

    public int hashCode() {
        long var1 = 7L;
        var1 = 31L * var1 + Double.doubleToLongBits(this.x);
        var1 = 31L * var1 + Double.doubleToLongBits(this.y);
        return (int) (var1 ^ var1 >> 32);
    }

    public boolean equals(Object var1) {
        if (var1 == this) {
            return true;
        } else if (!(var1 instanceof Vector2D)) {
            return false;
        } else {
            Vector2D var2 = (Vector2D) var1;
            return this.x == var2.x && this.y == var2.y;
        }
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2D getDirection() {
        return new Vector2D(this.x / getMagnitude(), this.y / getMagnitude());
    }

    public static Vector2D extendVector(Vector2D point1, Vector2D point2, double multiplicationFactor) {
        Vector2D distance = point2.subtract(point1);
        return new Vector2D(point1.add(distance.multiplyBy(multiplicationFactor)));
    }

    private Vector2D add(Vector2D vector) {
        return new Vector2D(this.x+vector.x,this.y+vector.y);
    }

    private Vector2D subtract(Vector2D vector) {
        return new Vector2D(this.x - vector.x, this.y - vector.y);
    }


    @Override
    public String toString() {
        return "Vec2d[" + this.x + ", " + this.y + "]";
    }
}
