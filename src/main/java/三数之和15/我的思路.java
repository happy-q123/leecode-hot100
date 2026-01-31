package 三数之和15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 我的思路 {

    //排序 + 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        //排序
        Arrays.sort(nums);

        int length = nums.length;

        for(int i=0;i<length;i++){
            //如果当前数大于0，则三数之和一定大于0。()
            if(nums[i]>0)
                break;
            //去重
            if(i>0&&nums[i]==nums[i-1])
                continue;

            int l=i+1;
            int r=length-1;

            while(l<r){
                int sum=nums[i]+nums[l]+nums[r];
                if(sum==0){
                    result.add(Arrays.asList(nums[i],nums[l],nums[r]));

                    //跳过与nums[l]重复的。
                    // 出循环后nums[l]为与原nums[l]重复的最后一个位置
                    while(l<r&&nums[l]==nums[l+1])
                        l++;
                    //跳过与nums[r]重复的。
                    //出循环后nums[r]为与原nums[r]重复的最后一个位置
                    while(l<r&&nums[r]==nums[r-1])
                        r--;

                    //收缩寻找新的值
                    //一定注意，这里是同时收缩。因为三个数中，i已经定了，且j和l都去重了，索引j和l应该同时收缩。
                    // 如果仅仅收缩l，（此时r已经去重），无论怎么r--，再也找不到相加为0的r。仅仅收缩r也是同理。
                    l++;
                    r--;
                }else if(sum<0){
                    l++;
                }else{
                    r--;
                }
            }
        }
        return result;
    }
}