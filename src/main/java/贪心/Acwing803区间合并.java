package 贪心;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
public class Acwing803区间合并 {
    static StreamTokenizer sti = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int nextInt() throws Exception {
        sti.nextToken();
        return (int) sti.nval;
    }

    public static void main(String[] args) throws Exception {
    }
    class PIIS implements Comparable<PIIS>{
        int first, second;
        PIIS(int first, int second){
            this.first = first;
            this.second = second;
        }
        @Override
        public int compareTo(PIIS o) {
            return Integer.compare(first, o.first);
        }
    }
}
