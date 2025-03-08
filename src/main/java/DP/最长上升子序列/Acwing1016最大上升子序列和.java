package DP.最长上升子序列;

import java.util.*;
import java.io.*;
public class Acwing1016最大上升子序列和 {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception{
        sti.nextToken();
        return (int)sti.nval;
    }
    public static void main(String[] args) throws Exception{
        int n = nextInt();
        int[] nums = new int[n + 1];
        int[] f = new int[n + 1];
        int res = 0;
        for(int i = 1; i <= n; i ++) nums[i] = nextInt();
        for(int i = 1; i <= n; i ++){
            f[i] = nums[i];
            for(int j = 1; j < i; j ++){
                if(nums[j] < nums[i])
                    f[i] = Math.max(f[i], f[j] + nums[i]);
            }
            res = Math.max(f[i], res);
        }
        System.out.println(res);
    }
}
