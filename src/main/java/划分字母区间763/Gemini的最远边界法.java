package 划分字母区间763;

import java.util.ArrayList;
import java.util.List;

public class Gemini的最远边界法 {

    /*
    *
    * 核心思路： 只需要关注每个字符在字符串中最后一次出现的位置。
                预处理：遍历一遍字符串，记录每个字符最后出现的下标位置（例如 a 最后出现在下标 8）。
                遍历：再次遍历字符串，维护一个变量 end，表示当前片段至少要延伸到的最远位置。
                每遇到一个字符，就更新 end：end = Math.max(end, 当前字符的最后位置)。
                如果当前下标 i == end，说明当前片段已经包含了所有必要的字符，可以切分了。
      思路真妙
    * */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        // 1. 记录每个字符最后出现的下标
        int[] lastEdge = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastEdge[s.charAt(i) - 'a'] = i;
        }

        // 2. 遍历字符，动态更新当前片段的边界
        int start = 0; // 当前片段起始位置
        int end = 0;   // 当前片段的最远边界

        for (int i = 0; i < s.length(); i++) {
            // 更新边界：必须包含当前字符的最后出现位置
            end = Math.max(end, lastEdge[s.charAt(i) - 'a']);

            // 如果当前遍历到了边界，说明这个片段可以结束了
            if (i == end) {
                list.add(end - start + 1);
                start = end + 1; // 更新下一个片段的起始位置
            }
        }
        return list;
    }
}
