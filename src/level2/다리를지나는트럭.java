package level2;

import java.util.ArrayDeque;
import java.util.Queue;

public class 다리를지나는트럭 {
    public static void main(String[] args) {
        int bridgeLength = 2;
        int weight = 10;
        int[] truckWeights = { 7, 4, 5, 6 };

        System.out.println(solution(bridgeLength, weight, truckWeights));
    }

    public static int solution(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Integer> bridge = new ArrayDeque<>();
        int time = 0;
        int curWeight = 0;

        int idx = 0;
        while (idx < truckWeights.length || curWeight > 0) {
            time++;

            if (bridge.size() == bridgeLength) {
                curWeight -= bridge.poll();
            }

            if (idx < truckWeights.length) {
                int next = truckWeights[idx];
                if (curWeight + next <= weight) {
                    bridge.offer(next);
                    curWeight += next;
                    idx++;
                } else {
                    bridge.offer(0);
                }
            } else if (curWeight > 0) {
                bridge.offer(0);
            }
        }

        return time;
    }
}
