import java.util.*;

public class Solution {

    public static void main(String[] args) {

        int[] arr = {6, 6, 6, 6, 6, 6, 6, 6};
        int[] result = solution(5, arr);

        for (int i = 0; i < result.length; i++) {
            System.out.println("" + result[i]);
        }

    }

    public static int[] solution(int N, int[] stages) {

        int[] answer = new int[N];
        int[] failer = new int[stages.length];
        int[] challenger = new int[stages.length];
        List<Double> failRate = new ArrayList<>();
        Map<Double, List<Integer>> failMap = new HashMap<>();

        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < stages[i]; j++) {
                if (j == stages.length)
                    break;
                challenger[j]++;
            }
            failer[stages[i] - 1]++;
        }

        for (int i = 0; i < N; i++) {

            double rate = 0;

            if (challenger[i] != 0) {
                rate = (double) failer[i] / challenger[i];
            } else {
                rate = 0;
            }

            if (!failRate.contains(rate)) {
                failRate.add(rate);
                List<Integer> idxs = new ArrayList<>();
                idxs.add(i + 1);
                failMap.put(rate, idxs);
            } else {
                failMap.get(rate).add(i + 1);
            }

        }

        Collections.sort(failRate);

        int idx = 0;

        for (int i = failRate.size() - 1; i >= 0; i--) {
            double rate = failRate.get(i);
            List<Integer> list = failMap.get(rate);
            if (list != null) {
                Collections.sort(list);
                for (int j = 0; j < list.size(); j++) {
                    answer[idx++] = list.get(j);
                }
            }
        }

        return answer;

    }

}
