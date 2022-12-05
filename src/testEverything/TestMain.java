/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import arrayutil.ArraySorter;
import arrayutil.ArrayUtil;
import myScannerAndPrinter.Iotransformer;

import java.util.Arrays;

class TestMain {
    public static void main(String[] args) {
        Integer[] arrayA = ArrayUtil.randomIntegerArray(10, 1, 100);
        ArrayUtil.showArray(arrayA);
        Iotransformer.printer.print("输入要查找的数字：");
        int theNum = Iotransformer.scanner.nextInt();
        int result = ArrayUtil.binarySearch(arrayA, theNum,true);
        if (result == -1)
            Iotransformer.printer.println("没找到");
        else
            Iotransformer.printer.println("找到了,序号：" + result);
    }

}