public class CustomLinkedList<T> {
    private Node<T> head;
    private Long length;

    public class Node <T>{
        public T data;
        public Node<T> next;
        public Node<T> pre;
        public Node(){

        }
        public Node(T data){
            this.data=data;
        }
    }

    public CustomLinkedList(){
        this.head= new Node<>();
        length=0L;
    }

    public void addToEnd(T data){
        //工具变量
        Node<T> n=head;

        //存储数值的新节点
        Node<T> node= new Node<>();
        node.data=data;

        //寻找当前链表最后一个节点
        while(n.next!=null){
            n=n.next;
        }

        //插入新节点
        n.next=node;
        node.pre=n;
        length++;
    }

    /**
     * description 删除链表中下标为index的节点，下标从0开始。
     * author zzq
     * date 2025/12/16 13:02
     * param
     * return
     */
    public boolean removeByIndex(Long index){
        if(index+1>length||index<0){
            //链表中不存在这样的下标
            return false;
        }
        long l=0;
        Node<T> n=head;

        //由于是带头链表，下面循环拿到的是待删除节点的前一个节点。
        while(l<index){
            l++;
            n=n.next;
        }
        n.next=n.next.next;
        if(n.next!=null)
            //如果删除的不是最后一个节点，则将下一个节点的pre给n
            n.next.pre=n;
        length--;
        return true;
    }

    public boolean contain(T value){
        Node<T> n=head;
        //由于是带头链表，下面循环拿到的是待删除节点的前一个节点。
        if(n.next!=null&&!n.next.data.equals(value)){
            n=n.next;
        }
        //没找到，就false
        return n.next != null;
    }

    public boolean removeByValue(T value){
        Node<T> n=head;
        //由于是带头链表，下面循环拿到的是待删除节点的前一个节点。
        if(n.next!=null&&!n.next.data.equals(value)){
            n=n.next;
        }
        if(n.next==null){
            //没找到，就false
            return false;
        }
        n.next=n.next.next;
        if(n.next!=null)
            //如果删除的不是最后一个节点，则将下一个节点的pre给n
            n.next.pre=n;
        length--;
        return true;
    }
    public Long getLength(){
        return length;
    }

}
