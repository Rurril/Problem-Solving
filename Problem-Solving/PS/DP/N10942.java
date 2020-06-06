package PS;

import java.io.*;
public class N10942 {
    private static int n;
    private static int[] arr;
    private static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[2*n][(n+2)/2];
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<n;i++)arr[i+1] = Integer.parseInt(temp[i]);
        DP();
//        for(int i=1;i<2*n;i++){
//            for(int j=0;j<dp[0].length;j++){
//                System.out.print(dp[i][j] + " ");
//            }System.out.println();
//        }
        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            // start to end 까지해서 펠린드롬을 이루는지 ?
            if(Palindrome(start, end) == 1)bw.write("1\n");
            else bw.write("0\n");
        }
        bw.flush();
    }
    private static void DP(){
        for(int i=1;i<2*n;i++){
            // i 는 memoization 시작지점
            for(int j=0;j<=Math.min(i/2, n - (i+1)/2);j++){
                // j는 길이
                int mid = 0;
                if(i%2 == 1){ // 숫자를 기준으로 구하는 것

                    if(j==0){
                        dp[i][j] = 1; // 길이가 0일 경우에는 팰린드롬이다
                        continue;
                    }else{
                        if(dp[i][j-1] == 0)break; // 그 전 길이에서 팰린드롬이 아니면 그 다음도 팰린드롬이 무조건 아니다.
                        else{
                            if(arr[(i+1)/2-j] == arr[(i+1)/2+j])dp[i][j] = 1;
                            else break;
                        }
                    }
                }else{ // 공백을 기준으로 구하는 것

                    if(j==0)continue;
                    else if(j==1){
                        if(arr[i/2] == arr[i/2+1])dp[i][j] = 1;
                        else dp[i][j] = 0;
                    }else{
                        if(dp[i][j-1] == 0)break;
                        else{
//                            System.out.println(i + " " + j);
                            if(arr[i/2 - j+1] == arr[i/2 + j])dp[i][j] = 1;
                            else break;
                        }
                    }
                }


            }
        }
    }
    private static int Palindrome(int start, int end){
        int mid = start + end - 1;
        int length = (end - start + 1)/2;

        return dp[mid][length];
    }
}