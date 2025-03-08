package 图论;
import java.io.*;
import java.util.*;

public class Floyd{
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    static int N = 210;
    static int[] h = new int[N];
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int[] w = new int[N];
    static int[][] dist = new int[N][N];
    static int idx, n, m, k;
    static int INF = 0x3f3f3f3f;
    public static void main(String[] args) throws Exception{
        Arrays.fill(h, -1);
        for(int i = 0; i < N; i ++) Arrays.fill(dist[i], INF);
        for(int i = 0; i < N; i ++) dist[i][i] = 0;
        n = nextInt();
        m = nextInt();
        k = nextInt();
        while(m -- != 0){
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            dist[a][b] = Math.min(dist[a][b], c);
        }
        Floyd();
        while(k -- != 0){
            int a = nextInt();
            int b = nextInt();
            if(dist[a][b] < INF/2) pw.println(dist[a][b]);
            else pw.println("impossible");
        }
        pw.flush();
    }

    static void Floyd(){
        for(int k = 1; k <= n; k ++){
            for(int i = 1; i <= n; i ++){
                for(int j = 1; j <= n; j ++){
                    if(dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
}
