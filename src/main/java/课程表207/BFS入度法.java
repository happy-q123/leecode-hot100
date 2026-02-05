package 课程表207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS入度法 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 初始化邻接表和入度数组
        // edges[i] 存放修完课程 i 之后可以修的后续课程
        List<List<Integer>> edges = new ArrayList<>();
        // indegrees[i] 存放课程 i 的入度（还需要修几门先修课）
        int[] indegrees = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        // 2. 建图并填写入度数组
        // prerequisites[i] = [ai, bi] 表示 bi -> ai (修完 bi 才能修 ai)
        for (int[] info : prerequisites) {
            int cur = info[0];   // 目标课程
            int pre = info[1];   // 先修课程
            
            edges.get(pre).add(cur); // pre 指向 cur
            indegrees[cur]++;        // cur 的入度 +1
        }

        // 3. 将所有初始入度为 0 的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. 开始 BFS (拓扑排序)
        int finishedCount = 0; // 记录已修课程数量

        while (!queue.isEmpty()) {
            // 取出一门可以修的课
            int u = queue.poll();
            finishedCount++;

            // 遍历这门课的后续课程
            for (int v : edges.get(u)) {
                // 后续课程的入度减 1
                indegrees[v]--;
                // 如果入度变为 0，说明所有先修课都搞定了，加入队列
                if (indegrees[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // 5. 判断是否所有课程都修完了
        return finishedCount == numCourses;
    }
}