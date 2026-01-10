package 异位字符串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gemini给的计数解法 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Key: 唯一的特征字符串 (比如 "1#0#2#...#0")
        // Value: 对应的单词列表
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // 1. 统计词频 (保留你的逻辑)
            int[] counts = new int[26];
            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }

            // 2. 将数组转换成唯一的 Key
            // 例如 "aab" -> counts数组 -> "2#1#0#0...#0"
            // 这样所有异位词生成的 Key 都是一样的
            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append(count).append('#'); // 加个分隔符防止数字混淆
            }
            String key = sb.toString();

            // 3. 放入 Map (如果桶不存在就新建，存在就加进去)
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        // 4. 返回所有桶里的内容
        return new ArrayList<>(map.values());
    }
}
