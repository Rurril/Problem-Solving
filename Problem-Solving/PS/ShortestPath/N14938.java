package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class N14938 {

    private static int n,m,r; // 지역의 개수, 수색범위, 길의 개수
    private static int[] items;
    private static int[][] map;
    private static final int INF = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        r = Integer.parseInt(temp[2]);

        items = new int[n+1];
        map = new int[n+1][n+1];
        for(int i=1;i<=n;i++) Arrays.fill(map[i], INF);

        temp = br.readLine().split(" ");
        for(int i=0;i<n;i++)items[i+1] = Integer.parseInt(temp[i]);

        for(int i=0;i<r;i++){
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);
            map[start][end] = weight;
            map[end][start] = weight;
        }

        FloyWarshall();
        int max = 0;
        for(int i=1;i<=n;i++){
            int cnt =0;
            cnt += items[i];
            for(int j=1;j<=n;j++){
                if(i==j)continue;
                if(map[i][j] <= m){
                    cnt += items[j];
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
    }

    public static void FloyWarshall(){

        for(int k=1;k<=n;k++){

            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(i==j)continue;
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }

    private static class Edge{
        int end, weight;
        Edge(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
}
