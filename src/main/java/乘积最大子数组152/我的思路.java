package 乘积最大子数组152;

public class 我的思路 {
    public int maxProduct(int[] nums) {
//        D_max[i]=Max(nums[i],D_max[i-1]×nums[i],D_min[i-1]×nums[i])

        //D_max[x]表示以 nums[x]为结尾的连续子数组的乘积最大值
        int[] D_max=new int[nums.length+1];
        //D_min[x]表示以 nums[x]为结尾的连续子数组的乘积最小值
        int[] D_min=new int[nums.length+1];
        D_max[0]=nums[0];
        D_min[0]=nums[0];
        int MAX=nums[0];
        for(int i=1;i<nums.length;i++){
            D_max[i]=nums[i];
            D_min[i]=nums[i];
            int max1=0;
            int min1=0;
            //// 计算三个候选值中的最大值
            //D_max[i] = Math.max(nums[i], Math.max(D_max[i-1] * nums[i], D_min[i-1] * nums[i]));
            //
            //// 计算三个候选值中的最小值
            //D_min[i] = Math.min(nums[i], Math.min(D_max[i-1] * nums[i], D_min[i-1] * nums[i]));
            if(D_max[i-1]*nums[i]>D_min[i-1]*nums[i]){
                max1=D_max[i-1]*nums[i];
                min1=D_min[i-1]*nums[i];
            }else{
                max1=D_min[i-1]*nums[i];
                min1=D_max[i-1]*nums[i];
            }
            if(D_max[i]<max1){
                D_max[i]=max1;
            }

            if(min1<D_min[i]){
                D_min[i]=min1;
            }

            if(MAX<D_max[i]){
                MAX=D_max[i];
            }
        }
        return MAX;
    }
}
