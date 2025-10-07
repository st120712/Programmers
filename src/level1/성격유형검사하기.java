package level1;

import java.util.HashMap;
import java.util.Map;

public class 성격유형검사하기 {

    public static void main(String[] args) {
        String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices = {5, 3, 2, 7, 5};
        System.out.println(solution(survey, choices));
    }

    static String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('R', 0);
        map.put('T', 0);

        map.put('C', 0);
        map.put('F', 0);

        map.put('J', 0);
        map.put('M', 0);

        map.put('A', 0);
        map.put('N', 0);

        for (int i = 0; i < survey.length; i++) {
            char[] arr = survey[i].toCharArray();
            int choice = choices[i];

            int score = 4 - choice;

            map.put(arr[0], map.get(arr[0]) + score);
        }

        StringBuilder sb = new StringBuilder();

        if (map.get('R') < map.get('T')) {
            sb.append('T');
        } else {
            sb.append('R');
        }

        if (map.get('C') < map.get('F')) {
            sb.append('F');
        } else {
            sb.append('C');
        }

        if (map.get('J') < map.get('M')) {
            sb.append('M');
        } else {
            sb.append('J');
        }

        if (map.get('A') < map.get('N')) {
            sb.append('N');
        } else {
            sb.append('A');
        }

        return sb.toString();
    }
}
