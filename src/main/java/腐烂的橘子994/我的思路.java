package 腐烂的橘子994;

public class 我的思路 {

    /*
    * 思路： //有问题
    *
    *
    * */
    //经过一轮污染后，节点为1的数量
    int nums1=0;
    // 本轮是否发生了污染的行为
    boolean isHavePollute=false;
    public int orangesRotting(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        int result=0;
        while(true){
            boolean [][] visited=new boolean[grid.length][grid[0].length];
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(grid[i][j]!=0){
                        dfs(visited,grid,false,i,j);
                    }
                }
            }

            //如果发没有污染行为
            if (!isHavePollute){
                //如果1的节点数量为0，则已经全部污染返回
                if (nums1==0){
                    break;
                }else {
                    //如果仍有1的节点未污染，则无法污染，返回-1
                    //打印grid
                    for(int i=0;i<rows;i++){
                        for(int j=0;j<cols;j++){
                            System.out.print(grid[i][j]+" ");
                        }
                    }
                    return -1;
                }
            } else {
                //如果发生污染行为
                result++;
            }

            //更新参数
            isHavePollute=false;
            nums1=0;
        }
        return result;
    }

    private void dfs(boolean[][] visited,int[][] grid,boolean isPollute,int currentHang,int currentLie){
        int rows=grid.length;
        int cols=grid[0].length;
        boolean isPolluteOther=false;
        //越界 或 无效位置 就返回
        if(currentHang<0||currentLie<0||currentHang>=rows||currentLie>=cols|| grid[currentHang][currentLie]==0){
            return;
        }

        //是否受到别人的污染
        if (isPollute&&grid[currentHang][currentLie]==1){
            grid[currentHang][currentLie]=2;
            isHavePollute=true;
        }

        //如果已经访问过，就离开
        if (visited[currentHang][currentLie]){
            return;
        }

        visited[currentHang][currentLie]=true;
        //当前位置已经腐烂
        if(!isPollute&&grid[currentHang][currentLie]==2){
            isPolluteOther=true;
        }



        dfs(visited,grid,isPolluteOther,currentHang-1,currentLie);
        dfs(visited,grid,isPolluteOther,currentHang+1,currentLie);
        dfs(visited,grid,isPolluteOther,currentHang,currentLie-1);
        dfs(visited,grid,isPolluteOther,currentHang,currentLie+1);
        if (grid[currentHang][currentLie]==1)
            nums1++;
    }

}
