package 二分;

import java.util.Scanner;

import java.util.*;

import java.util.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean[][] visited = new boolean[n][n];
        long total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;

                List<int[]> positions = new ArrayList<>();
                int x = i, y = j;
                do {
                    positions.add(new int[]{x, y});
                    int[] next = rotate(x, y, n);
                    x = next[0];
                    y = next[1];
                } while (x != i || y != j);

                List<Integer> values = new ArrayList<>();
                for (int[] pos : positions) {
                    if (!visited[pos[0]][pos[1]]) {
                        values.add(matrix[pos[0]][pos[1]]);
                        visited[pos[0]][pos[1]] = true;
                    }
                }

                if (!values.isEmpty()) {
                    int max = Collections.max(values);
                    for (int val : values) {
                        total += (max - val);
                    }
                }
            }
        }

        System.out.println(total);
    }

    private static int[] rotate(int x, int y, int n) {
        return new int[]{y, n - 1 - x};
    }

    import java.util.HashMap;

    public class Solution {
        public int solution(int[] A, int[] B, int N) {
            int[] degree = new int[N + 1];
            HashMap<Integer, HashMap<Integer, Integer>> edgeCount = new HashMap<>();

            for (int i = 0; i < A.length; i++) {
                int u = A[i];
                int v = B[i];
                degree[u]++;
                degree[v]++;

                int a = Math.min(u, v);
                int b = Math.max(u, v);
                if (!edgeCount.containsKey(a)) {
                    edgeCount.put(a, new HashMap<>());
                }
                HashMap<Integer, Integer> innerMap = edgeCount.get(a);
                innerMap.put(b, innerMap.getOrDefault(b, 0) + 1);
            }

            int maxRank = 0;
            for (int i = 0; i < A.length; i++) {
                int u = A[i];
                int v = B[i];
                int a = Math.min(u, v);
                int b = Math.max(u, v);
                int count = edgeCount.get(a).get(b);
                int currentRank = degree[u] + degree[v] - count;
                if (currentRank > maxRank) {
                    maxRank = currentRank;
                }
            }

            return maxRank;
        }
    }
}
