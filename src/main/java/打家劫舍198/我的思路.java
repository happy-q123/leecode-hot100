package 打家劫舍198;

public class 我的思路 {
    //设n为房子长度，D[x]为长度达到x时，最大的盗取数量，c[x]表示当前的这一个房子金额
    //那么对于新的房子，其最大金额在于偷不偷当前房子。
    //那么状态转移方程为：D[x]=max(偷第n个房子+偷上上一个房子的最大金额，偷上一个房子的最大金额)
    //                 =max(c[x]+D[n-2]，D[n-1])
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        //分析中的D
        int[] max_money=new int[nums.length];
        max_money[0]=nums[0];
        max_money[1]=Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            max_money[i]=Math.max(max_money[i-2]+nums[i],max_money[i-1]);
        }
        return max_money[nums.length-1];
    }
}
