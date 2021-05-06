import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main{



    //TODO: 이분탐색으로 길이를 구하고, check함수로, 모든 보석을 갖고 있는지를 확인하도록 한다.

    //TODO : 슬라이딩 윈도우 방식으로 풀어보자.
    // 결국 슬라이딩 윈도우 방식으로 인덱스를 조절하면서 풀고, set과 map을 활용해서 O(1)내로 보석들이 다 있는지를 확인하도록 하였다. 


    private static int size;
    private static HashMap<String, Integer> map;
    private static HashSet<String> set;
    private static boolean flag = false;

    public static int[] solution(String[] gems) {
        map = new HashMap<String, Integer>();
        set = new HashSet<String>();

        for(String gem : gems)map.put(gem, 0);

        size = map.size();
//        System.out.println(size);



        for(int i=0;i<size;i++){
            map.put(gems[i], map.get(gems[i])+1);
            set.add(gems[i]);
        }

        if(set.size() == map.size())flag = true;

        int[] answer ={0, gems.length-1};

        int left = 0;
        int right = size-1;
        while(true){

//            System.out.println(answer[0] + " " + answer[1]);
//            System.out.print(left + " " +right + " ");
//            for(String next : set)System.out.print(next + " ");
//            System.out.println();
//            System.out.println(map);

            if(flag){ // 모든 보석을 갖고 있다고 한다면

                if(right - left < answer[1] - answer[0]){ // 길이가 더 짧은 값이 등장했다면
//                    System.out.println(right + " " + left);
                    answer[0] = left; answer[1] = right;
                }

                // 왼쪽에 있는 보석 제거
                int value = map.get(gems[left]) - 1;
                map.put(gems[left], value);

                if(value == 0){
                    set.remove(gems[left]);
                    flag = false;
                }

                left++;

            }else{ // 하나라도 갖고 있지 않다고 한다면
                right++;
                if(right >= gems.length)break;

                int value = map.get(gems[right]) + 1;
                map.put(gems[right], value);
                set.add(gems[right]);

                if(set.size() == map.size())flag = true;


            }

            if(left >= gems.length || right >= gems.length)break;
        }
        answer[0]++; answer[1]++;
        return answer;
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
//        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//        String[] gems = {"XYZ", "XYZ", "XYZ"};
        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        int[] ret = solution(gems);

        System.out.println(ret[0] + " " + ret[1]);


    }
}