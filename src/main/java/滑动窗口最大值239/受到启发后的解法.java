package 滑动窗口最大值239;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class 受到启发后的解法 {
    public static void main(String[]args){
        受到启发后的解法 solution = new 受到启发后的解法();
        // 测试用例4
        int[] nums4 = {9,10,9,-7,-4,-8,2,-6};
        int k4 = 5;
        int[] result4 = solution.maxSlidingWindow(nums4, k4);
        System.out.print("输入: nums = [");
        for (int i = 0; i < nums4.length; i++) {
            System.out.print(nums4[i]);
            if (i < nums4.length - 1) System.out.print(", ");
        }
        System.out.println("], k = " + k4);
        System.out.print("输出: [");
        for (int i = 0; i < result4.length; i++) {
            System.out.print(result4[i]);
            if (i < result4.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue=new ArrayDeque(k);

        int[] result=new int[nums.length-k+1];
        int currentIndex=-1;

        for (int i=0;i<nums.length;i++) {
            if (queue.isEmpty()) {
                queue.addLast(nums[i]);
            }else{
                //如果当前有的值，新值小，那么可以删除旧值
                //该方案从i==1时就开始执行，可以保证队列的第一个元素一定是最大值。
                while(!queue.isEmpty()&&queue.getLast()<nums[i]){
                    queue.removeLast();
                }
                queue.addLast(nums[i]);
            }

            if(i+1>=k){
                //如果最大值与当前k第一个元素相等，则两种情况：
                //情况1：5 5的，删除一个5，还有一个5，不影响后续正常运行
                //情况2：5 4，删除一个5，后续才能正常运行

                currentIndex++;
                result[currentIndex]= queue.getFirst();
                if(queue.getFirst()==nums[i-k+1]){
                    queue.removeFirst();
                }
            }

        }
        return result;
    }
}
