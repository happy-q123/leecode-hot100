package 数据流的中位数295;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class 我的思路 {

    /*
    * 分析
    * 读题目感觉很简单呀，直接list存储，length/2就可以得到当前元素呀。这种做法需要存储中间元素。
    * 但是MedianFinder类中并没有get元素的方法，因此题目可能要求尝试设计牛逼的算法，以达到不存储中间变量的情况。
    * 当前代码错误：
    *   忽略了使用长度除以2时的有序性
    * */
    class MedianFinder {

        private List<Integer> list;
        public MedianFinder() {
            list=new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
        }

        public double findMedian() {
            PriorityQueue<Integer> pq=new PriorityQueue<>();

            int length=list.size();
            int index=length/2;
            if(length%2!=0){
                double median=list.get(index);
                if(median-((int)median)==0){
                    BigDecimal bd = BigDecimal.valueOf(median);

                    bd = bd.setScale(1, RoundingMode.DOWN);

                    median = bd.doubleValue();
                }
                return median;
            }else{
                double median=(list.get(index-1)+list.get(index))/2.0;

                if(median-((int)median)==0){
                    BigDecimal bd = BigDecimal.valueOf(median);

                    bd = bd.setScale(1, RoundingMode.DOWN);

                    median = bd.doubleValue();
                }
                return median;
            }
        }
    }
}
