package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 오픈채팅방 {

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        System.out.println(Arrays.toString(solution(record)));
    }

    public static String[] solution(String[] record) {
        Map<String, List<Log>> map = new HashMap<>();
        int size = 0;

        for (String r : record) {
            if (r.startsWith("E")) {
                String[] s = r.split(" ");
                List<Log> logs = map.getOrDefault(s[1], new ArrayList<>());

                for (Log log : logs) {
                    log.changeNickname(s[2]);
                }

                Log newLog = new Log(s[2], "님이 들어왔습니다.", size++);
                logs.add(newLog);

                map.put(s[1], logs);
            }

            if (r.startsWith("L")) {
                String[] s = r.split(" ");
                List<Log> logs = map.get(s[1]);
                String nickname = logs.get(0).getNickname();

                Log newLog = new Log(nickname, "님이 나갔습니다.", size++);
                logs.add(newLog);
            }

            if (r.startsWith("C")) {
                String[] s = r.split(" ");
                List<Log> logs = map.get(s[1]);

                for (Log log : logs) {
                    log.changeNickname(s[2]);
                }
            }
        }

        List<Log> all = new ArrayList<>();

        for (List<Log> logs : map.values()) {
            for (Log log : logs) {
                all.add(log);
            }
        }

        all.sort((a, b) -> Integer.compare(a.getIdx(), b.getIdx()));

        List<String> ans = new ArrayList<>();

        for (Log l : all) {
            ans.add(l.getMsg());
        }

        return ans.toArray(new String[0]);
    }

    public static class Log {

        String nickname;
        String msg;
        int idx;

        public Log(String nickname, String msg, int idx) {
            this.nickname = nickname;
            this.msg = msg;
            this.idx = idx;
        }

        public String getMsg() {
            return nickname + msg;
        }

        public String getNickname() {
            return nickname;
        }

        public int getIdx() {
            return idx;
        }

        public void changeNickname(String changed) {
            this.nickname = changed;
        }

        @Override
        public String toString() {
            return getMsg();
        }
    }
}
