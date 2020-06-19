package PS;
import java.io.*;
import java.util.Arrays;

public class N1509 {

    private static final int MAX = 2500;
    private static int[][] dp = new int[MAX][MAX];
    private static char[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        words = br.readLine().toCharArray();
        for(int i=0;i<MAX;i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 1;
        }

        palindrome();
        System.out.println(dp[0][words.length-1]);
    }

    private static void palindrome(){

        for(int i=0;i<words.length;i++){
            if(isPalindrome(0, i))dp[0][i] = 1;

            for(int j=0;j<i;j++){
                if(isPalindrome(j+1, i))dp[j+1][i] = 1;
                else dp[j+1][i] = i-j;
                dp[0][i] = Math.min(dp[0][i], dp[0][j] + dp[j+1][i]);
            }
        }

    }

    private static boolean isPalindrome(int start, int end){
        while(start < end){
            if(words[start++] != words[end--])return false;
        }
        return true;
    }
}
