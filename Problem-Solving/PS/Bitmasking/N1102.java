import java.io.*;
import java.util.Arrays;

public class N1102 {

    private static int N;
    private static int[][] map;
    private static int[][] dp;
    private static int min;
    private static final int MAX = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N+1][1<<N]; // i번째의 비트마스킹 값 -> cost
        for(int i=1;i<=N;i++) Arrays.fill(dp[i], -1);

        for(int i=0;i<N;i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        String[] condition = br.readLine().split("");
        int bitmask = 0;
        int cnt = 0;
        for(int i=0;i<N;i++){
            if(condition[i].charAt(0) == 'Y'){
                bitmask |= (1 << i);
                cnt++; // 사용가능한 발전소가 몇개인지 확인
            }
        }

        min = Integer.parseInt(br.readLine());

        int result = min == 0 ? 0 : DFS(bitmask, cnt);

        if(cnt == 0 && min > 0)result = MAX;
        else if(cnt > min)result = 0;

        if(result == MAX)System.out.println(-1);
        else System.out.println(result);

    }

    private static int DFS(int bitmask, int count){

        if(count >= min)return 0; // 목표 개수에 도달함.

        if(dp[count][bitmask] >= 0)return dp[count][bitmask];

        dp[count][bitmask] = MAX;

        for(int i=0;i<N; i++){
            if((bitmask & ( 1<<i )) != 0){ // 발전기가 켜져있으면
                for(int j=0; j<N; j++){
                    if((bitmask & (1 << j)) == 0){ // 발전기가 꺼져있으면

                        dp[count][bitmask] = Math.min(dp[count][bitmask], DFS((bitmask | (1 << j)), count+1) + map[i][j]);
                    }
                }
            }
        }

        return dp[count][bitmask];
    }
}
