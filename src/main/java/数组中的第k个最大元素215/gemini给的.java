package 数组中的第k个最大元素215;

public class gemini给的 {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public int quickSelect(int[] nums, int start, int end, int targetIndex) {
        if (start >= end) return nums[start];

        // 1. 选取基准值 (建议随机化，这里为了演示简化直接取 start)
        int pivot = nums[start];
        int left = start;
        int right = end;

        // 2. 核心 Partition 逻辑
        while (left < right) {

            // 步骤 A: 从右往左，找一个“比 pivot 小”的数 (因为要从大到小排)
            // 注意：这里必须要有 left < right 的检查，防止越界
            while (left < right && nums[right] <= pivot) {
                right--;
            }
            // 找到了大的，填入左边的坑
            nums[left] = nums[right];

            // 步骤 B: 从左往右，找一个“比 pivot 大”的数
            while (left < right && nums[left] >= pivot) {
                left++;
            }
            // 找到了小的，填入右边的坑
            nums[right] = nums[left];
        }

        // 3. 相遇位置就是 pivot 的最终位置
        nums[left] = pivot;

        // 4. 标准的快速选择递归逻辑
        if (left == targetIndex) {
            return nums[left];
        } else if (left > targetIndex) {
            return quickSelect(nums, start, left - 1, targetIndex);
        } else {
            return quickSelect(nums, left + 1, end, targetIndex);
        }
    }
}
