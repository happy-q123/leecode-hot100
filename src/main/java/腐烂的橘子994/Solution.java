package 腐烂的橘子994;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // 1. 初始化：找出所有腐烂的橘子入队，并统计新鲜橘子数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // 存坐标
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // 如果一开始就没有新鲜橘子，直接返回0
        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        // 2. BFS 循环
        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前这一轮（这一分钟）有多少个传染源
            boolean infectedInThisRound = false; // 标记这一分钟是否有新橘子被腐烂

            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int r = point[0];
                int c = point[1];

                // 尝试向四个方向扩散
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // 检查边界 和 是否是新鲜橘子
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // 腐烂它
                        freshCount--;     // 新鲜橘子减少
                        queue.offer(new int[]{nr, nc}); // 加入队列，下分钟它会传染别人
                        infectedInThisRound = true;
                    }
                }
            }

            // 只有这一轮真的发生了传染，时间才+1
            if (infectedInThisRound) {
                minutes++;
            }
        }

        // 3. 检查是否还有幸存的新鲜橘子
        return freshCount == 0 ? minutes : -1;
    }
}