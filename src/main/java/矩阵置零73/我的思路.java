package 矩阵置零73;

import java.util.HashSet;
import java.util.Set;

public class 我的思路 {
    public void setZeroes(int[][] matrix) {
        int hang_num = matrix.length;
        int lie_num = matrix[0].length;
        Set<Integer> hang_flag = new HashSet<>(hang_num);
        Set<Integer> lie_flag = new HashSet<>(lie_num);

        for(int hang=0;hang<hang_num;hang++){
            for(int lie=0;lie<lie_num;lie++){
                if(matrix[hang][lie]==0){
                    hang_flag.add(hang);
                    lie_flag.add(lie);
                }
            }
        }

        //Hang为0
        for (Integer hang : hang_flag) {
            for (int lie = 0; lie < lie_num; lie++) {
                matrix[hang][lie] = 0;
            }
        }

        //Lie为0
        for (Integer lie : lie_flag) {
            for (int hang = 0; hang < hang_num; hang++) {
                matrix[hang][lie] = 0;
            }
        }

    }
}
