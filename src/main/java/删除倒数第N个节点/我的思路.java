package 删除倒数第N个节点;

public class 我的思路 {
    public class ListNode {
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre=head;
        ListNode current=head;
        for(int i=0;i<n;i++){
            current=current.next;
        }
        if(current==null)
            //这种情况表示n==链表的长度，意思就是删除第一个节点
            return head.next;
        while(current.next!=null){
            current=current.next;
            pre=pre.next;
        }
        pre.next=pre.next.next;
        return head;

    }
}
