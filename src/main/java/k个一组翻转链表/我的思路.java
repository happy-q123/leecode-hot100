package k个一组翻转链表;

public class 我的思路 {

    public static void main(String[] args) {
        // 构建链表 [1,2,3,4,5]
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        
        // 测试 k 个一组翻转链表
        int k = 1; // 举例用 k=2
        ListNode result = reverseKGroup(head, k);
        
        // 打印结果
        System.out.print("翻转后的链表: ");
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }

    public static ListNode reverse(ListNode st,ListNode end){
//        System.out.println();
        ListNode t1=st;
        ListNode t2=st.next;
        ListNode t3;
        ListNode endD=end.next;
        //k为1的情况
        if(st==end)
            return st;

        while(t2!=endD){
            t3=t2.next;
            t2.next=t1;
            t1=t2;
            t2=t3;
        }
        st.next=endD;
        ListNode result=end;
//        while (result != endD) {
//            System.out.print(result.val + " ");
//            result = result.next;
//        }
//        System.out.println();
//        System.out.println(st.next.val);
        return st;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode t=head;
        ListNode st=null;
        ListNode newHead=head;
        ListNode lastReversedFinalNode=null;
        int flag=0;
        int x=0;
        while(t!=null){
            x++;
            if(x==1){
                st=t;
            }

            if(x==k){
                ListNode finalNode=reverse(st,t);
                if(lastReversedFinalNode==null){
                    lastReversedFinalNode=finalNode;
                }else{
                    lastReversedFinalNode.next=t;
                    lastReversedFinalNode=st;
                }
                x=0;
                //如果第一次反转
                if(flag==0){
                    flag=1;
                    //t是反转前最后一个，就是反转后第一个
                    newHead=t;
                }
                t=st;
            }
            t=t.next;
        }

        //w无论是否反转，都返回newHead
        return newHead;
    }

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


}

