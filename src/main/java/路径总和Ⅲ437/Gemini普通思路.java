package 路径总和Ⅲ437;

public class Gemini普通思路 {

    //思路二的实现

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        // 结果 = (以当前节点为起点的路径数) + (左子树里的路径数) + (右子树里的路径数)
        return rootSum(root, targetSum)
                + pathSum(root.left, targetSum)
                + pathSum(root.right, targetSum);
    }

    // 计算以 node 为起点的路径有多少条
    // 注意：这里使用 long 防止溢出
    private int rootSum(TreeNode node, long targetSum) {
        if (node == null) {
            return 0;
        }

        int count = 0;

        // 如果当前节点值正好等于剩余的目标值，说明找到了一条路径
        if (node.val == targetSum) {
            count++;
        }

        // 继续向下寻找，目标值减去当前节点值
        count += rootSum(node.left, targetSum - node.val);
        count += rootSum(node.right, targetSum - node.val);

        return count;
    }
}
