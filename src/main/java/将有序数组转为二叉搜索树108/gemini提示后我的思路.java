package 将有序数组转为二叉搜索树108;

/*
1、二叉搜索树（BST，Binary Search Tree），也称二叉排序树或二叉查找树。
    二叉搜索树：一棵二叉树，可以为空；如果不为空，满足以下性质：

    非空左子树的所有键值小于其根结点的键值。
    非空右子树的所有键值大于其根结点的键值。
    左、右子树都是二叉搜索树。
2、平衡二叉搜索树：
    在二叉搜索树的基础上，还需满足 左子树与右子树高度之差的绝对值不超过1
*
*
* */

/*
* 我的思考：我以为要写出平衡树的自动平衡代码，左旋转、右旋转什么的，太难了。没有理解好题目中升序的意义。
* 升序意味着每次找到的中点，其左右长度最多相差1。
* 因此有一句话：一个二叉搜索树（大的作为根）的中序遍历序列是一个升序数组。
*
* */
public class gemini提示后我的思路 {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0)
            return null;
        return compute(0,nums.length-1,nums);
    }

    private TreeNode compute(int st,int end,int[]nums){
        if(st==end){
            return new TreeNode(nums[st]);
        }
        if(st>end){
            return null;
        }
        // System.out.println("st："+st+" "+"end："+end);
        int mid = (st+end)/2;
        // System.out.println("md："+mid);
        TreeNode left = compute(st,mid-1,nums);
        TreeNode right = compute(mid+1,end,nums);
        TreeNode root=new TreeNode(nums[mid]);
        root.left=left;
        root.right=right;
        return root;
    }

//    //其实可以更加简洁
//    private TreeNode compute(int st, int end, int[] nums) {
//        // 唯一的 Base Case：这也是递归的出口
//        if (st > end) {
//            return null;
//        }
//
//        // 防溢出写法
//        int mid = st + (end - st) / 2;
//
//        // 先创建 root 还是先递归左右其实都可以，看个人习惯
//        // 这里改回比较常见的“先序”风格：先建根，再连子树
//        TreeNode root = new TreeNode(nums[mid]);
//        root.left = compute(st, mid - 1, nums);
//        root.right = compute(mid + 1, end, nums);
//
//        return root;
//    }
}
