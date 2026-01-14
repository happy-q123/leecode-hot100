package 只出现一次的数字136;

public class 官方解法值得学习 {
    /*
    * 任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
        任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
        异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
    *
    * */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

}
/*
* 异或运算的另一个好用处：无需第三个变量完成两个变量的值互换。
* a = a ^ b（此时a已经不是原来的a了）
  b = a ^ b (此时 b 变成了原来的 a)
  a = a ^ b (此时 a 变成了原来的 b)
*
* */
