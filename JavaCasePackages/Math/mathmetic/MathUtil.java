package mathmetic;

public class MathUtil {
    public static int factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }


}
