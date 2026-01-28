package 字符串解码394;
import java.util.LinkedList;
import java.util.Deque;
public class 双栈解法gemini {
    public static void main(String[] args) {
        SolutionDoubleStack sol = new SolutionDoubleStack();
        String q = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(sol.decodeString(q));
    }
    static class SolutionDoubleStack {
        public String decodeString(String s) {
            // 1. 定义两个辅助栈
            Deque<Integer> countStack = new LinkedList<>();
            Deque<StringBuilder> resStack = new LinkedList<>();

            // 2. 实时记录当前的状态
            StringBuilder currentRes = new StringBuilder();
            int k = 0;

            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    // 处理数字 (可能是多位数，如 10[a])
                    k = k * 10 + (c - '0');
                }
                else if (c == '[') {
                    // 遇到 '['：存档！
                    // 把当前的倍数存起来
                    countStack.push(k);
                    // 把目前为止算好的字符串存起来
                    resStack.push(currentRes);

                    // 重置状态，准备进入括号内部
                    currentRes = new StringBuilder();
                    k = 0;
                }
                else if (c == ']') {
                    // 遇到 ']'：读档！
                    // 1. 拿出当前的重复倍数
                    int curK = countStack.pop();
                    // 2. 拿出进括号前的"前缀"字符串
                    StringBuilder lastRes = resStack.pop();

                    // 3. 拼接： 新结果 = 前缀 + (倍数 * 当前括号内的内容)
                    // 注意：这里不用循环追加，StringBuilder 有更高效的方式，但为了逻辑清晰用循环演示
                    for (int i = 0; i < curK; i++) {
                        lastRes.append(currentRes);
                    }

                    // 4. 更新 currentRes (此时它已经包含了括号内解码后的内容)
                    currentRes = lastRes;
                }
                else {
                    // 普通字符，直接追加到当前处理的字符串中
                    currentRes.append(c);
                }
            }

            return currentRes.toString();
        }
    }
}
