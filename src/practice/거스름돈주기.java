package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 거스름돈주기 {

    public static void main(String[] args) {
        int amount = 350;
        System.out.println(Arrays.toString(solution(amount)));
    }

    public static int[] solution(int amount) {
        List<Integer> list = new ArrayList<>();

        while (amount != 0) {
            if (amount >= 100) {
                amount -= 100;
                list.add(100);
            } else if (amount >= 50) {
                amount -= 50;
                list.add(50);
            } else if (amount >= 10) {
                amount -= 10;
                list.add(10);
            } else {
                amount -= 1;
                list.add(1);
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}
