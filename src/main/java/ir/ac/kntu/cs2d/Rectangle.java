package ir.ac.kntu.cs2d;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Rectangle {
    private double x, y, width, height;

    @JsonCreator
    public Rectangle(@JsonProperty("x") double x, @JsonProperty("y") double y,
                     @JsonProperty("width") double width, @JsonProperty("height") double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
