package 岛屿数量200;

public class gemini思路 {
    /*
    * 这道题的本质就是在求有向图的联通分量个数。
    *   如果一个图是联通的（从任一节点出发可以到达任意节点），那么结果为1。
    *   思路：
    *       遍历每个节点，如果是1，则进行深度优先搜索，将所有相连的1都标记为0，并计数。
    * */

    public int numIslands(char[][] grid){
        if(grid==null||grid.length==0){
            return 0;
        }

        int count=0;
        int rows=grid.length;
        int cols=grid[0].length;

        //遍历每一个格子
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(grid[i][j]=='1'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }

    //深度优先搜索
    private void dfs(char[][] grid,int hang,int lie){
        int rows = grid.length;
        int cols = grid[0].length;

        if(hang<0||lie<0||hang>=rows||lie>=cols||grid[hang][lie]=='0'){
            return;
        }

        //将能走通的节点设置为0
        grid[hang][lie]='0';

        //继续向上下左右四个方向扩散
        dfs(grid,hang-1,lie);
        dfs(grid,hang+1,lie);
        dfs(grid,hang,lie-1);
        dfs(grid,hang,lie+1);


    }
}
