package 路径总和Ⅲ437;

public class 我的思路修复版 {

    private int result;
    
    public int pathSum(TreeNode root, int targetSum) {
        result = 0; // LeetCode中全局变量每次需重置
        compute(root, null, 0, targetSum);
        return result;
    }

    // start参数的含义修正为：
    // null  -> 代表当前是一个“全新的开始上下文”（我是起点，或者我之前的都在找起点）
    // !null -> 代表当前是一个“正在延续的路径”（上面有大哥带着，我不能私自开启新起点的搜索）
    private void compute(TreeNode root, TreeNode start, long currentSum, int targetSum) {
        if (root == null) return; // 必须处理空节点，否则下面root.left会空指针

        // 使用 long 防止加法溢出
        long tempSum = currentSum + root.val;

        if (tempSum == targetSum) {
            result++;
        }

        // --- 修改点 1：延续路径 ---
        // 既然是延续，说明有“起点”了。
        // 这里必须传一个非null值 (比如 root)，告诉子节点：“你只是路径的一部分，不要去开启新搜索”
        if (root.left != null) {
            compute(root.left, root, tempSum, targetSum); // <--- 原来是 null，改为 root
        }
        if (root.right != null) {
            compute(root.right, root, tempSum, targetSum); // <--- 原来是 null，改为 root
        }

        // --- 修改点 2：开启新路径 ---
        // 只有当当前环境是“寻找起点模式”(start == null) 时，才有资格决定让子节点也尝试作为起点
        if (start == null) {
            // 既然让子节点重新开始，对子节点来说，它们就是“新起点”
            // 这里必须传 null，告诉子节点：“把你当做根节点，去遍历你的子树吧”
            if (root.left != null) {
                compute(root.left, null, 0, targetSum); // <--- 原来是 root，改为 null
            }
            if (root.right != null) {
                compute(root.right, null, 0, targetSum); // <--- 原来是 root，改为 null
            }
        }
    }
}