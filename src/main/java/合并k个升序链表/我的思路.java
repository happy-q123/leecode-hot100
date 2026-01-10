package 合并k个升序链表;

public class 我的思路 {

    public static void main(String[] args) {
        // 创建测试用例 [[1,4,5],[1,3,4],[2,6]]
        ListNode node1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode node2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode node3 = new ListNode(2, new ListNode(6));
        
        ListNode[] lists = {node1, node2, node3};
        
        ListNode result = mergeKLists(lists);
        
        // 打印结果
        System.out.print("合并结果: ");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) {
                System.out.print("->");
            }
            result = result.next;
        }
        System.out.println();
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

    //缺点：时间击败5%，有点差。
    //缺点原因：每次在lists对所有第一个节点循环时，拿到的是最小的一个节点。
    //能否每次选择多个节点呢？
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode[] currentNode=lists;

        //新链表头节点
        ListNode newListHead=null;
        //新链表最后一个节点
        ListNode newListLast=null;

        //当前最值小节点
        ListNode currentMinNode=null;
        int currentMinIndex=-1;
        while(true){
            currentMinNode=null;
            currentMinIndex=-1;
            //选出一个最小的节点。
            for(int i=0;i<lists.length;i++){
                if(currentMinNode==null&&currentNode[i]!=null){
                    currentMinNode=currentNode[i];
                    currentMinIndex=i;
                }
                else{
                    if(currentNode[i]!=null&&currentMinNode.val>currentNode[i].val){
                        currentMinNode=currentNode[i];
                        currentMinIndex=i;
                    }
                }
            }

            if(currentMinNode==null){
                if(newListLast!=null)
                    newListLast.next=null;
                //说明所有节点全部擦插入到新链表完毕
                break;
            }

            if(newListHead==null){
                //首次插入
                newListHead=currentMinNode;
                newListLast=currentMinNode;
            }else{
                newListLast.next=currentMinNode;
                newListLast=currentMinNode;
            }
            currentNode[currentMinIndex]=currentNode[currentMinIndex].next;
        }
        return newListHead;
    }
}
