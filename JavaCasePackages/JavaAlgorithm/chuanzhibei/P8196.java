package JavaAlgorithm.chuanzhibei;

import java.util.Scanner;

public class P8196 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int groupNum = scanner.nextInt();
        int[] result = new int[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int[] arr = read();
            result[i] = solve(arr);
        }
        for (int re : result) {
            System.out.println(re);
        }
    }

    public static int[] read() {
        int num = scanner.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scanner.nextInt();
        }
        return arr;
    }

    public static int solve(int[] arr) {
        int sum = 0;
        for (int i = 1; i <= arr.length; i++)
            for (int j = i; j <= arr.length; j++)
                for (int k = j; k <= arr.length; k++)
                    if (arr[i - 1] + arr[j - 1] == arr[k - 1])
                        sum++;
        return sum;
    }
}
