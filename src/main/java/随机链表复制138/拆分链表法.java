package 随机链表复制138;

public class 拆分链表法 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //该方法 空间复杂度为O(1)
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1. 复制节点并拼接： A -> B  变成  A -> A' -> B -> B'
        Node cur = head;
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next; // 移动到下一个原节点
        }

        // 2. 处理 random 指针
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                // cur.next 是新节点
                // cur.random 是原随机节点，cur.random.next 就是该随机节点的副本
                //这行代码真精彩
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next; // 跳过新节点，移动到下一个原节点
        }

        // 3. 拆分链表：恢复原链表，提取新链表
        Node newHead = head.next;
        cur = head;
        Node temp = null;

        while (cur != null) {
            temp = cur.next; // temp 指向新节点
            cur.next = temp.next; // 原节点指向下一个原节点（跳过新节点）
            if (temp.next != null) {
                temp.next = temp.next.next; // 新节点指向下一个新节点
            }
            cur = cur.next;
        }

        return newHead;
    }
}
