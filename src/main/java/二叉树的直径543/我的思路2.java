package 二叉树的直径543;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class 我的思路2 {
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


    //感觉就是求左子树和右子树的深度之和
    public int diameterOfBinaryTree(TreeNode root) {
        return computeMax(root);
    }

//    后续遍历求最大
    private int computeMax(TreeNode node){
        if(node == null) return 0;
        TreeNode left = node.left;
        while(left!=null){
            computeMax(left);
            left=left.left;
        }
        while(node.right!=null){
            computeMax(node.right);
            node.right=node.right.right;
        }
        int l=0;
        int r=0;
        l=computeDepthSum(node.left);
        r=computeDepthSum(node.right);
        return Math.max(l,r);
    }
    private int computeDepthSum(TreeNode node){
        if(node == null) return 0;
        int l=computeDepth(node.left);
        int r=computeDepth(node.right);
        return l+r;
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
        return 1+Math.max(l,r);
    }
}
