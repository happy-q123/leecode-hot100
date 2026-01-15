package 环形链表141;

public class 我的解法 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(true){
            slow = slow.next;
            fast=fast.next;
            if(fast==null){
                return false;
            }
            fast=fast.next;
            if(fast==null){
                return false;
            }
            if(fast==slow){
                return true;
            }
        }
    }
}
