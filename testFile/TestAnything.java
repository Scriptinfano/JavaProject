import arrayutil.ArrayUtil;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

import java.util.Arrays;

public class TestAnything {
private static final ScannerPlus scanner=new ScannerPlus();

    public static void main(String[] args) {
        Integer[]arr=ArrayUtil.randomIntegerArray(100,1,1000);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        while (true){
            System.out.println("输入查询数字:");
            int num=scanner.nextInt();
            if (ArrayUtil.binarySearchRecursion(arr,num,-1,arr.length)) {
                System.out.println("找到了");
            }
            else System.out.println("未找到 ");
            try {
                scanner.noMoreScan();
            } catch (NoMoreScanException e) {
                break;
            }
        }

    }

}

