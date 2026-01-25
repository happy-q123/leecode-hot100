package 搜索插入位置35;

public class gemini简洁版本 {
    public int searchInsert(int[] nums, int target) {
        return compute(nums, target, 0, nums.length - 1);
    }

    private int compute(int[] nums, int target, int left, int right) {
        // Base case: 搜索范围无效，left即为插入点
        if (left > right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            // 目标在左边，递归查找 [left, mid-1]
            return compute(nums, target, left, mid - 1);
        } else {
            // 目标在右边，递归查找 [mid+1, right]
            return compute(nums, target, mid + 1, right);
        }
    }
}
