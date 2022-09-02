/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        int test1 = InputChecker.getInt();
        int test2 = InputChecker.getInt();
        System.out.println("第一个数字：" + test1);
        System.out.println("第二个数字：" + test2);
    }
}

class InputChecker {
    private static Scanner scanner = new Scanner(System.in);

    //只需要将要输入的数字传入函数，即可检测输入的是否正确
    public static int getInt() {
        int data = 0;
        while (true) {
            try {
                data = scanner.nextInt();
                return data;
            } catch (InputMismatchException e) {
                System.out.println("输入的数据不是整型，请重新输入：");
                scanner = new Scanner(System.in);//java中通过重新分配Scanner对象来达到刷新缓冲区的目的
            }
        }
    }
}

