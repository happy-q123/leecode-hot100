package 寻找旋转排序数组的最小值153;

public class 我的思路 {


    //就是33题的二次二分法的写法。
    //为何是与右边而不是左边比较呢？
    public int findMin(int[] nums) {
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
}
