package 搜索旋转排序数组33;

public class 我的思路 {

    public static void main(String args[]){
        int[] nums = {4,5,6,7,0,1,2};
        int target = 4;
        // 创建对象并调用
        int result = new 我的思路().search(nums, target);
        System.out.println(result);
    }

    /**
     * description
     *  失败
     * author zzq
     * date 2026/1/30 14:51
    */

    public int search(int[] nums, int target) {
        int result=find(nums,target);
        return result;
    }

    private int find(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        int mid=-1;
        while(left<=right){
            mid=left+(right-left)/2;
            System.out.println("left:"+left+" mid:"+mid+" right:"+right);
            if(nums[mid] == target){
                return mid;
            }if(nums[mid]>target){
                //正常情况应该向左
                if(nums[left]> nums[right]){
                    //这时应该向右
                    left=mid+1;
                }else{
                    //正常情况向左
                    right=mid-1;
                }
            }else{
                //正常情况应该向右
                if(nums[left]> nums[right]){
                    //这时应该向左
                    right=mid-1;
                }else{
                    //正常情况向右
                    left=mid+1;
                }
            }
        }
        return -1;
    }
}
