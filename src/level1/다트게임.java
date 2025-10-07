package level1;

import java.util.ArrayList;
import java.util.List;

public class 다트게임 {

    // 1 .총 3회
    // 2. 각 점수는 0 ~ 10점
    // 3. 제곱수 S : 1, D : 2, T : 3
    // 4. 옵션 (* 해당 점수와 바로 이전 점수를 각각 2배), (# 해당 점수가 -)
    // 4-1. 처음에 *가 나오면 해당 점수만 2배
    // 4-2. *는 모든 조건에서 중첩가능
    // 5. S, D, T는 점수마다 하나씩만 존재함
    // 6. *,#이 동시에 존재하는 점수는 없다.
    public static void main(String[] args) {
        String dartResult = "1S2D*3T";
        System.out.println(solution(dartResult));
    }

    static int solution(String dartResult) {
        StringBuilder sb = new StringBuilder();
        char[] arr = dartResult.toCharArray();

        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (Character.isDigit(c)) {
                sb.append(c);
                continue;
            }

            int score = Integer.parseInt(sb.toString());
            if (c == 'S') {
                score = (int) Math.pow(score, 1);
            } else if (c == 'D') {
                score = (int) Math.pow(score, 2);
            } else if (c == 'T') {
                score = (int) Math.pow(score, 3);
            }

            try {
                char nc = arr[i + 1];
                if (Character.isDigit(nc)) {
                    scores.add(score);
                    sb = new StringBuilder();
                    continue;
                }
                if (nc == '*') {
                    score *= 2;
                    if (!scores.isEmpty()) {
                        scores.set(scores.size() - 1, scores.get(scores.size() - 1) * 2);
                    }
                } else if (nc == '#') {
                    score *= -1;
                }
                scores.add(score);
                sb = new StringBuilder();
                i++;
            } catch (Exception e) {
                scores.add(score);
                break;
            }
        }

        int ans = 0;

        for (int i = 0; i < scores.size(); i++) {
            ans += scores.get(i);
        }

        return ans;
    }
}
