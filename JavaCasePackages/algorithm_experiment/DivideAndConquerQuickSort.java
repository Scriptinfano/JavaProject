package algorithm_experiment;

import java.util.Arrays;

public class DivideAndConquerQuickSort {
    public static final int[] arr = new int[]{9, 4, 6, 2, 5, 8, 4, 5, 6, 22};

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void recurse(int low, int high) {
        if (low < high) {
            int i = low, j = high;
            int standard = arr[i];//选定枢轴值
            while (i < j) {
                //如果每次都选取区间的左端作为枢轴值，则右侧指针先向左走然后左侧指针再朝右走
                //只要右指针指向的值仍然大于枢轴值，则不断将右指针向左移动
                while (arr[j] >= standard && i < j) {
                    j--;
                }
                //此时右指针指向了小于枢轴值的值
                if (i < j) {
                    swap(i, j);
                    i++;
                }
                while (arr[i] <= standard && i < j) {
                    i++;
                }
                if (i < j) {
                    swap(i, j);
                    j--;
                }
            }
            //此时i和j相等，i和j的值就是轴值的下标，轴值左侧都是小于轴值的值，轴值右侧都是大于轴值的值
            recurse(low, i - 1);
            recurse(i + 1, high);
        }
    }

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(arr));
        recurse(0, arr.length - 1);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}
