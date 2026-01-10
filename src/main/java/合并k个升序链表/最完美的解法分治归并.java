package 合并k个升序链表;

public class 最完美的解法分治归并 {

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // 开启分治：从索引 0 到 最后一个 
        return merge(lists, 0, lists.length - 1);
    }

    // 1. 分治逻辑 (类似归并排序)
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 递归终止条件：只剩一个链表了，直接返回它
        if (left == right) {
            return lists[left];
        }

        // 找出中间位置，防止整数溢出
        int mid = left + (right - left) / 2;

        // 递归处理左半部分
        ListNode l1 = merge(lists, left, mid);
        // 递归处理右半部分
        ListNode l2 = merge(lists, mid + 1, right);

        // 2. 合并逻辑：将左右两部分的结果合并 (复用之前的逻辑)
        return mergeTwoLists(l1, l2);
    }

    // 3. 基础工具：合并两个有序链表 (双指针法)
    private ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        ListNode head = new ListNode(0);
        ListNode tail = head;

        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = (a != null ? a : b);
        return head.next;
    }
}
