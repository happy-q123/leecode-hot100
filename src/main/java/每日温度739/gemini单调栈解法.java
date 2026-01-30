package 每日温度739;

import java.util.Deque;
import java.util.LinkedList;

public class gemini单调栈解法 {
    public int[] dailyTemperatures(int[] temperatures) {
        int length=temperatures.length;
        int[] result=new int[length];

        //单调栈，存储下标
        Deque<Integer> stack=new LinkedList<>();

        for(int i=0;i<length;i++){
            //拿到今天的温度
            int currentTemp = temperatures[i];

            //栈非空，且今天温度大于栈顶温于时，就进行循环：将栈顶温度的下一天给今天。
            // 算法精妙在 从下标0开始，因此只要找到符合条件的下一天，那么他必定是最近的“下一天”，因此直接出栈。
            //栈里的温度对应的天数一顶是小于i的。所以栈存储了前几天的温度。
            while (!stack.isEmpty()&&temperatures[stack.peek()]<currentTemp){
                // 栈顶那一天找到了更好的温度，弹出栈顶温度对应的天数
                int preIndex = stack.pop();
                // 计算距离
                result[preIndex]=i-preIndex;
            }

            //将当前天数下标入栈，等待寻找它的下一个更高温度
            //当栈空，或者今天温度小于栈顶天温度时，说明今天不能成为栈顶温度的下一天，因此将今天入栈。
            stack.push(i);
        }

        //栈中剩余元素对应的result默认为0（int 数组初始化为0），无需额外处理。
        return result;
    }
}
