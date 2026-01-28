package 二叉树的最近公共祖先236;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private boolean pIsFind=false;
    private boolean qIsFind=false;
    private List<TreeNode> pPath = new ArrayList<>();
    private List<TreeNode> qPath = new ArrayList<>();


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        compute(root, p, q);
        int length=Math.min(pPath.size(), qPath.size());
        for (int i = 0; i < length; i++) {
            if(pPath.get(i)==qPath.get(i))
                continue;
            else
                return pPath.get(i-1);
        }
        return pPath.get(length-1);

    }

    private void compute(TreeNode root,TreeNode p,TreeNode q) {
        if (root == null) return;

        if(!pIsFind){
            pPath.add(root);
        }

        if(!qIsFind){
            qPath.add(q);
        }

        if(root==p){
            pIsFind=true;
        }

        if(root==q){
            qIsFind=true;
        }

        //只要一个没找到就继续
        if(!pIsFind||!qIsFind){
            if(root.left!=null){
                compute(root.left,p,q);
                if(!qIsFind){
                    qPath.removeLast();
                }
                if(!pIsFind){
                    pPath.removeLast();
                }
            }

            if(root.right!=null){
                compute(root.right,p,q);
                if(!qIsFind){
                    qPath.removeLast();
                }
                if(!pIsFind){
                    pPath.removeLast();
                }
            }
        }

    }
}
