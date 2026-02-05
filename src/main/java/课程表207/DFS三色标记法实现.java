package 课程表207;

import java.util.ArrayList;
import java.util.List;

public class DFS三色标记法实现 {
    //存储有向图
    List<List<Integer>> edges;

    //标记数组：0=未搜索，1=搜索中，2=已完成
    int[] visited;

    //是否有环，即结果
    boolean result;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //初始化邻接表
        edges=new ArrayList<>();
        for (int i=0;i<numCourses;i++){
            edges.add(new ArrayList<>());
        }

        //根据prerequisites构建邻接表
        for (int[] edge:prerequisites){
            //edge[1] 的前驱为 edge[0]，所有edges.get(edge[1]) 添加 edge[0]
            edges.get(edge[1]).add(edge[0]);
        }

        //初始化标记数组
        visited=new int[numCourses];
        result=true;

        //遍历所有节点
        // 因为图可能不是连通的，需要对每个未访问过的节点都发起一次 DFS
        for (int i=0;i<numCourses;i++){
            if (visited[i]==0){
                dfs(i);
                // 如果在某次 DFS 中发现了环，直接返回 false
                if (!result){
                    return false;
                }
            }
        }
        return true;
    }
    public void dfs(int u) {
        //给节点打上搜索中的标记
        visited[u] = 1;
        for (int v : edges.get(u)){
            if (visited[v]==0){
                dfs(v);
                if (!result){
                    return;
                }
            } else if (visited[v]==1){
                result=false;
                return;
            }
        }
        //这个节点的所有指向节点都遍历完成，就赋值为2
        visited[u]=2;
    }
}

