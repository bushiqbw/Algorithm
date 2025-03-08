package DP.背包;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Acwing完全背包 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
    public static void main(String[] args) throws Exception{
        int n = nextInt();
        int v = nextInt();
        int[] w = new int[n];
        int[] f = new int[v + 1];
        int res = 0;
        for(int i = 0; i < n; i ++){
            w[i] = nextInt();
            int val = nextInt();
            for(int j = w[i]; j <= v; j ++)
                f[j] = Math.max(f[j], f[j - w[i]] + val);

        }
        for(int i = 0; i <= v; i ++) res = Math.max(res, f[i]);
        System.out.println(res);

    }
}
