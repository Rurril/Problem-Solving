import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main{



    //TODO: 이분탐색으로 길이를 구하고, check함수로, 모든 보석을 갖고 있는지를 확인하도록 한다.

    //TODO : 슬라이딩 윈도우 방식으로 풀어보자.


    private static int size;

    public static int[] solution(String[] gems) {
        HashSet<String> set = new HashSet<String>();
        for(String gem : gems)set.add(gem);

        size = set.size();
//        System.out.println(size);

        int[] answer ={0, gems.length-1};
        int left = 0;
        int right = size-1;
        while(true){

            if(check(left, right, gems)){ // 모든 보석을 갖고 있다고 한다면

                if(right - left < answer[1] - answer[0]){
//                    System.out.println(right + " " + left);
                    answer[0] = left; answer[1] = right;
                }

                left++;
                if(left > right || right - left < size-1)right++;
            }else{ // 하나라도 갖고 있지 않다고 한다면
                right++;
                if(right >= gems.length)break;
            }

            if(left >= gems.length || right >= gems.length)break;
        }
        answer[0]++; answer[1]++;
        return answer;
    }

    public static boolean check(int left, int right, String[] gems){
        HashSet<String> set = new HashSet<>();

        //        System.out.println(left + " " + right);
        for(int i = left; i <= right; i++){
            set.add(gems[i]);
        }

//        System.out.print(left-1 + " " + right-1 + " ");
//        for(String next : set)System.out.print(next + " " );
//        System.out.println();

        if(set.size() == size)return true;
        else return false;
    }

//    public static int[] solution(String[] gems) {
//
//        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
//        for(String gem : gems)hashMap.put(gem, false);
//
//        int size = hashMap.size();
//
//
//
//        int[] answer = {};
//        return answer;
//    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//        String[] gems = {"XYZ", "XYZ", "XYZ"};
//        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] ret = solution(gems);

        System.out.println(ret[0] + " " + ret[1]);


    }
}