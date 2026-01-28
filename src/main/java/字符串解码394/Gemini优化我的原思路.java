package 字符串解码394;

import java.util.Deque;
import java.util.LinkedList;

public class Gemini优化我的原思路 {

    public static void main(String[] args) {
        Gemini优化我的原思路 x = new Gemini优化我的原思路();
        String q = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(x.decodeString(q));
    }

    //得到结果后不需要globalTmp，而是将其写回stack
    public String decodeString(String s) {
        // 使用单栈，只存字符
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 1. 只要不是右括号，统统入栈
            if (c != ']') {
                stack.addLast(c);
            } 
            // 2. 遇到右括号，开始"局部结算"
            else {
                // A. 提取括号内的字符串 (例如 "jk")
                LinkedList<Character> decodedString = new LinkedList<>();
                while (!stack.isEmpty() && stack.getLast() != '[') {
                    decodedString.addFirst(stack.pollLast());
                }

                // B. 弹出左括号 '['
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }

                // C. 提取数字 (例如 "2")
                LinkedList<Character> countList = new LinkedList<>();
                while (!stack.isEmpty() && Character.isDigit(stack.getLast())) {
                    countList.addFirst(stack.pollLast());
                }
                
                // 将数字字符转为 int
                StringBuilder countStr = new StringBuilder();
                for (char numChar : countList) {
                    countStr.append(numChar);
                }
                int k = Integer.parseInt(countStr.toString());

                // D. 构造重复后的字符串 (例如 "jkjk")
                // 注意：这里不需要 globalTemp，我们直接要把结果塞回栈里
                StringBuilder tempRes = new StringBuilder();
                for (char ch : decodedString) {
                    tempRes.append(ch);
                }
                String repeatStr = tempRes.toString().repeat(k);

                // E. 【核心】将结果拆解为字符，重新入栈！
                // 这样外层括号处理时，看到的只是普通字符，完全感觉不到内层括号的存在
                for (int j = 0; j < repeatStr.length(); j++) {
                    stack.addLast(repeatStr.charAt(j));
                }
            }
        }

        // 3. 最后栈里剩下的就是解码后的全部字符
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }
}