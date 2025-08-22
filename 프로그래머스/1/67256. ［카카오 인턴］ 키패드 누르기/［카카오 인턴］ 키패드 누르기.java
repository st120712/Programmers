import java.util.HashMap;
import java.util.Map;

class Solution {
    public static String solution(int[] numbers, String hand) {
    Map<Integer, int[]> keyMap = new HashMap<>();
    for (int i = 0; i < 9; i++) {
      int r = i / 3;
      int c = i % 3;
      keyMap.put(i + 1, new int[] { r, c });
    }
    keyMap.put(10, new int[] { 3, 0 });
    keyMap.put(0, new int[] { 3, 1 });
    keyMap.put(11, new int[] { 3, 2 });

    int left = 10;
    int right = 11;

    String ans = "";

    for (int n : numbers) {
      if (n == 1 || n == 4 || n == 7) {
        left = n;
        ans += "L";
      }
      if (n == 3 || n == 6 || n == 9) {
        right = n;
        ans += "R";
      }
      if (n == 2 || n == 5 || n == 8 || n == 0) {
        int[] next = keyMap.get(n);
        int[] curL = keyMap.get(left);
        int[] curR = keyMap.get(right);

        int distL = Math.abs(next[0] - curL[0]) + Math.abs(next[1] - curL[1]);
        int distR = Math.abs(next[0] - curR[0]) + Math.abs(next[1] - curR[1]);

          System.out.println(distL + " " + distR);

        if (distL > distR) {
          right = n;
          ans += "R";
        } else if (distL < distR) {
          left = n;
          ans += "L";
        } else {
          if (hand.equals("right")) {
            right = n;
            ans += "R";
          } else {
            left = n;
            ans += "L";
          }
        }
      }
    }

    return ans;
  }
}