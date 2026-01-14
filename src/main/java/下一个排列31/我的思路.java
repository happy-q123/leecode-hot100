package 下一个排列31;

public class 我的思路 {

    public static void main(String[]args){
        我的思路 x=new 我的思路();
        int[] input=new int[]{3,4,7,6,5};
        x.nextPermutation(input);
        for(int i=0;i<input.length;i++){
            System.out.print(input[i]+" ");
        }

    }

    //下一个排列，即两种情况：
    //1、从后往前，找到第一个 降序序列，记录降序的第一个数字为x，位置为x_in，
    // 在升序序列里找到第一个比x大的数记为y，让y与x互换，然后翻转x_xin后的序列
    //    0 1 2 3 4
    //如：3 4 7 6 5 ->7 6 5为升序（从右往左），4 7为降序，4为降序第一个，7 6 5中第一个比4大的是5，
    // 互换得到 3 5 7 6 4，反转 7 6 4得到3 5 4 6 7
    //再如：4 6 5 7 ->第一个降序为5 7，直接5 7互换，得到 4 6 7 5，反转5 得到4 6 7 5
    //再如：3 5 7 6 4->第一个降序为5 7，选中5，在7 6 4中找到第一个比5大的是6，互换得到 3 6 7 5 4，反转7 5 4得到 3 6 4 5 7
    //2、如果没找到降序，说明当前排列已经是最大的，直接反正整个数组即可。
    //如：4 3 2 1，得到1 2 3 4
    public void nextPermutation(int[] nums) {
        //从右到左

        //升序的第一个数字索引
        int sheng_st=nums.length-1;
        //升序的最后一个数组
        int sheng_end=sheng_st;
        int jiang_index;
        int first_beyond_jiang=-1;
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]>=nums[sheng_end]){
                sheng_end=i;
            }else{
                jiang_index=i;
                //在升序中找到第一个比first_beyond_jiang大的数。
                for(int t=sheng_st;t>=sheng_end;t--){
                    if(nums[t]>nums[jiang_index]){
                        first_beyond_jiang=t;
                        break;
                    }
                }
                //互换两个数
                int p=nums[jiang_index];
                nums[jiang_index]=nums[first_beyond_jiang];
                nums[first_beyond_jiang]=p;
                break;
            }
            if(i==0){
                //如果i==0还没跳出去，说明没有降序，直接end=0，准备反转整个数组
                sheng_end=0;
            }
        }

        //反转数组 [sheng_end,sheng_st]
        int length=sheng_st-sheng_end+1;
        int c=length/2;
        for(int i=0;i<c;i++){
            int p=nums[i+sheng_end];
            nums[sheng_end]=nums[sheng_st-i];
            nums[sheng_st-i]=p;
        }
    }
}
