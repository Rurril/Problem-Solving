package PS;
import java.io.*;
import java.util.Arrays;

public class N5582 {

    private static char[] first;
    private static char[] second;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();
        if(B.length() < A.length()){
            String temp = A;
            A = B;
            B = temp;
        }
        first = A.toCharArray();
        second = B.toCharArray();

        dp = new int[first.length+1][second.length+1];


        int max = 0;
        for(int i=0;i<first.length;i++){
            for(int j=0;j<second.length;j++){
                if(first[i] == second[j]){
                    dp[i+1][j+1] = dp[i][j] + 1;

                    if(max < dp[i+1][j+1])max = dp[i+1][j+1];
                }
            }
        }

        System.out.println(max);

//        for(int i=0;i<dp.length;i++){
//            for(int j=0;j<dp[0].length;j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }


    }

}
