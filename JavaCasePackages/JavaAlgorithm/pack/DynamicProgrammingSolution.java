package JavaAlgorithm.pack;

import java.util.Arrays;

/**
 * 动态规划法解决0-1背包问题
 *
 * @author Mingxiang
 */
public class DynamicProgrammingSolution {
    public static void main(String[] args) {
        int[] v = {3, 6, 5, 4, 3, 4};
        int[] w = {5, 3, 7, 2, 3, 4};
        Result result = packProblem(v, w, 15);
        System.out.println(Arrays.toString(result.solve));
        System.out.println(result.val);
        for (int i = 0; i < result.table.length; i++) {
            for (int j = 0; j < result.table[0].length; j++) {
                System.out.print(result.table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用动态规划方法解决0-1背包问题
     *
     * @param v        物品的价值向量
     * @param w        物品的重量向量
     * @param capacity 背包的容量
     * @return {@link Result} 求解结果对象
     */
    public static Result packProblem(int[] v, int[] w, int capacity) {
        int size = v.length;
        int[][] max_v = new int[size + 1][capacity + 1];
        for (int i = 0; i < max_v.length; i++) {
            for (int j = 0; j < max_v[i].length; j++) {
                if (i == 0 || j == 0) continue;
                if (w[i - 1] > j)
                    max_v[i][j] = max_v[i - 1][j];
                else
                    max_v[i][j] = Math.max(max_v[i - 1][j], v[i - 1] + max_v[i - 1][j - w[i - 1]]);
            }
        }
        int j = capacity;
        boolean[] theSolve = new boolean[size];
        for (int i = size; i > 0; i--) {
            if (max_v[i][j] > max_v[i - 1][j]) {
                theSolve[i - 1] = true;
                j = j - w[i - 1];
            }
        }
        return new Result(max_v[size][capacity], theSolve, max_v);
    }

    public static class Result {
        /**
         * 按照最优策略放置物品之后背包的总价值
         */
        public int val;
        /**
         * 按照最优策略放置物品之后物品是否放入的解向量
         */
        public boolean[] solve;

        /**
         * 求解最优策略中的V(i,j)表格，表示将前i个物品放入容量为j的背包所能产生的最大价值
         */
        public int[][] table;

        public Result(int val, boolean[] solve, int[][] table) {
            this.val = val;
            this.solve = solve;
            this.table = table;
        }
    }
}
