package 不同路径62;

public class 我的思路 {

    /* 思路：
        00 01 01 01 01 01 01
        01 02 03 04 05 06 07
        01 03 06 10 15 21 28
        第一行和第一列的每个位置均只有一种到达方案，所以始终为1。
        D[hang][lie]=D[hang-1][lie]+D[hang][lie-1]
        依据该式子就能推到。
    */
    public int uniquePaths(int m, int n) {
        int[][] D=new int[m][n];
        //第一列行设置为1
        for (int hang = 0; hang < m; hang++) {
            D[hang][0]=1;
        }

        //第一行设置为1
        for (int lie = 0; lie < n; lie++) {
            D[0][lie]=1;
        }

        //从第二行第二列开始计算
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                D[i][j]=D[i-1][j]+D[i][j-1];
            }
        }
        return D[m-1][n-1];
    }


}
