package 括号生成22;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {

    public static void main(String[] args) {
        我的思路 x = new 我的思路();
        List<String>r=x.generateParenthesis(3);
        for (String s: r) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String dic = "()";
        compute(new StringBuffer(), new StringBuffer(), result, n, dic);
        return result;
    }

    //当前字符串、当前字符串括号是否匹配、结果、输入n、要遍历的字符
    public void compute(StringBuffer currentString, StringBuffer stack, List<String> result, int n, String dic) {
        //结束条件
        //满足字符个数
        if (currentString.length() == (n * 2)) {
            //括号是否有效
            if (stack.isEmpty()) {
                result.add(currentString.toString());
            }
            return;
        }
        char[] x = dic.toCharArray();
        for (char c : x) {
            if (c == '(') {
                //左括号直接入队
                stack.append(c);
                currentString.append(c);

                compute(currentString, stack, result, n, dic);

                stack.deleteCharAt(stack.length() - 1);
                currentString.deleteCharAt(currentString.length() - 1);

            } else {

                //c为右括号时，stack要么为空要么
                if (!stack.isEmpty() && (stack.charAt(stack.length() - 1) == '(')) {
                    stack.deleteCharAt(stack.length() - 1);
                    currentString.append(c);

                    compute(currentString, stack, result, n, dic);
                    currentString.deleteCharAt(currentString.length() - 1);
                    //之前删除的要再加上
                    stack.append('(');
                }
            }
        }
    }
}
