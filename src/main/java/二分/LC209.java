package 二分;

// (前缀和+二分) O(nlogn)
// 二分区间长度
public class LC209 {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] s = new int[n + 1];
        for(int i = 1; i <= n; i ++) s[i] += s[i - 1] + nums[i - 1];
        if(s[n] < target) return 0;
        // 二分
        int l = 1, r = n;
        while(l < r){
            int mid = l + r >> 1;
            if(check(s, mid, n, target)) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    public boolean check(int[] s, int length, int n, int target){
        for(int i = length; i <= n; i ++){
            if(s[i] - s[i - length] >= target)
                return true;
        }
        return false;
    }
}
