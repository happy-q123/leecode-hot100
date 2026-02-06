package 最长回文字串5;

class Solution {
    public String longestPalindrome(String s) {
        // 1. 边界条件处理：如果字符串为空或长度小于1，直接返回空串
        if (s == null || s.length() < 1) {
            return "";
        }

        // 用于记录最长回文子串的起始和结束位置（这是最终截取字符串用的）
        // 初始化为0，即默认最长是第一个字符
        int start = 0;
        int end = 0;

        // 2. 遍历字符串，以每个字符为中心进行扩散
        for (int i = 0; i < s.length(); i++) {
            // 情况 A：回文串长度为奇数（如 "aba"），中心是一个字符 s[i]
            // 以 i 为左边界，i 为右边界开始扩散
            int len1 = expandAroundCenter(s, i, i);

            // 情况 B：回文串长度为偶数（如 "abba"），中心是两个字符 s[i] 和 s[i+1]
            // 以 i 为左边界，i+1 为右边界开始扩散
            int len2 = expandAroundCenter(s, i, i + 1);

            // 取两种情况中较长的那个长度
            int len = Math.max(len1, len2);

            // 3. 如果计算出的长度 len 大于之前记录的长度（即 end - start），则更新记录
            // 注意：这里 end - start 其实是上一轮记录的长度（实际长度是 end - start + 1，
            // 但因为 Java substring 是左闭右开，这里的 end 仅仅作为一个坐标标记，逻辑上比较长度差即可）
            if (len > end - start) {
                // 核心难点：如何根据中心点 i 和长度 len 推算出 start 和 end？
                // 例子1（奇数）：s="aba", i=1, len=3. start = 1 - (3-1)/2 = 0. 正确。
                // 例子2（偶数）：s="abba", i=1, len=4. start = 1 - (4-1)/2 = 0. 正确。
                // 这里的除法是整数除法，(len-1)/2 能巧妙地同时兼容奇偶长度
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        // 4. 返回截取的子串
        // substring 方法是“左闭右开”区间 [start, end)，所以结束位置要 +1
        return s.substring(start, end + 1);
    }

    /**
     * 辅助函数：中心扩散
     * @param s     原始字符串
     * @param left  扩散的左起始点
     * @param right 扩散的右起始点
     * @return      扩散后得到的回文串长度
     */
    private int expandAroundCenter(String s, int left, int right) {
        // 当左下标未越界，右下标未越界，且两端字符相等时，继续向外扩散
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;  // 向左移动
            right++; // 向右移动
        }
        
        // 循环结束时，left 和 right 指向的字符通常已经是不相等的了（或者越界了）。
        // 比如回文是 "aba"，循环结束时 left 指向 -1，right 指向 3。
        // 实际回文长度计算公式：(right - 1) - (left + 1) + 1
        // 化简后：right - left - 1
        return right - left - 1;
    }
}