package 每日温度739;

import java.util.ArrayDeque;
import java.util.Deque;

public class 我的思路2 {

    public static void main(String args[]){
        int[] temperatures={73,74,75,71,69,72,76,73};
        int[] result=new 我的思路2().dailyTemperatures(temperatures);
        for(int i:result){
            System.out.print(i+" ");
        }
    }
    /**
     * description 这次直接O(N^2)解决吧。超时
     * author zzq
     * date 2026/1/29 13:30
    */

    public int[] dailyTemperatures(int[] temperatures) {
        int length=temperatures.length;
        int []result=new int[length];

        for(int i=0;i<length;i++){
            result[i]=0;
            for(int j=i+1;j<length;j++){
                if(temperatures[j]>temperatures[i]){
                    result[i]=j-i;
                    break;
                }
            }
        }
        return result;
    }
}
