import java.util.*;

public class x {
    class Entry{
        public Integer k1;
        public Integer k2;
        Entry(Integer k1,Integer k2){
            this.k1=k1;
            this.k2=k2;
        }

    }

    public static void  main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int x=sc.nextInt();
        String inputString=String.valueOf(x);
        int[] pow={0,1,4,9,6,5,6,9,4,1};
        int sum;
        while(true){
            sum=0;
            for(int i=0;i<inputString.length();i++){
                int n=inputString.charAt(i)-'0';
                sum+=pow[n];
            }
            if (sum==1){
                break;
            }
        }
     }
}
