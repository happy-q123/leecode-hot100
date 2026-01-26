package 前k个高频元素347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class 我的思路 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(
                (entry1,entry2)
                -> entry2.getValue() - entry1.getValue());

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            pq.offer(entry);
        }

        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            Map.Entry<Integer,Integer> entry=pq.poll();
            res[i] = entry.getKey();
        }
        return res;
    }
}
