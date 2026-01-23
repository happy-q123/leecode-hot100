package 分割回文串131;

import java.util.ArrayList;
import java.util.List;

public class gemini给的思路 {
    List<List<String>> result=new ArrayList<>();
    List<String> path=new ArrayList<>();

    public static void main(String[] args) {

    }

    public List<List<String>> partition(String s) {
        backtracking(s,0);
        return result;
    }

    private void backtracking(String s,int startIndex){

        //终止条件
        if(startIndex >= s.length()){
            result.add(new ArrayList<>(path));
            return;
        }

        //单层搜索逻辑
        //i代表当前子串的“结束位置”
        //尝试截取区间[startIndex,i]
        for(int i=startIndex;i<s.length();i++){

            //判断s[startIndex,i]是否是回文
            if(isPalindrome(s,startIndex,i)){
                //如果是回文，就把他加入到当前路径中
                String substr=s.substring(startIndex,i+1);
                path.add(substr);
                backtracking(s,i+1);
                //还原状态
                path.remove(path.size()-1);
            }
            //如果不是回文，则你乘积。for循环继续。
        }
    }

    private boolean isPalindrome(String s,int startIndex,int endIndex){
        while(startIndex<endIndex){
            if(s.charAt(startIndex)!=s.charAt(endIndex)){
                return false;
            }
            startIndex++;
            endIndex--;
        }
        return true;
    }
}
