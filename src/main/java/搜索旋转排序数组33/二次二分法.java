package 搜索旋转排序数组33;

public class 二次二分法 {

    public static void main(String args[]) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 4;
        int result = new 二次二分法().search(nums, target);
        System.out.println("Result index: " + result);
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        // 步骤 1: 第一次二分，找到最小值的索引 (旋转点)
        int pivotIndex = findMinIndex(nums);

        // 步骤 2: 根据 target 和 pivot 的值，判断 target 在哪个有序区间
        // 旋转点将数组分为：[0, pivotIndex-1] 和 [pivotIndex, length-1]
        
        // 如果 pivotIndex 是 0，说明数组没旋转（或旋转了n次），直接查整个数组
        if (pivotIndex == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        // 判断 target 属于哪一段
        // 比较 target 和 数组最后一个元素（或第一个元素）来决定
        if (target >= nums[pivotIndex] && target <= nums[nums.length - 1]) {
            // target 在右半段（较小的那段）
            return binarySearch(nums, pivotIndex, nums.length - 1, target);
        } else {
            // target 在左半段（较大的那段）
            return binarySearch(nums, 0, pivotIndex - 1, target);
        }
    }

    /**
     * 第一次二分：寻找旋转排序数组的最小值索引
     */
    private int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) { // 注意这里没有 =
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // mid 在左半段高位，最小值肯定在 mid 右边
                left = mid + 1;
            } else {
                // mid 在右半段低位，mid 可能是最小值，也可能在左边
                right = mid;
            }
        }
        return left; // 循环结束时 left == right，指向最小值
    }

    /**
     * 第二次二分：标准的二分查找
     */
    private int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}