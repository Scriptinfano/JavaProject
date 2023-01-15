package DataStructure.tree;

import DataStructure.tree.nodes.BinarySortTreeNode;
import DataStructure.tree.trees.BinarySortTree;
import arrayutil.ArrayUtil;
import myScannerAndPrinter.IOTransformer;
import myScannerAndPrinter.NoMoreScanException;
import myScannerAndPrinter.ScannerPlus;

public class TreeTester {
    public static void main(String[] args) {
        TestBinarySortTree.main(null);
    }
}
class TestBinarySortTree {
    public static void main(String[] args) {

        //Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 100);

        Integer[] arr = {34, 21, 99, 9, 57, 76, 46, 61, 28, 50};
        ArrayUtil.showArray(arr);
        BinarySortTree theSortTree = new BinarySortTree(arr);
        //testSearch(theSortTree);
        testDelete(theSortTree);
    }

    private static void testSearch(BinarySortTree theSortTree) {
        while (true) {
            IOTransformer.printer.print("输入要查找的数字：");
            int theNum = IOTransformer.scanner.nextInt();
            BinarySortTreeNode theNode = theSortTree.searchNode(theNum);
            if (theNode != null) {
                IOTransformer.printer.println("找到了位于" + theNode + "的节点");
            } else {
                IOTransformer.printer.println("没找到");
            }
            try {
                IOTransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                break;
            }
        }
        ScannerPlus.pause();
    }

    //TODO 测试删除功能未完成
    private static void testDelete(BinarySortTree theSortTree) {
        while (true) {
            IOTransformer.printer.println("输入要删除的数字");
            int theNum = IOTransformer.scanner.nextInt();
            if (theSortTree.searchNode(theNum) != null) {
                //找到了该节点，可以删除
                theSortTree.deleteNode(theNum);
            } else {
                IOTransformer.printer.println("没有找到节点，无法删除");
            }
            try {
                IOTransformer.scanner.noMoreScan();//询问用户是否还需要输入
            } catch (NoMoreScanException e) {
                break;
            }

        }
    }
}