import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        for (int i = 0; i < T; i++) {
            String s = sc.nextLine();
            System.out.println(decrypt(s));
        }
    }

    private static String decrypt(String s) {
        StringBuilder t = new StringBuilder();
        StringBuilder prev = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'R') {
                prev = new StringBuilder(t);
                t.reverse();
            } else if (c == 'Z') {
                if (prev != null) {
                    t = new StringBuilder(prev);
                    prev = null; // Z只能出现一次，处理完prev置空
                }
            } else {
                prev = new StringBuilder(t);
                t.append(c);
            }
        }
        return t.toString();
    }
}