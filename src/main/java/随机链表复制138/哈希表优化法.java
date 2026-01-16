package 随机链表复制138;

import java.util.HashMap;
import java.util.Map;

public class 哈希表优化法 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
        //该方法空间复杂度为O(N)，与我的方法一样
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            // Map: 原节点 -> 新节点
            Map<Node, Node> map = new HashMap<>();

            Node cur = head;

            // 第一轮遍历：创建新节点，并建立映射关系
            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }

            cur = head;

            // 第二轮遍历：连接 next 和 random 指针
            while (cur != null) {
                Node newNode = map.get(cur);
                // 直接从 map 中获取对应的 next 和 random 新节点
                newNode.next = map.get(cur.next);
                newNode.random = map.get(cur.random);
                cur = cur.next;
            }

            return map.get(head);
        }
    }
    
}
