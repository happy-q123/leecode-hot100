package 字符串解码394;

import java.util.*;

public class gemini实现我的理想思路 {
    public String decodeString(String s) {
        // 你的原始结构：单栈
        Deque<Character> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 1. 如果不是右括号，统统入栈
            if (c != ']') {
                stack.addLast(c);
            }
            // 2. 遇到右括号 ']'，说明我们要消灭一对最近的 "k[...]"
            else {
                // --- A. 弹出 [...] 中的字母 ---
                // 我们用一个临时列表来收集弹出的字符
                List<Character> content = new ArrayList<>();
                while (!stack.isEmpty() && stack.getLast() != '[') {
                    content.add(stack.removeLast());
                }
                // 因为是栈，弹出来是反的 (例如 c, b, a)，需要反转回 (a, b, c)
                Collections.reverse(content);

                // --- B. 弹出左括号 '[' ---
                stack.removeLast(); // 弹出 '['

                // --- C. 弹出数字 k ---
                // 注意：数字可能是多位的，比如 "100"，所以要循环弹
                StringBuilder countStr = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.getLast())) {
                    countStr.insert(0, stack.removeLast()); // 插在头部
                }
                int k = Integer.parseInt(countStr.toString());

                // --- D. 执行解码逻辑 ---
                // 把 content 重复 k 次
                // 此时我们不生成 String，而是生成 Character 列表，方便回填

                // --- E. 【核心技巧】回填 ---
                // 我们把解码后的字符，一个一个重新塞回栈里！
                // 这样，栈里就只有 "处理好的字符"，外层括号根本不知道刚才这里发生过解码。
                for (int j = 0; j < k; j++) {
                    for (char charCode : content) {
                        stack.addLast(charCode);
                    }
                }
            }
        }

        // 3. 栈里剩下的就是最终结果
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }
}
