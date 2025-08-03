package Stream_InputOutput;

import java.util.*;

class Raindrop implements Comparable<Raindrop> {
    int x, y;

    Raindrop(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Raindrop other) {
        return Integer.compare(this.y, other.y);
    }
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            List<Raindrop> raindrops = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                raindrops.add(new Raindrop(x, y));
            }
            Collections.sort(raindrops);

            boolean possible = true;
            int currentY = -1;
            boolean currentGroupValid = true;
            for (Raindrop rd : raindrops) {
                if (rd.y != currentY) {
                    currentY = rd.y;
                    currentGroupValid = (rd.x == k);
                } else {
                    currentGroupValid &= (rd.x == k);
                }
                if (!currentGroupValid) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                System.out.println(-1);
                continue;
            }

            double maxV = 0;
            int previousTime = 0;
            for (int i = 0; i < raindrops.size(); i++) {
                Raindrop rd = raindrops.get(i);
                if (i == 0) {
                    previousTime = rd.y;
                    int distance = Math.abs(rd.x - k);
                    maxV = Math.max(maxV, (double) distance / rd.y);
                } else {
                    int deltaTime = rd.y - previousTime;
                    previousTime = rd.y;
                    int distance = Math.abs(rd.x - k);
                    if (deltaTime == 0) continue;
                    maxV = Math.max(maxV, (double) distance / deltaTime);
                }
            }

            int minV = (int) Math.ceil(maxV);
            System.out.println(minV);
        }
    }
}
