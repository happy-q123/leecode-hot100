package 最长公共子序列1143;

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        // 获取两个字符串的长度
        int m = text1.length();
        int n = text2.length();
        
        // 创建 dp 表
        // dp[i][j] 表示 text1 的前 i 个字符和 text2 的前 j 个字符的 LCS 长度
        // 数组大小设为 (m+1) * (n+1) 是为了处理空字符串的基础情况 (padding)
        // Java 中 int 数组默认初始化为 0，所以不需要手动填充 0
        int[][] dp = new int[m + 1][n + 1];
        
        // 遍历 text1
        for (int i = 1; i <= m; i++) {
            // 获取 text1 的第 i 个字符 (注意：字符串索引从 0 开始，所以是 i-1)
            char c1 = text1.charAt(i - 1);
            
            // 遍历 text2
            for (int j = 1; j <= n; j++) {
                // 获取 text2 的第 j 个字符
                char c2 = text2.charAt(j - 1);
                
                    if (c1 == c2) {
                        // 情况 1：字符相同
                        // 当前长度 = 左上角长度 + 1
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        // 情况 2：字符不同
                        // 当前长度 = max(上方长度, 左方长度)

                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
            }
        }
        
        // 返回右下角的结果
        return dp[m][n];
    }
}