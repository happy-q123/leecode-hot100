package 反转链表206;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class 我的思路 {

     public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    /**
     * description 递归
     * author zzq
     * date 2026/1/12 16:28
     */
    public ListNode reverseList(ListNode head) {
        if(head==null)
            return null;

        head=diGui(null,head);
        return head;
    }

    //最终返回反转后的新head
    public ListNode diGui(ListNode pre,ListNode current){
        if(current!=null){
            ListNode newHead=current;
            if(current.next!=null){
                newHead=diGui(current,current.next);
            }
            current.next=pre;
            return newHead;
        }else{
            return current;
        }
    }
//     /**
//      * description 非递归
//      * author zzq
//      * date 2026/1/12 16:28
//      */
//    public ListNode reverseList(ListNode head) {
//         if(head==null)
//             return null;
//
//         ListNode p=head;
//         ListNode mid=head.next;
//         ListNode x;
//         while(mid!=null){
//             x=mid.next;
//             mid.next=p;
//             p=mid;
//             mid=x;
//         }
//         head.next=null;
//         head=p;
//         return head;
//    }
}
