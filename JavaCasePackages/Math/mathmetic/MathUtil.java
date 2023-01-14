package Math.mathmetic;

public class MathUtil {
    public static int factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }

    public static int maxPrime(int num) {
        for (int i = num - 1; i >= 2; i--) {
            if (isPrime(i))
                return i;
        }
        return 2;
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
