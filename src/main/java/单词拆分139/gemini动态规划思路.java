package 单词拆分139;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class gemini动态规划思路 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        //D[i]表示s中[0,i-1]共i个字符是否可以匹配
        //boolean数组默认值就是false
        boolean[] D = new boolean[s.length() + 1];

        //D[0]无意义，为true即可
        D[0]=true;
        for(int i=1;i<=s.length();i++){
            //寻找分割点
            for(int j=0;j<i;j++){
                if(D[j] &&wordSet.contains(s.substring(j,i))){
                    D[i]=true;
                    //只要找到一个分割点就ok了
                    break;
                }
            }
        }

        return D[s.length()];
    }
}
