package 分割等和子集416;

public class 思路 {

    /*
    * 思路：设第一个数和第二个数为a、b，值为5、1，他们可以看作两个子集相差了4。
    * 当遍历到第三个数c时，设c的值为x。则x-4可得到的数便是将x分到数b这个子集后，两子集的差。x+4便是将x分到数a这个子集后，两子集的差。
        a-b=4
        a-(b+11)=4-11=a-b-11=-7。即差了7
        a+11-b=4+11 差了15。
    因此，可以设定状态Min。Min[x]表示前x个数字分成子集后的最小差的绝对值。设数组长度为p，则Min[p]为0时，表示可以分割。
    另一个状态Max[x]，表示前x个数分成子集的最大差，即一个集合什么也不分，一个集合分所有数字，也即前x个数字之和
    Min[x]=Math.min(abs(Min[x-1]-nums[x]),Min[x-1]+nums[x],abs(nums[x]-Max[x-1]));
    min(abs(Min[x-1]-nums[x]),Min[x-1]+nums[x])的考虑是将前面数字分成两集合时的最小差，由最小差决定当前数字该加入哪个集合。
    abs(nums[x]-Max[x-1])的考虑是将所有数字成一个集合，当前元素属于唯一的另一个集合。

    Max[x]由于只用一次，可用一个int变量取代
    *     * */


    //思路错误，过早优化，如输入[3,3,3,4,5]应该为true，而不是false
    public boolean canPartition(int[] nums) {
        int[] Min=new int[nums.length+1];
        int sum=0;
        Min[0]=0;
        for(int i=0;i<nums.length;i++){
            int c_index=i+1;

            //更新Min
            Min[c_index]=Math.min(Math.abs(Min[i]-nums[i]),Math.abs(Min[i]+nums[i]));
            Min[c_index]=Math.min(Min[c_index],Math.abs(nums[i]-sum));

            //更新Max
            sum+=nums[i];
        }
        return Min[nums.length] == 0;
    }
}
