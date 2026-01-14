package 有效的括号20;

import java.util.*;

public class 我的思路 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        List<Character>left=new ArrayList<>();
        left.add('(');
        left.add('[');
        left.add('{');
        List<Character>right=new ArrayList<>();
        right.add(')');
        right.add(']');
        right.add('}');
        Map<Character,Character> map=new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(left.contains(c)){
                stack.addLast(c);
            }else {
                if(map.containsKey(c)){
                    if(!stack.isEmpty()&&map.get(c)==stack.getLast()){
                        stack.pollLast();
                    }else  {
                        return false;
                    }
                }else return false;
            }
        }
        return stack.isEmpty();
    }
}
