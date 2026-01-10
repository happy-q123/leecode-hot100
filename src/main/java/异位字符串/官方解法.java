package 异位字符串;

import java.util.*;

public class 官方解法 {
    /**
     * description 原理：对于异位字符，排序后的字符是完全一样的，以此作为key比较好。
     * 另一种统计词频作为map的key的思路是利用了异位字符的词频是一样的原理。
     * author zzq
     * todo 唉，感觉自己对java常用类的方法掌握不够好，特别是最后一行和getOrDefault
     * date 2026/1/7 21:38
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>>map=new HashMap<>();

        for (String s : strs) {
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String str = String.valueOf(array);
            List<String> list = map.getOrDefault(str, new ArrayList<>());
            list.add(s);
            map.put(str, list);
        }
        return new ArrayList<>(map.values());
    }
}
