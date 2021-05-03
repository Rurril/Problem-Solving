import java.io.*;


public class Main {

    // 문자열 s에서 최대 부분 문자열의 길이를 출력하는 문제
    // 내가 곂쳐도 된다는 말을 제대로 읽지 않아서 햇갈렸다.
    static int getMax(String s) {
        int j = 0, n = s.length(), max = 0;
        int pi[] = new int[n];
        for(int i = 1; i < n; i++) {
            while(j > 0 && s.charAt(i) != s.charAt(j)) j = pi[j-1];
            if(s.charAt(i) == s.charAt(j)) max = Math.max(max, pi[i] = ++j);
        }

//        for(int next : pi){
//            System.out.print(next + " ");
//        }
//        System.out.println();
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        int n = word.length(), max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, getMax(word.substring(i, n)));
        }
        System.out.println(max);
    }



}
