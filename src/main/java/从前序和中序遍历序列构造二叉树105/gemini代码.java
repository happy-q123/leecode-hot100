package 从前序和中序遍历序列构造二叉树105;

import java.util.HashMap;
import java.util.Map;

public class gemini代码 {
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

    // 为了快速定位 inorder 中的根节点位置，避免每次都遍历，使用哈希表
    //为inorder构建一个map，使得根据值查找对应的索引的时间复杂度为O(1)。
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        indexMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            indexMap.put(inorder[i],i);
        }

        TreeNode root=compute(0,preorder.length-1,0,inorder.length-1,preorder,inorder);
        return root;
    }

    //子树在preorder中起点、终点、在inorder中起点、终点、preorder、inorder
    /**
     * @param preorder 先序数组
     * @param preSt 先序数组的当前子树 起始下标
     * @param preEnd   先序数组的当前子树 结束下标
     * @param inorder  中序数组
     * @param inSt  中序数组的当前子树 起始下标
     * @param inEnd    中序数组的当前子树 结束下标
     */
    private TreeNode compute(int preSt, int preEnd, int inSt, int inEnd, int[] preorder, int[] inorder) {
        if (preSt > preEnd) {
            return null;
        }

        int rootVal = preorder[preSt];
        int inIndex = indexMap.get(rootVal);

        // 拿到左子树长度
        int leftLength = inIndex - inSt;

        // --- 1. 准备左子树参数 ---
        int left_pre_st = preSt + 1;
        int left_pre_end = preSt + leftLength; // 【修正点1】基于 preSt 计算
        int left_in_st = inSt;
        int left_in_end = inIndex - 1;

        TreeNode root = new TreeNode(rootVal);

        // 【修正点2】参数位置要一一对应：(preStart, preEnd, inStart, inEnd)
        root.left = compute(left_pre_st, left_pre_end, left_in_st, left_in_end, preorder, inorder);

        // --- 2. 准备右子树参数 ---
        int right_pre_st = left_pre_end + 1;
        int right_pre_end = preEnd;
        int right_in_st = inIndex + 1; // 【修正点3】基于根节点位置 inIndex 计算
        int right_in_end = inEnd;

        // 【修正点4】参数位置对应
        root.right = compute(right_pre_st, right_pre_end, right_in_st, right_in_end, preorder, inorder);

        return root;
    }
}
