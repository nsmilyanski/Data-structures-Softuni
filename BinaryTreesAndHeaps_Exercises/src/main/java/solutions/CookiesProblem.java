package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int k, int[] cookies) {
        Queue<Integer> queue = new PriorityQueue<>();

        for (int cookie : cookies) {
            queue.add(cookie);
        }

        Integer peek = queue.peek();

        int counter = 0;

        while (peek < k && queue.size() > 1) {
            counter++;
            Integer firstCookie = queue.poll();
            Integer secondCookie = queue.poll();

            int mixed = firstCookie + (2 * secondCookie);
            queue.add(mixed);
            peek = queue.peek();
        }
        return peek > k ? counter : -1;
    }
}
