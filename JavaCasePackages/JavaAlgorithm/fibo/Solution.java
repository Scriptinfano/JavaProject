package JavaAlgorithm.fibo;

public class Solution {
    public static int fibo(int n) {
        if (n == 0 || n == 1)
            return n;
        return fibo(n - 2) + fibo(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(fibo(4));
    }
}
