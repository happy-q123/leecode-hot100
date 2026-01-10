package 缺失的第一个正数;

public class gemini给的思路 {
    /*
    * 核心思路：各归其位
    我们可以把数组视为一个哈希表。 规则是：数值为 k 的数字，应该放在数组下标为 k-1 的位置上。

    数字 1 应该放在下标 0。

    数字 2 应该放在下标 1。

    ...

    数字 5 应该放在下标 4。

    我们遍历数组，不断交换数字，直到每个数字都回到了它该去的位置（或者它无法回到属于它的位置，比如负数、大于数组长度的数、
    或已经是正确位置的重复数）。
    *
    *
    * */
    public static int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 核心循环：将 nums[i] 放到它该去的位置（即下标 nums[i] - 1）
            // 只有满足以下 3 个条件才进行交换：
            // 1. nums[i] > 0： 必须是正数
            // 2. nums[i] <= len： 数值不能越界（比如数组长5，数字是100，就没地方放，忽略）
            // 3. nums[nums[i] - 1] != nums[i]： 目标位置上没有正确的数字（避免死循环和重复交换）
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 第二次遍历：查找哪个位置的数字是不对的
        for (int i = 0; i < len; i++) {
            // 如果下标 i 的位置存的不是 i+1，说明 i+1 缺失了
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 如果所有位置都对上了（例如 [1, 2, 3]），那缺失的就是下一个数
        return len + 1;
    }

    // 辅助交换方法
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
