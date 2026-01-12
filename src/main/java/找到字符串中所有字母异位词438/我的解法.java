package 找到字符串中所有字母异位词438;

import java.util.*;


public class 我的解法 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> pHashMap = new HashMap<>();
        Deque <Integer>deque=new ArrayDeque<>();
        Map<Character, Integer> currentHashMap = new HashMap<>();

        //统计p中各个字符的个数
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            int currentNum = pHashMap.getOrDefault(c, 0);
            pHashMap.put(c, currentNum + 1);
        }

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(!pHashMap.containsKey(c))
                continue;
            if(result.isEmpty()){
                deque.addLast(i);
                int currentNum = currentHashMap.getOrDefault(c, 0);
                currentHashMap.put(c, currentNum + 1);

                boolean addToResult=true;

                //判断当前map是否覆盖p的map
                for(char cx:pHashMap.keySet()){
                    if(!(currentHashMap.containsKey(cx)&&currentHashMap.get(cx)>=pHashMap.get(cx))){
                        addToResult=false;
                        break;
                    }
                }
                if (addToResult){
                    int firstIndex=deque.getFirst();
                    char c2=s.charAt(firstIndex);
                    result.add(deque.pollFirst());
                    currentNum=currentHashMap.get(c2);
                    currentHashMap.put(c2,currentNum-1);

                    char lastDeleteC=c2;

                    firstIndex=deque.getFirst();
                    c2=s.charAt(firstIndex);
                    while(currentHashMap.get(c2)>pHashMap.get(c2)&&currentHashMap.get(lastDeleteC)>=pHashMap.get(lastDeleteC)){
                        result.add(firstIndex);
                        deque.pollFirst();
                        currentNum=currentHashMap.get(c2);
                        currentHashMap.put(c2,currentNum-1);
                        lastDeleteC=c2;
                        firstIndex=deque.getFirst();
                        c2=s.charAt(firstIndex);
                    }
                    //判断当前map是否覆盖p的map
                    for(char cx:pHashMap.keySet()){
                        if(!(currentHashMap.containsKey(cx)&&currentHashMap.get(cx)>=pHashMap.get(cx))){
                            addToResult=false;
                            break;
                        }
                    }
                    if(addToResult){
                        firstIndex=deque.getFirst();
                        c2=s.charAt(firstIndex);
                        result.add(deque.pollFirst());
                        currentNum=currentHashMap.get(c2);
                        currentHashMap.put(c2,currentNum-1);
                    }
                    
                }

            }else{
                deque.addLast(i);
                int currentNum = currentHashMap.getOrDefault(c, 0);
                currentHashMap.put(c, currentNum + 1);

                boolean addToResult=true;

                //判断当前map是否覆盖p的map
                for(char cx:pHashMap.keySet()){
                    if(!(currentHashMap.containsKey(cx)&&currentHashMap.get(cx)>=pHashMap.get(cx))){
                        addToResult=false;
                        break;
                    }
                }
                if (addToResult){
                    int firstIndex=deque.getFirst();
                    char c2=s.charAt(firstIndex);
                    result.add(deque.pollFirst());
                    currentNum=currentHashMap.get(c2);
                    currentHashMap.put(c2,currentNum-1);
                    char lastDeleteC=c2;
                    firstIndex=deque.getFirst();
                    c2=s.charAt(firstIndex);
                    while(currentHashMap.get(c2)>pHashMap.get(c2)&&currentHashMap.get(lastDeleteC)>=pHashMap.get(lastDeleteC)){
                        result.add(firstIndex);
                        deque.pollFirst();
                        currentNum=currentHashMap.get(c2);
                        currentHashMap.put(c2,currentNum-1);
                        lastDeleteC=c2;
                        firstIndex=deque.getFirst();
                        c2=s.charAt(firstIndex);
                    }
                    //判断当前map是否覆盖p的map
                    for(char cx:pHashMap.keySet()){
                        if(!(currentHashMap.containsKey(cx)&&currentHashMap.get(cx)>=pHashMap.get(cx))){
                            addToResult=false;
                            break;
                        }
                    }
                    if(addToResult){
                        firstIndex=deque.getFirst();
                        c2=s.charAt(firstIndex);
                        result.add(deque.pollFirst());
                        currentNum=currentHashMap.get(c2);
                        currentHashMap.put(c2,currentNum-1);
                    }

                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        我的解法 solution = new 我的解法();
        
        // 测试用例1
        String s1 = "abab";
        String p1 = "ab";
        System.out.println("输入: s = \"" + s1 + "\", p = \"" + p1 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s1, p1));
        
        // 测试用例2
        String s2 = "cbaebabacd";
        String p2 = "abc";
        System.out.println("输入: s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s2, p2));
        
        // 测试用例3
        String s3 = "baa";
        String p3 = "aa";
        System.out.println("输入: s = \"" + s3 + "\", p = \"" + p3 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s3, p3));
        
        // 测试用例4 - 空字符串
        String s4 = "";
        String p4 = "a";
        System.out.println("输入: s = \"" + s4 + "\", p = \"" + p4 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s4, p4));
        
        // 测试用例5 - p比s长
        String s5 = "a";
        String p5 = "ab";
        System.out.println("输入: s = \"" + s5 + "\", p = \"" + p5 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s5, p5));
        
        // 测试用例6 - 相同字符串
        String s6 = "abc";
        String p6 = "abc";
        System.out.println("输入: s = \"" + s6 + "\", p = \"" + p6 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s6, p6));
        
        // 测试用例7 - 包含多个重复字符
        String s7 = "aaaaaa";
        String p7 = "aaa";
        System.out.println("输入: s = \"" + s7 + "\", p = \"" + p7 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s7, p7));
        
        // 测试用例8 - 大写字母（虽然题目通常只考虑小写）
        String s8 = "abbbbbbacccccb";
        String p8 = "abc";
        System.out.println("输入: s = \"" + s8 + "\", p = \"" + p8 + "\"");
        System.out.println("输出: " + solution.findAnagrams(s8, p8));
    }
}
