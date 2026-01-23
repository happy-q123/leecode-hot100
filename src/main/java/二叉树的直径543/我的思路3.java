package 二叉树的直径543;

public class 我的思路3 {

    public class TreeNode {
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
    private int maxDepth=0;

    //感觉就是求左子树和右子树的深度之和
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int l=computeDepth(root.left);
        int r=computeDepth(root.right);
        maxDepth=Math.max(maxDepth,l+r);
        return maxDepth;
    }

    private int computeDepth(TreeNode node) {
        if(node == null) return 0;
        int l=0;
        int r=0;
        if(node.left!=null){
            l=computeDepth(node.left);
        }
        if(node.right!=null){
            r=computeDepth(node.right);
        }
        maxDepth=Math.max(maxDepth,l+r);
        return 1+Math.max(l,r);
    }
}
