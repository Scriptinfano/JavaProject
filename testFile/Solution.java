class Solution {
    public int removeDuplicates(int[] nums) {
        int startNum = nums[0];
        int lastNum = nums[nums.length - 1];
        int j = 0;
        for (int i = startNum; i <= lastNum; i++) {
            nums[j] = i;
            j++;
        }
        return lastNum + 1;
    }
}
