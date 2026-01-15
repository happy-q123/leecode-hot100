package 两两交换链表中的节点24;

public class 我的解法 {
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
    public ListNode swapPairs(ListNode head) {
        if(head==null)
            return null;
        ListNode current=head;
        ListNode hou=head.next;
        ListNode newHead=hou;
        ListNode lastHou=null;
        while(hou!=null){

            ListNode temp=hou.next;
            hou.next=current;
            current.next=temp;

            if(lastHou==null)
                lastHou=current;
            else{
                lastHou.next=hou;
                lastHou=current;
            }


            current=current.next;
            if(current==null){
                break;
            }
            hou=current.next;
        }

        if(newHead!=null)
            return newHead;
        return head;
    }
}
