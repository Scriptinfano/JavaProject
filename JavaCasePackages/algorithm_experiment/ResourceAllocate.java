package algorithm_experiment;

public class ResourceAllocate {
    public static final int m = 8;//可分配的资源份额
    public static final int n = 3;//工程项目的个数
    //利润函数
    public static final int[][] G = {
            {0, 4, 26, 40, 45, 50, 51, 52, 53},
            {0, 5, 15, 40, 60, 70, 73, 74, 75},
            {0, 5, 15, 40, 80, 90, 95, 98, 100}
    };
    public static final int[] optq = new int[n];//最优分配时各工程所得到的份额


    public static void main(String[] args) {

    }
}
