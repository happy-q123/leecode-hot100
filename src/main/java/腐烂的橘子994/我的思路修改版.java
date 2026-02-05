package 腐烂的橘子994;

public class 我的思路修改版 {

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int result = 0;
        
        // 统计一开始有多少个新鲜橘子
        int nums1 = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(grid[i][j] == 1) nums1++;
            }
        }

        // 每一轮循环代表 1 分钟
        while (true) {
            boolean isHavePollute = false;
            
            // --- 第一步：标记阶段 ---
            // 找出所有本来就是腐烂的(2)，把它们周围的新鲜橘子(1)标记为“将要腐烂”(3)
            // 注意：这里不能直接变成2，否则会发生连锁反应
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 2) {
                        // 尝试污染上下左右，如果有新鲜橘子，标记为污染行为发生
                        if(infect(grid, i-1, j)) isHavePollute = true;
                        if(infect(grid, i+1, j)) isHavePollute = true;
                        if(infect(grid, i, j-1)) isHavePollute = true;
                        if(infect(grid, i, j+1)) isHavePollute = true;
                    }
                }
            }

            // --- 第二步：更新阶段 ---
            // 这一轮扫描完了，把所有标记为 3 的变成 2
            if (isHavePollute) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (grid[i][j] == 3) {
                            grid[i][j] = 2;
                            nums1--; // 新鲜橘子少了一个
                        }
                    }
                }
                result++; // 只有发生了污染，时间才+1
            } else {
                // 如果没有发生污染，检查是否还有新鲜橘子
                if (nums1 > 0) {
                    return -1; // 还有橘子没烂，但已经没法传染了
                } else {
                    return result; // 全部烂完了，返回结果
                }
            }
        }
    }

    // 辅助函数：尝试传染
    // 返回值：true表示成功让一个新鲜橘子变质（变成了3），false表示无效操作
    private boolean infect(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 越界处理
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return false;
        }
        
        // 只有新鲜橘子(1)才能被传染
        // 注意：这里我们把它改成 3，而不是 2
        if (grid[r][c] == 1) {
            grid[r][c] = 3; 
            return true;
        }
        
        return false;
    }
}