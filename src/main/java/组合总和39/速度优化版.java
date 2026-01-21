package 组合总和39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 速度优化版 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        // 1. 排序：这是剪枝的前提
        Arrays.sort(candidates);
        
        // 开启回溯，从索引 0 开始
        backtrack(candidates, target, 0, path, result);
        
        return result;
    }

    /**
     * @param candidates 候选数组
     * @param target     剩余需要凑的目标值（直接减，省去 sum 变量）
     * @param start      本层递归搜索的起始下标（用于去重）
     * @param path       当前路径
     * @param result     结果集
     */
    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> result) {
        // base case：找到目标组合
        if (target == 0) {
            // 只有在存入结果时才 new 一个新 list，节省内存
            result.add(new ArrayList<>(path));
            return;
        }

        // 从 start 开始遍历，避免重复组合（如 [2,3] 和 [3,2]）
        for (int i = start; i < candidates.length; i++) {
            
            // 2. 剪枝：如果当前数字已经大于剩余 target，后面的数字更大，直接结束循环
            if (candidates[i] > target) {
                break;
            }

            path.add(candidates[i]);
            
            // 关键点：递归传入 i 而不是 i+1，因为题目允许数字重复使用
            backtrack(candidates, target - candidates[i], i, path, result);
            
            // 3. 回溯：移除最后一个元素，尝试下一个可能
            path.remove(path.size() - 1);
        }
    }
}