package arrayutil;

import java.util.Arrays;
public class ArraySorterTester {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 200);
        //Integer[] arr ={121,9,182,501,493,213,45,732,314,46,697,6,81,316,75};
        System.out.println(Arrays.toString(arr));
        ArraySorter<Integer> sorter = new ArraySorter<>();
        sorter.setSortArray(arr);
        sorter.quickSort(1,arr.length);
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

}
