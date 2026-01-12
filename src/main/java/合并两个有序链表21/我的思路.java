package 合并两个有序链表21;

public class 我的思路 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newHead=null;
        ListNode newCurrent=null;
        ListNode n1=list1;
        ListNode n2=list2;
        ListNode min=null;

        if(list1==null&&list2==null)
            return null;
        if(list1==null)
            return n2;
        if(list2==null)
            return n1;

        while(n1!=null&&n2!=null){
            //找出最小的
            if(n1.val<n2.val){
               min=n1;
               n1=n1.next;
            }else{
                min=n2;
                n2=n2.next;
            }

            //连接新链表
            if(newHead==null){
                newHead=min;
                newCurrent=min;
            }else {
                newCurrent.next=min;
                newCurrent=min;
            }
        }
        if(n1==null){
            min.next=n2;
        }
        if(n2==null){
            min.next=n1;
        }
        return newHead;
    }

}
