package JavaAlgorithm.fibo;

public class Solution {

    public static int q(int n, int m) {
        int p;
        if (n < 1 || m < 1) p = 0;
        else if (n == 1 || m == 1) p = 1;
        else if (n <= m) p = q(n, n - 1) + 1;
        else p = q(n, m - 1) + q(n - m, m);
        return p;
    }
    public static void main(String[] args) {
        int q = q(5, 5);
        System.out.println(q);
    }
}
