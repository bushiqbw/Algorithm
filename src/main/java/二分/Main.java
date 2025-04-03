package 二分;

import java.util.Scanner;

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
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    List<int[]> group = new ArrayList<>();
                    int x = i;
                    int y = j;
                    do {
                        group.add(new int[]{x, y});
                        int[] next = rotate90(x, y, n);
                        x = next[0];
                        y = next[1];
                    } while (x != i || y != j);

                    for (int[] pos : group) {
                        visited[pos[0]][pos[1]] = true;
                    }

                    List<Integer> values = new ArrayList<>();
                    for (int[] pos : group) {
                        values.add(matrix[pos[0]][pos[1]]);
                    }

                    int max = Collections.max(values);
                    int sum = 0;
                    for (int val : values) {
                        sum += (max - val);
                    }
                    total += sum;
                }
            }
        }

        System.out.println(total);
    }

    private static int[] rotate90(int x, int y, int n) {
        return new int[]{y, n - 1 - x};
    }
}
