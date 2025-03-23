package 二分;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(maxApples(n, m, k));
    }

    private static int maxApples(int n, int m, int k) {
        int low = 1;
        int high = m;
        int ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long total = computeTotal(n, k, mid);
            if (total <= m) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private static long computeTotal(int n, int k, long x) {
        return leftSum(k, x) + rightSum(n, k, x) + x;
    }

    private static long leftSum(int k, long x) {
        int num = k - 1;
        if (num == 0) return 0;
        if (x >= num + 1) {
            return (long) num * (x - 1 + x - num) / 2;
        } else {
            long sum = x * (x - 1) / 2;
            sum += (num - (x - 1)) * 1L;
            return sum;
        }
    }

    private static long rightSum(int n, int k, long x) {
        int num = n - k;
        if (num == 0) return 0;
        if (x >= num + 1) {
            return (long) num * (x - 1 + x - num) / 2;
        } else {
            long sum = x * (x - 1) / 2;
            sum += (num - (x - 1)) * 1L;
            return sum;
        }
    }
}
