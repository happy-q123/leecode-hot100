package 数据流的中位数295;

import java.util.PriorityQueue;

public class Gemini超绝解法 {
    class MedianFinder {
        // 存放较小的一半，大顶堆
        private PriorityQueue<Integer> leftHeap;
        // 存放较大的一半，小顶堆 (默认)
        private PriorityQueue<Integer> rightHeap;

        public MedianFinder() {
            // 左边是大顶堆 (b - a)
            leftHeap = new PriorityQueue<>((a, b) -> b - a);
            // 右边是小顶堆 (默认)
            rightHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // 1. 先放入左边
            leftHeap.add(num);

            // 2. 此时左边最大的可能比右边最小的还大，不符合有序，
            //    所以把左边最大的拿出来，扔给右边
            rightHeap.add(leftHeap.poll());

            // 3. 平衡数量：如果右边比左边多，就把右边最小的拿回左边
            //    (我们要保证 leftHeap 的数量 >= rightHeap)
            if (rightHeap.size() > leftHeap.size()) {
                leftHeap.add(rightHeap.poll());
            }
        }

        public double findMedian() {
            // 如果左边比右边多 1 个，中位数就是左边堆顶
            if (leftHeap.size() > rightHeap.size()) {
                return leftHeap.peek();
            }
            // 否则是偶数个，取两个堆顶的平均值
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }
    }
}
