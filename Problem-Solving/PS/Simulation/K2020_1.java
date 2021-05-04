import java.io.*;


public class Main {

    public static String solution(int[] numbers, String hand) {
        String answer = "";


        int[] left = {3,0}; // *에 위치
        int[] right = {3,2}; // #에 위치
        for(int num : numbers){

//            System.out.print("LEFT : " + left[0] + " " + left[1]);
//            System.out.println(" RIGHT : " + right[0] + " " + right[1]);

            if(num == 1 || num == 4 || num == 7){
                answer += "L";
                left[0] = num / 3;
                left[1] = 0;
            }else if(num == 3|| num == 6 || num == 9){
                answer += "R";
                right[0] = num / 3 - 1;
                right[1] = 2;
            }else{
                if(num == 0)num = 11;
                int y = num / 3;
                int x = 1;

                double distX = Math.abs(left[0] - y) + Math.abs(left[1] - x);
                double distY = Math.abs(right[0] - y) + Math.abs(right[1] - x);
//                System.out.println("dist LEFT" + distX + " dist RIGHT" + distY);
                if(distX > distY){
                    answer += "R";
                    right[0] = y;
                    right[1] = x;
                }
                else if(distX < distY){
                    answer += "L";
                    left[0] = y;
                    left[1] = x;
                }else{ // 같을 때
                    if(hand.equals("right")){
                        answer += "R";
                        right[0] = y;
                        right[1] = x;
                    }else{
                        answer += "L";
                        left[0] = y;
                        left[1] = x;
                    }

                }

            }
        }
        return answer;
    }


    public static void main(String[] args) throws IOException {


//        이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서
//        숫자만을 입력하려고 합니다.
//        맨 처음 왼손 엄지손가락은 * 키패드에
//        오른손 엄지손가락은 # 키패드 위치에서 시작하며,
//        엄지손가락을 사용하는 규칙은 다음과 같습니다.



//        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
//        String hand = "right";

//        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
//        String hand = "left";

//        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] numbers = {0,0,0,0,0,0,0};
        String hand = "right";
        System.out.println(solution(numbers, hand));

    }



}
