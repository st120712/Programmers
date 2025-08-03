
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 충돌위험찾기 {

    public static void main(String[] args) {
        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {4, 2}, {4, 3}};

        System.out.println(solution(points, routes));
    }

    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;

        RobotMap robotMap = new RobotMap();

        for (int i = 0; i < routes.length; i++) {
            int[][] routePos = new int[routes[i].length][];

            for (int j = 0; j < routes[i].length; j++) {
                routePos[j] = Arrays.copyOf(points[routes[i][j] - 1], points[routes[i][j] - 1].length);
            }

            Robot robot = new Robot(routePos);
            robotMap.addRobot(robot);
        }

        while (robotMap.getNumOfRobots() != 0) {
            answer += robotMap.run();
        }

        return answer;
    }

    public static class Robot {

        private final int[][] routePos;
        private int[] destinationPos;
        private int destinationIndex;
        private final int[] curPos;
        private boolean stanbyShutdown = false;

        public Robot(int[][] routePos) {
            this.routePos = routePos;
            curPos = routePos[0];
            destinationPos = routePos[1];
            destinationIndex = 1;
        }

        public int[] getCurPos() {
            return curPos;
        }

        public int move() {
            if (stanbyShutdown) {
                return 1;
            }

            int diffR = destinationPos[0] - curPos[0];
            int diffC = destinationPos[1] - curPos[1];

            if (diffR > 0) {
                curPos[0]++;
            } else if (diffR < 0) {
                curPos[0]--;
            } else if (diffC > 0) {
                curPos[1]++;
            } else if (diffC < 0) {
                curPos[1]--;
            }

            if (Arrays.equals(curPos, destinationPos)) {
                if (routePos.length - 1 != destinationIndex) {
                    destinationPos = routePos[++destinationIndex];
                } else {
                    stanbyShutdown = true;
                }
            }

            return 0;
        }
    }

    public static class RobotMap {

        private Robot[] robots = new Robot[10];
        private int numOfRobots = 0;

        public RobotMap() {
        }

        public void addRobot(Robot robot) {
            if (robots.length == numOfRobots) {
                grow();
            }

            robots[numOfRobots++] = robot;
        }

        public void removeRobot(Robot robot) {
            int index = indexOf(robot);

            if (index == -1) {
                throw new RuntimeException("존재하지 않는 로봇은 삭제할 수 없습니다.");
            }

            for (int i = index; i < numOfRobots - 1; i++) {
                robots[i] = robots[i + 1];
            }
            numOfRobots--;
        }

        public Robot[] getRobots() {
            return Arrays.copyOf(robots, numOfRobots);
        }

        public int getNumOfRobots() {
            return numOfRobots;
        }

        public int checkDanger() {
            int danger = 0;
            Map<String, Integer> map = new HashMap<>();

            if (numOfRobots < 2) {
                return danger;
            }

            for (Robot r : getRobots()) {
                String key = Arrays.toString(r.getCurPos());

                map.put(key, map.getOrDefault(key, 0) + 1);
            }

            for (int count : map.values()) {
                if (count > 1) {
                    danger++;
                }
            }

            return danger;
        }

        public int run() {
            Robot[] removeReserved = new Robot[robots.length];
            int size = 0;
            int danger = checkDanger();

            System.out.print(danger + " ");

            for (Robot r : getRobots()) {
                System.out.print(Arrays.toString(r.getCurPos()) + " ");

                int shutdown = r.move();

                if (shutdown == 1) {
                    removeReserved[size++] = r;
                }
            }

            System.out.println();

            for (int i = 0; i < size; i++) {
                removeRobot(removeReserved[i]);
            }

            return danger;
        }

        private int indexOf(Robot robot) {
            for (int i = 0; i < numOfRobots; i++) {
                if (robots[i] == robot) {
                    return i;
                }
            }

            return -1;
        }

        private void grow() {
            robots = Arrays.copyOf(robots, numOfRobots * 2);
        }
    }
}
