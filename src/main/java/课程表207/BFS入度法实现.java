package 课程表207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS入度法实现 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //初始化邻接表
        //edges[i] 存放修完课程i之后可以修的后续课程
        List<List<Integer>> edges=new ArrayList<>();

        //初始化邻接表
        for(int i=0;i<numCourses;i++){
            edges.add(new ArrayList<>());
        }

        //初始化入读数组
        //indegrees[i] 存放课程i的入读数
        int[] indegrees=new int[numCourses];

        //建立图并写入度数组
        for (int[] info:prerequisites){
            int cur=info[0];//当前课程
            int pre=info[1];//前置课程

            edges.get(pre).add(cur);//添加边
            indegrees[cur]++;//当前课程的入度+1
        }

        //初始化BFS所需的队列
        Queue<Integer>queue=new LinkedList<>();

        //找到所有入度为0的课程
        for (int i=0;i<numCourses;i++){
            if (indegrees[i]==0){
                queue.offer(i);
            }
        }

        //开始BFS

        //已经修的课程数量
        int finishedNum=0;
        while(!queue.isEmpty()){
            //取出一门课程
            int u=queue.poll();
            finishedNum++;

            //遍历当前课程的后续课程
            for (int v:edges.get(u)){

                //让他们的入度减1
                indegrees[v]--;

                //如果入度减为0，则加入队列
                if (indegrees[v]==0){
                    queue.offer(v);
                }
            }
        }

        //如果出了循环，且已经修的课程数量等于课程总数，则说明可以完成课程。
        //否则，存在环.
        /*
         在这个算法中，不为环时，所有节点都可入队。
                    为环时，环节点不可入队。
                    而finishedCount只有出队时才更新，所有可以根据finishedNum==numCourses判断是否有环。


        */

        return finishedNum==numCourses;
    }
}