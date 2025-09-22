package test.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exam3 {

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(Arrays.toString(solution(today, terms, privacies)));
    }

    static Map<String, Integer> Terms = new HashMap<>();

    public static int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();
        for (String t : terms) {
            String kind = t.split(" ")[0];
            int term = Integer.parseInt(t.split(" ")[1]);
            Terms.put(kind, term);
        }

        for (int i = 0; i < privacies.length; i++) {
            if (!validate(today, privacies[i])) {
                list.add(i + 1);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    static boolean validate(String today, String privacy) {
        String days = privacy.split(" ")[0];
        String kind = privacy.split(" ")[1];
        int term = Terms.get(kind);

        YYMMDD itoday = new YYMMDD(today);
        YYMMDD iday = new YYMMDD(days);
        iday.go(term);

        return itoday.toString().compareTo(iday.toString()) > 0 ? false : true;
    }

    static class YYMMDD {

        int year;
        int month;
        int day;

        public YYMMDD(String days) {
            String[] arr = days.split("\\.");
            year = Integer.parseInt(arr[0]);
            month = Integer.parseInt(arr[1]);
            day = Integer.parseInt(arr[2]);
        }

        public void go(int term) {
            if (term >= 12) {
                year += term / 12;
                term %= 12;
            }
            month += term;
            if (month > 12) {
                year += 1;
                month -= 12;
            }
            day -= 1;
            if (day == 0) {
                month -= 1;
                day = 28;
            }
            if (month == 0) {
                year -= 1;
                month = 12;
            }
        }

        @Override
        public String toString() {
            String m = month + "";
            String d = day + "";
            if (month < 10) {
                m = "0" + month;
            }
            if (day < 10) {
                d = "0" + day;
            }
            return String.format("%s.%s.%s", year, m, d);
        }
    }
}
