package 除了自身以外数组的乘积283;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class 我的思路 {

    public static void main(String[] args) {
        我的思路 solution = new 我的思路();
        
        // 测试用例1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.print("输入: [1,2,3,4] -> 输出: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
            if (i < result1.length - 1) System.out.print(",");
        }
        System.out.println("]");
        
        // 测试用例2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.print("输入: [-1,1,0,-3,3] -> 输出: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
            if (i < result2.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
    //可以运行正确，但是超时
    public int[] productExceptSelf(int[] nums) {
        int[] result=new int[nums.length];
        Deque<Integer>deque=new ArrayDeque<>(nums.length);
        //索引加到队列里
        for(int i=0;i<nums.length;i++){
            deque.addLast(i);
        }
        
        for(int i=0;i<nums.length;i++){
            while(deque.getFirst()!=i){
                deque.addLast(deque.pollFirst());
            }

            int head=deque.pollFirst();
            deque.addLast(head);
            int x=1;
            while(deque.getFirst()!=head){
                x*=nums[deque.getFirst()];
                deque.addLast(deque.pollFirst());
            }
            result[i]=x;
        }
        return result;
    }
}
