package level2;

import java.util.Arrays;

public class 구명보트 {

    public static void main(String[] args) {
        int[] people = {30, 30, 30, 70, 90, 90};
        int limit = 100;

        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boats = 0;

        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
                j--;
            } else {
                j--;
            }
            boats++;
        }

        return boats;
    }
}
