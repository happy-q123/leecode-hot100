package 每日温度739;

import java.util.ArrayDeque;
import java.util.Deque;

public class 受启发后自己写的单调栈 {
    public static void main(String args[]){
        int[] temperatures={73,74,75,71,69,72,76,73};
        int[] result=new 受启发后自己写的单调栈().dailyTemperatures(temperatures);
        for(int i:result){
            System.out.print(i+" ");
        }
    }
    public int[] dailyTemperatures(int[] temperatures) {
        int length=temperatures.length;
        Deque<Integer> stack=new ArrayDeque<>();
        int[] result=new int[length];

        for(int i=0;i<length;i++){
            int temp=temperatures[i];
            while(!stack.isEmpty()&&temperatures[stack.getLast()]<temp){
                int topIndex=stack.pollLast();
                result[topIndex]=i-topIndex;
            }
            stack.addLast(i);
        }
        return result;
    }

}
