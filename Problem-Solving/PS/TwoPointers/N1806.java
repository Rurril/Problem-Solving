package PS;
import java.io.*;
public class N1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int min = Integer.parseInt(temp[1]);

        temp = br.readLine().split(" ");
        int[] arr = new int[n+1];
        int[] dp = new int[n+1];
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(temp[i]);
        dp[0] = 0;
        dp[1] = arr[0];
        for(int i=2;i<=n;i++) dp[i] = dp[i-1] + arr[i-1];

        int s = 1;
        int e = 1;
        int result = 1_000_000;
        while(true){
            int sum = dp[e] - dp[s-1];
            if(sum >= min){
                if(result > (e - s + 1))result = (e - s + 1); // 길이 비교
                s++;
            }else if(sum < min){
                e++;
            }

            if(e == n+1)break;
        }

        if(result == 1_000_000) System.out.println(0);
        else System.out.println(result);

    }
}
