import DataStructure.exception.NodeNotFoundException;
import DataStructure.tree.trees.BinaryBalanceTree;
import arrayutil.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static arrayutil.ArrayUtil.swap;


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

class Solution {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                    p++;
                }
            }
            q++;
        }
        return p + 1;
    }

    public static int removeElement(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val)
                swap(nums, i--, j--);
        }
        return j + 1;
    }


    public static void main(String[] args) {
        ArrayList<Integer> cache = new ArrayList<>();
        cache.add(3);
        cache.add(3);
        cache.add(3);
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        names.forEach(name -> System.out.println("Hello, " + name));
        Optional<Integer> op = cache.stream().min(Integer::compareTo);
        System.out.println(op.get());
    }

}

