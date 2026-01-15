package LRU缓存126;

import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

public class MeLRUCache {
    public  HashMap<Integer,Integer>map;
    public int capacity;
    public HashMap<Integer,Integer>use_ums;
    public MeLRUCache(int capacity) {
        this.map=new HashMap<>(capacity);
        this.capacity=capacity;
        this.use_ums=new HashMap<>(capacity);
    }

    public int get(int key) {
        int result = -1;
        if(map.containsKey(key)){
            result = map.get(key);
            //更新频率
            use_ums.put(key,use_ums.get(key)+1);
        }
        return result;
    }

    //大错特错，没弄清LRU和LFU。

    public void put(int key, int value) {
        if(map.containsKey(key)||map.size()<this.capacity){
            map.put(key,value);
            if(!use_ums.containsKey(key)){
                //如果第一次新加入，就初始化为0
                use_ums.put(key,0);
            }else {
                use_ums.put(key,use_ums.get(key)+1);
            }
        }else{
            //不包含key，且容量已经满了
            //移除最小频率那个
            Set<Integer> keySet = map.keySet();
            // 建议方案
            int remove = keySet.stream()
                    .min(Comparator.comparingInt(use_ums::get)).get();
            map.remove(remove);
            use_ums.remove(remove);
            map.put(key,value);
            use_ums.put(key,0);
        }


    }
}
