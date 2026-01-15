package LRU缓存126;
import java.util.*;

public class gemini给的 {

    public class MeLRUCache {

        // 核心修改1：直接使用 LinkedHashMap，它内部维护了双向链表
        private LinkedHashMap<Integer, Integer> map;
        private int capacity;

        public MeLRUCache(int capacity) {
            this.capacity = capacity;
            // 核心修改2：构造函数中开启 accessOrder = true
            // 参数含义：初始容量, 负载因子, accessOrder (true表示按访问顺序排序，false表示按插入顺序)
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true);
        }

        public int get(int key) {
            // LinkedHashMap 的 get 方法会自动把被访问的节点移动到链表尾部（表示最近使用）
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            // 1. 如果包含，直接 put（LinkedHashMap 会自动更新值并移到尾部）
            if (map.containsKey(key)) {
                map.put(key, value);
                return;
            }

            // 2. 如果不包含，且容量已满 -> 移除最久未使用的
            if (map.size() >= this.capacity) {
                // 在 accessOrder=true 模式下，keySet 的第一个元素就是最久未使用的 (LRU)
                int oldestKey = map.keySet().iterator().next();
                map.remove(oldestKey);
            }

            // 3. 放入新元素（自动在尾部）
            map.put(key, value);
        }
    }
}
