package 数组中的第k个最大元素215;

import java.util.PriorityQueue;

public class 我的思路 {

    //直接使用堆，注意(x,y)->y-x
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((x,y)->y-x);
        for(int i=0;i<nums.length;i++)
            pq.add(nums[i]);
        Integer k_max=null;
        for(int i=0;i<k;i++)
            k_max=pq.poll();
        return k_max;
    }
}
