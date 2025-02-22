package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem6 {
    public static List<String> solution(List<List<String>> forms) {


        boolean[] chk = new boolean[forms.size()];
        calcChk(forms, chk);
//        System.out.println(Arrays.toString(chk));
        List<String> output = calcOutput(forms, chk);
        output.sort(String::compareTo);

        return output;
    }

    // chk 배열을 이용해서 중복 닉네임을 가진 크루의 이메일 을 List에 저장
    private static List<String> calcOutput(List<List<String>> forms, boolean[] chk) {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < forms.size(); i++) {
            if(chk[i])
                output.add(forms.get(i).get(0));
        }
        return output;
    }

    // KMP 알고리즘을 사용해서 chk 배열 계산
    private static void calcChk(List<List<String>> forms, boolean[] chk) {
        for (int i = 0; i < forms.size(); i++) {
            List<String> pattern = forms.get(i);

            for (int j = i + 1; j < forms.size(); j++) {
                List<String> parent = forms.get(j);
                if (KMP(parent.get(1), pattern.get(1))) {
                    chk[i] = true;
                    chk[j] = true;
                }
            }
        }
    }

    // KMP를 위한 테이블 생성 메서드
    static int[] makeTable(String pattern) {
        int n = pattern.length();
        int[] table = new int[n];

        int idx=0;
        for (int i = 1; i < n; i++) {

            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(idx)) {
                idx += 1;
                table[i] = idx;
            }
        }
        return table;
    }

    // KMP 알고리즘 메서드
    static boolean KMP(String parent, String pattern) {
        int[] table = makeTable(pattern);

        int n1 = parent.length();
        int n2 = pattern.length();

        int idx = 0;
        for(int i=0; i< n1; i++) {
            while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
                idx = table[idx-1];
            }
            if(parent.charAt(i) == pattern.charAt(idx)) {
                if(idx>=1) {
//                    System.out.println("겹침!!");
                    return true;
                }
                if(idx == n2-1) {
                    idx =table[idx];
                }else {
                    idx += 1;

                }
            }
        }
        return false;
    }
}