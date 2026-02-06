package 最小路径和64;

public class 我的思路 {

    /*
    * 思路：
    *   与“不同路径62”题思路一致。
    *   只不过当前第一列的值为D[hang][0]=grid[hang][0]+D[hang-1][0]
    *   第一行的值为D[0][lie]=grid[0][lie]+D[0][lie-1]
    *   其他位置的为D[i][j]=grid[i][j]+Math.min(D[i-1][j],D[i][j-1])
    * */
    public int minPathSum(int[][] grid) {
        int hangNum=grid.length;
        int lieNum=grid[0].length;
        int[][] D=new int[hangNum][lieNum];
        D[0][0]=grid[0][0];
        //设置第一列
        for (int hang = 1; hang < hangNum; hang++) {
            D[hang][0]=grid[hang][0]+D[hang-1][0];
        }

        //设置第一行
        for (int lie = 1; lie < lieNum; lie++) {
            D[0][lie]=grid[0][lie]+D[0][lie-1];
        }

        //从第二行第二列开始计算
        for(int i=1;i<hangNum;i++){
            for(int j=1;j<lieNum;j++){
                D[i][j]=grid[i][j]+Math.min(D[i-1][j],D[i][j-1]);
            }
        }
        return D[hangNum-1][lieNum-1];
    }
}
