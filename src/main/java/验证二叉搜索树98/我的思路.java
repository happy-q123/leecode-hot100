package 验证二叉搜索树98;

public class 我的思路 {

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
    private boolean result=true;

    //无法通过[5,4,6,null,null,3,7]这一用例。还是中序遍历，判断是否严格递增好一点。
    public boolean isValidBST(TreeNode root) {
        compute(root);
        return result;
    }

    //后序遍历判断,函数返回子树最大值
    public void compute(TreeNode node) {
        Integer leftMax = null;
        Integer rightMax = null;
        if (node.left != null) {
            compute(node.left);
        }
        if (node.right != null) {
            compute(node.right);
        }

        if ((node.left != null && node.left.val >= node.val) || (node.right != null && node.right.val <= node.val)) {
            result = false;
        }
    }
}
