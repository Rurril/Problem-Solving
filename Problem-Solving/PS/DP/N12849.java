package PS;
import java.io.*;


public class N12849 {

    private static int D;
    private static final int MOD = 1_000_000_007;
    private static int[] dp = new int[8];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        D = Integer.parseInt(br.readLine());
        dp[0] = 1;

        System.out.println(move());
    }

    private static int move(){
        int t0,t1,t2,t3,t4,t5,t6,t7;
        while(D-- > 0){
            t0 = (dp[1] + dp[2])%MOD;
            t1 = ((dp[0] + dp[2])%MOD + dp[3])%MOD;
            t2 = (((dp[0] + dp[1])%MOD + dp[3])%MOD + dp[4])%MOD;
            t3 = (((dp[1] + dp[2])%MOD + dp[4])%MOD + dp[5])%MOD;
            t4 = (((dp[2] + dp[3])%MOD + dp[5])%MOD + dp[7])%MOD;
            t5 = ((dp[3] + dp[4])%MOD + dp[6])%MOD;
            t6 = (dp[5] + dp[7])%MOD;
            t7 = (dp[4] + dp[6])%MOD;
            dp[0] = t0;
            dp[1] = t1;
            dp[2] = t2;
            dp[3] = t3;
            dp[4] = t4;
            dp[5] = t5;
            dp[6] = t6;
            dp[7] = t7;
        }
        return dp[0];
    }


}
