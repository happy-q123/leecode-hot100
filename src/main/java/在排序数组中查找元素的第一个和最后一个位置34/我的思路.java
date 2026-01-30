package 在排序数组中查找元素的第一个和最后一个位置34;

public class 我的思路 {

    public static void main(String args[]){
        int[] nums={5,7,7,8,8,8,8,10};
        int target=8;
        int[] result=new 我的思路().searchRange(nums,target);
//        for(int i:result){
//            System.out.print(i+" ");
//        }
    }

    /**
     * description
     * 思路1：
     *    二分查找找到目标值，然后向左、向右遍历。
     *    （假如出现“122222222222222223”，且是寻找2的情况）的情况，时间复杂度就相当于O(N)，不合题意。
     * 思路2：
     *     寻找比目标值大的最小值位置，和寻找比目标值小的最大值位置，就是结果。
     * author zzq
     * date 2026/1/30 14:00
    */

    public int[] searchRange(int[] nums, int target) {
        int[] result=new int[2];
        int st=-1;
        int end=-1;
        int mid=-1;

        int stForFind=0;
        int endForFind=nums.length-1;
        int midForFind=-1;

        int max=-1;
        int min=-1;
        while(stForFind<endForFind){
            midForFind=stForFind+(endForFind-stForFind)/2;
            System.out.println("stForFind"+stForFind+" endForFind"+endForFind+" midForFind"+midForFind);

            if(nums[midForFind]==target){
                System.out.println("max："+ max+" min："+min);

                return new int[]{midForFind,midForFind};
            }else if (nums[midForFind]<target){
                stForFind=midForFind+1;
                min=midForFind;
            }else {
                endForFind=midForFind-1;
                max=midForFind;
            }
        }
        System.out.println("max："+ max+" min："+min);
        return result;
    }


}
