package 差分;
import java.util.*;
import java.io.*;
public class Acwing798二维差分 {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        int n = nextInt();
        int m = nextInt();
        int q = nextInt();
        int[][] nums = new int[n + 10][m + 10];
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= m; j ++){
                int val = nextInt();
                insert(i,i,j,j,val, nums);
            }
        }
        while(q -- != 0){
            int x1 = nextInt();
            int y1 = nextInt();
            int x2 = nextInt();
            int y2 = nextInt();
            int val = nextInt();
            insert(x1, x2, y1, y2, val, nums);
        }
        for(int i = 1; i <= n; i ++){
            for(int j = 1; j <= m; j ++){
                nums[i][j] += nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
                pw.print(nums[i][j] + " ");
            }
            pw.println();
        }
        pw.flush();
    }
    static void insert(int x1, int x2, int y1, int y2, int val, int[][] nums){
        nums[x1][y1] += val;
        nums[x2 + 1][y1] -= val;
        nums[x1][y2 + 1] -= val;
        nums[x2 + 1][y2 + 1] += val;
    }
}
