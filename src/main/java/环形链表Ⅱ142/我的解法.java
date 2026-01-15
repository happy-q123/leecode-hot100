package 环形链表Ⅱ142;

public class 我的解法 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(true){
            slow = slow.next;
            fast=fast.next;
            if(fast==null){
                return null;
            }
            fast=fast.next;
            if(fast==null){
                return null;
            }
            if(fast==slow){
                break;
            }
        }

        slow=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
}
