package JavaAlgorithm.chuanzhibei;

import java.util.Scanner;

/**
 * 检测给定字符串出现了几次chuanzhi
 *
 * @author Mingxiang
 */
public class P8195 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] charArr = str.toCharArray();
        char[] chuan = {'c', 'h', 'u', 'a', 'n', 'z', 'h', 'i'};
        int sum2 = 0;
        int j = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == chuan[j]) {
                j++;
                if (j == chuan.length) {
                    sum2++;
                    j = 0;
                }

            } else {
                j = 0;
            }

        }
        System.out.println(sum2);
    }
}
