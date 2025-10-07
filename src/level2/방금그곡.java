package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 방금그곡 {

    // 1. 
    public static void main(String[] args) {
        String m = "ABC";
        String[] musicInfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m, musicInfos));
    }

    static String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    static Map<String, Character> noteMap = new HashMap<>();

    static {
        for (int i = 0; i < notes.length; i++) {
            noteMap.put(notes[i], (char) ('A' + i));
        }
    }

    static String solution(String m, String[] musicinfos) {
        List<Music> list = new ArrayList<>();

        m = convert(m);

        for (int i = 0; i < musicinfos.length; i++) {
            String[] arr = musicinfos[i].split(",");
            list.add(new Music(i, arr[0], arr[1], arr[2], arr[3]));
        }

        List<Music> ans = new ArrayList<>();

        for (Music music : list) {
            if (music.melody.contains(m)) {
                ans.add(music);
            }
        }

        if (ans.isEmpty()) {
            return "(None)";
        }

        ans.sort((a, b) -> {
            if (a.playTime == b.playTime) {
                return Integer.compare(a.idx, b.idx);
            }
            return Integer.compare(b.playTime, a.playTime);
        });

        return ans.get(0).title;
    }

    static class Music {

        int idx;
        int playTime;
        String title;
        String melody;

        public Music(int idx, String start, String end, String title, String melody) {
            this.playTime = toMinutes(end) - toMinutes(start);
            this.title = title;
            melody = convert(melody);

            StringBuilder sb = new StringBuilder();
            int n = melody.length();
            for (int i = 0; i < playTime / n; i++) {
                sb.append(melody);
            }

            sb.append(melody.substring(0, playTime % n));
            this.melody = sb.toString();
        }
    }

    static String convert(String m) {
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < m.length(); i++) {
            if (i + 1 < m.length() && m.charAt(i + 1) == '#') {
                list.add(m.substring(i, i + 2));
                i++;
            } else {
                list.add(m.charAt(i) + "");
            }
        }

        for (String s : list) {
            if (s.equals("B#")) {
                s = "C";
            }
            if (s.equals("E#")) {
                s = "F";
            }
            sb.append(noteMap.get(s));
        }

        return sb.toString();
    }

    static int toMinutes(String time) {
        int[] arr = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return arr[0] * 60 + arr[1];
    }
}
