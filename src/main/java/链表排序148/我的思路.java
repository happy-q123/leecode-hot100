package 链表排序148;

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

    //冒泡排序
    public ListNode sortList(ListNode head) {
        //没有或只有一个元素的情况
        if (head == null || head.next == null) {
            return head;
        }

        ListNode x, p;
        ListNode currentLast = null;
        boolean flag = false;
        ListNode current_head = head;
        ListNode x_last = null;
        while (current_head.next != currentLast) {
            //初始化x，p
            x = current_head;
            p = x.next;
            flag = false;
            x_last = null;
            //当p为已经拍好序的第一个节点时，不进入循环。
            //也就表示这current_head永远无法与currentLast相等。
            //因此第一个while结束应该是current_head.next==currentLast，此时x=current_head，p=current_head.next，而p==current_head
            while (p != currentLast) {
                if (x.val > p.val) {
                    if (x == current_head) {
                        //说明是当前第一个要进行调换
                        current_head = p;
                    }
                    ListNode tmp = p.next;
                    p.next = x;
                    x.next = tmp;
                    flag = true;

                    if (x_last == null) {
                        x_last = p;
                    } else {
                        x_last.next = p;
                        x_last = p;
                    }
                    p = x.next;
                } else {
                    x_last=x;
                    x = p;
                    p = p.next;
                }

            }
            if (!flag) {
                break;
            }
            //更新currentLast，currentLast表示当前已经排好序的第一个数
            currentLast = x;
            // System.out.print(currentLast.val + " ");
        }
        return current_head;

    }
}
