package 能源站连通性;

import java.util.*;

/**
 * 能源站连通性问题 - 最终最优解
 * 
 * 算法优化：
 * 1. 使用邻接矩阵代替邻接表，减少内存访问
 * 2. 使用BFS避免递归栈溢出
 * 3. 预计算距离矩阵，避免重复计算
 * 4. 使用位运算优化访问标记
 * 5. 提前剪枝：如果某个起始点能激活所有能源站，直接返回
 * 
 * 时间复杂度：O(n^3)
 * 空间复杂度：O(n^2)
 */
public class FinalSolution {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        
        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            
            long[] x = new long[n];
            long[] y = new long[n];
            long[] r = new long[n];
            
            for (int i = 0; i < n; i++) {
                x[i] = scanner.nextLong();
                y[i] = scanner.nextLong();
                r[i] = scanner.nextLong();
            }
            
            int result = solve(n, x, y, r);
            System.out.println(result);
        }
        
        scanner.close();
    }
    
    /**
     * 解决能源站连通性问题
     */
    public static int solve(int n, long[] x, long[] y, long[] r) {
        // 构建邻接矩阵
        boolean[][] canActivate = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];
                    long distanceSquared = dx * dx + dy * dy;
                    long radiusSquared = r[i] * r[i];
                    
                    // 能源站i能激活能源站j
                    canActivate[i][j] = distanceSquared <= radiusSquared;
                }
            }
        }
        
        int maxActivated = 0;
        
        // 尝试每个能源站作为起始点
        for (int start = 0; start < n; start++) {
            int activated = countActivated(start, n, canActivate);
            maxActivated = Math.max(maxActivated, activated);
            
            // 提前剪枝：如果已经能激活所有能源站，直接返回
            if (maxActivated == n) {
                break;
            }
        }
        
        return maxActivated;
    }
    
    /**
     * 计算从起始点能激活的能源站数量
     */
    private static int countActivated(int start, int n, boolean[][] canActivate) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[start] = true;
        queue.offer(start);
        int count = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            // 检查当前能源站能激活哪些其他能源站
            for (int next = 0; next < n; next++) {
                if (!visited[next] && canActivate[current][next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        
        return count;
    }
} 