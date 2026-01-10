package 相交链表160;

public class 我的解法 {


     public static class ListNode {
     int val;
     ListNode next;

     ListNode(int x) {
         val = x;
         next = null;
     }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode x1=headA,x2=headB;
        while(x1!=null&&x2!=null){
            x1= x1.next;
            x2=x2.next;
        }
        ListNode newh=null;
        ListNode remain=null;
        ListNode lastHead=null;
        if(x1==null&&x2!=null){
            remain=x2;
            //长链表的头
            newh=headB;
            //短链表的头
            lastHead=headA;
        }else if(x2==null&&x1!=null){
            remain=x1;
            //长链表的头
            newh=headA;
            //短链表的头
            lastHead=headB;
        }else{
            //当两链表长度一样时
            remain=null;
            //长链表的头
            newh=headA;
            //短链表的头
            lastHead=headB;
        }
        //再存一次短链表的头
        ListNode duan=lastHead;
        while(remain!=null){
            remain=remain.next;
            newh=newh.next;
        }
        //进行到此处说明两链表尾部已经对齐
        ListNode latestNoSameNode=null;
        while(newh!=null&&lastHead!=null){
            if(newh!=lastHead){
                latestNoSameNode=newh;
            }
            newh=newh.next;
            lastHead=lastHead.next;
        }
        if (latestNoSameNode==null){
            //说明对齐后全部相等
            return duan;
        }
        return latestNoSameNode.next;
    }
    
    public static void main(String[] args) {
        我的解法 solution = new 我的解法();
        
        // 创建两个相交的链表
        // 链表A:      4 -> 1 -> 8 -> 4 -> 5
        // 链表B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        // 相交于节点8
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);
        
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = common;
        
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = common;
        
        ListNode intersection = solution.getIntersectionNode(headA, headB);
        if (intersection != null) {
            System.out.println("相交节点值: " + intersection.val);
        } else {
            System.out.println("没有相交节点");
        }
        
        // 测试不相交的链表
        ListNode listA = new ListNode(1);
        listA.next = new ListNode(2);
        listA.next.next = new ListNode(3);
        
        ListNode listB = new ListNode(4);
        listB.next = new ListNode(5);
        listB.next.next = new ListNode(6);
        
        ListNode noIntersection = solution.getIntersectionNode(listA, listB);
        if (noIntersection != null) {
            System.out.println("相交节点值: " + noIntersection.val);
        } else {
            System.out.println("没有相交节点");
        }
    }
}
