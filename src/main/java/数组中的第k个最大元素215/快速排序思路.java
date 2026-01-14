package 数组中的第k个最大元素215;

import java.util.PriorityQueue;

public class 快速排序思路 {
    //直接使用堆，注意(x,y)->y-x
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1);
        return nums[k-1];
    }

    public void quickSort(int[] nums, int start, int end) {
        //从大到小
        if(start>=end)
            return;
        int base_value=nums[start];
        int current_index=start;
        int t_st=start;
        int t_end=end;
        boolean left=false;
        // 注意： 这里必须有等号
        while(t_st<=t_end){
            //右边
            if(!left){
                if(nums[t_end]>=base_value){
                    nums[current_index]=nums[t_end];
                    left=true;
                    current_index=t_end;
                    t_end--;
                }else{
                    t_end--;
                }
            }else{
                //左边
                if(nums[t_st]<=base_value){
                    nums[current_index]=nums[t_st];
                    left=false;
                    current_index=t_st;
                    t_st++;
                }else{
                    t_st++;
                }
            }
        }
        nums[current_index]=base_value;
        quickSort(nums,start, current_index-1);
        quickSort(nums,current_index+1, end);
        
    }
}
