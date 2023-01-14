package JavaAlgorithm.hanoi;

public class HanoiProblemSolver {

    /**
     * 输出汉诺塔问题的求解步骤，搬运步骤
     * n代表汉诺塔一号盘子上有几个盘子，x,y,z初始请传入1，2，3*/
    public static void showSolutions(int n, int x, int y, int z) {
        if (n == 1) {
            System.out.println("1号盘从" + x + "号塔移动到" + z + "号塔");
        } else {
            showSolutions(n - 1, x, z, y);
            System.out.println(n + "号盘从" + x + "号塔移动到" + z + "号塔");
            showSolutions(n - 1, y, x, z);
        }
    }
}
