import java.util.Set;

public class CunstomSingleLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Long length;
    
    public class Node <T>{
        public T data;
        public Node<T> next;
        public Node(){

        }
        public Node(T data){
            this.data=data;
        }
    }

    /**
     * description 反转链表，但不借助length
     * author zzq
     * date 2025/12/16 14:02
     * param
     * return
     */
    public void reverse() {
        Node<T> n1 = head;
        Node<T> t = head;
        Node<T> n2 = n1.next;
        Node<T> n3;
        if (n1.next == null || n1.next.next == null) {
            //如果没有元素,或者只有一个元素，直接返回
            return;
        }
        //反转后原本第一个有效节点变成了最后一个，需要指向null
        n1 = null;
        //循环到最后一个节点终止
        while (n2.next != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //将最后一个节点的next给原来的倒数第二个。
        n2.next = n1;
        //将头节点的next给原来的最后一个。
        t.next = n2;
    }

    /**
     * description 第二个版本的reverse
     * author zzq
     * date 2025/12/16 15:25
     * param
     * return
     */
    public void reverseV2(){
        Node<T> n1 = head;
        Node<T> n2 = n1.next;
        Node<T> n3;
        Node<T> t = head;
        if(n1.next==null||n1.next.next==null){
            //如果没有元素,或者只有一个元素，直接返回
            return;
        }
        n1=null;
        while(n2!=null){
            n3=n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
        }
        t.next = n1;
    }

    /**
     * description 递归版本的reverse
     * author zzq
     * date 2025/12/16 15:25
     * param
     * return
     */
    public void reverseV3(){
        Node<T> n1 = head;
        if(n1.next==null||n1.next.next==null){
            //如果没有元素,或者只有一个元素，直接返回
            return;
        }
        reverseV3Exector(null,n1.next);
    }

    /**
     * description 递归版本的reverse执行函数
     * author zzq
     * date 2025/12/16 15:27
     * param pre为上一个节点，node为当前节点
     * return
     */
    private void reverseV3Exector(Node<T> pre, Node<T> node){
        if(node.next==null){
            head.next=node;
            node.next=pre;
            return;
        }
        reverseV3Exector(node,node.next);
        node.next=pre;
    }

    //    /**
//     * description 递归反转的第二种写法。
//     * author zzq
//     * date 2025/12/16 15:41
//     * param
//     * return
//     */
//    private void reverseV3Exector(Node<T> pre,Node<T> node){
//        if(node==null)
//            return;
//        reverseV3Exector(node,node.next);
//        if(node.next==null){
//            head.next=node;
//        }
//        node.next=pre;
//    }

    /**
     * description 单链表冒泡排序（用到了length）
     * author zzq
     * date 2025/12/17 11:46
     * param
     * return
     */
    public void sortByMaoPao(){
        if(length==0||length==1)
            //长度为0或者1，不需要排序
            return;
        Node<T> n1;
        Node<T> n2;
        Node<T> p;
        Node<T> n1Pre;
        boolean isChange;
        for(long x=0;x<length;x++){
            //每轮排序初始化
            n1=head.next;
            n1Pre=head;
            isChange=false;
            for(long t=0;t<length-x-1;t++){
                n2=n1.next;

                //如果p为null，则说明已经遍历到最后一个节点了。尽管for里的循环次数限制了null，但是则个用以兜底。
                if (n2==null)
                    break;

                //如果前一个节点比后一个节点大，则交换位置
                //如果发生交换，则n2就是原本节点的下一个节点
                if(n1.data.compareTo(n2.data)>0){
                    p=n2.next;
                    n2.next=n1;
                    n1.next=p;

                    //如果发生交换时，还需要将原n1前一个节点的next指向当前的n2（因为n1和n2交换了位置）。
                    n1Pre.next=n2;
                    n1Pre=n2;
                    isChange=true;
                }else {
                    //如果没有发生交换，则,n1Pre需要指向n1
                    n1Pre=n1;
                    //n2需要指向下一个节点
                    n1=n1.next;
                }
            }
            if (!isChange)
                break;
        }
    }

    /**
     * description 单链表冒泡排序（不用length），lastNode记录每轮的最后一个节点。
     * author zzq
     * date 2025/12/17 11:48
     * param
     * return
     */
    public void sortByMaoPaoV2(){
        if(head.next==null||head.next.next==null)
            //链表长度小于2，不需要排序
            return;

        Node<T> n1Pre;
        Node<T> n1;
        Node<T> n2;
        Node<T> p;
        boolean isChange;

        //控制总轮数
        Node<T> q=head;

        //每轮的最后一个节点
        Node<T> lastNode=null;

        while(q.next!=null){
            n1=head.next;
            n1Pre=head;
            isChange=false;
            while(n1.next!=lastNode){
                n2=n1.next;
                if(n1.data.compareTo(n2.data)>0){
                    //交换两节点位置
                    p=n2.next;
                    n2.next=n1;
                    n1.next=p;

                    //更新原n1前一个节点的指向
                    n1Pre.next=n2;
                    n1Pre=n2;
                    isChange=true;
                }else {
                    //如果没有发生交换，则,n1Pre需要指向n1
                    n1Pre=n1;
                    //n2需要指向下一个节点
                    n1=n1.next;
                }
            }
            if (!isChange)
                break;

            //更新lastNode。能出循环则说明n1.next等于lastNode，更新时直接让lastNode等于n1即可。
            lastNode=n1;
            q=q.next;
        }
    }

    /**
     * description 移除末尾第n个节。递归法（需要递归到最后一个节点，如果链表很长，则递归栈很深）
     * author zzq
     * date 2025/12/17 12:10
     * param
     * return
     */
    public boolean removeEndN(long n){
        if (n<=0)
            return false;

        Node<T> node=head;
        long result=removeEndNExecutor(node,n);
        return result==-1;
    }

    /**
     * description 移除末尾第n个节点的执行者
     * author zzq
     * date 2025/12/17 12:11
     * param
     * return
     */
    private long removeEndNExecutor(Node<T> node,long n){
        if(node.next == null)
            return 1;

        //下一个节点是倒数第nextNodeN个节点
        long nextNodeN=removeEndNExecutor(node.next,n);

        if (nextNodeN==-1)
            //说明已经删除了，不需要再动了
            return -1;

        //倒数第nextNodeN个节点等于n时
        if(nextNodeN==n){
            node.next=node.next.next;
            return -1;
        }

        return nextNodeN+1;
    }


    /**
     * description 移除倒数第n个节点，双指针法
     * （让一个指针先走n步，然后两另一个指针从头一起走。当先走的到末尾时，后走的恰好是倒数第n个节点）
     * author zzq
     * date 2025/12/17 12:30
     * param
     * return
     */
    public boolean removeEndNV2(long n){
        if (n<=0)
            return false;

        Node<T> end=head;
        Node<T> pre=head;

        //end先走n步
        for(long x=0;x<n;x++){
            if(end.next!=null)
                end=end.next;
            else
                //链表长度小于n，直接false
                return false;
        }

        //让两个指针一起走
        while(end.next!=null){
            int []x=new int['z'+1];
            end=end.next;
            pre=pre.next;
        }

        //当end到达最后一个节点时，pre就是倒数第n个节点的前一个节点
        pre.next=pre.next.next;
        return true;
    }
}
