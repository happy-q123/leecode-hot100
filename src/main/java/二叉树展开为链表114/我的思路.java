package 二叉树展开为链表114;

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

    public void flatten(TreeNode root) {
        List<TreeNode> list=new ArrayList<>();
        compute(root,list);
        for(int i=0;i<list.size()-1;i++){
            list.get(i).left=null;
            list.get(i).right=list.get(i+1);
        }
        if(!list.isEmpty()){
            list.getLast().right=null;

        }
    }
    public void compute(TreeNode root, List<TreeNode> node_list) {
        if(root==null){
            return;
        }
        node_list.add(root);
        if(root.left!=null){
            compute(root.left,node_list);
        }
        if(root.right!=null){
            compute(root.right,node_list);
        }
    }
}
