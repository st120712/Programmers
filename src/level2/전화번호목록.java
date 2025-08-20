package level2;

import java.util.Arrays;

public class 전화번호목록 {

    public static void main(String[] args) {

        String[] phoneBook = {"123", "456", "789"};

        System.out.println(solution(phoneBook));
    }

    public static boolean solution(String[] phoneBook) {
        Arrays.sort(phoneBook);

        for (int i = 0; i < phoneBook.length - 1; i++) {
            if (phoneBook[i + 1].startsWith(phoneBook[i])) {
                return false;
            }
        }

        return true;
    }
}
