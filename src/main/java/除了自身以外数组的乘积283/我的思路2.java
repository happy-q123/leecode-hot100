package 除了自身以外数组的乘积283;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class 我的思路2 {

    public static void main(String[] args) {
        我的思路2 solution = new 我的思路2();

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
        //qian_z标识前x数字的乘积
        int[] qian_z=new int[nums.length+1];

        //hou_z[x]标识后x数字的乘积
        int[] hou_z=new int[nums.length+1];
        int[] result=new int[nums.length];


        //初始化
        qian_z[0]=1;
        hou_z[0]=1;

        //计算前缀乘积
        for(int i=0;i<nums.length;i++){
            qian_z[i+1]=nums[i]*qian_z[i];
        }

        //计算后缀成绩
        for(int i=nums.length-1;i>=0;i--){
            hou_z[nums.length-i]=nums[i]*hou_z[nums.length-i-1];
        }

        for(int i=0;i<nums.length;i++){
            //标识第linshi个元素
//            nums[i]: 1 2 3 4
//                  i: 0 1 2 3

            int need_hz_index=nums.length-i-1;
            int need_qz_index=i;
            int result_x=1;

            //其实不需要判断，因为hou_z[0]和qian_z[0]都是1
            if(need_hz_index!=0){
                result_x*=hou_z[need_hz_index];
            }
            if(need_qz_index!=0){
                result_x*=qian_z[need_qz_index];
            }
            result[i]=result_x;
        }
        return result;
    }
}
