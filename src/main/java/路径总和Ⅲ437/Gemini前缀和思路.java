package 路径总和Ⅲ437;

import java.util.HashMap;
import java.util.Map;

public class Gemini前缀和思路 {

    /*
    *
    * 我们需要维护一个 HashMap 来记录遍历过程中出现过的前缀和及其出现次数。
        遍历：使用 DFS（先序遍历）访问每个节点。
        累加：维护一个变量 currSum，记录从根到当前节点的值。
        查询：在 Map 中查找是否存在 currSum - targetSum 这个 key。
            如果存在，说明中间有一段路径的和正好是 targetSum，将 Map 中对应的 value（次数）加到结果中。
            例如：[1,2,3,4]的和是10，有这个10时，必定有了[1,2]的前缀和为3，那么10-3得到的就是[3,4]的前缀和。真妙啊。
        记录：将当前的 currSum 放入 Map 中（计数 +1），以便它的子节点可以使用。
        递归：继续处理左右子树。
        回溯 (Backtrack) [非常重要]：
        当该节点的左右子树都处理完，准备返回父节点时，必须从 Map 中移除（或计数 -1）当前的 currSum。
        原因：前缀和只对“当前路径上的子孙节点”有效。左子树的前缀和不能被右子树借用。
    * */
    public int pathSum(TreeNode root, int targetSum) {
        //key：前缀和
        //value：该前缀和出现的次数

        Map<Long,Integer> prefixMap=new HashMap<>();

        //初始化：前缀和为0的情况出现了1次
        //作用：如果某个节点本身的前缀和直接等于targetSum
        //那么currSum - targetSum=0，需要这一项来计数
        prefixMap.put(0L,1);

        return recursion(root,0L,targetSum,prefixMap);
    }

    private int recursion(TreeNode node,long currSum,int targetSum,Map<Long,Integer> prefixMap){
        if(node==null){
            return 0;
        }

        int count=0;

        //更新当前路径的前缀和
        currSum += node.val;

        //核心逻辑
        //看看哈希表是否有 currSum-targetSum
        //如果有，说明从那个旧位置到当前位置的路径和 = targetSum
        if (prefixMap.containsKey(currSum - targetSum)){
            count += prefixMap.get(currSum - targetSum);
        }

        prefixMap.put(currSum,prefixMap.getOrDefault(currSum,0)+1);

        //递归左子树
        count += recursion(node.left,currSum,targetSum,prefixMap);
        //递归右子树
        count += recursion(node.right,currSum,targetSum,prefixMap);

        //回溯
        prefixMap.put(currSum,prefixMap.getOrDefault(currSum,0)-1);
        return count;

    }
}
