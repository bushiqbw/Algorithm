package DP.最长上升子序列;

import java.util.*;

public class Main {
    static List<Integer>[] adj;
    static int[] a;
    static long result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt() % 2;
        }
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        result = 0;
        dfs(1, -1);
        System.out.println(result);
    }

    static class Pair {
        long even, odd;
        Pair(long e, long o) { even = e; odd = o; }
    }

    static Pair dfs(int u, int parent) {
        long even = 0, odd = 0;
        int current = a[u];
        // 自身作为路径
        if (current == 0) {
            even = 1;
        } else {
            odd = 1;
        }
        // 初始化累计的奇偶路径数
        long totalEven = even;
        long totalOdd = odd;
        for (int v : adj[u]) {
            if (v == parent) continue;
            Pair child = dfs(v, u);
            long childEven = child.even;
            long childOdd = child.odd;
            // 根据u的奇偶性调整子树的奇偶路径数
            if (current == 1) {
                // 子树的路径奇偶性翻转
                long temp = childEven;
                childEven = childOdd;
                childOdd = temp;
            }
            // 计算跨子树的路径
            result += childEven * even; // 当前子树的even与之前累计的even组合
            result += childOdd * odd;   // 当前子树的odd与之前累计的odd组合
            // 更新累计的奇偶路径数
            even += childEven;
            odd += childOdd;
            // 累加单边路径（当前子树到u的路径）
            result += childEven;
        }
        return new Pair(even, odd);
    }
}