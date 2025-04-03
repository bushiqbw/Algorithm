package 二分;

import java.util.Scanner;

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
        System.out.println(minOperations(matrix));
    }

    public static int minOperations(int[][] matrix) {
        int n = matrix.length;
        int totalOperations = 0;
        boolean[][] visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    List<Integer> group = new ArrayList<>();
                    int x = i;
                    int y = j;
                    for (int k = 0; k < 4; k++) {
                        group.add(matrix[x][y]);
                        visited[x][y] = true;
                        int tempX = y;
                        int tempY = n - 1 - x;
                        x = tempX;
                        y = tempY;
                    }
                    totalOperations += minOperationsForGroup(group);
                }
            }
        }
        return totalOperations;
    }

    private static int minOperationsForGroup(List<Integer> group) {
        Collections.sort(group);
        int median = group.get(group.size() / 2);
        int operations = 0;
        for (int num : group) {
            operations += Math.abs(num - median);
        }
        return operations;
    }
}
