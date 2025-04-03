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
}
