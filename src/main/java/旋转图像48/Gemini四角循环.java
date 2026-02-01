package 旋转图像48;

public class Gemini四角循环 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 只需要遍历矩阵左上角的四分之一区域
        // 如果 n 是奇数，中间的行/列需要特殊考虑，所以行到 n/2，列到 (n+1)/2
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                // 暂存左上角的值
                int temp = matrix[i][j];

                // 1. 左下 -> 左上
                matrix[i][j] = matrix[n - 1 - j][i];

                // 2. 右下 -> 左下
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];

                // 3. 右上 -> 右下
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];

                // 4. 左上(temp) -> 右上
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}
