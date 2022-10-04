package hanoi;

public class HanoiProblemSolver {

    public static void showSolutions(int n, int x, int y, int z) {
        if (n == 1) {
            System.out.println("1号盘从" + x + "号塔移动到" + z + "号塔");
        } else {
            showSolutions(n - 1, x, z, y);
            System.out.println(n + "号盘从" + x + "号塔移动到" + z + "号塔");
            showSolutions(n - 1, y, x, z);
        }
    }

    public static void main(String[] args) {

    }
}
