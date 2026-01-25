package 买卖股票的最佳时机121;

public class 贪心我的思路 {
    public int maxProfit(int[] prices) {
        Integer max=null;
        for(int i=0;i<prices.length;i++){
            for(int e=i;e<prices.length;e++){
                int cha=prices[e]-prices[i];
                if(max==null||cha>max){
                    max=cha;
                }
            }
        }
        return max;
    }
}
