package 最长公共子序列1143;

public class Gemini思路 {

    /*
    * 感觉很难理解
    * */
    public int longestCommonSubsequence(String text1, String text2) {
        //获取两个字符串的长度
        int m = text1.length();
        int n = text2.length();

        //创建dp表
        //dp[i][j]表示text1的前i个字符和text2的前j个字符的lcs长度
        //数组大小为(m+1)×(n+1)是为了处理空字符串的基础情况
        //默认值为0
        int[][] D = new int[m + 1][n + 1];

        //遍历text1
        for (int i = 1; i <= m; i++) {
            //获取text1的字符
            char c1 = text1.charAt(i - 1);

            //遍历text2
            for (int j = 1; j <= n; j++) {
                //获取text2的字符
                char c2 = text2.charAt(j - 1);

                if (c1 == c2) {
                    //情况1：字符相同
                    //当前长度=左上角长度+1
                    D[i][j] = D[i - 1][j - 1] + 1;

                } else {
                    //情况2，字符不同
                    //当前长度=max(上方长度, 左方长度)

                    //就是看 下面两个长度：
                    //       text1的前i-1个字符与text2的前j个字符的lcs长度。（即注释中的“上方”。以text1作为每行来看）
                    //       text1的前i个字符与text2的前j-1个字符的lcs长度。（即注释中的“左方”。以text1作为每行来看）
                    D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
                }

            }
        }
        //返回右下角结果
        return D[m][n];
    }

}