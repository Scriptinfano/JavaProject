package LeetCode;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数
 *
 * @author Mingxiang
 */
public class Rotate {
    public static int[] rotate(int[] nums, int k) {
        int length = nums.length;
        int[] temp = new int[length];
        //把原数组值放到一个临时数组中，
        System.arraycopy(nums, 0, temp, 0, length);
        //然后在把临时数组的值重新放到原数组，并且往右移动k位
        for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = temp[i];
        }
        return nums;
    }

    public static int[] rotate2(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);//反转所有元素
        reverse(nums, 0, k - 1);//反转前k个元素
        reverse(nums, k, length - 1);//反转剩下的元素
        return nums;
    }

    public static void reverse(int[] nums, int begin, int end) {
        if (begin < 0 || begin > end || end > nums.length - 1)
            throw new IllegalArgumentException();
        if (begin == end)
            return;
        while (begin <= end) {
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }


    public static void main(String[] args) {
        int[] arr = Rotate.rotate2(new int[]{1, 2, 3, 4, 5, 6, 7}, 4);
        System.out.println(Arrays.toString(arr));


    }
}
