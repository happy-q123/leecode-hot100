package 合并k个升序链表;
import java.util.PriorityQueue;
public class gemini给的题解 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static ListNode mergeKListsOptimized(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 1. 创建优先队列（最小堆），按照节点的值 val 从小到大排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        // 2. 将所有非空链表的头节点加入队列
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        // 虚拟头节点，简化代码（省去处理 head 为 null 的逻辑）
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // 3. 循环弹出最小值，并将该节点的下一个节点入队
        while (!pq.isEmpty()) {
            // 取出当前最小的节点
            ListNode minNode = pq.poll();

            // 接入结果链表
            tail.next = minNode;
            tail = tail.next;

            // 如果被取出的节点还有下一个节点，将其加入队列
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummy.next;
    }
}
