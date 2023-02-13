package JavaAlgorithm.dynamicProgame;

public class UniquePath {
    /**
     * 求解问题
     *
     * @param m 机器人所走矩阵的行数
     * @param n 机器人所走矩阵的列数
     * @return int 返回机器人从左上角走到右下角的走法数量
     */
    public static int solve(int m, int n) {
        int[][] f = new int[m][n];//开辟二维状态数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    f[i][j] = 1;
                else f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
