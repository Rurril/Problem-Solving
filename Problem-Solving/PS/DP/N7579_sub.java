package PS;
import java.io.*;

public class N7579_sub {

    private static int N, M;
    private static int[] memories, costs;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        int result = Integer.MAX_VALUE;
        memories = new int[N];
        costs = new int[N];
        dp = new int[N][10001]; //
        temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)
            memories[i] = Integer.parseInt(temp[i]);

        temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)
            costs[i] = Integer.parseInt(temp[i]);

        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            int cost = costs[i];
            int memory = memories[i];

            for(int j=0; j<=10000;j++){ // j 가 cost값을 의미.

                // 입력 값이 아직 없을 때는 예외 처리
                if (i == 0) {
                    if(j >= cost)dp[i][j] = memory;
                }else{
                    if(j >= cost)
                        dp[i][j] = Math.max(dp[i-1][j-cost] + memory, dp[i-1][j]);
                    else dp[i][j] = dp[i-1][j];
                }

                if(dp[i][j] >= M)min = Math.min(min, j);
            }
        }
        System.out.println(min);


    }
}
