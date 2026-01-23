package 验证二叉搜索树98;

import java.util.ArrayList;
import java.util.List;

public class 我的思路2 {

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
    private List<Integer> list=new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        compute(root);
        for(int i=1;i<list.size();i++){
            if(list.get(i-1)>=list.get(i)){
                return false;
            }
        }
        return true;
    }

    //后序遍历判断,函数返回子树最大值
    public void compute(TreeNode node) {
        Integer leftMax = null;
        Integer rightMax = null;
        if (node.left != null) {
            compute(node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            compute(node.right);
        }
    }
}
