package 相交链表160;

public class gemini解法 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    /**
     * description 双指针
     *
     * 原理： 指针 A 走完 List A 后去走 List B；指针 B 走完 List B 后去走 List A。
     * 这样，两个指针走过的总路程都是 LenA + LenB。如果它们相交，它们最后一定会在交点相遇。
     * 个人评价：感觉就是创造了一个环，就像操场跑圈，跑得慢的和快的总会遇见。
     *
     * author zzq
     * date 2026/1/10 20:26
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pA = headA;
        ListNode pB = headB;

        // 当 pA == pB 时跳出循环（要么是交点，要么都是 null）
        while (pA != pB) {
            // pA 走到底了吗？到底了就转到 headB，否则继续走
            pA = (pA == null) ? headB : pA.next;

            // pB 走到底了吗？到底了就转到 headA，否则继续走
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }
    public static void main(String[] args) {
        gemini解法 solution = new gemini解法();

        // 创建两个相交的链表
        // 链表A:      4 -> 1 -> 8 -> 4 -> 5
        // 链表B: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        // 相交于节点8
        gemini解法.ListNode common = new gemini解法.ListNode(8);
        common.next = new gemini解法.ListNode(4);
        common.next.next = new gemini解法.ListNode(5);

        gemini解法.ListNode headA = new gemini解法.ListNode(4);
        headA.next = new gemini解法.ListNode(1);
        headA.next.next = common;

        gemini解法.ListNode headB = new gemini解法.ListNode(5);
        headB.next = new gemini解法.ListNode(6);
        headB.next.next = new gemini解法.ListNode(1);
        headB.next.next.next = common;

        gemini解法.ListNode intersection = solution.getIntersectionNode(headA, headB);
        if (intersection != null) {
            System.out.println("相交节点值: " + intersection.val);
        } else {
            System.out.println("没有相交节点");
        }

        // 测试不相交的链表
        gemini解法.ListNode listA = new gemini解法.ListNode(1);
        listA.next = new gemini解法.ListNode(2);
        listA.next.next = new gemini解法.ListNode(3);

        gemini解法.ListNode listB = new gemini解法.ListNode(4);
        listB.next = new gemini解法.ListNode(5);
        listB.next.next = new gemini解法.ListNode(6);

        gemini解法.ListNode noIntersection = solution.getIntersectionNode(listA, listB);
        if (noIntersection != null) {
            System.out.println("相交节点值: " + noIntersection.val);
        } else {
            System.out.println("没有相交节点");
        }
    }
}
