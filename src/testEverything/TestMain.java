/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import myScannerAndPrinter.ScannerPlus;

public class TestMain {
    private ScannerPlus cin = new ScannerPlus();

    public static void main(String[] args) {
        TestMain main = new TestMain();
        main.test();
    }

    public void test() {
        double size = 12;
        if (function(size)) {
            System.out.println("是不带小数部分的小数");
        }
    }

    private static boolean function(double value) {
        return value - Math.floor(value) == 0;
    }

}
