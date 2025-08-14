package level2;

import java.util.Arrays;

public class 광물캐기 {

    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        System.out.println(solution(picks, minerals));
    }

    public static int solution(int[] picks, String[] minerals) {
        // 총 곡괭이 개수로 캘 수 있는 자원의 수
        int available = 0;
        for (int p : picks) {
            available += p * 5;
        }

        // 캘 수 있는 자원 양
        int availableMineralSize = available > minerals.length ? minerals.length : available;

        // 캘 수 있는 자원까지 5개의 자원을 한 그룹으로 묶음
        MineralGroup[] mineralGroups = new MineralGroup[(int) Math.ceil(availableMineralSize / 5.0)];

        for (int i = 0; i < mineralGroups.length; i++) {
            String[] temp = new String[5];
            int size = 0;

            for (int j = 0; j < 5; j++) {
                if (i * 5 + j == availableMineralSize) {
                    break;
                }

                temp[j] = minerals[i * 5 + j];
                size++;
            }

            mineralGroups[i] = new MineralGroup(Arrays.copyOf(temp, size));
        }

        // 자원 그룹을 레벨 기준 내림차순으로 정렬
        Arrays.sort(mineralGroups, (a, b) -> b.getLevel() - a.getLevel());

        // 레벨이 높은 것 부터(0번 부터) 사용가능한 곡괭이 중 가장 피로도가 덜 드는 곡괭이로 캠
        int tired = 0;

        for (MineralGroup mg : mineralGroups) {
            if (picks[0] != 0) {
                tired += diamondPick(mg.getMinerals());
                picks[0]--;
            } else if (picks[1] != 0) {
                tired += ironPick(mg.getMinerals());
                picks[1]--;
            } else {
                tired += stonePick(mg.getMinerals());
                picks[2]--;
            }
        }

        return tired;
    }

    public static class MineralGroup {

        String[] minerals;
        int level;

        public MineralGroup(String[] minerals) {
            this.minerals = minerals;
            this.level = calculateLevel(minerals);
        }

        public String[] getMinerals() {
            return minerals;
        }

        public int getLevel() {
            return level;
        }

        public static int calculateLevel(String[] minerals) {
            int level = 0;

            for (String m : minerals) {
                switch (m) {
                    case "diamond":
                        level += 25;
                        break;
                    case "iron":
                        level += 5;
                        break;
                    case "stone":
                        level += 1;
                        break;
                    default:
                        throw new AssertionError();
                }

            }

            return level;
        }

        @Override
        public String toString() {
            return String.format("minerals : %s level : %d", Arrays.toString(minerals), level);
        }
    }

    public static int stonePick(String[] mg) {
        int tired = 0;

        for (String m : mg) {
            switch (m) {
                case "diamond":
                    tired += 25;
                    break;
                case "iron":
                    tired += 5;
                    break;
                case "stone":
                    tired += 1;
                    break;
                default:
                    throw new AssertionError();
            }
        }

        return tired;
    }

    public static int ironPick(String[] mg) {
        int tired = 0;

        for (String m : mg) {
            switch (m) {
                case "diamond":
                    tired += 5;
                    break;
                case "iron":
                    tired += 1;
                    break;
                case "stone":
                    tired += 1;
                    break;
                default:
                    throw new AssertionError();
            }
        }

        return tired;
    }

    public static int diamondPick(String[] mg) {
        int tired = 0;

        for (String m : mg) {
            switch (m) {
                case "diamond":
                    tired += 1;
                    break;
                case "iron":
                    tired += 1;
                    break;
                case "stone":
                    tired += 1;
                    break;
                default:
                    throw new AssertionError();
            }
        }

        return tired;
    }
}
