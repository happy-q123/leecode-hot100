package 腐烂的橘子994;

import java.util.LinkedList;
import java.util.Queue;

public class GeminiBFS搜索 {
    public int orangesRotting(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;

        Queue<int[]> queue=new LinkedList<>();
        int freshNum=0;
        //找出所有腐烂的橘子入队，并统计新鲜的橘子数量
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]==2){
                    //存进去坐标
                    queue.offer(new int[]{i,j});
                }else if (grid[i][j]==1){
                    freshNum++;
                }
            }
        }
        if (freshNum==0)
            return 0;
        int result=0;
        int[][] dirs=new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        //BFS遍历
        while (!queue.isEmpty()){
            //当前轮还有几个腐烂橘子
            int size=queue.size();
            //标记当前轮是否还有新的橘子被腐烂
            boolean infectedInThisRound=false;

            for (int i=0;i<size;i++){
                int[] point=queue.poll();
                int hang=point[0];
                int lie=point[1];

                for(int[] dir:dirs){
                    int newHang=hang+dir[0];
                    int newLie=lie+dir[1];

                    //检查边界
                    if (newHang<0||newLie<0||newHang>=rows||newLie>=cols||grid[newHang][newLie]!=1){
                        continue;
                    }
                    //腐烂它
                    grid[newHang][newLie]=2;
                    freshNum--;
                    queue.offer(new int[]{newHang,newLie});
                    infectedInThisRound=true;
                }
            }
            //只有实际发生了感染，才++
            if (infectedInThisRound)
                result++;

        }

        //检查是否还剩余新鲜的橘子，然后返回结果
        return freshNum==0?result:-1;
    }
}
