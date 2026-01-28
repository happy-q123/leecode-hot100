package 字符串解码394;

import java.util.Deque;
import java.util.LinkedList;


public class 我的思路 {

    public static void main(String[] args) {
        我的思路 x=new 我的思路();
//        String q="3[a]2[bc]";
        String q="3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(x.decodeString(q));
    }
    //失败，无法通过"3[z]2[2[y]pq4[2[jk]e1[f]]]ef"的测试，因为globalTem加tem的时机不对。
    // 更好的时机可能是，当栈内左括号数量等于右括号数量时，再出栈。

    //下面是失败的思路
    /*
    * 思路：
    *
    * 先从左到右遍历：
    *    如果遇到['a','z']，则判断stack是否为空，为空直接加入result，不为空说明当前处于括号内，加入stack
    *    如果遇到['0','9']或者'['，则直接入栈即可。
    *    如果遇到']',则出栈，
    *           若出栈遇到['a','z']，则组成临时temp，每个字符往头添加。
    *           若出栈遇到['0','9']，则出栈，组成临时int_temp
    *           若出栈遇到'['，则用int_temp乘以temp，组成新的temp。
    *               当'['出栈后，需要将数字找完，当下一个不再是数字或者栈空时此次出栈结束。
    *                   若栈空，则result+temp，并让temp为空。
    *                   若栈不空，继续遍历字符串。
    *
    * */
    private Deque<Character> stack=new LinkedList<>();
    public String decodeString(String s) {
        StringBuffer result = new StringBuffer();
        StringBuffer globalTemp=new StringBuffer();
        StringBuffer intTemp=new StringBuffer();
        int len=s.length();
        for(int i=0;i<len;i++){

            //如果是小写英文字母
            if(s.charAt(i)>='a'&&s.charAt(i)<='z'){
                //如果栈为空，说明当前字母不在“[]”中。
                if(stack.isEmpty()){
                    result.append(s.charAt(i));
                }else{
                    stack.addLast(s.charAt(i));
                }
                continue;
            }

            if(s.charAt(i)<='9'&&s.charAt(i)>='0'){
                stack.addLast(s.charAt(i));
                continue;
            }

            //如果是左括号，直接入栈。
            if(s.charAt(i)=='['){
                stack.addLast(s.charAt(i));
                continue;
            }

            //如果是右括号，则可以出栈到下一个左括号
            if(s.charAt(i)==']'){
                StringBuffer temp=new StringBuffer();
                //先找到第一个左括号
                while(!stack.isEmpty()&&stack.getLast()!='['){
                    char top=stack.pollLast();
                    //这里一定是top>='a'&&top<='z'
                    temp.insert(0,top);
                }
                //将左括号出栈。其实这里不用判断就行，直接出栈即可。
                if(stack.getLast()=='['){
                    stack.pollLast();
                }
                //将寻找乘数数字
                while(!stack.isEmpty()&&(stack.getLast()<='9'&& stack.getLast()>='0')){
                    char top=stack.pollLast();
                    //这里一定是数字
                    intTemp.insert(0,top);
                }

//                进行字符串乘法
                int nums=Integer.parseInt(intTemp.toString());

                if(globalTemp.isEmpty()){
                    globalTemp=temp;
                }else {
                    globalTemp.append(temp);
                }

                //进行字符串乘法
                globalTemp.repeat(globalTemp, Math.max(0,nums-1));

                //重置intTemp
                intTemp=new StringBuffer();

                //栈空就添加
                if (stack.isEmpty()){
                    result.append(globalTemp);
                    //重置temp
                    globalTemp=new StringBuffer();
                }
            }

        }
        return result.toString();
    }
}
