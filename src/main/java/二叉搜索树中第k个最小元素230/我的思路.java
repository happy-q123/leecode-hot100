package 二叉搜索树中第k个最小元素230;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {

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
    private int n=0;
    Integer result=null;
    public int kthSmallest(TreeNode root, int k) {
        compute(root,k);
        if(result==null){
            return -1;
        }
        return result;

    }
    private void compute(TreeNode node,int k) {
        if(result!=null)
            return;
        if (node == null) {
            return;
        }
        if (node.left != null) {
            compute(node.left,k);
        }
        n++;
        if(n==k){
            result=node.val;
            return;
        }
        if(node.right != null) {
            compute(node.right,k);
        }
    }
}
