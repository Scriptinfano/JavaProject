import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.trees.BinaryBalanceTree;
import arrayutil.ArrayUtil;

public class TestAnything {
    public static void main(String[] args) {
        Integer[] arr = {34, 21, 99, 9, 57, 76, 46, 61, 28, 50};
        ArrayUtil.showArray(arr);
        BinaryBalanceTree theBalanceTree = new BinaryBalanceTree(arr);
        try {
            theBalanceTree.search(57);
        } catch (NodeNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

