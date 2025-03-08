package 差分;
import java.util.*;
import java.io.*;
public class Acwing797一维差分 {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static void insert(int l, int r, int val, int[] nums){
        nums[l] += val;
        nums[++ r] -= val;
    }
    public static void main(String[] args) throws Exception{
        int n = nextInt();
        int m = nextInt();
        int[] nums = new int[n + 10];
        for(int i = 1; i <= n; i ++) {
            int x = nextInt();
            insert(i, i, x, nums);
        }
        for(int i = 1; i <= m; i ++){
            int l = nextInt();
            int r = nextInt();
            int x = nextInt();
            insert(l, r, x, nums);
        }
        for(int i = 1; i <= n; i ++){
            nums[i] += nums[i - 1];
            pw.print(nums[i] + " ");
        }
        pw.flush();
    }
}
