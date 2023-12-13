package JavaAlgorithm.chuanzhibei;

import java.util.Scanner;

public class P8822 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        int m = scanner.nextInt();
        int a = scanner.nextInt();
        int upTimes = n / m;//提升价格的次数
        int last = n % m;
        int sum = 0;
        int now = 0;
        for (int i = 0; i < upTimes; i++) {
            now = v + a * i;
            sum += now * m;
        }
        sum += last * now;
        System.out.println(sum);
    }
}
