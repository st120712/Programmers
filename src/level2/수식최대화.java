package level2;

import java.util.ArrayList;
import java.util.List;

public class 수식최대화 {

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }

    static long solution(String expression) {
        long[] ans = new long[6];

        List<String> list = splitExpression(expression);
        ans[0] = Long.parseLong(mul(sub(sum(list))).get(0));
        ans[1] = Long.parseLong(mul(sum(sub(list))).get(0));
        ans[2] = Long.parseLong(sub(mul(sum(list))).get(0));
        ans[3] = Long.parseLong(sum(mul(sub(list))).get(0));
        ans[4] = Long.parseLong(sub(sum(mul(list))).get(0));
        ans[5] = Long.parseLong(sum(sub(mul(list))).get(0));
        long max = 0;

        for (long a : ans) {
            max = Math.max(max, Math.abs(a));
        }

        return max;
    }

    static List<String> splitExpression(String expression) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (!Character.isDigit(c)) {
                list.add(c + "");
                continue;
            }

            StringBuilder sb = new StringBuilder();
            while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                sb.append(expression.charAt(i++));
            }
            list.add(sb.toString());
            i--;
        }

        return list;
    }

    static List<String> sum(List<String> list) {
        List<String> returns = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("+")) {
                String n = list.get(i + 1);
                String pn = returns.remove(returns.size() - 1);
                s = "" + (Long.parseLong(pn) + Long.parseLong(n));
                i++;
            }
            returns.add(s);
        }

        return returns;
    }

    static List<String> sub(List<String> list) {
        List<String> returns = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("-")) {
                String n = list.get(i + 1);
                String pn = returns.remove(returns.size() - 1);
                s = "" + (Long.parseLong(pn) - Long.parseLong(n));
                i++;
            }
            returns.add(s);

        }

        return returns;
    }

    static List<String> mul(List<String> list) {
        List<String> returns = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.equals("*")) {
                String n = list.get(i + 1);
                String pn = returns.remove(returns.size() - 1);
                s = "" + (Long.parseLong(pn) * Long.parseLong(n));
                i++;
            }
            returns.add(s);
        }

        return returns;
    }
}
