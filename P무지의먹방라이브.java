package PS;

import java.util.Arrays;

public class P무지의먹방라이브 {

    public static void main(String[] args) {
        int[] food_times = {30, 15, 26};
        long k = 60;
        System.out.println(solution(food_times, k));
    }
    public static int solution(int[] food_times, long k) {

        int answer = 0;

        int[] foods = food_times.clone();
        Arrays.sort(foods);

        long totalEat = 0;
        int cnt = 0;
        int eatSize = 0; // 접시마다 먹은 양
        int remain = food_times.length;
        while(true){

            totalEat += (foods[cnt] - eatSize)*remain; // 만큼의 시간이 흐른 것
            if(totalEat > k){ // 남은 음식들을 모두 먹을 사이클이 되지 않은 경우. 끝
                totalEat -= (foods[cnt] - eatSize)*remain; // 다시 원상 복구
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
        // @TODO
        // 이부분이 제대로 while문으로 반복해서 작동하도록 바꾸자.
        System.out.println(eatSize + " " + remainSize);
        int result = 0;
        for(int i=0;i<foods.length;i++){

            if(foods[i] <= eatSize)continue;

            if(remainSize == 0)result = i+1;
            remainSize--;
        }

        return result;
    }

}
