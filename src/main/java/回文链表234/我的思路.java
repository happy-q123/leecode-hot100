package 回文链表234;

import java.util.List;

public class 我的思路 {

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    public boolean isPalindrome(ListNode head) {
         if(head.next==null)
             return true;
         ListNode pre=head;
         ListNode current=head;
         //快慢指针。current为null时，pre为回文串后串的第一个节点。
         while(current!=null){
             pre=pre.next;
             if(current.next!=null){
                 current=current.next.next;
             }else {
                 //说明是奇数链表，直接出去即可，此时pre已经到达中间。
                 current=null;
             }
         }
         //此时pre为回文串后串的第一个。
         //逆置后串
         ListNode x1=pre;
         ListNode x2=x1.next;
         ListNode t;
         while(x2!=null){
             t=x2.next;
             x2.next=x1;
             x1=x2;
             x2=t;
         }
         pre.next=null;

         t=head;
         while(x1!=null){
             if(x1.val!=t.val){
                 return false;
             }
             x1=x1.next;
             t=t.next;
         }
         return true;
    }
}
