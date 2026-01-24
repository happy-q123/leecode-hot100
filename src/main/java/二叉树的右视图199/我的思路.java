package 二叉树的右视图199;

import java.util.*;

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

    //感觉就是选好层序遍历的最后一个有效节点
    public List<Integer> rightSideView(TreeNode root) {
        Deque<TreeNode> deque=new ArrayDeque<>();
        List<Integer> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        deque.addLast(root);
        TreeNode end=deque.getFirst();
        TreeNode current=null;
        TreeNode nextEnd=null;
        while(!deque.isEmpty()){
            current=deque.pollFirst();
            if(current.left!=null){
                deque.addLast(current.left);
                nextEnd=current.left;
            }
            if(current.right!=null){
                deque.addLast(current.right);
                nextEnd=current.right;
            }
            //当前层遍历到了最后一个结点，添加值。
            if(current==end){
                result.add(current.val);
                end=nextEnd;
            }
        }
        return result;
    }


}
