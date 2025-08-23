import java.util.HashSet;
import java.util.Set;

class Solution {
    public static int solution(int[] nums) {
    int N = nums.length;
    int G = N / 2;

    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }

    if (set.size() >= G) {
      return G;
    } else {
      return set.size();
    }
  }
}