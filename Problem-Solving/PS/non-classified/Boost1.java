import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Boost1 {

    public static void main(String[] args) throws IOException{

//        String[] name_list = {"가을", "우주", "너굴"};
//        String[] name_list = {"봄","여울","봄봄"};
        String[] name_list = {"너굴", "너울", "여울", "서울"};
        System.out.println(solution(name_list));

    }

    public static boolean solution(String[] name_list){


        boolean answer = false;

        for(int i=0;i<name_list.length;i++){
            for(int j=i+1; j<name_list.length;j++){

                if(check(name_list[i], name_list[j]))return true;
            }
        }




        return answer;
    }

    public static boolean check(String A, String B){

        // A가 B에 들어가있나.

        if(A.length() > B.length())return false;
        else if(A.length() == B.length()){
            if(A.equals(B))return true;
            else return false;
        }else{

            for(int i=0; i < B.length()-A.length();i++){


                if(A.equals(B.substring(i, i+A.length()))){

                    return true;
                }
            }
            return false;

        }
    }
}
