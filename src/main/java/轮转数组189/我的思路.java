package 轮转数组189;

public class 我的思路 {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        if(length == 0){
            return;
        }
        boolean[] isMove=new boolean[length];
        int moveSteps=k%length;

        //i表示每个起点。
        for(int i=0;i<moveSteps;i++){
            int current=i;
            int curentValue=nums[current];
            while(!isMove[current]){
                isMove[current]=true;
                //计算目的地index
                int destination = (current+moveSteps)%length;
                int temp = nums[destination];
                nums[destination] = curentValue;

                //更新current
                curentValue=temp;
                current=destination;
            }
        }
    }
}
