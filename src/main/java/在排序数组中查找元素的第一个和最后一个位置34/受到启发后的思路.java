package 在排序数组中查找元素的第一个和最后一个位置34;

public class 受到启发后的思路 {
    public static void main(String args[]){
        int[] nums = {5, 7, 7, 8, 8, 8, 8, 10};
        int target = 8;
        // 实例化并调用
        int[] result = new 受到启发后的思路().searchRange(nums, target);

        System.out.println("Result: [" + result[0] + ", " + result[1] + "]");
    }
    public int[] searchRange(int[] nums, int target) {
        //
        int leftIdx= findBound(nums,target,true);
        if (leftIdx == -1) {
            return new int[]{-1, -1};
        }

        int rightIdx= findBound(nums,target,false);
        return new int[]{leftIdx, rightIdx};
    }
    /**
     * 受到启发后写的。应该明白二分查找结束的本质，仔细想想二分查找什么时候会结束。
     */
    private int findBound(int[] nums, int target, boolean isLeft) {
        int left = 0, right = nums.length - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid]>target){
                right=mid-1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                // nums[mid]= target的情况
                candidate=mid;
                if(isLeft){
                    right=mid-1;
                }else{
                    left=mid+1;
                }
            }
        }
        return candidate;
    }
}
