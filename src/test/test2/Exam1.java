package test.test2;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Exam1 {

    public static void main(String[] args) {
        String[] s = {"1110"};
        System.out.println(Arrays.toString(solution(s)));
    }

    public static String[] solution(String[] s) {
        int n = s.length;
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            ArrayDeque<Character> st = new ArrayDeque<>();
            int cnt110 = 0;
            for (char c : s[i].toCharArray()) {
                st.push(c);

                if (st.size() >= 3) {
                    char a = st.pop();
                    char b = st.pop();
                    char c3 = st.pop();

                    if (c3 == '1' && b == '1' && a == '0') {
                        cnt110++;
                    } else {
                        st.push(c3);
                        st.push(b);
                        st.push(a);
                    }
                }
            }

            StringBuilder sb = new StringBuilder(st.size());
            while (!st.isEmpty()) {
                sb.append(st.removeLast());
            }

            String base = sb.toString();

            int pivot = base.lastIndexOf('0');
            String insert = "110".repeat(cnt110);

            if (pivot == -1) {
                ans[i] = insert + base;
            } else {
                ans[i] = base.substring(0, pivot + 1) + insert + base.substring(pivot + 1);
            }
        }

        return ans;
    }
}
