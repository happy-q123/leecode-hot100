package 搜索插入位置35;

public class 我的思路 {
    public static void main(String[] args) {
        int[] nums=new int[]{1,3,5,6};
        int target=2;
        我的思路 x=new 我的思路();
        System.out.println(x.searchInsert(nums,target));
    }

    //nums = [1,3,5,6], target = 4
    private int result=0;
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0){
            return result;
        }
        compute(nums,target,0,nums.length-1);
        return result;
    }
/*
*  1 3 5 6
*  0 1 2 3
*
* 1 r=1
* 2 3
* mid=2
* */
    //当left==mid时，比mid小，就添加在mid位置，比mid大，就添加在mid+1位置
    private void compute(int[] nums, int target,int left, int right) {
        if(left > right){
            return;
        }

        int mid=(right+left)/2;
        result=mid;
        System.out.println(left+" "+right+" "+mid+" "+target);
        if(nums[mid]>target){
            if(left==right){
//                result=Math.max(0,result-1);
            }else {
                compute(nums,target,left,mid-1);
            }
        }else if(nums[mid]<target){
            if(left==right){
                result=mid+1;
            }else {
                compute(nums,target,mid+1,right);
            }
        }
    }
}
