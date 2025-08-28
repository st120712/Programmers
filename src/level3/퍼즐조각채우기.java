package level3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class 퍼즐조각채우기 {

    public static void main(String[] args) {
        int[][] gameBoard = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
        int[][] table = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};

        System.out.println(solution(gameBoard, table));
    }

    public static int solution(int[][] gameBoard, int[][] table) {
        // 1) 보드의 빈칸(0) 컴포넌트, 테이블의 블록(1) 컴포넌트 추출
        List<List<Point>> holes = findComponents(gameBoard, 0);
        List<List<Point>> blocks = findComponents(table, 1);

        // 2) 테이블 블록들을 "회전 허용 canonical signature"로 카운팅
        Map<String, Integer> blockCount = new HashMap<>();
        Map<String, Integer> blockSize = new HashMap<>();
        for (List<Point> b : blocks) {
            String key = canonicalSignature(b);
            blockCount.merge(key, 1, Integer::sum);
            blockSize.putIfAbsent(key, b.size());
        }

        // 3) 보드 빈칸을 같은 방식의 canonical signature로 매칭
        int filled = 0;
        for (List<Point> hole : holes) {
            String key = canonicalSignature(hole);
            Integer cnt = blockCount.get(key);
            if (cnt != null && cnt > 0) {
                filled += hole.size();          // 칸 수 더하기 (조각 개수 아님!)
                blockCount.put(key, cnt - 1);   // 사용 처리
            }
        }
        return filled;
    }

    /* ====== 컴포넌트 추출 (BFS) ====== */
    private static List<List<Point>> findComponents(int[][] board, int target) {
        int n = board.length;
        boolean[][] vis = new boolean[n][n];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        List<List<Point>> res = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (vis[r][c] || board[r][c] != target) {
                    continue;
                }

                Queue<Point> q = new ArrayDeque<>();
                List<Point> comp = new ArrayList<>();
                vis[r][c] = true;
                q.offer(new Point(r, c));

                while (!q.isEmpty()) {
                    Point cur = q.poll();
                    comp.add(cur);
                    for (int[] d : dirs) {
                        int nr = cur.r + d[0];
                        int nc = cur.c + d[1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                            continue;
                        }
                        if (vis[nr][nc] || board[nr][nc] != target) {
                            continue;
                        }
                        vis[nr][nc] = true;
                        q.offer(new Point(nr, nc));
                    }
                }
                res.add(normalize(comp)); // 좌표를 항상 정규화해 저장
            }
        }
        return res;
    }

    /* ====== 회전 허용 canonical signature ====== */
    private static String canonicalSignature(List<Point> shape) {
        // 시작은 정규화된 상태여야 한다.
        List<Point> cur = normalize(shape);
        String best = signature(cur);
        // 90, 180, 270 회전 각각 시도하여 "가장 사전순 앞선" 서명을 사용
        for (int i = 0; i < 3; i++) {
            cur = rotate90CW(cur);
            String s = signature(cur);
            if (s.compareTo(best) < 0) {
                best = s;
            }
        }
        return best;
    }

    /* ====== 90도 시계 회전 ======
       (r, c) -> (c, h-1-r), 여기서 h는 현재 도형의 높이(정규화된 상태 기준) */
    private static List<Point> rotate90CW(List<Point> pts) {
        // pts는 정규화되어 있다고 가정 (min r = 0, min c = 0)
        int h = 0, w = 0;
        for (Point p : pts) {
            h = Math.max(h, p.r);
            w = Math.max(w, p.c);
        }
        h = h + 1; // 높이
        // w는 여기선 직접 쓰지 않아도 됨

        List<Point> rotated = new ArrayList<>(pts.size());
        for (Point p : pts) {
            rotated.add(new Point(p.c, (h - 1 - p.r)));
        }
        return normalize(rotated);
    }

    /* ====== 정규화(translation-invariant) + 정렬 ====== */
    private static List<Point> normalize(List<Point> pts) {
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;
        for (Point p : pts) {
            if (p.r < minR) {
                minR = p.r;
            }
            if (p.c < minC) {
                minC = p.c;
            }
        }
        List<Point> norm = new ArrayList<>(pts.size());
        for (Point p : pts) {
            norm.add(new Point(p.r - minR, p.c - minC));
        }
        norm.sort((a, b) -> (a.r != b.r ? a.r - b.r : a.c - b.c));
        return norm;
    }

    /* ====== 서명 문자열 (좌표 내용 기반 비교용) ====== */
    private static String signature(List<Point> pts) {
        StringBuilder sb = new StringBuilder();
        for (Point p : pts) {
            sb.append(p.r).append(':').append(p.c).append(';');
        }
        return sb.toString();
    }

    /* ====== 좌표용 값 객체 ====== */
    private static final class Point {

        final int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point p = (Point) o;
            return r == p.r && c == p.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public String toString() {
            return "(" + r + "," + c + ")";
        }
    }
}
