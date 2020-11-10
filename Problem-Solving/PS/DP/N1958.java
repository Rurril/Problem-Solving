import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1958 {

    private static int[][][] dp;
    private static char[] a,b,c;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        c = br.readLine().toCharArray();

        dp = new int[a.length+1][b.length+1][c.length+1];

        // dp[i][j][k] = dp[i-1][j-1][k-1] + 1
        solution();
    }

    private static void solution(){

        int max = 0;
        for(int i=1;i<=a.length;i++){
            for(int j=1;j<=b.length;j++){

                for(int k=1;k<=c.length;k++){

                    if(a[i-1] == b[j-1] && b[j-1] == c[k-1]){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }else{
                        int tmp1 = Math.max(Math.max(dp[i-1][j-1][k], dp[i-1][j][k-1]), dp[i][j-1][k-1]);
                        int tmp2 = Math.max(Math.max(dp[i][j][k-1], dp[i][j-1][k]), dp[i-1][j][k]);
                        dp[i][j][k] = Math.max(tmp1,tmp2);
                    }
//                    System.out.print(dp[i][j][k]);
                }
//                System.out.println();
            }
//            System.out.println();
//            System.out.println();
        }
        System.out.println(dp[a.length][b.length][c.length]);

    }
}
