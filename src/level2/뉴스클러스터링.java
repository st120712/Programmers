package level2;

import java.util.HashMap;
import java.util.Map;

public class 뉴스클러스터링 {

    // 1. 자카드 유사도 : 교집합 크기 / 합집합 크기
    // 2. 두 집합 모두 공집합인 경우 자카드 유사도 1
    // 3. 원소가 중복으로 들어가 있을 경우 확장 가능
    // 3-1. 교집합은 min, 합집합은 합과 각원소들의 max
    // 4. 대소문자 구분 없음
    // 5. 두 문자를 2글자씩 잘라 다중 집합을 만들고 자카드 유사도 계산
    // 5-1. 원소의 두 문자 중 하나 이상의 특수문자가 포함된 경우 그 원소를 집합에서 제외함
    // 6. 자카드 유사도는 소수 이므로 65536을 곱하여 소수점 아래를 버린 정수로 리턴
    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        System.out.println(solution(str1, str2));
    }

    static int MOD = 65536;

    static int solution(String str1, String str2) {
        Map<String, Integer> cnt1 = new HashMap<>();
        Map<String, Integer> cnt2 = new HashMap<>();

        toMutiset(str1, cnt1);
        toMutiset(str2, cnt2);
        int inter = 0;
        int union = 0;

        for (Map.Entry<String, Integer> entry : cnt1.entrySet()) {
            String key = entry.getKey();
            if (cnt2.get(key) != null) {
                inter += Math.min(cnt2.get(key), entry.getValue());
            }
        }

        for (Map.Entry<String, Integer> entry : cnt1.entrySet()) {
            String key = entry.getKey();
            if (cnt2.get(key) != null) {
                union += Math.max(cnt2.get(key), entry.getValue());
            } else {
                union += entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : cnt2.entrySet()) {
            String key = entry.getKey();
            if (cnt1.get(key) == null) {
                union += entry.getValue();
            }
        }

        System.out.println(union);

        if (union == 0) {
            return 1 * MOD;
        }

        return (int) (((float) inter) / union * MOD);
    }

    static void toMutiset(String s, Map<String, Integer> map) {
        for (int i = 0; i < s.length() - 1; i++) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);

            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                String e = (c1 + "" + c2).toLowerCase();
                map.put(e, map.getOrDefault(e, 0) + 1);
            }
        }
    }
}
