package 最长回文字串5;

public class Gemini中心扩散法 {
    public String longestPalindrome(String s) {
        // 边界条件处理：如果字符串为空或者长度小于1，直接返回空串
        if (s == null || s.length() < 1) {
            return "";
        }

        int start=0;
        int end=0;

        for(int i=0;i<s.length();i++){
            // 情况 A：回文串长度为奇数（如 "aba"），中心是一个字符 s[i]
            // 以 i 为左边界，i 为右边界开始扩散
            int len1=expandAroundCenter(s,i,i);

            // 情况B：回文串长度为偶数（如 "abba"），中心是两个字符 s[i] 和 s[i+1]
            // 以 i 和 i+1 为中心开始扩散
            int len2=expandAroundCenter(s,i,i+1);

            //比较两个长度，取最大
            int len=Math.max(len1,len2);

            //根据长度拿到起止点下标
            if (len>end-start){
                // 核心难点：如何根据中心点 i 和长度 len 推算出 start 和 end？
                // 例子1（奇数）：s="aba", i=1, len=3. start = 1 - (3-1)/2 = 0. 正确。
                // 例子2（偶数）：s="abba", i=1, len=4. start = 1 - (4-1)/2 = 0. 正确。
                // 这里的除法是整数除法，(len-1)/2 能巧妙地同时兼容奇偶长度
                start=i-(len-1)/2;
                end=i+len/2;
            }
        }
        return s.substring(start,end+1);
    }

    /**
     * 辅助函数：中心扩散
     * @param s     原始字符串
     * @param left  扩散的左起始点
     * @param right 扩散的右起始点
     * @return      扩散后得到的回文串长度
     */
    private int expandAroundCenter(String s, int left, int right) {
        //如果位置合法，且左右两字符相等，则扩散
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        //循环结束时，left和right指向的字符通常已经是不相等的了（或者越界了）
        // 比如回文是 "aba"，循环结束时 left 指向 -1，right 指向 3。
        // 实际回文长度计算公式：(right - 1) - (left + 1) + 1
        // 化简后：right - left - 1
        return right-left-1;
    }

}
