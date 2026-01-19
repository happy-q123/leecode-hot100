package 单词拆分139;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 我的思路 {
    //线性扫描，失败带代码。遇到这样的输入就完蛋了：
    /*
        s ="aaaaaaa"，wordDict =["aaaa","aaa"]
        正确的匹配是，先匹配wordDict[0],在匹配wordDict[1]
        而当前代码是先匹配两次wordDict[1],剩余一个'a'却无法匹配。
    * */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict==null|| wordDict.isEmpty())
            return false;
        Map<String, Boolean> map = new HashMap<>();
        Integer max=null;
        Integer min=null;

        for (String word : wordDict) {
            map.put(word, true);
            //初始化
            if(max==null){
                max=word.length();
                min=max;
            }else{
                if(max<word.length()){
                    max=word.length();
                }
                if(min>word.length()){
                    min=word.length();
                }
            }
        }
        int current=0;
        boolean need_judge=false;
        for(int i = 0; i < s.length(); i++) {
            current++;
            if(current>=min&&current<=max) {
                String word = s.substring(i-current+1, current);
                if(map.containsKey(word)) {
                    current=0;
                    need_judge=false;
                }else need_judge=true;
            }else {
                if(need_judge){
                    return false;
                }
            }
        }
        //前面都匹配了，但是后面字符长度小于min
        if(current<min)
            return false;
        return true;
    }
}
