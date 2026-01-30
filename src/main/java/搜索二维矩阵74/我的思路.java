package 搜索二维矩阵74;

import java.security.PublicKey;

public class 我的思路 {

    public static void main(String args[]){
        //[[1,3,5,7],[10,11,16,20],[23,30,34,50]]
        int[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target=3;
        System.out.println(new 我的思路().searchMatrix(matrix, target));
    }
//        7 20 50
//        0 2-》mid1-》0 1-》mid0-》0，-1
    /**
     * description
     * 要学会：
     * 1、标准的二分查找。
     * 2、用二分查找，找到第一个比x大或等于的索引。
     * author zzq
     * date 2026/1/29 15:13
    */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 1. 提取每行的最后一个元素
        int[] lastElements = new int[rows];
        for (int i = 0; i < rows; i++) {
            lastElements[i] = matrix[i][cols - 1];
        }

        // 2. 二分查找行：找到第一个 "元素值 >= target" 的行索引
        int rowIndex = findFirstRow(lastElements, target);

        // 如果返回 -1，说明所有行的最大值都比 target 小，直接返回 false
        if (rowIndex == -1) {
            return false;
        }

        // 3. 在具体的行中二分查找
        return binarySearchRow(matrix[rowIndex], target);
    }

    // 查找第一个值 >= target 的索引
    public int findFirstRow(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                ans = mid;      // 记录当前位置，并尝试往左找更早的
                right = mid - 1;
            } else {
                left = mid + 1; // 还没到，往右找
            }
        }
        return ans;
    }

    // 标准二分查找是否存在
    public boolean binarySearchRow(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

}
