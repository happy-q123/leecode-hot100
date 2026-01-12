package 合并区间56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class gemin思路2贪心超简洁 {
    public static void main(String[] args) {
        gemin思路2贪心超简洁 solution = new gemin思路2贪心超简洁();

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
        // 1. 按起点排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            // 如果结果集为空，或者当前区间起点 > 结果集最后一个区间的终点
            // 说明不重叠，直接添加
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(interval);
            } else {
                // 否则，说明有重叠，更新结果集最后一个区间的终点
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
