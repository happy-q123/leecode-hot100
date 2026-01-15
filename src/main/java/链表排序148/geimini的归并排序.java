package 链表排序148;

public class geimini的归并排序 {
    /**
     * 链表节点定义 (通常题目或实际开发中会给出)
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode sortList(ListNode head) {
            // 1. Base Case: 链表为空或只有一个节点，无需排序
            if (head == null || head.next == null) {
                return head;
            }

            // 2. 找中点并断开链表 (Split)
            // 使用快慢指针法
            ListNode mid = getMidAndSplit(head);

            // 3. 递归排序左右两部分 (Recurse)
            ListNode left = sortList(head);
            ListNode right = sortList(mid);

            // 4. 合并有序链表 (Merge)
            return merge(left, right);
        }

        /**
         * 辅助函数：找到链表中点，并将链表从中间切断
         * 返回右半部分的头节点
         */
        private ListNode getMidAndSplit(ListNode head) {
            ListNode slow = head;
            // 注意：fast 初始化为 head.next
            // 这是为了确保当只有 2 个节点时，slow 停在第 1 个节点，从而正确切分
            ListNode fast = head.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode mid = slow.next; // 中点（右半部分开头）
            slow.next = null;         // 【关键】切断左半部分和右半部分的连接
            return mid;
        }

        /**
         * 辅助函数：合并两个有序链表
         */
        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0); // 哨兵节点
            ListNode tail = dummy;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    tail.next = l1;
                    l1 = l1.next;
                } else {
                    tail.next = l2;
                    l2 = l2.next;
                }
                tail = tail.next;
            }

            // 接上剩余的部分
            if (l1 != null) {
                tail.next = l1;
            } else if (l2 != null) {
                tail.next = l2;
            }

            return dummy.next;
        }
    }
}
