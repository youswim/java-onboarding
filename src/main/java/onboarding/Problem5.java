package onboarding;

import java.util.Collections;
import java.util.List;

public class Problem5 {
    public static List<Integer> solution(int money) {
        List<Integer> answer = Collections.emptyList();
        return answer;
    }


    private static void calcMoneyNum(int money, List<Integer> output, int[] arr) {
        for (int j : arr) {
            output.add(money / j);
            money %= j;
        }
    }
}