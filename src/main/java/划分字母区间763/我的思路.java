package 划分字母区间763;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 我的思路 {


    /*
    * 思路：
    *   1、先统计整个字符串的字符出现的次数
    *   2、遍历字符串，不停地添加字符到小列表中，然后减去次数，当某个字符次数为0时，触发检查，检查小列表里所有字符的次数是否都为0。
    *   若是，则列表成立，加入结果集。
    *   若不是，则继续遍历。
    *
    * */

      //版本3，其实不需要StringBuffer，结果需要的是长度。
        public List<Integer> partitionLabels(String s) {
            char[] ci = new char[26];
            List<Integer> result=new ArrayList<>();
            //统计次数
            for (int i = 0; i < s.length(); i++) {
                ci[s.charAt(i) - 'a']++;
            }

            int currentStrLength=0;
            Set<Character> charSet = new HashSet<>();

            int lingNum=0;
            for(int i=0;i<s.length();i++){
                currentStrLength++;
                char c=s.charAt(i);
                charSet.add(c);
                ci[c-'a']--;
                if(ci[c-'a']==0){
                    //比较为0的次数和set内字符数量即可。
                    lingNum++;
                    if(lingNum==charSet.size()){
                        result.add(currentStrLength);
                        currentStrLength=0;
                        charSet=new HashSet<>();
                        lingNum=0;
                    }
                }
            }
            return result;
        }

//    //版本2，其实只需比较为0的次数和set内字符数量即可。
//    public List<Integer> partitionLabels(String s) {
//        char[] ci = new char[26];
//        List<Integer> result=new ArrayList<>();
//        //统计次数
//        for (int i = 0; i < s.length(); i++) {
//            ci[s.charAt(i) - 'a']++;
//        }
//        StringBuffer strList=new StringBuffer();
//        Set<Character> charSet = new HashSet<>();
//
//        int lingNum=0;
//        for(int i=0;i<s.length();i++){
//            char c=s.charAt(i);
//            strList.append(c);
//            charSet.add(c);
//            ci[c-'a']--;
//            if(ci[c-'a']==0){
//                //比较为0的次数和set内字符数量即可。
//                lingNum++;
//                if(lingNum==charSet.size()){
//                    result.add(strList.length());
//                    strList=new StringBuffer();
//                    charSet=new HashSet<>();
//                    lingNum=0;
//                }
//
//            }
//        }
//        return result;
//    }

//    //版本1
//    public List<Integer> partitionLabels(String s) {
//        char[] ci = new char[26];
//        List<Integer> result=new ArrayList<>();
//        //统计次数
//        for (int i = 0; i < s.length(); i++) {
//            ci[s.charAt(i) - 'a']++;
//        }
//        StringBuffer strList=new StringBuffer();
//        Set<Character> charSet = new HashSet<>();
//        for(int i=0;i<s.length();i++){
//            char c=s.charAt(i);
//            strList.append(c);
//            charSet.add(c);
//            ci[c-'a']--;
//            boolean flag=true;
//            if(ci[c-'a']==0){
//                //检查set内字符次数是否全为0
//                for(Character x:charSet){
//                    if(ci[c-'a']!=0){
//                        flag=false;
//                        break;
//                    }
//                }
//                if(flag){
//                    result.add(strList.length());
//                    strList=new StringBuffer();
//                    charSet=new HashSet<>();
//                }
//            }
//        }
//        return result;
//    }
}
