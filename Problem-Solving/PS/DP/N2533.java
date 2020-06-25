import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class N2533 {

    private static final int MAX = 1000001;
    private static final int START = 1;
    private static int N;
    private static int[][] dp = new int[MAX][2];
    private static boolean[] visited = new boolean[MAX];
    private static LinkedList<Integer>[] edges = new LinkedList[MAX];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for(int i=1;i< MAX; i++){
            edges[i] = new LinkedList();
        }

        for(int i=1;i <N;i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            edges[a].add(b);
            edges[b].add(a);
        }

        DFS(START);
        System.out.println(Math.min(dp[START][0], dp[START][1]));

//        for(int i=1;i<=N;i++){
//            System.out.print(dp[i][0] + " ");
//        }
//        System.out.println();
//        System.out.println();
//        for(int i=1;i<=N;i++){
//            System.out.print(dp[i][1] + " ");
//        }
//        System.out.println();

    }

    private static void DFS(int number){
        visited[number] = true;
        dp[number][0] = 0; // 얼리어답터 일때
        dp[number][1] = 1; // 얼리어답터가 아닐때

        for(int next : edges[number]){

            if(!visited[next]){
                DFS(next);
                dp[number][0] += dp[next][1];
                dp[number][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }

    }


}
