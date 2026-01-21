package 组合总和39;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class 我的思路 {
    public static void main(String[] args) {
        我的思路 x=new 我的思路();
        List<List<Integer>>p=x.combinationSum(new int[]{2,3,6,7},8);
        for(List<Integer> list:p){

            for(Integer i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        if(candidates.length==0){
            return result;
        }

        compute(new ArrayList<>(),0,candidates,result,target);

        return  result;
    }

    public void compute(List<Integer> currentList,int currentSum,int[]candidates,List<List<Integer>> result,int target){
        if(currentSum==target){
            result.add(new ArrayList<>(currentList));
        }else if(currentSum>target){
            return;
        }
        int sum=currentSum;
        for(int i=0;i<candidates.length;i++){
            if(!currentList.isEmpty() &&candidates[i]<currentList.getLast()){
                continue;
            }
            currentList.add(candidates[i]);
            sum+=candidates[i];

            compute(currentList,sum,candidates,result,target);

            currentList.remove(currentList.size()-1);
            sum-=candidates[i];
        }
    }
}
