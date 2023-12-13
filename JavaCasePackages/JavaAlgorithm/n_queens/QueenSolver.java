package JavaAlgorithm.n_queens;

import java.util.Arrays;

public class QueenSolver {
    public static void main(String[] args) {
        System.out.println("四皇后问题的解向量=" + Arrays.toString(n_queens(4)));
    }

    public static boolean place(int[] x, int k) {
        for (int i = 1; i < k; i++)
            if (x[i] == x[k] || Math.abs(x[i] - x[k]) == Math.abs(i - k))
                return false;
        return true;
    }

    public static int[] n_queens(int n) {
        int k = 1;
        int[] x = new int[n + 1];
        x[1] = 0;
        x[0] = 1;
        while (k > 0) {
            x[k] = x[k] + 1;
            while (x[k] <= n && !place(x, k)) {
                x[k] = x[k] + 1;
            }
            x[0]++;
            if (x[k] <= n) {
                if (k == n) break;
                else {
                    k++;
                    x[k] = 0;
                    x[0]++;
                }
            } else {
                x[k] = 0;
                k--;
                x[0]--;
            }
        }
        return x;
    }
}