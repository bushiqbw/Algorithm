import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine(); // 跳过第一行的换行符
        while (T-- > 0) {
            String s = sc.nextLine();
            System.out.println(decrypt(s));
        }
        sc.close();
    }

    private static String decrypt(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        boolean isReversed = false;
        Object[] prevAction = null; // 记录最近一次可撤销的操作，格式为 [类型, 参数]

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'R') {
                // 记录之前的反转状态，并切换当前状态
                prevAction = new Object[]{"R", isReversed};
                isReversed = !isReversed;
            } else if (c == 'Z') {
                if (prevAction != null) {
                    String type = (String) prevAction[0];
                    if (type.equals("R")) {
                        // 撤销反转操作，恢复之前的反转状态
                        isReversed = (boolean) prevAction[1];
                    } else if (type.equals("APPEND")) {
                        // 撤销添加操作，根据之前的位置删除字符
                        boolean addedToFront = (boolean) prevAction[1];
                        if (addedToFront) {
                            deque.pollFirst();
                        } else {
                            deque.pollLast();
                        }
                    }
                    prevAction = null; // Z操作后清空prevAction
                }
            } else {
                // 根据当前反转状态决定添加到头还是尾
                boolean addedToFront = isReversed;
                if (addedToFront) {
                    deque.addFirst(c);
                } else {
                    deque.addLast(c);
                }
                prevAction = new Object[]{"APPEND", addedToFront};
            }
        }

        // 构建结果字符串
        StringBuilder sb = new StringBuilder();
        if (isReversed) {
            // 反向遍历
            Iterator<Character> it = deque.descendingIterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        } else {
            // 正向遍历
            for (Character ch : deque) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}