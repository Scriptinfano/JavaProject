package JavaIo;

import java.util.Scanner;

public class IoMain {
    public static void main(String[] args) {

    }

    public static void  testIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String str1 = scanner.next();
                System.out.println("输入的字符串：" + str1);
            } else break;

        }
    }

}
