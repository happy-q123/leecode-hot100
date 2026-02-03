package 跳跃游戏55;

public class 我的思路 {

    public static void main(String[] args) {
        int[] nums={2,1,0,1,4};
        System.out.println(new 我的思路().canJump(nums));
    }

    private boolean result=false;
    private int maxIndex;

    //思路类似深度优先搜索，但是会超时
    /*
    * 为什么你的代码会超时？
        重复计算：你的代码会尝试每一条可能的路径。例如 nums = [2, 3, 1, 1, 4]，当你在下标 0 时，你可以跳到 1 或 2。
            如果你跳到 1，后续会计算从 1 开始能不能到终点；如果你跳到 2，后续也会计算从 2 开始能不能到终点。
            但在复杂的数组中，很多下标会被从不同的前驱节点重复访问多次，导致指数级的时间复杂度。

        缺乏记忆化：你没有记录“某个下标是否已经测试过无法到达终点”。
            如果有记录（记忆化搜索），可以减少时间，但对于这道题，DFS 依然不是最优解。
    * */
    public boolean canJump(int[] nums) {
        maxIndex=nums.length-1;
        compute(nums,0);
        return result;
    }

    private void compute(int[] nums,int currentIndex){
        if(currentIndex==maxIndex){
            result=true;
        }

        int value=nums[currentIndex];

        if (value==0)
            return;

        for(int i=1;i<=value;i++){
            int nextIndex=currentIndex+i;

            if(nextIndex>maxIndex||result)
                return;

            compute(nums,nextIndex);
        }
    }
}
