package 二分;

import java.util.*;

public class Main {
    private static List<Long> twoDigitUnlucky = new ArrayList<>();

    static {
        for (int a = 1; a <= 9; a++) {
            if (a % 3 == 0) continue;
            for (int b = 0; b <= 9; b++) {
                if (b % 3 == 0) continue;
                int sum = a + b;
                if (sum % 3 != 0) {
                    twoDigitUnlucky.add((long) (a * 10 + b));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            long L = scanner.nextLong();
            long R = scanner.nextLong();

            // 计算一位数的非3的倍数的数目
            long start1 = Math.max(L, 1);
            long end1 = Math.min(R, 9);
            long count1NonMultiples = 0;
            if (start1 <= end1) {
                long count1Digit = end1 - start1 + 1;
                long cntMultiples = (end1 / 3) - ((start1 - 1) / 3);
                count1NonMultiples = count1Digit - cntMultiples;
            }

            // 计算两位数的非幸运数目
            long start2 = Math.max(L, 10);
            long end2 = Math.min(R, 99);
            long count2Unlucky = 0;
            if (start2 <= end2) {
                for (long num : twoDigitUnlucky) {
                    if (num >= start2 && num <= end2) {
                        count2Unlucky++;
                    }
                }
            }

            // 总非幸运数目
            long totalUnlucky = count1NonMultiples + count2Unlucky;
            long total = R - L + 1;
            long result = total - totalUnlucky;
            System.out.println(result);
        }
    }
}
