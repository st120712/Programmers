package level3;

import java.util.Arrays;

public class 표현가능한이진트리 {

    public static void main(String[] args) {
        long[] numbers = {63, 111, 95};
        System.out.println(Arrays.toString(solution(numbers)));
    }

    static int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            String s = toPaddedBinary(numbers[i]);
            ans[i] = isValidTree(s) ? 1 : 0;
        }

        return ans;
    }

    static boolean isValidTree(String s) {
        return check(s, 0, s.length() - 1, false);
    }

    static boolean check(String s, int l, int r, boolean parentZero) {
        int mid = (l + r) >>> 1;
        char root = s.charAt(mid);

        if (parentZero && root == '1') {
            return false;
        }

        if (l == r) {
            return true;
        }

        boolean nextParentZero = (root == '0');
        return check(s, l, mid - 1, nextParentZero) && check(s, mid + 1, r, nextParentZero);
    }

    static String toPaddedBinary(long x) {
        String s = Long.toBinaryString(x);
        int len = 1;
        while (len < s.length()) {
            len = (len << 1) + 1;
        }

        int pad = len - s.length();
        if (pad == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < pad; i++) {
            sb.append('0');
        }
        return sb.append(s).toString();
    }
}
