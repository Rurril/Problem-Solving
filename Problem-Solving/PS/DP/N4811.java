package PS;
import java.io.*;
public class N4811 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        while(true){
            int number = Integer.parseInt(br.readLine());

            if(number == 0)break;

            long[][] dp = new long[31][31]; // 행이 1개, 열이 0.5개

            dp[number-1][1] = 1;

            while(true){
                boolean flag = false;
                long[][] t_dp = new long[31][31];

                for(int i=0;i<=30;i++){
                    for(int j=0;j<=30;j++){

                        if(i == 0 && j == 0)continue;

                        if(dp[i][j] != 0){
                            flag = true;
                            if(i > 0)t_dp[i-1][j+1] += dp[i][j];
                            if(j > 0)t_dp[i][j-1] += dp[i][j];
                        }
                    }
                }

//                for(int i=0;i<=10;i++){
//                    for(int j=0;j<=10;j++){
//                        System.out.print(dp[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println();

                if(!flag){
                    bw.write(dp[0][0] + "\n");
                    break;
                }else{
                    dp = t_dp.clone();
                }
            }


        }
        bw.flush();
        bw.close();



    }

    private static void test1() throws IOException {
        while(true){

            int number = Integer.parseInt(br.readLine());

            if(number == 0)break;

            long[] dp = new long[61]; // 0, 0.5 ~ 29.5 ~ 30
            dp[number*2-1] = 1;

            while (true) {
                boolean flag = false;

                for(int i=1;i<=60;i++){
                    if(dp[i] != 0){
                        flag = true;

                        long num = dp[i];
                        if(i == 1){
                            dp[0] += num;
                            dp[i] = 0;
                        }else{
                            dp[i-2] += num;
                            dp[i-1] += num;
                            dp[i] = 0;
                        }
                    }
                }

                for(int i=0;i<=60;i++){
                    System.out.print(dp[i] + " ");
                }
                System.out.println();

                if(!flag)break;
            }

            bw.write(dp[0] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
