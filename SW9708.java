import java.io.*;
import java.util.Arrays;

public class SW9708 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        int cnt = 1;
        while(tc-- > 0){

            int n =Integer.parseInt(br.readLine());
            int result = 1;

            int[] arr = new int[n];
            int[] dp = new int[1_000_001];
            String[] temp =br.readLine().split(" " );
            for(int i=0;i<n;i++){
                arr[i] = Integer.parseInt(temp[i]);
                dp[arr[i]] = 1;
            }

            Arrays.sort(arr);

            for(int i=0;i<n;i++){

                int tmp = arr[i]*2;

                while(tmp <= arr[n-1]){

                    if(dp[tmp] != 0 && tmp % arr[i] == 0){
                        dp[tmp] = Math.max(dp[tmp], dp[arr[i]] + 1);
                        result = Math.max(result, dp[tmp]);
                    }
                    tmp += arr[i];
                }
            }

            bw.write("#" + cnt+ " " + result +"\n");
            cnt++;
        }
        bw.flush();
    }
}
