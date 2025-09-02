package level3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 다단계칫솔판매 {

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        String master = "minho";
        Map<String, String> tree = new HashMap<>();
        Map<String, Integer> money = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String ref = referral[i];
            String enr = enroll[i];
            money.put(enr, 0);
            if (ref.equals("-")) {
                ref = master;
            }

            tree.put(enr, ref);
        }

        for (int i = 0; i < seller.length; i++) {
            String sell = seller[i];
            int amo = amount[i] * 100;
            while (amo > 0 && !sell.equals(master)) {
                int fee = amo / 10;
                money.put(sell, money.get(sell) + amo - fee);
                sell = tree.get(sell);
                amo = fee;
            }
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = money.get(enroll[i]);
        }

        return ans;
    }
}
