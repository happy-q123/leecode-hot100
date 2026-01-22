package 括号生成22;
import java.util.ArrayList;
import java.util.List;

public class Gemni速度优化版本 {
    public static void main(String[] args) {
        Gemni速度优化版本 x = new Gemni速度优化版本();
        List<String> r = x.generateParenthesis(3);
        for (String s : r) {
            System.out.println(s);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // 使用 StringBuilder 性能稍好于 StringBuffer (非多线程环境下)
        backtrack(new StringBuilder(), result, 0, 0, n);
        return result;
    }

    /**
     * @param cur   当前构建的字符串
     * @param res   结果集
     * @param left  已经使用的左括号数量
     * @param right 已经使用的右括号数量
     * @param n     括号对数
     */
    private void backtrack(StringBuilder cur, List<String> res, int left, int right, int n) {
        // 结束条件：字符串长度达到 2*n
        if (cur.length() == n * 2) {
            res.add(cur.toString());
            return;
        }

        // 剪枝 1: 只有左括号没用完时，才能加左括号
        if (left < n) {
            cur.append('(');
            backtrack(cur, res, left + 1, right, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }

        // 剪枝 2: 只有右括号数量小于左括号时，才能加右括号（保证有效性）
        if (right < left) {
            cur.append(')');
            backtrack(cur, res, left, right + 1, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }
    }
}
