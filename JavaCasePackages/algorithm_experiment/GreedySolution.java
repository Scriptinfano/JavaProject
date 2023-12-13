package algorithm_experiment;

import java.util.Arrays;

/**
 * 用贪心法求解背包问题
 */
public class GreedySolution {
    public static double[] w = new double[]{10, 20, 30, 40, 50};//重量向量
    public static double[] v = new double[]{20, 30, 66, 40, 60};//价值向量
    public static double[] x = new double[w.length];
    public static double c = 100;//背包剩余总容量

    public static double knapsack() {
        /*
         * 1、预备工作
         * */
        double opt = 0;//opt是已装入背包的物体的总价值

        /*
         * 2、排序
         * 将物品按照单位重量的价值（单位重量价值=某物品价值/某物品重量）从大到小排序
         * */
        Arrays.sort(v);
        Arrays.sort(w);

        /*
         * 2、将可以完整装入的物品先写入解集x[]
         * */
        int i;
        for (i = 0; i < w.length; i++) {
            if (w[i] > c) break;//当当前要装的物品重量>当前背包的剩余容量时，就不能整个装入
            x[i] = 1;//解集的某个元素为1，代表对应位置的物品装入了背包
            opt += v[i];//累加背包的总价值
            c -= w[i];//背包的剩余容量减少
        }

        /*
         * 3、将不可完整装入背包的物品的一部分写入解集x[]
         * */
        if (i < w.length) {
            x[i] = c / w[i];
            opt += c * (v[i] / w[i]);
        }

        return opt;
    }

    public static void main(String[] args) {
        System.out.println("重量向量：" + Arrays.toString(w));
        System.out.println("价值向量：" + Arrays.toString(v));
        double result = knapsack();
        System.out.println("价值总量=" + result);
        System.out.println("解向量=" + Arrays.toString(x));
    }
}
