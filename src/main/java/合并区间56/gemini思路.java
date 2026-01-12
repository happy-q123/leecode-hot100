package 合并区间56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class gemini思路 {
    public static void main(String[] args) {
        gemini思路 solution = new gemini思路();

        // 测试用例1
        int[][] intervals1 = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("输入: " + Arrays.deepToString(intervals1));
        int[][] result1 = solution.merge(intervals1);
        System.out.println("输出: " + Arrays.deepToString(result1));
        System.out.println();

        // 测试用例2
        int[][] intervals2 = {{1,4},{4,5}};
        System.out.println("输入: " + Arrays.deepToString(intervals2));
        int[][] result2 = solution.merge(intervals2);
        System.out.println("输出: " + Arrays.deepToString(result2));
        System.out.println();

        // 测试用例3 - 空数组
        int[][] intervals3 = {};
        System.out.println("输入: " + Arrays.deepToString(intervals3));
        int[][] result3 = solution.merge(intervals3);
        System.out.println("输出: " + Arrays.deepToString(result3));
        System.out.println();

        // 测试用例4 - 单个区间
        int[][] intervals4 = {{1,4}};
        System.out.println("输入: " + Arrays.deepToString(intervals4));
        int[][] result4 = solution.merge(intervals4);
        System.out.println("输出: " + Arrays.deepToString(result4));
        System.out.println();

        // 测试用例5 - 需要排序的情况
        int[][] intervals5 = {{2,3},{4,5},{1,10}};
        System.out.println("输入: " + Arrays.deepToString(intervals5));
        int[][] result5 = solution.merge(intervals5);
        System.out.println("输出: " + Arrays.deepToString(result5));
        System.out.println();

        // 测试用例6 - 包含重复和重叠区间
        int[][] intervals6 = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        System.out.println("输入: " + Arrays.deepToString(intervals6));
        int[][] result6 = solution.merge(intervals6);
        System.out.println("输出: " + Arrays.deepToString(result6));
        System.out.println();
    }
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. 按照起点排序 (关键一步)
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> resultList = new ArrayList<>();

        // 2. 将第一个区间加入结果集
        int[] currentInterval = intervals[0];
        resultList.add(currentInterval);

        for (int i = 1; i < intervals.length; i++) {
            int currentEnd = currentInterval[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            // 3. 因为已排序，只需判断：后一个的起点 <= 前一个的终点
            if (nextStart <= currentEnd) {
                // 有重叠，更新当前区间的终点为两者的最大值
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // 无重叠，将新区间设为“当前区间”，并加入结果集
                currentInterval = intervals[i];
                resultList.add(currentInterval);
            }
        }

        // 4. 转换回二维数组
        return resultList.toArray(new int[resultList.size()][]);
    }
}
