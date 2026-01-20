package 杨辉三角118;

import java.util.ArrayList;
import java.util.List;

public class 我的思路 {
    public static void main(String[] args){
        我的思路 solution = new 我的思路();
        solution.generate(5);
    }
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>>zong=new ArrayList<>();
        for(int hang=1;hang<=numRows;hang++){
            List<Integer>current=new ArrayList<>();
            //每行最多hang个元素
            for(int i=0;i<hang;i++){
                //第一个、最后一个元素为1
                if(i==0||i==hang-1){
                    current.add(1);
                }else {
                    List<Integer>last_hang=zong.get(zong.size()-1);
                    int current_value=last_hang.get(i)+last_hang.get(i-1);
                    current.add(current_value);
                }
            }
            zong.add(current);
        }
//        int i=0;
//        for(List<Integer> l:zong){
//            i++;
//            System.out.println("第"+i+"行");
//            for(Integer x:l){
//                System.out.print(x+" ");
//            }
//            System.out.println();
//        }
        return zong;
    }
}
