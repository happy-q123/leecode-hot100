package 二叉树的直径543;

public class gemini写的 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    // 设置一个全局变量（或成员变量）来记录遍历过程中遇到的最大直径
    int maxDiameter = 0;

    //在我的思路1的基础上，添加了一个全局比较
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0; // 初始化
        maxDepth(root);
        return maxDiameter;
    }

    // 这个函数的定义是：返回以 node 为根的子树的最大深度（高度）
    // 在计算深度的过程中，顺便更新 maxDiameter
    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 1. 递归计算左子树的最大深度
        int leftDepth = maxDepth(node.left);

        // 2. 递归计算右子树的最大深度
        int rightDepth = maxDepth(node.right);

        // 3. 关键点：穿过当前 node 的路径长度 = 左深度 + 右深度
        // 我们用这个值来尝试更新全局最大直径
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 4. 返回给父节点的信息：当前节点的最大深度
        // 深度 = 1 (当前节点本身) + 左右子树中较深的那个
        return 1 + Math.max(leftDepth, rightDepth);
    }
}

