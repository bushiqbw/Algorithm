package 图论;
import java.util.*;
import java.io.*;

public class SPFA {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    static int N = 100010;
    static int[] h = new int[N];
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int[] w = new int[N];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    static int idx, n, m;
    static int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws Exception {
        Arrays.fill(h, -1);
        n = nextInt();
        m = nextInt();
        while(m -- != 0){
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            add(a, b, c);
        }
        int t = spfa();
        if(t == INF) pw.println("impossible");
        else pw.println(t);
        pw.flush();
    }
    static void add(int a, int b, int c){
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx ++;
    }
    static int spfa(){
        Arrays.fill(dist, INF);
        Queue<Integer> q = new LinkedList();
        q.add(1);
        st[1] = true;
        dist[1] = 0;
        while(!q.isEmpty()){
            int t = q.poll();
            st[t] = false;
            for(int i =h[t]; i != -1; i = ne[i]){
                int j = e[i];
                if(dist[j] > dist[t] + w[i]){
                    dist[j] = dist[t] + w[i];
                    if(!st[j]){
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return dist[n];
    }
}