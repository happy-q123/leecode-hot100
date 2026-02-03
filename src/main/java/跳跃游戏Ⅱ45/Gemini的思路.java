package 跳跃游戏Ⅱ45;

public class Gemini的思路 {

    public static void main(String[] args) {
        int[] nums={2,3,1,1,4};
        System.out.println(new Gemini的思路().jump(nums));
    }
    public int jump(int[] nums) {
        int end = 0;        // 当前这一步的边界（必须到了这里才算一步结束）
        int maxPos = 0;     // 探测到的最远位置
        int step = 0;

        // 注意：i 不要遍历到最后一个元素，因为在倒数第二个位置跳完就能到了
        for(int i = 0; i < nums.length - 1; i++){

            // 1. 总是记录能跳到的最远位置（这是在“看”，不是在“跳”）
            maxPos = Math.max(maxPos, i + nums[i]);

            // 2. 只有当 i 走到了当前这一步的边界，才被迫加一步
            if(i == end){
                end = maxPos; // 把边界推到刚才看到的最远位置
                step++;
            }
        }
        return step;
    }
}
