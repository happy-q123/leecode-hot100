package 合并区间56;

import java.util.*;
import java.util.stream.Collectors;

public class 我的思路 {

    public static void main(String[] args) {
        我的思路 solution = new 我的思路();

//        // 测试用例1
//        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
//        System.out.println("输入: " + Arrays.deepToString(intervals1));
//        int[][] result1 = solution.merge(intervals1);
//        System.out.println("输出: " + Arrays.deepToString(result1));
//        System.out.println();
//
//        // 测试用例2
//        int[][] intervals2 = {{1,4},{4,5}};
//        System.out.println("输入: " + Arrays.deepToString(intervals2));
//        int[][] result2 = solution.merge(intervals2);
//        System.out.println("输出: " + Arrays.deepToString(result2));
//        System.out.println();
//
//        // 测试用例3 - 空数组
//        int[][] intervals3 = {};
//        System.out.println("输入: " + Arrays.deepToString(intervals3));
//        int[][] result3 = solution.merge(intervals3);
//        System.out.println("输出: " + Arrays.deepToString(result3));
//        System.out.println();
//
//        // 测试用例4 - 单个区间
//        int[][] intervals4 = {{1,4}};
//        System.out.println("输入: " + Arrays.deepToString(intervals4));
//        int[][] result4 = solution.merge(intervals4);
//        System.out.println("输出: " + Arrays.deepToString(result4));
//        System.out.println();
//
//        // 测试用例5 - 需要排序的情况
//        int[][] intervals5 = {{2,3},{4,5},{1,10}};
//        System.out.println("输入: " + Arrays.deepToString(intervals5));
//        int[][] result5 = solution.merge(intervals5);
//        System.out.println("输出: " + Arrays.deepToString(result5));
//        System.out.println();

        // 测试用例6 - 包含重复和重叠区间
        int[][] intervals6 = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        System.out.println("输入: " + Arrays.deepToString(intervals6));
        int[][] result6 = solution.merge(intervals6);
        System.out.println("输出: " + Arrays.deepToString(result6));
        System.out.println();
    }
    //版本2，在最开始，对intervals按照st进行排序。解决版本1的问题。
    public int[][] merge(int[][] intervals) {

        if(intervals.length==0){
            return intervals;
        }
        //Comparator.comparingInt(a -> a[0])从小到达排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<List<Integer>> resultList=new ArrayList<>();

        List<Integer> firstList=new ArrayList<>();
        firstList.add(intervals[0][0]);
        firstList.add(intervals[0][1]);
        resultList.add(firstList);

        for(int i=1;i<intervals.length;i++){
            for(int x=0;x<resultList.size();x++){
                int stFromResultList=resultList.get(x).get(0);
                int endFromResultList=resultList.get(x).get(1);
                //能合并的情况有两种：相交、包含。相交、相交的必要条件是 较大的st，要小于较小的st区间的end。

                int max_st,max_end;
                int min_st,min_end;
                //寻找较大的st区间
                if(intervals[i][0]>stFromResultList){
                    max_st=intervals[i][0];
                    max_end=intervals[i][1];
                    min_st=stFromResultList;
                    min_end=endFromResultList;
                }else {
                    max_st=stFromResultList;
                    max_end=endFromResultList;
                    min_st=intervals[i][0];
                    min_end=intervals[i][1];
                }

                if(max_st<=min_end){
                    //能合并
                    int new_end=Math.max(max_end,min_end);
                    int new_st=Math.min(min_st,max_st);
                    resultList.get(x).set(0,new_st);
                    resultList.get(x).set(1,new_end);
                }else {
                    if(x==resultList.size()-1){
                        //与result存在的所有区间都不能合并，就新加入result区间中
                        List<Integer> p=new ArrayList<>();
                        p.add(intervals[i][0]);
                        p.add(intervals[i][1]);
                        resultList.add(p);
                    }
                }
            }
        }
//        // 在你的代码末尾
//        int[][] result = new int[resultList.size()][2];
//        for (int i = 0; i < resultList.size(); i++) {
//            result[i][0] = resultList.get(i).get(0);
//            result[i][1] = resultList.get(i).get(1);
//        }
//        return result;
        return resultList.stream()
                .map(list -> new int[]{list.get(0), list.get(1)})
                .toArray(int[][]::new);

    }
//    //版本1，在测试用例5时会出问题，当前区间可以和 resultList中多个区间进行合并，导致最终resultList的区间仍然可以合并
//    //解决思路1：在最开始，对intervals按照st进行排序。
//    //解决思路2：在算法末尾，对resultList排序，再合并。
//    //思路1更彻底。
//    public int[][] merge(int[][] intervals) {
//        List<List<Integer>> resultList=new ArrayList<>();
//
//        if(intervals.length==0){
//            return intervals;
//        }
//        List<Integer> firstList=new ArrayList<>();
//        firstList.add(intervals[0][0]);
//        firstList.add(intervals[0][1]);
//        resultList.add(firstList);
//
//        for(int i=1;i<intervals.length;i++){
//            boolean needAdd=false;
//            for(int x=0;x<resultList.size();x++){
//                int stFromResultList=resultList.get(x).get(0);
//                int endFromResultList=resultList.get(x).get(1);
//                //能合并的情况有两种：相交、包含。相交、相交的必要条件是 较大的st，要小于较小的st区间的end。
//
//                int max_st,max_end;
//                int min_st,min_end;
//                //寻找较大的st区间
//                if(intervals[i][0]>stFromResultList){
//                    max_st=intervals[i][0];
//                    max_end=intervals[i][1];
//                    min_st=stFromResultList;
//                    min_end=endFromResultList;
//                }else {
//                    max_st=stFromResultList;
//                    max_end=endFromResultList;
//                    min_st=intervals[i][0];
//                    min_end=intervals[i][1];
//                }
//
//                if(max_st<=min_end){
//                    //能合并
//                    int new_end=Math.max(max_end,min_end);
//                    int new_st=Math.min(min_st,max_st);
//                    resultList.get(x).set(0,new_st);
//                    resultList.get(x).set(1,new_end);
//                }else {
//                   needAdd=true;
//                }
//            }
//            if(needAdd){
//                //与result存在的所有区间都不能合并，就新加入result区间中
//                List<Integer> p=new ArrayList<>();
//                p.add(intervals[i][0]);
//                p.add(intervals[i][1]);
//                resultList.add(p);
//            }
//        }
//
////        // 在你的代码末尾
////        int[][] result = new int[resultList.size()][2];
////        for (int i = 0; i < resultList.size(); i++) {
////            result[i][0] = resultList.get(i).get(0);
////            result[i][1] = resultList.get(i).get(1);
////        }
////        return result;
//
//        return resultList.stream()
//                .map(list -> new int[]{list.get(0), list.get(1)})
//                .toArray(int[][]::new);
//
//    }
}
