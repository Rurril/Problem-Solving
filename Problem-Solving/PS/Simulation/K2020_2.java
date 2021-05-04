

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


// 그냥 빡 구현 문제... 재미도 없고 감동도 없고
// 구현력을 물어보는 문제였다. 
public class Main {

    public static long solution(String expression) {
        long answer = 0;
        ArrayList<Long> num = new ArrayList<>();
        ArrayList<Character> tt = new ArrayList<>();

        int index = 0;
        while(true){
//            System.out.println(expression);
            if(expression.charAt(index) =='-'
                    || expression.charAt(index) == '+'
                    || expression.charAt(index) == '*'){
                num.add(Long.parseLong(expression.substring(0,index)));
                tt.add(expression.charAt(index));
                expression = expression.substring(index+1);
                index = 0;
            }
            index++;

            if(expression.length() == index) {
                num.add(Long.parseLong(expression));
                break;
            }
        }
//        for(long i : num)System.out.print(i + " " );
//        System.out.println();
//        for(char i : tt)System.out.print(i + " " );
//        System.out.println();

        char[][] prior = {{'-','+','*'}
                ,{'-','*','+'}
                ,{'+','-','*'}
                ,{'+','*','-'}
                ,{'*','-','+'}
                ,{'*','+','-'}};

        long[] input1 = new long[num.size()];
        char[] input2 = new char[tt.size()];
        for(int i=0;i<num.size();i++)input1[i] = num.get(i);
        for(int i=0;i<tt.size();i++)input2[i] = tt.get(i);

        for(int i=0;i<6;i++){
            answer = Math.max(answer, Math.abs(result(input1, input2, prior[i])));
        }

        return answer;
    }

    public static long result(long[] numbers, char[] tt, char[] prior){


        ArrayList<Long> list = new ArrayList();
        ArrayList<Character> ttt = new ArrayList();

        for(long i : numbers)list.add(i);
        for(char i : tt)ttt.add(i);

        loop : for(char next : prior){

            for(int i=0;i<ttt.size();i++){

//                System.out.println(next + " " + i);
//                for(long value : list)System.out.print(value + " ");
//                System.out.println();

                if(list.size() == 1)break loop;

                if(ttt.get(i) == next){
                    list.set(i, math(list.get(i), list.get(i+1), next));
                    list.remove(i+1);
                    ttt.remove(i);
                    i = -1;
                }
            }
        }

        return list.get(0);
    }

    public static long math(long a, long b, char c){
        if(c == '+'){
            return a+b;
        }else if(c == '-'){
            return a-b;
        }else{ // (c == '*')
            return a*b;
        }
    }


    public static void main(String[] args) throws IOException {


        String hand = "100-200*300-500+20";
        System.out.println(solution(hand));

    }



}
