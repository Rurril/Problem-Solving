package PS;
import java.io.*;
import java.util.regex.Pattern;

public class N2671 {

    private static int[] word;
    private static int wordIndex = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        String sound = new BufferedReader(new InputStreamReader(System.in)).readLine();
//        if(Pattern.matches("(100+1+|01)+", sound)) System.out.println("SUBMARINE");
//        else System.out.println("NOISE");
        
        String[] temp = br.readLine().split("");
        word = new int[temp.length];

        for(int i=0;i<temp.length;i++){
            word[i] = Integer.parseInt(temp[i]);
        }


        String result = "SUBMARINE";
        while(wordIndex < word.length){
            if(!check()){
                result = "NOISE";
                break;
            }
        }

        System.out.println(result);
    }

    private static boolean caseOne(){

        if(wordIndex+1 >= word.length)return false;

        if(word[wordIndex] == 0 && word[wordIndex +1] == 1){
            wordIndex += 2;
            return true;
        }
        return false;
    }

    private static boolean caseTwo(){

        if(wordIndex+2 >= word.length)return false;

        if(word[wordIndex] == 1 && word[wordIndex +1] == 0 && word[wordIndex +2] == 0){
            // 0의 반복을 허용한다.
            wordIndex += 3;
            // 0의 반복을 확인
            while(true){
                if(wordIndex >= word.length)return false; // 길이를 넘어간다면 false
                if(word[wordIndex] == 0){
                    wordIndex++;
                }else{
                    break;
                }
            }
            // 1의 반복을 확인
            while(true){
                if(wordIndex >= word.length)return true; // 자연스럽게 끝난 것, false가 아님
                if(word[wordIndex] == 1){
                    wordIndex++;
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean check(){

        boolean one = caseOne();
        boolean two = caseTwo();
        boolean hidden = false;
        if(one || two)return true;
        else{ // 하나의 히든 케이스
            if(wordIndex > 0){
                wordIndex--;
                hidden = caseTwo();
            }
            return hidden;
        }
    }
}
