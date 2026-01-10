package 最长连续序列128;

import java.util.HashSet;
import java.util.Set;

public class gemini帮助的 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s= new HashSet<>();
        int maxLength=0;
        for(int num:nums){
            s.add(num);
        }

//        重点：再次遍历 Set (比遍历 nums 更快，因为去重了)
        for (Integer num : s) {
            int length = 1;
            int w = num;
            //该值前面还有元素，就不必计数了。
            if(s.contains(num-1))
                continue;
            while (true) {
                w = w + 1;
                if (s.contains(w)) {
                    length++;
                } else
                    break;
            }
            if (length > maxLength) {
                maxLength = length;
            }
        }
        return maxLength;
    }
}
