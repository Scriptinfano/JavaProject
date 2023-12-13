package algorithm_experiment;

import java.util.Arrays;

/**
 * 分治算法求10个实数序列中的最大元素和最小元素
 *
 * @author Mingxiang
 */
public class DivideAndConquerFindMaxAndMin {
    public static final int[] arr = new int[]{9, 3, 6, 2, 1, 8, 4, 5, 7, 23};

    public static MaxMin findMaxMin(int low, int high) {
        MaxMin result = new MaxMin();
        MaxMin leftResult, rightResult;
        // 如果数组中只有一个元素
        if (low == high) {
            result.max = arr[low];
            result.min = arr[low];
            return result;
        }
        // 如果数组中有两个元素
        if (high == low + 1) {
            result.max = Math.max(arr[low], arr[high]);
            result.min = Math.min(arr[low], arr[high]);
            return result;
        }
        // 如果数组中有三个以上的元素，递归地分解为两个子问题
        int mid = (low + high) / 2;
        leftResult = findMaxMin(low, mid);
        rightResult = findMaxMin(mid + 1, high);
        // 合并子问题的结果
        result.max = Math.max(leftResult.max, rightResult.max);
        result.min = Math.min(leftResult.min, rightResult.min);
        return result;
    }

    public static void main(String[] args) {
        MaxMin result = findMaxMin(0, arr.length - 1);
        System.out.println("比较序列为：" + Arrays.toString(arr));
        System.out.println("最大元素=" + result.max);
        System.out.println("最小元素=" + result.min);
    }

    public static class MaxMin {
        int max;
        int min;
    }
}
