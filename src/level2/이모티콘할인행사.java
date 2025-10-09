package level2;

import java.util.Arrays;

public class 이모티콘할인행사 {

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        System.out.println(Arrays.toString(solution(users, emoticons)));
    }

    static int maxPlus = 0;
    static int maxPrice = 0;

    static int[] solution(int[][] users, int[] emoticons) {
        int n = emoticons.length;
        int[][] costs = new int[n][4];

        for (int i = 0; i < n; i++) {
            int[] discounts = new int[4];

            for (int j = 0; j < 4; j++) {
                int rate = (j + 1) * 10;
                int cost = (emoticons[i] * (100 - rate)) / 100;
                discounts[j] = cost;
            }

            costs[i] = discounts;
        }

        dfs(0, costs, users, new int[users.length]);

        return new int[]{maxPlus, maxPrice};
    }

    static void dfs(int depth, int[][] costs, int[][] users, int[] results) {
        if (depth == costs.length) {

            int plus = 0;
            int price = 0;
            for (int i = 0; i < users.length; i++) {
                if (results[i] > users[i][1]) {
                    plus++;
                } else {
                    price += results[i];
                }
            }

            if (maxPlus < plus) {
                maxPlus = plus;
                maxPrice = price;
            } else if (maxPlus == plus) {
                maxPrice = Math.max(maxPrice, price);
            }

            return;
        }

        int[] discounts = costs[depth];

        for (int i = 0; i < 4; i++) {
            int rate = (i + 1) * 10;

            for (int j = 0; j < users.length; j++) {
                int[] user = users[j];
                if (user[0] <= rate) {
                    results[j] += discounts[i];
                }
            }

            dfs(depth + 1, costs, users, results);

            for (int j = 0; j < users.length; j++) {
                int[] user = users[j];
                if (user[0] <= rate) {
                    results[j] -= discounts[i];
                }
            }
        }
    }
}
