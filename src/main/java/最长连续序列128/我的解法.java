package 最长连续序列128;

import java.util.HashSet;
import java.util.Set;

/**
 * description //我的解法存在问题：1、重复的元素会重复循环，且很多循环在做无用功。2、只有是起始元素时，再进行计数比较好。
 * 对于问题1，先利用set去重比较好。对于问题2，s.contains(w-1)为true，则说明该元素不是起始的，直接pass掉即可。
 * author zzq
 * date 2026/1/7 22:35
 */
public class 我的解法 {
    public int longestConsecutive(int[] nums){
        Set<Integer> s= new HashSet<>();
        int maxLength=0;
        for (int num : nums) {
            int length = 1;
            s.add(num);
            int w = num;
            while (true) {
                w = w - 1;
                if (s.contains(w)) {
                    length++;
                } else
                    break;
            }
            w = num;
            while (true) {
                w = w + 1;
                if (s.contains(w)) {
                    length++;
                } else {
                    break;
                }
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }
}
