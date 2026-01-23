package 二叉树的层序遍历102;

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        List<Integer> list=null;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        int st=0;
        int currentCenNum=1;
        int nextCenNum=0;
        while (!queue.isEmpty()) {
            TreeNode node=queue.pollFirst();
            if(st==0){
               list=new ArrayList<>();
            }
            list.add(node.val);
            st++;
            if(node.left!=null){
                queue.addLast(node.left);
                nextCenNum++;
            }
            if(node.right!=null){
                queue.addLast(node.right);
                nextCenNum++;
            }
            if(st==currentCenNum){
                res.add(list);
                list=null;
                st=0;
                currentCenNum=nextCenNum;
                nextCenNum=0;
            }
        }
        return res;
    }
}
