package 岛屿数量200;

class Solution {
    public int numIslands(char[][] grid) {
        // 1. 边界条件检查：如果网格为空，直接返回 0
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        // 2. 遍历每一个格子
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 3. 如果发现陆地 '1'
                if (grid[i][j] == '1') {
                    count++; // 岛屿数量加 1
                    dfs(grid, i, j); // 启动 DFS，将连接的陆地全部“淹没”
                }
            }
        }
        return count;
    }

    // 辅助函数：深度优先搜索
    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;

        // 递归终止条件：
        // 1. 下标越界 (r, c 超出网格范围)
        // 2. 当前位置已经是水 '0' (或者是已经被访问过的陆地)
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }

        // 核心操作：将当前陆地标记为水 '0' (沉岛操作)，防止重复计算
        grid[r][c] = '0';

        // 继续向上下左右四个方向扩散
        dfs(grid, r - 1, c); // 上
        dfs(grid, r + 1, c); // 下
        dfs(grid, r, c - 1); // 左
        dfs(grid, r, c + 1); // 右
    }
}