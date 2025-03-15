package 二分;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long l1 = scanner.nextLong();
        long r1 = scanner.nextLong();
        long l2 = scanner.nextLong();
        long r2 = scanner.nextLong();
        scanner.close();

        System.out.println(calculate(l1, r1, l2, r2));
    }

    private static long calculate(long l1, long r1, long l2, long r2) {
        if (l1 > r1 || l2 > r2) {
            return 0;
        }

        long L = Math.max(l2, 1L);
        long R = Math.min(r2, r1);

        if (L > R) {
            return 0;
        }

        long sum1 = getSum(r1, L, R);
        long sum2 = getSum(l1 - 1, L, R);

        return sum1 - sum2;
    }

    private static long getSum(long n, long a, long b) {
        if (a > b) {
            return 0;
        }

        long sum = 0;

        long sqrtN = (long) Math.sqrt(n);
        long start = a;
        long end = Math.min(b, sqrtN);

        for (long j = start; j <= end; j++) {
            sum += n / j;
        }

        long maxK = (a == 0) ? 0 : n / a;
        for (long k = 1; k <= maxK; k++) {
            long jMin = n / (k + 1) + 1;
            long jMax = n / k;

            jMin = Math.max(jMin, a);
            jMax = Math.min(jMax, b);

            if (jMin > jMax) {
                continue;
            }

            if (jMax <= sqrtN) {
                continue;
            }

            jMin = Math.max(jMin, sqrtN + 1);

            if (jMin > jMax) {
                continue;
            }

            sum += (jMax - jMin + 1) * k;
        }

        return sum;
    }
}
