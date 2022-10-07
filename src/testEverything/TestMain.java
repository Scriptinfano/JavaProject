/**
 * 这是一个临时的程序，用来测试各种代码
 */
package testEverything;

import arrayutil.ArrayUtil;

import java.util.Arrays;

class TestMain {
    public static void main(String[] args) {
        int[] arrayA = ArrayUtil.randomIntArray(10, 1, 100);
        int[] arrayB = ArrayUtil.randomIntArray(7, 1, 100);
        System.out.println(arrayA[0] + ", "+arrayB[1] + ", "+arrayA[2] + ", "+arrayB[3])
        int[] result = mergeSort(arrayA, arrayB);
        System.out.println(Arrays.toString(result));
    }


    /**
     * 将两个升序表，合并为一个降序表
     */
    public static int[] mergeSort(int[] a, int[] b) {
        int[] merge = new int[a.length + b.length];
        int i = a.length - 1;
        int j = b.length - 1;
        int k = 0;
        while (k != merge.length) {
            if (i >= 0 && j >= 0) {
                if (a[i] >= b[j]) {
                    merge[k] = a[i];
                    i--;
                } else {
                    merge[k] = b[j];
                    j--;
                }
            } else if (i < 0) {
                merge[k] = b[j];
                j--;
            } else {
                merge[k] = a[i];
                i--;
            }
            k++;
        }
        return merge;
    }

}