package 其他.集合转换;


import java.util.*;
import java.util.stream.Collectors;

public class 数组和list {
    public static void main(String[] args){
        arrayToList();
        listToArray();
        setToArray();
        mapTo();
    }

    class A{

    }

    public static void arrayToList(){
        int[] p=new int[10];

        //1、法1：stream流
        //List<Integer> list1=Arrays.stream(p).boxed().collect(Collectors.toList())
        Collection<Integer> c= Arrays.stream(p).boxed().collect(Collectors.toList());
        List<Integer> list1=new ArrayList<>(c);

        //2、使用asList，基本数据类型需要先包装。
        A[] a=new A[10];
        List<A> al=new ArrayList<>(Arrays.asList(a));

        //3、仅使用asList，产生的list长度不可变，不可使用remove和add。
        List<A> al2=Arrays.asList(a);
    }


    public static void listToArray(){
        List<Integer> list=new ArrayList<>(Arrays.asList(1,2,3,4,5));

        //1、对于基本数据类型需要拆箱。用stream流
        int[] x=list.stream().mapToInt(Integer::intValue).toArray();

        //2、对于对象类型，直接toArray
        //注意：list.toArray(new Integer[10])和list.toArray(new Integer[0])区别
        //      其实区别就是toArray(T[])的方法内部逻辑
        Integer[] y=list.toArray(new Integer[0]);
    }

    public static void setToArray(){
        Set<Integer> list=new HashSet<>(Arrays.asList(1,2,1,3,4,5));

        //1、stream流
        int[] intArray=list.stream().mapToInt(Integer::intValue).toArray();

        //2、toArray
        Integer[] y=list.toArray(new Integer[0]);
    }

    public static void mapTo(){
        Map<String,Integer> map=new HashMap<>(Map.of("11",2,"22",3,"33",4));
        List<Integer>values=new ArrayList<>(map.values());
        Set<String> keys=map.keySet();
    }
}
