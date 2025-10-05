package level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 주차요금계산 {
  public static void main(String[] args) {
    int[] fees = { 180, 5000, 10, 600 };
    String[] records = {
        "06:00 0000 IN", // 0000
        "06:34 0000 OUT",
        "05:34 5961 IN", // 5961
        "07:59 5961 OUT",
        "07:59 0148 IN", // 0148
        "19:09 0148 OUT",
        "18:59 0000 IN", // 0000
        "22:59 5961 IN", // 5961
        "23:00 5961 OUT"
    };

    String result = Arrays.toString(solution(fees, records));

    System.out.println(result);

  }

  static int[] solution(int[] fees, String[] records) {
    Map<String, Integer> accum = new HashMap<>();
    Map<String, Integer> timeAt = new HashMap<>();

    

    return new int[0];
  }
}
