package level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 주차요금계산 {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {
            "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
        };

        String result = Arrays.toString(solution(fees, records));

        System.out.println(result);
    }

    static int[] solution(int[] fees, String[] records) {
        int limit = toMinutes("23:59");

        Map<String, Integer> accum = new HashMap<>();
        Map<String, Integer> timeAt = new HashMap<>();

        for (String r : records) {
            String[] arr = r.split(" ");
            int time = toMinutes(arr[0]);
            String carNum = arr[1];
            String state = arr[2];

            if (state.equals("IN")) {
                timeAt.put(carNum, time);
            } else {
                int preTime = timeAt.get(carNum);
                timeAt.remove(carNum);
                accum.put(carNum, accum.getOrDefault(carNum, 0) + (time - preTime));
            }
        }

        for (Map.Entry<String, Integer> e : timeAt.entrySet()) {
            String carNum = e.getKey();
            int time = e.getValue();
            accum.put(carNum, accum.getOrDefault(carNum, 0) + (limit - time));
        }

        return calFee(fees, accum);
    }

    static int toMinutes(String timeStr) {
        int[] arr = Arrays.stream(timeStr.split(":")).mapToInt(Integer::parseInt).toArray();
        return arr[0] * 60 + arr[1];
    }

    static int[] calFee(int[] fees, Map<String, Integer> accum) {
        String[] keys = accum.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        int[] costs = new int[accum.size()];

        for (int i = 0; i < accum.size(); i++) {
            String key = keys[i];
            int accumTime = accum.get(key);
            int cost = fees[1];
            if (accumTime > fees[0]) {
                int overTime = accumTime - fees[0];
                if (overTime % fees[2] == 0) {
                    cost += (overTime / fees[2]) * fees[3];
                } else {
                    cost += ((overTime / fees[2]) + 1) * fees[3];
                }
            }

            costs[i] = cost;
        }

        return costs;
    }
}
