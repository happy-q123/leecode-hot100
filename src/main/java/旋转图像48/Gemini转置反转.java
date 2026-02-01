package 旋转图像48;

public class Gemini转置反转 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int n = matrix.length;

        // 1. 转置矩阵 (Transpose)
        // 规律：matrix[i][j] 与 matrix[j][i] 交换
        // 注意：j 从 i + 1 开始，只遍历右上三角，否则换过去又换回来了
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. 左右翻转 (Reflect)
        // 规律：每一行的第 j 个元素与倒数第 j 个元素交换
        // 注意：j 只需遍历到 n / 2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
