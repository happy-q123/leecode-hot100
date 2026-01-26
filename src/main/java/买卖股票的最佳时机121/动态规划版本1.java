package 买卖股票的最佳时机121;

public class 动态规划版本1 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int[] dp = new int[n];

        // 初始化
        dp[0] = 0; // 第0天不可能有利润（因为不能当天买当天卖算利润，或者利润为0）
        int minPrice = prices[0]; // 辅助变量：记录历史最低价

        for (int i = 1; i < n; i++) {
            // 维护历史最低价
            minPrice = Math.min(minPrice, prices[i]);

            // 状态转移：要么维持昨天的最大利润，要么今天卖出结算
            dp[i] = Math.max(dp[i-1], prices[i] - minPrice);
        }

        return dp[n-1];
    }
}
