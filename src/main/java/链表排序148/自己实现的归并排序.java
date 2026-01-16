package 链表排序148;

public class 自己实现的归并排序 {
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

    //分和治。
    //分就是要断链，治就是要把断的链再连接起来。
    public ListNode sortList(ListNode head) {
        //为空或只有一个的情况
        if(head==null || head.next==null){
            return head;
        }
        ListNode middle=findMiddle(head);
        ListNode m_n=middle.next;
        middle.next=null;
        ListNode left_head=sort(head);
        ListNode right_head=null;
        if(m_n!=null){
            right_head=sort(m_n);
        }
        return merge(left_head,right_head);

    }

    //返回排序好子序列的新头
    private ListNode sort(ListNode st){
        ListNode middle=findMiddle(st);
        if(st==middle)
            return st;

//        if(st.next==middle){
        if(st.next==middle&&middle.next==null){
            if(st.val>middle.val){
                ListNode tmp = null;
                middle.next = st;
                st.next=tmp;
                return middle;
            }
            return st;
        }
        ListNode m_n=middle.next;
        middle.next=null;
        ListNode left_head=sort(st);
        ListNode right_head=sort(m_n);
        return merge(left_head,right_head);
    }


    //寻找中点，只有一个节点时，返回节点本身
    //只有两个节点时，返回最后一个节点
    private ListNode findMiddle(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        ListNode slow=head;
        ListNode fast=head;

        while(fast.next!=null){
            slow=slow.next;
            fast=fast.next;
            if(fast.next!=null){
                fast=fast.next;
            }
        }
        return slow;
    }

    //返回新的head
    private ListNode merge(ListNode left_head,ListNode right_head){
        //合并两个子序列
        ListNode new_head=null;
        ListNode new_n=null;
        ListNode l=left_head;
        ListNode r=right_head;

        while(l!=null&&r!=null){
            ListNode temp=null;
            if(l.val < r.val){
                temp=l;
                l=l.next;
            }else{
                temp=r;
                r=r.next;
            }
            if(new_head==null){
                new_head=temp;
                new_n=temp;
            }else {
                new_n.next=temp;
                new_n=temp;
            }
        }

        if (new_n==null){
            if(l!=null){
                new_n=l;
            }else if(r!=null){
                new_n=r;
            }
            return new_n;
        }

        if (l != null) {
            new_n.next = l;
        } else {
            new_n.next = r;
        }
        return new_head;
    }
}
