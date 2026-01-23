package 分割回文串131;
import java.util.ArrayList;
import java.util.List;

class 动态规划版本 {
    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();
    boolean[][] dp; // dp[i][j] 表示 s[i...j] 是否是回文串

    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];

        // 1. 动态规划预处理 (DP Pre-computation)
        // 也就是填表过程。
        // 注意遍历顺序：我们需要用到 dp[i+1][j-1] (左下角的结果) 来推导 dp[i][j]
        // 所以 i 必须从大到小遍历 (从最后一行往上推)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                // 只有当两端字符相同时，才有可能是回文
                if (s.charAt(i) == s.charAt(j)) {
                    // 情况1: 下标差 <= 1 (例如 "a" 或 "aa")，直接是回文。（因为前提是s.charAt(i) == s.charAt(j)）
                    // 情况2: 长度 > 2，则取决于中间部分 dp[i+1][j-1] 是否是回文
                    if (j - i <= 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        // 2. 开始回溯
        backtracking(s, 0);
        return result;
    }

    private void backtracking(String s, int startIndex) {
        // 终止条件：切割线到达字符串末尾
        if (startIndex >= s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 单层搜索逻辑
        for (int i = startIndex; i < s.length(); i++) {
            // 直接查表，O(1) 时间复杂度判断
            if (dp[startIndex][i]) {

                // 记录切割下来的回文子串
                path.add(s.substring(startIndex, i + 1));

                // 递归切割剩余部分
                backtracking(s, i + 1);

                // 回溯
                path.remove(path.size() - 1);
            }
        }
    }
}
