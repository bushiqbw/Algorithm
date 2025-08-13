package 二分;





import java.util.*;

public class Main {
    static final long MOD = 1000000007;
    static final int MAX = 200000;
    static long[] fact = new long[MAX + 1];
    static long[] inv_fact = new long[MAX + 1];

    static {
        fact[0] = 1;
        for (int i = 1; i <= MAX; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        inv_fact[MAX] = modPow(fact[MAX], MOD - 2, MOD);
        for (int i = MAX - 1; i >= 0; i--) {
            inv_fact[i] = inv_fact[i + 1] * (i + 1) % MOD;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        List<int[]> nonZero = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] != 0) {
                nonZero.add(new int[]{i, a[i]});
            }
        }

        if (nonZero.isEmpty()) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < nonZero.size(); i++) {
            int prevVal = nonZero.get(i - 1)[1];
            int currVal = nonZero.get(i)[1];
            if (prevVal > currVal) {
                System.out.println(0);
                return;
            }
        }

        long ans = 1;
        for (int i = 0; i < nonZero.size() - 1; i++) {
            int[] left = nonZero.get(i);
            int[] right = nonZero.get(i + 1);
            int k = right[0] - left[0] - 1;
            if (k <= 0) continue;

            int L = left[1];
            int R = right[1];
            long d = R - L + 1;
            long product = 1;
            for (int j = 0; j < k; j++) {
                product = product * (d + j) % MOD;
            }
            product = product * inv_fact[k] % MOD;
            ans = (ans * product) % MOD;
        }
        System.out.println(ans);
    }

    public static long modPow(long base, long exp, long modulus) {
        base %= modulus;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % modulus;
            base = (base * base) % modulus;
            exp >>= 1;
        }
        return result;
    }
}
