package DP.最长上升子序列;
import java.util.*;
import java.io.*;
public class Acwing1014登山 {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    public static void main(String[] args) throws Exception{
        int n = nextInt();
        int[] f = new int[n + 1];
        int[] g = new int[n + 1];
        int[] nums = new int[n + 1];
        int res = 1;
        for(int i = 1; i <= n; i ++) nums[i] = nextInt();
        // 从前往后走
        for(int i = 1; i <= n; i ++){
            f[i] = 1;
            for(int j = 1; j < i; j ++){
                if(nums[j] < nums[i])
                    f[i] = Math.max(f[i], f[j] + 1);
            }
        }
        // 从后往前走
        for(int i = n; i >= 1; i --){
            g[i] = 1;
            for(int j = n; j > i; j --){
                if(nums[j] < nums[i])
                    g[i] = Math.max(g[i], g[j] + 1);
            }
            res = Math.max(res, f[i] + g[i] - 1);
        }
        System.out.println(res);
    }
}
