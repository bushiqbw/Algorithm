package DP.最长上升子序列;

import java.io.*;
import java.util.*;

public class Main{
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    static int N = 10010;
    static int[] h = new int[N * 2];
    static int[] e = new int[N * 2];
    static int[] ne = new int[N * 2];
    static int[] a = new int[N];
    static boolean[] v = new boolean[N];
    static int idx, n, m;
    static int res = 0;
    static void add(int a, int b){
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx ++;
    }
    public static void main(String[] args) throws Exception{
        n = nextInt();
        for(int i = 0; i < n; i ++)
            a[i] = nextInt();
        for(int i = 0; i < n - 1; i ++){
            int a = nextInt();
            int b = nextInt();
            add(a, b);
            add(b, a);
        }
        dfs(0, 0);
        pw.println(res);
        pw.flush();
    }
    public static void dfs(int u, int cur){
        v[u] = true;
        int tmp = cur + a[u];
        if(tmp != 0 && tmp % 2 == 0) res ++;
        for(int i = h[u]; i != -1; i = ne[i]){
            int j = e[i];
            if(!v[j]) dfs(j, tmp);
        }
        return ;
    }

}