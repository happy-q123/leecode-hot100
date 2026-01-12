package 最小覆盖字串76;

import java.util.*;

public class 我的思路 {
    public static void main(String[] args) {
        我的思路 solution = new 我的思路();

//        // 测试用例1
//        String s1 = "ADOBECODEBANC";
//        String t1 = "ABC";
//        System.out.println("输入: s = \"" + s1 + "\", t = \"" + t1 + "\"");
//        System.out.println("输出: \"" + solution.minWindow(s1, t1) + "\"");
//        System.out.println();
//
//        // 测试用例2
//        String s2 = "a";
//        String t2 = "a";
//        System.out.println("输入: s = \"" + s2 + "\", t = \"" + t2 + "\"");
//        System.out.println("输出: \"" + solution.minWindow(s2, t2) + "\"");
//        System.out.println();
//
//        // 测试用例3
//        String s3 = "a";
//        String t3 = "aa";
//        System.out.println("输入: s = \"" + s3 + "\", t = \"" + t3 + "\"");
//        System.out.println("输出: \"" + solution.minWindow(s3, t3) + "\"");
//        System.out.println();
//
//        // 测试用例4
//        String s4 = "ab";
//        String t4 = "b";
//        System.out.println("输入: s = \"" + s4 + "\", t = \"" + t4 + "\"");
//        System.out.println("输出: \"" + solution.minWindow(s4, t4) + "\"");

        // 测试用例4
        String s5 = "cbabcabba";
        String t5 = "acab";
        System.out.println("输入: s = \"" + s5 + "\", t = \"" + t5 + "\"");
        System.out.println("输出: \"" + solution.minWindow(s5, t5) + "\"");
    }
    public String minWindow(String s, String t) {
        String result=null;

        //出队元素下标
        Integer headOut=null;
        Map<Character,Integer> tHashMap=new HashMap<>();
        Map<Character,Integer> rHashMap=new HashMap<>();

        //其实也可以用char['a']=元素个数、char['b']=元素个数，查找时间也是o(1),但完全比对需要对比o(asic码长度)次数。
        //将t的元素存储在hashmap，用于后续方便判断字符是否在t中，以及队列中相应字符个数是否满足t
        for(int i=0;i<t.length();i++){
            Integer currentNum=tHashMap.getOrDefault(t.charAt(i),0);
            currentNum++;
            tHashMap.put(t.charAt(i),currentNum);
        }


        //队列，存储元素下标
        Deque<Integer> deque=new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            //若map包含这个key,直接入队
            if(tHashMap.containsKey(s.charAt(i))){
                //第一次入队的情况,也可以判断headOut==null
                if(result==null){
                    deque.addLast(i);

                    //更新map中元素个数。那么，第一次覆盖字串的判断就是map中所有key的value为0
                    Integer currentNum=rHashMap.getOrDefault(s.charAt(i),0);
                    currentNum++;
                    rHashMap.put(s.charAt(i),currentNum);
//
//                    if(deque.size()>1&&s.charAt(i)==s.charAt(deque.getFirst())){
//                        while(!deque.isEmpty()&&rHashMap.get(s.charAt(i))>tHashMap.get(s.charAt(i))){
//
//                            int delete=deque.removeFirst();
//                            rHashMap.put(s.charAt(delete),rHashMap.get(s.charAt(delete))-1);
//                        }
//                    }


                    int flag=0;
                    //比较两map 的各字符数量
                    for (Character key : tHashMap.keySet()) {
                        //这里要用大于号，因为rmap可能包含重复字符。
                        if(rHashMap.containsKey(key)){
                            if(tHashMap.get(key)>rHashMap.get(key)){
                                //flag为1不合格
                                flag=1;
                                break;
                            }
                        }else {
                            flag=1;
                            break;
                        }
                    }

                    //说明是覆盖字串
                    if(flag==0){
                        result=s.substring(deque.getFirst(),deque.getLast()+1);

                        while(!deque.isEmpty()){
                            int x=0;
                            int first=deque.getFirst();
                            if(rHashMap.get(s.charAt(first))>tHashMap.get(s.charAt(first))){
                                int delete=deque.removeFirst();
                                rHashMap.put(s.charAt(delete),rHashMap.get(s.charAt(delete))-1);
                                x=1;
                            }
                            if(x==0)
                                break;
                        }
                        flag=0;
                        //比较两map 的各字符数量
                        for (Character key : tHashMap.keySet()) {
                            //这里要用大于号，因为rmap可能包含重复字符。
                            if(rHashMap.containsKey(key)){
                                if(tHashMap.get(key)>rHashMap.get(key)){
                                    //flag为1不合格
                                    flag=1;
                                    break;
                                }
                            }else {
                                flag=1;
                                break;
                            }
                        }
                        if(flag==0){
                            String newResult=s.substring(deque.getFirst(),deque.getLast()+1);
                            if(result.length()>newResult.length()){
                                result=newResult;
                            }
                        }
                    }
                }else {
                    //map包含，但是不是第一次找覆盖串

                    deque.addLast(i);
                    rHashMap.put(s.charAt(i),rHashMap.get(s.charAt(i))+1);

                    //新加入元素后，直接让队头出队直到找到该元素或者rmap中个数小于等于tmap中个数。因为队列只存储包含在t串中的字符
                    //当队尾有新的时，队前的相同元素全部去掉即可。只有这样找到的下一个串才能比当前result的长度短。
                    while(!deque.isEmpty()){
                        int x=0;
                        int first=deque.getFirst();
                        if(rHashMap.get(s.charAt(first))>tHashMap.get(s.charAt(first))){
                            int delete=deque.removeFirst();
                            rHashMap.put(s.charAt(delete),rHashMap.get(s.charAt(delete))-1);
                            x=1;
                        }
                        if(x==0)
                            break;
                    }
                    int flag=0;
                    //比较两map 的各字符数量
                    for (Character key : tHashMap.keySet()) {
                        //这里要用大于号，因为rmap可能包含重复字符。
                        if(rHashMap.containsKey(key)){
                            if(tHashMap.get(key)>rHashMap.get(key)){
                                //flag为1不合格
                                flag=1;
                                break;
                            }
                        }else {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0){
                        String newResult=s.substring(deque.getFirst(),deque.getLast()+1);
                        if(result.length()>newResult.length()){
                            result=newResult;
                        }
                    }

                }
            }
        }
        if (result==null){
            result="";
        }
        return result;
    }
//    public String minWindow(String s, String t) {
//        String result=null;
//
//        //出队元素下标
//        Integer headOut=null;
//        Map<Character,Integer> tHashMap=new HashMap<>();
//        //其实也可以用char['a']=元素个数、char['b']=元素个数，查找时间也是o(1),但完全比对需要对比o(asic码长度)次数。
//        //将t的元素存储在hashmap，用于后续方便判断字符是否在t中，以及队列中相应字符个数是否满足t
//        for(int i=0;i<t.length();i++){
//            Integer currentNum=tHashMap.getOrDefault(t.charAt(i),0);
//            currentNum++;
//            tHashMap.put(t.charAt(i),currentNum);
//        }
//
//
//        //队列，存储元素下标
//        Deque<Integer> deque=new ArrayDeque<>();
//        for(int i=0;i<s.length();i++){
//            //若map包含这个key,直接入队
//            if(tHashMap.containsKey(s.charAt(i))){
//                //第一次入队的情况,也可以判断headOut==null
//                if(result==null){
//                    deque.addLast(i);
//
//                    //更新map中元素个数。那么，第一次覆盖字串的判断就是map中所有key的value为0
//                    Integer currentNum=tHashMap.get(s.charAt(i));
//                    currentNum--;
//                    tHashMap.put(s.charAt(i),currentNum);
//
//                    int flag=0;
//                    for (Character key : tHashMap.keySet()) {
//                        if(tHashMap.get(key)!=0){
//                            flag=1;
//                            break;
//                        }
//                    }
//
//                    //说明是覆盖字串
//                    if(flag==0){
//                        result=s.substring(deque.getFirst(),deque.getLast()+1);
//                        headOut=deque.poll();
//                        if(!deque.isEmpty()){
//                            //如果队头不被map 的key包含，即不在t中，则一直出队。
//                            while(!tHashMap.containsKey(s.charAt(deque.getFirst()))){
//                                deque.removeFirst();
//                            }
//                        }
//                    }
//                }else {
//                    //map包含，但是不是第一次找覆盖串
//                    deque.addLast(i);
//                    //如果新来的恰好是出队的
//                    if(s.charAt(i)==s.charAt(headOut)){
//
//                        String newResult=s.substring(deque.getFirst(),deque.getLast()+1);
//                        if(result.length()>newResult.length()){
//                            result=newResult;
//                        }
//                        headOut=deque.poll();
//                        if(!deque.isEmpty()){
//                            //如果队头不被map 的key包含，即不在t中，则一直出队。
//                            while(!tHashMap.containsKey(s.charAt(deque.getFirst()))){
//                                deque.removeFirst();
//                            }
//                        }
//                    }
//
//                }
//
//
//            }else {
//                //若map不包含这个key，如果队头有元素，就进去。
//                if(!deque.isEmpty()){
//                    deque.addLast(i);
//                }
//            }
//        }
//        if (result==null){
//            result="";
//        }
//        return result;
//    }

}
