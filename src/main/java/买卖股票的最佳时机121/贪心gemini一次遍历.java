package 买卖股票的最佳时机121;

public class 贪心gemini一次遍历 {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 记录历史最低价
        int maxProfit = 0; // 记录最大利润

        for (int price : prices) {
            // 1. 如果当前价格比历史最低价还低，更新最低价（也就是买入）
            if (price < minPrice) {
                minPrice = price;
            }
            // 2. 如果当前价格卖出能赚更多，更新最大利润
            else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}

