package 最长递增子序列300;

public class 我的思路 {
    public int lengthOfLIS(int[] nums) {
        //D[x]必须以 nums[i] 这个数值结尾的最长递增子序列的长度。
        int[] D=new int[nums.length];
        D[0]=1;
        int max=1;
        for(int i=1;i<nums.length;i++){
            //初始化为1
            D[i]=1;
            for(int j=0;j<=i;j++){
                if(nums[j]<nums[i]){
                    D[i]=Math.max(D[i],D[j]+1);
                }
            }
            if(max<D[i]){
                max=D[i];
            }
        }

        return max;
    }

}
