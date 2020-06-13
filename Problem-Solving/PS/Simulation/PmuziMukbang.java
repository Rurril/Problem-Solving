

import java.util.ArrayList;
import java.util.Arrays;

public class PPmuziMukbang {

    public static void main(String[] args) {
        int[] food_times = {30, 30, 30, 31 };
        long k = 120;
        System.out.println(solution(food_times, k));
    }
    public static int solution(int[] food_times, long k) {

        int answer = -1;

        int[] foods = food_times.clone();
        Arrays.sort(foods);

        long totalEat = 0;
        int cnt = 0;
        int eatSize = 0; // 접시마다 먹은 양
        int remain = food_times.length;
        while(true){

            if(cnt == foods.length){
                return -1; // 다 먹어서 더 이상 먹을 음식이 없음.
            }

            totalEat += ((long)(foods[cnt] - eatSize))*(long)remain; // 만큼의 시간이 흐른 것
            if(totalEat > k){ // 남은 음식들을 모두 먹을 사이클이 되지 않은 경우. 끝
                totalEat -= ((long)(foods[cnt] - eatSize))*(long)remain; // 다시 원상 복구
//                System.out.println(totalEat);
                answer = eatRemains(food_times, eatSize, k -totalEat); // eatSize 이하의 접시들은 모두 먹어치운 것, 그리고 남은 먹을 음식들
                break;
            }
//            System.out.println(totalEat);
            eatSize = foods[cnt];
            cnt++;
            remain--;

        }


        return answer;
    }

    private static int eatRemains(int[] foods, int eatSize, long remainSize){

        ArrayList<Integer> remainDishs = new ArrayList<>();

        for(int i=0;i<foods.length;i++){
            if(foods[i] <= eatSize)continue;
            remainDishs.add(i+1);
        }

        long order = remainSize % remainDishs.size();
        return remainDishs.get((int)order);

//        while(true){
//
////            System.out.println(remainSize);
//            pointer++;
//            if(pointer == foods.length)pointer = 0;
//            if(foods[pointer] <= eatSize)continue;
//
//            if(remainSize == 0){
//                result = pointer+1;
//                break;
//            }
//
//            remainSize--;
//        }
//
//        return result;
    }

}
