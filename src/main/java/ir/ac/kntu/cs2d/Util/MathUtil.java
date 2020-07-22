package ir.ac.kntu.cs2d.Util;

public class MathUtil {
    public static double clamp(double value, double min, double max) {
        if (value > max) {
//            System.out.println("max");
            return max;
        } else if (value < min) {
//            System.out.println("min, value is" + value);
            return min;
        }
        return value;
    }
}
