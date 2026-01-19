package 爬楼梯70;

public class 我的思路 {

    public int climbStairs(int n) {
        int power1=1;
        int power2=2;
        if(n==1)
            return 1;
        else if(n==2)
            return 2;

        int current=3;
        int last_1=power2;
        int last_2=power1;
        while(current<n){
            int c=last_2+last_1;
            last_2=last_1;
            last_1=c;
            current++;
            //表示last_1和last_2已经做好准备，可以计算第current层。
        }
        return last_1+last_2;
    }
}
