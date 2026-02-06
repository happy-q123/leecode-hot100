package 最长回文字串5;

public class 我的思路 {
    public static void main(String[] args) {
//        String s="ababa";
        String s="cbbd";
        System.out.println(new 我的思路().longestPalindrome(s));
    }
    /*
            思路：
            D[m][j]是回文串的条件是，s[m]==s[j]&&D[m+1][j-1]
            以ababa为例，
            首先当m=j时，始终为1，填写矩阵为
            1 x x x x
              1 x x x
                1 x x
                  1 x
                    1
            D[m+1][j-1]相当于矩阵中当前位置的左下角，于是根据地推公式得到
            1 x 1 x x
              1 x 1 x
                1 x 1
                  1 x
                    1
            同理，可得
            1 x 1 x 1
              1 x 1 x
                1 x 1
                  1 x
                    1
            遍历，A与B比较，B与C比较，C与D比较，地推，得到
            1 0 1 x 1
              1 0 1 x
                1 0 1
                  1 0
                    1
            然后再地推，得到：
            1 0 1 0 1
              1 0 1 0
                1 0 1
                  1 0
                    1
            因此我只需要做两件事：
            1、补充对角线为1即可。
            2、遍历字符串，让A与B比较，B与C比较，C与D比较，相等为1，不相等为0
            有了上面两步，即可使用式子s[m]==s[j]&&D[m+1][j-1]推出其余位置。

            当为1时，计算的长度为j-m+1，并比较max，最终即可得到max。
         */
    public String longestPalindrome(String s) {

        int length=s.length();
        boolean[][] D=new boolean[length][length];
        int maxLength=0;
        String maxString="";

        //赋值对角线和对角线的后一位
        for (int i = 0; i < length; i++) {
            //赋值对角线
            D[i][i]=true;
            if (maxLength<1){
                maxLength=1;
                maxString=s.substring(i,i+1);
            }
            if (i>0){
                //赋值对角线元素的上一位，即上一个对角线元素的后一位
                if (s.charAt(i)==s.charAt(i-1)){
                    D[i-1][i]=true;
                    if (maxLength<2){
                        maxLength=2;
                        maxString=s.substring(i-1,i+1);
                    }
                }
            }
        }
        for(int j=2;j<length;j++){
            //i控制行，j控制偏移
            //更新右上三角的内对角线（即除了第一和第二对角线，因为上面已经更新了）
            // 如j=2时是更新[0,2]、[1,3]、[2,4]
            for(int i=0;i+j<length;i++){
                int end=i+j;
                D[i][end]=(s.charAt(i)==s.charAt(end))&&D[i+1][end-1];
                if (D[i][end]){
                    if (maxLength<end-i+1){
                        maxLength=end-i+1;
                        maxString=s.substring(i,end+1);
                    }
                }
            }
        }
        return maxString;
    }
}
