package JavaAlgorithm.hanoi;

import java.util.Stack;

public class HanoiProblemSolver {

    /**
     * 输出汉诺塔问题的求解步骤，搬运步骤
     * n代表汉诺塔一号盘子上有几个盘子，x,y,z初始请传入1，2，3
     */
    public static void showSolutions(int n, int x, int y, int z) {
        if (n == 1) {
            System.out.println("1号盘从" + x + "号塔移动到" + z + "号塔");
        } else {
            showSolutions(n - 1, x, z, y);
            System.out.println(n + "号盘从" + x + "号塔移动到" + z + "号塔");
            showSolutions(n - 1, y, x, z);
        }
    }

    public static void hanoi(Stack<Integer> A, Stack<Integer> B, Stack<Integer> C) {
        int n = A.size();
        move(n, A, B, C);
    }

    public static void move(int n, Stack<Integer> A, Stack<Integer> B, Stack<Integer> C) {
        if (n == 1) {
            C.push(A.pop());
            return;
        }
        move(n - 1, A, C, B);
        C.push(A.pop());
        move(n - 1, B, A, C);
    }

    public static void main(String[] args) {
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        Stack<Integer> C = new Stack<>();
        Integer[] plates = new Integer[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        for (Integer i : plates) {
            A.push(i);
        }
        hanoi(A, B, C);
        while (!C.isEmpty()) {
            Integer num = C.pop();
            System.out.println(num);
        }

    }

}
