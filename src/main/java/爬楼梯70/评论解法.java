package 爬楼梯70;

public class 评论解法 {

    public int climbStairs(int n) {
        // 爬一楼
        int p = 1;
        // 爬二楼
        int q = 2;
        if(n == 1){
            return p;
        }else if(n == 2){
            return q;
        }else{
            // 从第三楼开始，只有两种上楼方式，从前一层再爬一楼和从前二层再爬两楼。
            // 可以推出 f(n) = f(n -1) + f(n -2)
            // 直接递归会超时，所以用的for循环求结果
            int r = 0;
            for(int i = 3; i <= n; i++){
                r = q + p;
                p = q;
                q = r;
            }
            return r;
        }
    }
}
