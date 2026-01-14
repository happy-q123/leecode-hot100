package 寻找重复数287;

public class 我的思路 {

    //我的方法超时了。
    //时间复杂度为O(n^2)
    public int findDuplicate(int[] nums) {
        int x=nums[0];
        for(int i=0;i<nums.length-1;i++){
            x=nums[i];
            for(int q=i+1;q<nums.length;q++){
                // System.out.println(q);
                if((x^nums[q])==0)
                    return x;
            }
        }
        return x;
    }

}
