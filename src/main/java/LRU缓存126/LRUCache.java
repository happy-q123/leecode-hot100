package LRU缓存126;

import java.util.HashMap;
//gemini不依赖linkedhashmap的实现。该代码里的head、tail为虚拟节点，存在作用是方便操作
class LRUCache {

    // 1. 定义双向链表节点
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        public Node() {} // 默认构造
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> map;
    private int capacity;
    // 哨兵节点：伪头和伪尾
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        
        // 初始化双向链表，让 head 和 tail 互连
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部（表示最近刚用过）
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        
        if (node != null) {
            // 1. 如果 key 已存在，更新 value，并移到头部
            node.value = value;
            moveToHead(node);
        } else {
            // 2. 如果 key 不存在，创建新节点
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode); // 放到头部
            
            // 3. 检查容量，如果超了，删除尾部（最久未使用的）
            if (map.size() > capacity) {
                Node tailNode = removeTail();
                // 别忘了从 map 中也删除
                map.remove(tailNode.key);
            }
        }
    }

    // --- 下面是操作双向链表的辅助函数 (面试加分项：封装清晰) ---

    // 将节点加入到头节点之后 (成为最新的节点)
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // 删除一个节点
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 移动一个节点到头部 (相当于先删除，再添加)
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    // 删除尾部节点 (即 tail 的前一个节点)，并返回它以便在 Map 中删除
    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}