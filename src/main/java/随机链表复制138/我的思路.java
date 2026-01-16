package 随机链表复制138;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 我的思路 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //虽然能通过，但是使用了额外空间
    //尝试想一想如何在O(1)空间中完成。
    public Node copyRandomList(Node head) {
        if(head==null)
            return null;
        List<Node> nodes = new ArrayList<>();
        Map<Node, Integer> map = new HashMap<>();
        Node t=head;
        Node newHead=null;
        Node new_c=null;
        int i=0;
        while (t!=null){
            map.put(t,i);
            if(newHead==null){
                //创建头节点，复制val，以及random，next在后续建立过程被覆盖
                newHead=new Node(t.val);
                nodes.add(newHead);
                new_c=newHead;
            }else {
                //在尾部插入节点
                new_c.next=new Node(t.val);
                new_c=new_c.next;
                nodes.add(new_c);
            }
            i++;
            t=t.next;
        }
        t=head;
        i=0;
        while(t!=null){
            Node random=t.random;
            if(random==null){
                nodes.get(i).random=null;
            }else {
                int index=map.get(random);
                nodes.get(i).random=nodes.get(index);
            }
            t=t.next;
            i++;
        }
        return newHead;
    }
}
