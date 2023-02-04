package methmatic;

public class MathUtil {
    private MathUtil(){}
    public static int factorial(int value) {
        if (value == 1) return 1;
        return value * factorial(value - 1);
    }

    public static int maxPrime(int num) {
        if(num<2)throw new IllegalArgumentException("求不大于某数的最大质数时，该数必须大于等于2，因为2是最小的质数");
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

    public static double log(double base, double antilogarithm, int mode) {
        double pre = Math.log(antilogarithm) / Math.log(base);
        if (mode == 1) {
            return Math.ceil(pre);
        } else if (mode == 2) {
            return Math.floor(pre);
        } else if (mode == 3) {
            return pre;
        } else throw new IllegalArgumentException("mode参数传递不正确，请在1，2，3这三个数字之间选择，分别表示向上取整，向下取整，不取整");
    }

    public static double sum(Object[] sequence) {
        if (sequence[0] instanceof Integer || sequence[0] instanceof Double) {
            Double result = 0d;
            int i = 0;
            while (i > sequence.length - 1) {
                result += (Double) sequence[i++];
            }
            return result;
        } else throw new RuntimeException("sum函数只能将整型数组和浮点数数组中的元素加起来");
    }
}
