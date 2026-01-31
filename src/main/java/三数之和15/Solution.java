package 三数之和15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. 特判：如果数组为空或长度小于3，直接返回
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        // 2. 排序：这是使用双指针的前提
        Arrays.sort(nums);
        
        int n = nums.length;
        
        // 3. 遍历第一个数 nums[i]
        for (int i = 0; i < n; i++) {
            // 优化：如果当前数字大于0，则三数之和一定无法等于0（因为数组已排序，后面的数都比它大），直接结束
            if (nums[i] > 0) {
                break;
            }
            
            // 去重逻辑 A：跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int L = i + 1;
            int R = n - 1;
            
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    
                    // 去重逻辑 B：跳过重复的 nums[L]
                    while (L < R && nums[L] == nums[L + 1]) L++;
                    // 去重逻辑 C：跳过重复的 nums[R]
                    while (L < R && nums[R] == nums[R - 1]) R--;
                    
                    // 找到答案后，双指针同时收缩
                    L++;
                    R--;
                } else if (sum < 0) {
                    // 和小于0，需要更大的数，L右移
                    L++;
                } else { // sum > 0
                    // 和大于0，需要更小的数，R左移
                    R--;
                }
            }
        }
        
        return result;
    }
}