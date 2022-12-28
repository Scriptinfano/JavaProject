package arrayutil;

import java.util.Arrays;


public class ArraySorterTester {
    public static void main(String[] args) {
        Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 200);
        System.out.println(Arrays.toString(arr));
        ArraySorter<Integer> sorter = ArraySorter.createArraySorter(arr);
        sorter.heapSort();
        System.out.println(Arrays.toString(arr));
    }

    public static void test(){
        Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 100);
        System.out.println(Arrays.toString(arr));
        ArraySorter<Integer> sorter = ArraySorter.createArraySorter(arr);
        sorter.mergeSort();
        System.out.println(Arrays.toString(arr));
    }
}
