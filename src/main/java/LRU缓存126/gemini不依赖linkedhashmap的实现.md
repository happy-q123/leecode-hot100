面试官问 LRU 算法 (LeetCode 146题)，通常有**两个考察点**：

1. **能不能做出来**：逻辑是对的。
2. **能不能手写底层结构**：面试官通常**禁止**使用 Java 自带的 `LinkedHashMap`，因为那就太简单了，体现不出数据结构功底。你需要自己用 **哈希表 + 双向链表** 来实现。

### 面试标准答案 (哈希表 + 双向链表)

这是一个非常经典的 **$O(1)$** 复杂度解法。

#### 核心思路

1. **HashMap**：用来存储 `Key -> Node` 的映射，保证 `get` 查找是 $O(1)$。
2. **双向链表 (Double Linked List)**：用来维护顺序。
   - **最新被访问的**（Get 或 Put），移动到 **链表头**。
   - **最久没用的**（LRU），自然就会排在 **链表尾**。
   - 当容量满了，直接删除链表尾部的节点。

为了写代码不把自己绕晕，**最重要的技巧**是使用两个**哨兵节点（伪头 head 和 伪尾 tail）**，这样添加和删除节点时永远不用判断 `null`，代码会极其清爽。

#### Java 代码实现



```Java
import java.util.HashMap;

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
```

### 面试时如何讲解代码（得分点）

如果你在面试中写出这段代码，一定要顺带说出以下几点，面试官会非常满意：

1. **为什么用双向链表？**
   - 因为我们需要删除节点。虽然单向链表添加很快，但**删除**时需要找到前驱节点，单链表只能从头遍历，是 $O(N)$。而双向链表可以直接获取 `node.prev`，实现 **$O(1)$** 删除。
2. **为什么 Node 里要存 Key？**
   - 注意看 `removeTail()` 那里。当链表满了需要淘汰尾部节点时，我们拿到了那个 `Node` 对象。我们不仅要从链表里删掉它，还要从 `HashMap` 里删掉它。
   - 如果我们只存 `Value`，就不知道这个 `Node` 对应的 `HashMap Key` 是什么，就没法删 Map 了。
3. **为什么用伪头和伪尾？**
   - 这是工程上的常见优化（Boundary Sentinel）。有了它们，我们在 `addToHead` 或 `removeNode` 时，永远不用写 `if (head == null)` 这种边界判断逻辑，代码不容易出现空指针异常 (NPE)。

### 记忆口诀

面试写 LRU，记住：**"Map查，链表排，头尾哨兵防意外。"**

- Map 负责快速找人 ($O(1)$)。
- 链表负责排队（谁刚来谁站第一排，最后那个人淘汰）。
- 两个哨兵负责让你不写 Bug。