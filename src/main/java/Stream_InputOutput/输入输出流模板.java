package Stream_InputOutput;

import java.util.*;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 输入输出流模板 {
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
        pw.println();
        pw.flush();
    }



}
