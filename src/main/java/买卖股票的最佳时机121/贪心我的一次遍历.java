package 买卖股票的最佳时机121;

public class 贪心我的一次遍历 {
    public int maxProfit(int[] prices) {
        Integer max=null;
        Integer history_min=null;
        for(int i=0;i<prices.length;i++){
            int price=prices[i];
            if(history_min==null||history_min>price){
                history_min=price;
            }

            int profit=prices[i]-history_min;
            if(max==null||profit>max){
                max=profit;
            }
        }
        return max;
    }
}
