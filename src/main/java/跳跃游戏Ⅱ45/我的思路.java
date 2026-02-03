package 跳跃游戏Ⅱ45;

public class 我的思路 {
    public int jump(int[] nums) {
        //维护目前能达到的最远距离

        /*
        rightmost 并不只是代表一个点，它实际上代表了从起点 0 开始，你所能到达的连续范围是 [0, rightmost]
        * */
        int rightMost=0;
        int length=nums.length;
        int step=0;
        if(length==1)
            return 0;


        for(int i=0;i<length;i++){
            //更新到达的最远距离
            //i+nums[i] 代表从当前位置能跳到的最远位置
            if(rightMost<i+nums[i]){
                rightMost=i+nums[i];
                step++;
            }
//            rightMost=Math.max(rightMost,i+nums[i]);

            //如果已经能到达最后一个下标，直接返回 true
            if(rightMost>=length-1){
                return step;
            }
        }

        return step;
    }
}
