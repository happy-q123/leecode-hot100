package 在排序数组中查找元素的第一个和最后一个位置34;

public class Solution {

    public static void main(String args[]){
        int[] nums = {5, 7, 7, 8, 8, 8, 8, 10};
        int target = 8;
        // 实例化并调用
        int[] result = new Solution().searchRange(nums, target);
        
        System.out.println("Result: [" + result[0] + ", " + result[1] + "]");
    }

    public int[] searchRange(int[] nums, int target) {
        // 1. 寻找左边界
        int leftIdx = findBound(nums, target, true);
        
        // 如果左边界都找不到，说明目标不存在，直接返回 {-1, -1}
        if (leftIdx == -1) {
            return new int[]{-1, -1};
        }
        
        // 2. 寻找右边界
        int rightIdx = findBound(nums, target, false);
        
        return new int[]{leftIdx, rightIdx};
    }

    /**
     * 辅助方法：二分查找边界
     * @param nums 数组
     * @param target 目标值
     * @param isLeft true表示找左边界(First)，false表示找右边界(Last)
     * @return 边界的索引，如果没找到返回 -1
     */
    private int findBound(int[] nums, int target, boolean isLeft) {
        int left = 0;
        int right = nums.length - 1;
        int candidate = -1; // 用于记录潜在的目标位置

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                // 中间值太小，往右找
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 中间值太大，往左找
                right = mid - 1;
            } else {
                // 重点：nums[mid] == target
                candidate = mid; // 先记录下当前位置，因为这可能是边界
                
                if (isLeft) {
                    // 如果是要找【左】边界，就收缩右侧，继续在左半边找有没有更靠前的
                    right = mid - 1; 
                } else {
                    // 如果是要找【右】边界，就收缩左侧，继续在右半边找有没有更靠后的
                    left = mid + 1; 
                }
            }
        }
        return candidate;
    }
}