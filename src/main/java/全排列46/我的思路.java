package 全排列46;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {

    public static void main(String[] args) {

    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0) return result;
        int[] used = new int[nums.length];
        compute(new ArrayList<>(),result,nums,used);
        return result;

    }
//    //[1,2,3]全排列共三个数组，第一层递归添加第一个数字，第二层添加第二个数字，第三层添加第三个数字
//    //当前list、当前总的list，当前nums
//    //版本1：太吃内存
//    public void compute(List<Integer> currentList,List<List<Integer>> result, int[] nums) {
//
//        if(currentList != null&&currentList.size()==nums.length){
//            //在最深层递归给result添加list
//            result.add(currentList);
//        }
//
//        for(int i=0;i<nums.length;i++){
//            List<Integer> tool_list=new ArrayList<>(currentList);
//            if(!currentList.contains(nums[i])){
//                tool_list.add(nums[i]);
//                compute(tool_list,result,nums);
//            }
//        }
//    }
    //版本2
    public void compute(List<Integer> currentList,List<List<Integer>> result, int[] nums,int[] used) {

        if(currentList != null&&currentList.size()==nums.length){
            //在最深层递归给result添加list
            result.add(new ArrayList<>(currentList));
            return;
        }
        List<Integer> tool_list=currentList;
        for(int i=0;i<nums.length;i++){
            //int初始化为0，0表示没有使用
            if(used[i]==0){
                tool_list.add(nums[i]);
                used[i]=1;
                compute(tool_list,result,nums,used);

                //递归完恢复状态，以供该层继续
                used[i]=0;
                tool_list.remove(tool_list.size()-1);
            }
        }
    }


}
