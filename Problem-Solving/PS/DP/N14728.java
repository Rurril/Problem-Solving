import java.io.*;
public class N14728 {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int T = Integer.parseInt(temp[1]);

        int[]dp = new int[10001];

        while(N-- > 0){
            temp = br.readLine().split(" ");
            int K = Integer.parseInt(temp[0]);
            int S = Integer.parseInt(temp[1]);

            for (int i = T; i >= K; i--)
                dp[i] = Math.max(dp[i], dp[i - K] + S);
        }

        System.out.println(dp[T]);
    }
}
