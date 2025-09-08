package practice;

public class 연습장2 {

    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(solution(k, dungeons));
    }

    static int ans;
    static boolean[] visited;

    public static int solution(int k, int[][] dungeons) {
        ans = 0;
        int n = dungeons.length;
        visited = new boolean[n];

        dfs(k, dungeons, 0);

        return ans;
    }

    static void dfs(int k, int[][] dungeons, int cnt) {
        if (cnt > ans) {
            ans = cnt;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (k < dungeons[i][0]) {
                continue;
            }
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(k - dungeons[i][1], dungeons, cnt + 1);
            visited[i] = false;
        }
    }
}
