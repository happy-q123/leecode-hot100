package 滑动窗口最大值239;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 我的解法 {

    public static void main(String[] args) {
        我的解法 solution = new 我的解法();
        
//        // 测试用例1
//        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
//        int k1 = 3;
//        int[] result1 = solution.maxSlidingWindow(nums1, k1);
//        System.out.print("输入: nums = [");
//        for (int i = 0; i < nums1.length; i++) {
//            System.out.print(nums1[i]);
//            if (i < nums1.length - 1) System.out.print(", ");
//        }
//        System.out.println("], k = " + k1);
//        System.out.print("输出: [");
//        for (int i = 0; i < result1.length; i++) {
//            System.out.print(result1[i]);
//            if (i < result1.length - 1) System.out.print(", ");
//        }
//        System.out.println("]\n");
//
//        // 测试用例2
//        int[] nums2 = {1};
//        int k2 = 1;
//        int[] result2 = solution.maxSlidingWindow(nums2, k2);
//        System.out.print("输入: nums = [");
//        for (int i = 0; i < nums2.length; i++) {
//            System.out.print(nums2[i]);
//            if (i < nums2.length - 1) System.out.print(", ");
//        }
//        System.out.println("], k = " + k2);
//        System.out.print("输出: [");
//        for (int i = 0; i < result2.length; i++) {
//            System.out.print(result2[i]);
//            if (i < result2.length - 1) System.out.print(", ");
//        }
//        System.out.println("]\n");
//
//        // 测试用例3
//        int[] nums3 = {1,3,-1,-3,5,3,6,7};
//        int k3 = 3;
//        int[] result3 = solution.maxSlidingWindow(nums3, k3);
//        System.out.print("输入: nums = [");
//        for (int i = 0; i < nums3.length; i++) {
//            System.out.print(nums3[i]);
//            if (i < nums3.length - 1) System.out.print(", ");
//        }
//        System.out.println("], k = " + k3);
//        System.out.print("输出: [");
//        for (int i = 0; i < result3.length; i++) {
//            System.out.print(result3[i]);
//            if (i < result3.length - 1) System.out.print(", ");
//        }
//        System.out.println("]");

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
    class Node{
        public int val;
        public int index;
        public Node(int val,int index){
            this.val=val;
            this.index=index;
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length-k+1];
        int currentIndex=-1;
        PriorityQueue<Node> priorityQueue=new PriorityQueue<>(nums.length,(x1,x2)->x2.val-x1.val);
        for(int i=0;i<nums.length;i++){
            if(i+1<k){
                Node node=new Node(nums[i],i);
                //若小于等于k，则直接加进去
                priorityQueue.add(node);
            } else{
                //i+1>=k的情况
                Node node=new Node(nums[i],i);
                //若小于等于k，则直接加进去
                priorityQueue.add(node);
                Node currentMaxNode=priorityQueue.peek();

                //不在当前k序列中的，全部出去
                while(i+1-k>currentMaxNode.index){
                    priorityQueue.poll();
                    currentMaxNode=priorityQueue.peek();
                }
                currentIndex++;
                result[currentIndex]=currentMaxNode.val;

                }
                for(int q=0;q<=currentIndex;q++){
                    System.out.print(result[q]+" ");
                    if(q==currentIndex)
                        System.out.println();
                }
            }
        return result;
    }

//    /**
//     * Reversion1
//     * description 可以跑，但是超时。最大瓶颈在remove操作。
//     * author zzq
//     * date 2026/1/10 12:43
//     */
//    public int[] maxSlidingWindow(int[] nums, int k) {
//        int[] result = new int[nums.length-k+1];
//        int currentIndex=-1;
//        boolean flag=false;
//        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(k,(x1,x2)->x2-x1);
//        for(int i=0;i<nums.length;i++){
//            priorityQueue.add(nums[i]);
//
//            //
//            if(i+1==k){
//                flag=true;
//            }
//            if(flag){
//                //得到最大的值（不移除）、移走第一个
//                currentIndex++;
//                result[currentIndex]=priorityQueue.peek();
//                priorityQueue.remove(nums[i+1-k]);
//            }
//        }
//
//        return result;
//    }
}
