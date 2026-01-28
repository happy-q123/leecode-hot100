package 字符串解码394;

import java.util.Deque;
import java.util.LinkedList;

public class 我优化我的原思路 {
    public static void main(String[] args) {
        我优化我的原思路 x=new 我优化我的原思路();
//        String q="3[a]2[bc]";
        String q="3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(x.decodeString(q));
    }
    // 相较于原思路，将得到的globalTemp直接写回stack，而不是将试图存储globalTemp
    private Deque<Character> stack=new LinkedList<>();
    public String decodeString(String s) {
        StringBuffer result = new StringBuffer();
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

                //进行字符串乘法
                temp.repeat(temp, Math.max(0,nums-1));

                //重置intTemp
                intTemp=new StringBuffer();

                //栈空就添加
                if (stack.isEmpty()){
                    result.append(temp);
                }else {
                    //不为空，将temp入栈
                    for (char c : temp.toString().toCharArray()) {
                        stack.addLast(c);
                    }
                }
            }

        }
        return result.toString();
    }
}
