package 打家劫舍198;

public class 我的思路优化内存版本 {
    //设n为房子长度，D[x]为长度达到x时，最大的盗取数量，c[x]表示当前的这一个房子金额
    //那么对于新的房子，其最大金额在于偷不偷当前房子。
    //那么状态转移方程为：D[x]=max(偷第n个房子+偷上上一个房子的最大金额，偷上一个房子的最大金额)
    //                 =max(c[x]+D[n-2]，D[n-1])
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        //分析中的D
        int last1=Math.max(nums[0], nums[1]);
        int last2=nums[0];
        for (int i = 2; i < nums.length; i++) {
            int current=Math.max(last2+nums[i],last1);
            last2=last1;
            last1=current;
        }
        return last1;
    }
}
