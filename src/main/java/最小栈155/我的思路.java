package 最小栈155;

import java.util.*;

public class 我的思路 {
    class MinStack {

        //用于存放数据
        private Deque<Integer> stack;
        //Dmin[x]表示前x个数中最小的那个
        private List<Integer> Dmin;

        public MinStack() {
            stack = new ArrayDeque<>();
            Dmin = new ArrayList<>();
        }

        public void push(int val) {
            //stack只管加入就行
            stack.push(val);

            //Dmin需要维护
            if(Dmin.isEmpty()){
                Dmin.add(val);
            }else {
                int min = Dmin.getLast();
                //当前值val与前Dmin.getLast()个值的最小值比较，得到加入val后的最小值。
                int insert = Math.min(min, val);
                Dmin.add(insert);
            }
        }

        public void pop() {
            if(!stack.isEmpty() && !Dmin.isEmpty()){
                stack.pop();
                Dmin.remove(Dmin.size()-1);
            }
        }

        public int top() {
            if(!stack.isEmpty()){
                return stack.peek();
            }
            return -1;
        }

        public int getMin() {
            return Dmin.getLast();
        }
    }
}
