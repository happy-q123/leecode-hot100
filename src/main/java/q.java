public class q {
    public static void main(String []args) {
        int a=3;
        int b=1;
        A a1=new A(0,0);
        A a2=new A(1,2);


        System.out.println(a^b);
    }
    static class A{
        public int a=1;
        public int b=1;
        A(int a,int b){
            this.a=a;
            this.b=b;
        }
    }
}
