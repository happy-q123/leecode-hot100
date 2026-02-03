package 盛最多水的容器11;

public class 我的思路 {
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(new 我的思路().maxArea(height));
    }
    /*
    * 我的思路已经是最优解法，但是Integer拆装箱会很耗时，所以尽量避免使用Integer
    * 我的思路：
    *   1、左边和右边都有一个指针，为left、right，一开始left指向第一个元素，right指向最后一个元素
    *   2、计算左右指针的盛水面积赋值给max。变量lastLeft赋值left、lastRight赋值right
    *   3、让right和left较小的向内移动，比如left较小，让left 向右移动。
    *       left移动后为x，比较x与lastLeft。
    *       （1）如果x比lastLeft小，则继续移动（因为越向内，矩形长度越小，所以必须要求x要大于lastLeft才有可能成为更大的max）。
    *       （2）如果x大于lastLeft，则计算盛水面积，若面积大于max，则：更新max为该面积、更新lastLeft为x。
    *      若right较小，则类比上面（1）（2）。
    *   4、重复步骤3，直到left 和 right相遇，输出max。
    * */
    public int maxArea(int[] height) {
        Integer left=0,lastLeft=null;
        Integer right=height.length-1,lastRight=null;
        Integer max=null;
        while(left<right){
            int chang=right-left;
            int kuan=Math.min(height[left],height[right]);
            int ji=chang*kuan;
            if(max==null||ji>max){
                max=ji;
                lastLeft=left;
                lastRight=right;
            }

            if(height[left]<=height[right]){
                left++;

                //优化一些不该计算的
                while(left<right&&height[left]<=height[lastLeft]){
                    left++;
                }
            }else {
                right--;

                //优化一些不该计算的
                while(left<right&&height[right]<=height[lastRight]){
                    right--;
                }
            }

        }
        return max==null?0:max;
    }
}
