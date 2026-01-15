package LRU缓存126;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
//代码里的head、tail不是虚拟节点，手动维护。
//其实变成虚拟节点时，代码更容易维护
public class 根据geimin给的思路写出的不依赖linkedhashmap的实现 {
     private class Node{
        int key;
        int value;
        Node next;
        Node pre;
        Node(int key, int value, Node pre, Node next){
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    private HashMap<Integer,Node> map;
    private Node head=null;
    private Node tail=null;
    private int capacity;
    public 根据geimin给的思路写出的不依赖linkedhashmap的实现(int capacity) {
        this.map=new HashMap<>(capacity);
        this.capacity=capacity;
    }

    public int get(int key) {
        Node result=map.get(key);
        if(result==null){
            return -1;
        }else {
            head=moveToHead(result);
            return result.value;
        }

    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node result=map.get(key);
            result.value=value;
            map.put(key,result);

            //跟新使用
            moveToHead(result);
            return;
        }

        if(map.size()>=capacity){
            //满了就删除一个
            map.remove(tail.key);
            removeTail();
        }
        Node n=new Node(key,value,null,null);
        map.put(key,n);
        addToHead(n);
    }

    //把某个节点移动到头部（表示最近使用）
    public Node moveToHead(Node node){
        //已经是首节点
        if(node.pre==null){
            return node;
        }

        //删除节点
        node.pre.next = node.next;
        if(node.next!=null){
            node.next.pre = node.pre;
        }else{
            //node.next为null，说明其是最后一个节点，需要更新tail
            tail=node.pre;
        }

        //插到头部
        node.next=head;
        node.pre=null;
        head.pre=node;

        head=node;
        return node;
    }

    public Node addToHead(Node node){
        if (head == null) {
            head = node;
            tail = node; // 必须同时更新 tail！
        } else {
            node.next = head;
            head.pre = node; // 别忘了反向指针
            head = node;
        }
        return head;
    }

    //返回新的尾部
    public Node removeTail(){
        //如果只有一个元素
        if(tail.pre==null){
            head=null;
            tail=null;
            return null;
        }

        tail.pre.next = null;

        tail=tail.pre;
        return tail;
    }



}
