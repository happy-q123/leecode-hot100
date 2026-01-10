package 异位字符串;

import java.util.ArrayList;
import java.util.List;

public class 我的解法1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        char op = 'a';
        char[][] x = new char[strs.length]['z' - op + 1];

        //统计每个string的字符出现次数
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            for (int j = 0; j < str.length(); j++) {
                x[i][str.charAt(j) - op] += 1;
            }
        }

        List<List<String>> results = new ArrayList<>();
        char[] completedIndex = new char[strs.length];
        //遍历词频数组
        for (int i = 0; i < strs.length; i++) {
            if (completedIndex[i] == 1)
                continue;
            int flag = 0;
            List<String> someResult = new ArrayList<>();
            //遍历后续词频数组
            for (int w = i + 1; w < strs.length; w++) {
                if (completedIndex[w] == 1)
                    continue;
                //将当前词频数组与后续数组逐个字符比较
                for (int q = 0; q < 'z' - op + 1; q++) {
                    if (x[i][q] != x[w][q]){
                        break;
                    }
                    if (q == 'z' - op) {
                        if (completedIndex[i] == 0) {
                            someResult.add(strs[i]);
                            completedIndex[i] = 1;
                        }
                        if (completedIndex[w] == 0) {
                            someResult.add(strs[w]);
                            completedIndex[w] = 1;
                        }
                        flag=1;
                    }
                }
            }
            if (flag==0){
                someResult.add(strs[i]);
            }
            results.add(someResult);
        }
        return results;
    }

}
