package 分割等和子集416;

public class gemini修正后 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;

        // 奇数直接排除
        if (sum % 2 != 0) return false;

        // 只需要记录差值。最大可能的差值就是 sum。
        // hasDiff[i] 为 true 表示：可以凑出差值 i
        boolean[] hasDiff = new boolean[sum + 1];
        hasDiff[0] = true;

        // 只需要追踪目前的“最大可能差值”，减少遍历范围
        int maxDiff = 0;

        for (int num : nums) {
            //基本类型boolean的数组默认值为false
            boolean[] nextDiff = new boolean[sum + 1];
            // 我们只需要遍历到当前可能的最大差值即可
            for (int d = 0; d <= maxDiff; d++) {
                //d对所有可能构成的差值进行遍历，hasDiff[d]为true表示该差值可以被前面几个数字所组成的集合产生。
                if (hasDiff[d]) {
                    // 逻辑同上：
                    // 1. 差值变大：d + num
                    nextDiff[d + num] = true;
                    // 2. 差值变小：abs(d - num)
                    nextDiff[Math.abs(d - num)] = true;
                }
            }
            hasDiff = nextDiff;
            //maxDiff是前n项和，他决定了可能插值的上限。即：所有数字分到同一个子集合的情况。
            maxDiff += num; // 更新可能的最大边界
        }

        //对于第x个数字的hasDiff数组来说，该数组的意义：
        // hasDiff表示了前x个数字所构成的子集合，能产生的所有差值。
        // hasDiff的下标就是差值，值为true时表示可以构造
        //通过循环内的赋值可以看到，一个数要么加入到已经形成了两个均有元素的子集合中，要么和前面所有数字均在同一个子集合。
        return hasDiff[0];
    }
}
