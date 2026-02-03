package 跳跃游戏55;

public class Gemini贪心算法 {
    public boolean canJump(int[] nums) {
        //维护目前能达到的最远距离

        /*
        rightmost 并不只是代表一个点，它实际上代表了从起点 0 开始，你所能到达的连续范围是 [0, rightmost]
        * */
        int rightMost=0;
        int length=nums.length;

        for(int i=0;i<length;i++){
            //如果当前位置i超过了能到达的最远距离，则说明跳不过去了
            if(i>rightMost){
                return false;
            }

            //更新到达的最远距离
            //i+nums[i] 代表从当前位置能跳到的最远位置
            rightMost=Math.max(rightMost,i+nums[i]);

            //如果已经能到达最后一个下标，直接返回 true
            if(rightMost>=length-1){
                return true;
            }
        }

        return false;
    }
}
