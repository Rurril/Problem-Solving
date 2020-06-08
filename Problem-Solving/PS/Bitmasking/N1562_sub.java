import java.io.*;

public class N1562_sub {
    final static int MOD = 1_000_000_000;
    private static int n, visit=1<<10;
    private static long[][][] dp = new long[101][10][visit];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        DP();
    }

    private static void DP() {
        long sum = 0;
        for(int i=1; i<10; i++) {
            dp[1][i][1<<i] = 1;
        }

        for(int i=2; i<=n; i++) {
            for(int j=0; j<=9; j++) {
                for(int k=0; k<visit; k++) {
                    int bitmask = k | (1<<j);
                    dp[i][j][bitmask] = (dp[i][j][bitmask] + ((0<j ? dp[i-1][j-1][k] : 0) + (j<9 ? dp[i-1][j+1][k] : 0))%MOD)%MOD;
                }
            }
        }
        for(int i=0; i<10; i++) sum = (sum + dp[n][i][visit-1])%MOD;
        System.out.println(sum);
    }
}