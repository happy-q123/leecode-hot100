package 完全平方数279;

public class 收到启发后的思路 {
    public int numSquares(int n) {
        //D[x]表示和为x的最少平方数的个数
        int[] D=new int[n+1];
        D[0]=0;
        D[1]=1;
        for (int i = 2; i <= n; i++) {
            //默认为i个，即i个1相加组成了i
            D[i]=i;
            for (int j = 1; j <= i; j++) {
                int j_pow2=j*j;
                if(j_pow2>i)
                    break;
                int candidate=D[i-j_pow2]+1;
                if(candidate<D[i])
                    D[i]=candidate;
            }
        }
        return D[n];
    }
}
