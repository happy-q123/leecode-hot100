package 每日温度739;

import java.util.ArrayDeque;
import java.util.Deque;

public class 我的思路 {

    public static void main(String args[]){
        int[] temperatures={73,74,75,71,69,72,76,73};
        int[] result=new 我的思路().dailyTemperatures(temperatures);
        for(int i:result){
            System.out.print(i+" ");
        }
    }
    /**
     * description 失败
     * author zzq
     * date 2026/1/28 22:26
    */

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            stack.addLast(temperatures[i]);
        }
        int[] result=new int[temperatures.length];
        Integer current=null;
        int currentIndex = temperatures.length-1;
        int topIndex = temperatures.length;
        Integer top=null;

        while (!stack.isEmpty()) {
            topIndex--;
            top = stack.pollLast();
            if(current==null||current <= top){
                current=top;
                currentIndex=topIndex;
                result[topIndex]= 0;
            }else {
                result[topIndex]=currentIndex-topIndex;
                current=top;
                currentIndex=topIndex;
            }
        }
        return result;
    }
}
