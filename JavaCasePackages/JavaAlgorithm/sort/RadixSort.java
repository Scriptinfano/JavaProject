package JavaAlgorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3097, 3673, 2985, 1358, 6138, 9135, 4782, 1367, 3684, 139};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //创建10个桶，代表0到9十个数字
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            lists.add(new ArrayList<>());
        //找出数组中最大的元素
        int maxElement = getMax(arr);
        //获得最大数字的位数，也就是基数排序的轮数
        int maxDigit = String.valueOf(maxElement).length();
        for (int k = 0; k < maxDigit; k++) {
            //外层循环为基数排序的轮数
            for (int i = 0; i < arr.length; i++) {
                //获得当前数字后，将其转换为字符串然后在前面补0以便获得和最大数字相同的位数
                String num = "0".repeat(maxDigit - String.valueOf(arr[i]).length()) + arr[i];
                //获取最低位的数字，并以其为要放入的桶的编号
                int targetIndex = Integer.parseInt(String.valueOf(num.charAt(num.length() - 1 - k)));
                //放入对应的桶中
                lists.get(targetIndex).add(arr[i]);
            }
            //收集桶中的元素
            int m = 0;
            for (ArrayList<Integer> list : lists) {
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        arr[m] = list.get(i);
                        m++;
                    }
                    list.clear();
                }
            }
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
