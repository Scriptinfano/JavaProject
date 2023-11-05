package LeetCode;

import java.util.Arrays;

/**
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素
 *
 * @author Mingxiang
 */
public class RemoveElement {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {3, 2, 2, 3};
        int index = s.removeElement(arr, 3);
        System.out.println(Arrays.toString(arr));
        System.out.println(index);
    }

}

class Solution {
    public int removeElement(int[] nums, int val) {
        int p = 0, q = 0;
        while (q <= nums.length - 1) {
            if (nums[q] == val) {
                q++;
            }
            if (q < nums.length) {
                nums[p] = nums[q];
                p++;
                q++;
            } else break;
        }
        return p;
    }
}