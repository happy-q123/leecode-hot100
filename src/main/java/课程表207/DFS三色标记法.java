package 课程表207;

import java.util.ArrayList;
import java.util.List;

class DFS三色标记法 {
    // 存储有向图
    List<List<Integer>> edges;
    // 标记数组：0=未搜索, 1=搜索中, 2=已完成
    int[] visited;
    // 记录是否有环，默认为 true (可以完成)，一旦发现环变为 false
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 初始化邻接表
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        
        // 2. 建图：prerequisites[i] = [ai, bi] 代表 bi -> ai
        for (int[] info : prerequisites) {
            // info[1] 是先修课，指向 info[0]
            edges.get(info[1]).add(info[0]);
        }

        // 3. 初始化标记数组
        visited = new int[numCourses];

        // 4. 遍历所有节点
        // 因为图可能不是连通的，需要对每个未访问过的节点都发起一次 DFS
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                dfs(i);
                // 如果在某次 DFS 中发现了环，直接返回 false
                if (!valid) {
                    return false;
                }
            }
        }

        return true;
    }

    public void dfs(int u) {
        // 将当前节点标记为“搜索中”
        visited[u] = 1;

        // 遍历当前节点的后续课程
        for (int v : edges.get(u)) {
            if (visited[v] == 0) {
                // 如果后续节点没访问过，递归访问
                dfs(v);
                if (!valid) return; // 剪枝：如果已经发现环，直接返回
            } else if (visited[v] == 1) {
                // 核心判断：如果后续节点是“搜索中”，说明在这个递归栈里遇到了自己
                // 这意味着存在环
                valid = false;
                return;
            }
            // 如果 visited[v] == 2，说明是安全的节点，直接跳过
        }

        // 当前节点及其后续都检查完了，标记为“已完成”
        visited[u] = 2;
    }
}