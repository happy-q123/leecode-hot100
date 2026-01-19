package 零钱兑换322;

public class 我的思路 {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0)
            return -1;

        int[] D = new int[amount + 1];
        D[0] = 0;
        for (int i = 1; i < amount; i++) {
            //定义最大值，表示该金额无法凑出。
            //为什么amount+1是最大值？因为amount是要凑的金额，即使使用最小的硬币1，最多需要amount个硬币。因此amount+1就是一个最大值。
            D[i] = amount + 1;
        }

        //i表示当前金额，D[i]表示凑成i金额最低需要多少个硬币。D[i]为amount+1时表示无法凑到。
        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;
            //尝试使用硬币凑
            for (int j = 0; j < coins.length; j++) {
                //i为当前要凑的金额，j为第j个硬币的金额，进去j，看剩下需要几个凑
                int x = i - coins[j];
                if (x < 0)
                    continue;
                //+1是因为减去了一个coins[j]，即使用了一个面额为j的硬币
                int current_nums = D[x] + 1;
                if (min > current_nums) {
                    min = current_nums;
                }
            }
            D[i] = min;
        }
        if (D[amount] == amount + 1)
            return -1;
        return D[amount];
    }
}
