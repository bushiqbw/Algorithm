package 差分;

import java.util.*; import java.io.*; public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static int nextInt() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    static double nextDouble() throws IOException {
        st.nextToken();
        return st.nval;
    }
    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }

    public static void main(String[] args) throws IOException {
        //输出
        String s = nextString();
        StringBuilder sb = new StringBuilder(s);
        sb.delete(0,2);
        System.out.println(sb);
    }
    public class Solution {
        public String decodeString(String s) {
            StringBuilder t = new StringBuilder();
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    if (p == 0) {
                        p = c - '0';
                    } else {
                        p = p * 10 + (c - '0');
                    }
                } else {
                    int len = t.length();
                    if (len > 0) {
                        int shift = p % len;
                        if (shift < 0) {
                            shift += len;
                        }
                        if (shift > 0) {
                            String part = t.substring(0, shift);
                            t.delete(0, shift);
                            t.append(part);
                        }
                    }
                    p = 0;
                    if (c == 'B') {
                        t.reverse();
                    } else {
                        t.append(c);
                    }
                }
            }
            return t.toString();
        }
    }
}