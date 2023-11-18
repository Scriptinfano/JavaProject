package JavaAlgorithm.pack;

/**
 * 用贪心法求解背包问题
 */
public class GreedySolution {
    /**
     * @param c 背包初始时的剩余容量（总容量）
     * @param w 各个物品的重量
     * @param v 各个物品的价值
     * @param x 将问题的最优解存入此数组，确保x的所有值为0
     */
    public static double knapsack(double c, double[] w, double[] v, double[] x) {
        /*
         * 1、预备工作
         * */
        int n = w.length;//有多少种物品
        double opt = 0;//opt是已装入背包的物体的总价值

        /*
         * 2、排序
         * 将物品按照单位重量的价值（单位重量价值=某物品价值/某物品重量）从大到小排序
         * */
        quick_sort(v, w, 0, n - 1);

        /*
         * 2、将可以完整装入的物品先写入解集x[]
         * */
        int i;
        for (i = 0; i < n; i++) {
            if (w[i] > c) break;//当当前要装的物品重量>当前背包的剩余容量时，就不能整个装入
            x[i] = 1;//解集的某个元素为1，代表对应位置的物品装入了背包
            opt += v[i];//累加背包的总价值
            c -= w[i];//背包的剩余容量减少
        }

        /*
         * 3、将不可完整装入背包的物品的一部分写入解集x[]
         * */
        if (i < n) {
            x[i] = c / w[i];
            opt += c * (v[i] / w[i]);
        }

        return opt;
    }

    private static void quick_sort(double[] arr, double[] arr2, int low, int high) {
        int k;
        if (low < high) {
            k = split(arr, arr2, low, high);
            quick_sort(arr, arr2, low, k - 1);
            quick_sort(arr, arr2, k + 1, high);
        }
    }

    private static int split(double[] A, double[] B, int low, int high) {
        int i = low;
        double x = A[low] / B[low];
        for (int j = low + 1; j <= high; j++) {
            if (A[j] / B[j] <= x) {
                i++;
                if (i != j) {
                    swap(A, i, j);
                    swap(B, i, j);
                }
            }
        }
        swap(A, low, i);
        swap(B, low, i);
        return i;
    }

    private static void swap(double[] A, int i, int j) {
        double temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
