package 缺失的第一个正数;

import java.util.Arrays;

public class 我的思路 {
    public static void main(String[] args){
        int []nums=new int[]{0,1,2,2,4,5};


        System.out.println(firstMissingPositive(nums));
    }
    public static int firstMissingPositive(int[] nums) {
        int[] sortedResult=Arrays.stream(nums).sorted().toArray();
        int index=-1;

        //寻找第一个正数的下标
        for(int i=0;i<sortedResult.length;i++){
            if(sortedResult[i]>0){
                index=i;
                break;
            }
        }

        //如果没有正数，或者第一个正数大于1
        if(index==-1||sortedResult[index]>1){
            return 1;
        }

        //在正数范围两两比较，是否等差为1的递增
        for(int i=index+1;i<nums.length;i++){
            if(sortedResult[i-1]!=sortedResult[i]&&sortedResult[i-1]+1!=sortedResult[i])
                return sortedResult[i-1]+1;
        }

        //如果能出来，说明数组中正数是严格等差为1递增的
        return sortedResult[nums.length-1]+1;
    }
}
