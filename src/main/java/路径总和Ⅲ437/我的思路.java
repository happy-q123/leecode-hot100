package 路径总和Ⅲ437;


public class 我的思路 {
    /*
    * 思路一：
    *   1、使用递归的方式，先序遍历树。在每个节点上加上当前值并判断是否等于目标和。
    *   2、步骤1可以判断以根节点出发的所有情况，但不能判断子树情况。因此有步骤3。
    *   3、使用start标志位，表示以当前节点为起点往下，不包含此节点的值。可以覆盖子树的情况。
    *       当start为null时，则从当前节点开始往下，不包含此节点的值。不为null时，说明该路径是子树路径，不需要再次使用start标志位。
    *思路二：
    *   思路一使用一个递归函数，思路二使用两个递归函数。
    *   写一个函数A遍历树，写一个函数B也是遍历树。函数B计算从当前节点出发的所有路径的和。函数A决定从哪个节点出发。
    *
    * */
    private int result;
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        compute(root,null,0,targetSum);
        return result;
    }

    //当前节点root、起点start(以该起点开始的往下，但不包含此节点的值)、当前和currentSum、目标和targetSum
    private void compute(TreeNode root, TreeNode start, int currentSum, int targetSum) {
        boolean isYe=true;

        if (currentSum+root.val==targetSum){
            result++;
        }

        //连接本节点时传递
        //传递左侧节点
        if(root.left!= null){
            compute(root.left,null,currentSum+root.val,targetSum);
            isYe=false;
        }
        //传递右侧节点
        if (root.right!= null){
            compute(root.right,null,currentSum+root.val,targetSum);
            isYe=false;
        }

        //如果非叶子节点
        if (!isYe){
            if (start == null){
                //取消本节点进行传递
                //传递左侧节点
                if (root.left!= null){
                    compute(root.left,root,0,targetSum);
                }
                //传递右侧节点
                if (root.right!= null){
                    compute(root.right,root,0,targetSum);
                }
            }
        }

    }
}
