package 电话号码的字母组合17;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {

    //Java 中 String 的不可变性（Immutability）导致的频繁内存分配和拷贝。
    //为了解决这个问题，应该使用 StringBuilder。它是可变的，我们只需要在同一个对象上进行 append（追加）和 delete（回溯/删除），
    // 从而避免反复创建新字符串。（可看“优化执行速度后的.java”）

    public static void main(String[] args) {
        String a="23";
        我的思路 x=new 我的思路();
        List<String> l=x.letterCombinations(a);
        System.out.println(l.size());
        for(String s:l){
            System.out.println(s);
        }
    }
    public List<String> letterCombinations(String digits) {
        //0,1,2,3,4,5,6,7,8,9。只有0和1无意义
        String[] str=new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> result=new ArrayList<>();
        int index=0;
        compute("",index,str,result,digits);

        return result;
    }

    //从上层拿到的字符串current、当前层在digits中对应的下标index、数字对应的字符组合字典str、最终结果result、原始digits
    public void compute(String current,int index,String[] str,List<String> result,String digits){
        if(current.length()==digits.length()){
            result.add(current);
            return;
        }
        //当前层数字对应的字母组合
        String currentString=str[digits.charAt(index)-'0'];

        for(int i=0;i<currentString.length();i++){
            String new_current=current+currentString.charAt(i);
            compute(new_current,index+1,str,result,digits);
        }
    }
}
