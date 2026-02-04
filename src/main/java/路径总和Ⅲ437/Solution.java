package 路径总和Ⅲ437;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        // key: 前缀和
        // value: 该前缀和出现的次数
        Map<Long, Integer> prefixMap = new HashMap<>();
        
        // 初始化：前缀和为0的情况出现了1次。
        // 作用：如果某个节点本身的前缀和直接等于 targetSum，
        // 那么 currSum - targetSum = 0，我们需要这一项来计数。
        prefixMap.put(0L, 1);
        
        // 使用 long 是为了防止路径和溢出 int 范围
        return recursion(root, 0L, targetSum, prefixMap);
    }

    private int recursion(TreeNode node, long currSum, int targetSum, Map<Long, Integer> prefixMap) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        
        // 1. 更新当前路径的前缀和
        currSum += node.val;

        // 2. 核心逻辑：
        // 看看哈希表中是否有 (currSum - targetSum)
        // 如果有，说明从那个旧位置到当前位置的路径和 = targetSum
        if (prefixMap.containsKey(currSum - targetSum)) {
            count += prefixMap.get(currSum - targetSum);
        }

        // 3. 将当前的前缀和加入 map，给子节点用
        prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

        // 4. 继续向下递归
        count += recursion(node.left, currSum, targetSum, prefixMap);
        count += recursion(node.right, currSum, targetSum, prefixMap);

        // 5. 回溯 (清理现场)
        // 离开当前节点前，要把当前节点的前缀和计数减1。
        // 因为处理完该节点后，回到父节点去遍历兄弟分支时，兄弟分支不应该看到该节点的前缀和。
        prefixMap.put(currSum, prefixMap.get(currSum) - 1);

        return count;
    }
}