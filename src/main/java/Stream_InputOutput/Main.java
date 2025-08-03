package Stream_InputOutput;

import java.util.concurrent.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        String s = scanner.next();
        System.out.println(maxValueAfterDeleting(s, a));
    }

    public static int maxValueAfterDeleting(String s, int[] a) {
        int n = s.length();
        Map<String, Integer> dp = new HashMap<>();
        dp.put("NN", 0); // 初始状态：前两个字符为空

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int val = a[i];
            Map<String, Integer> tmp = new HashMap<>();

            for (Map.Entry<String, Integer> entry : dp.entrySet()) {
                String state = entry.getKey();
                int currentValue = entry.getValue();

                // 情况1：不保留当前字符，状态不变
                tmp.put(state, Math.max(tmp.getOrDefault(state, Integer.MIN_VALUE), currentValue));

                // 情况2：保留当前字符，检查是否形成110
                char prevPrev = state.charAt(0);
                char prev = state.charAt(1);
                if (!(prevPrev == '1' && prev == '1' && c == '0')) {
                    String newState = String.valueOf(prev) + c;
                    int newValue = currentValue + val;
                    tmp.put(newState, Math.max(tmp.getOrDefault(newState, Integer.MIN_VALUE), newValue));
                }
            }
            dp = new HashMap<>(tmp);
        }

        return dp.values().stream().max(Integer::compare).get();
    }
}
