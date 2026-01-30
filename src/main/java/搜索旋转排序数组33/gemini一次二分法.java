package 搜索旋转排序数组33;

public class gemini一次二分法 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        // 创建对象并调用
        int result = new gemini一次二分法().search(nums, target);
        System.out.println(result);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        return find(nums, target);
    }

    private int find(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 1. 找到目标
            if (nums[mid] == target) {
                return mid;
            }

            // 2. 判断哪一部分是有序的
            // 注意：这里用 <= 是为了处理 left == mid 的情况（只剩两个元素时）
            if (nums[left] <= nums[mid]) {
                // Case A: 左半边 [left, mid] 是有序的
                // 判断 target 是否在这个有序区间内
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左边，向左缩
                } else {
                    left = mid + 1;  // target 不在左边，去右边找
                }
            } else {
                // Case B: 右半边 [mid, right] 是有序的
                // 判断 target 是否在这个有序区间内
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target 在右边，向右缩
                } else {
                    right = mid - 1; // target 不在右边，去左边找
                }
            }
        }
        return -1;
    }
}
