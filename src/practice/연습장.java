package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 연습장 {

    public static void main(String[] args) {
    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> map = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            Genre g = map.getOrDefault(genres[i], new Genre(genres[i]));
            g.addSong(new int[]{i, plays[i]});
            map.put(genres[i], g);
        }

        Genre[] arr = map.values().toArray(new Genre[0]);
        Arrays.sort(arr, (a, b) -> Integer.compare(b.getTotalView(), a.getTotalView()));

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            List<int[]> songs = arr[i].getSongs();
            if (songs.size() == 1) {
                ans.add(songs.get(0)[0]);
            } else {
                ans.add(songs.get(0)[0]);
                ans.add(songs.get(1)[0]);
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public static class Genre {

        String title;
        List<int[]> songs;
        int totalView = 0;

        public Genre(String title) {
            this.title = title;
            this.songs = new ArrayList<>();
        }

        public void addSong(int[] song) {
            int view = song[1];
            totalView += view;
            songs.add(song);

            songs.sort((a, b) -> {
                if (a[1] != b[1]) {
                    return Integer.compare(b[1], a[1]);
                }
                return Integer.compare(a[0], b[0]);
            });
        }

        public String getTitle() {
            return title;
        }

        public List<int[]> getSongs() {
            return songs;
        }

        public int getTotalView() {
            return totalView;
        }
    }
}
