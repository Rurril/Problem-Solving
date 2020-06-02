import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N11438 {

    private static final int START = 1;
    private static final int MAXDEPTH = 17;
    private static int N, M;
    private static int[][] dp = new int[100001][MAXDEPTH];
    private static int[] depth = new int[100001];
    private static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp;
        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N+1];
        for(int i=1; i<=N;i++)list[i] = new ArrayList<>();
        for(int i=0; i<N-1;i++){
            temp = br.readLine().split(" ");
            int one = Integer.parseInt(temp[0]);
            int two = Integer.parseInt(temp[1]);

            list[one].add(two);
            list[two].add(one);
        }

        BFS();
//        DFS(1, 0);
        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M;i++){
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            bw.write(LCA(a, b) + "\n");
        }
        bw.flush();
    }

    private static int LCA(int a, int b){
        if (depth[a] > depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        for (int i = MAXDEPTH-1; i >= 0; i--) {
            if (depth[b] - depth[a] >= (1 << i))
                b = dp[i][b];
        }
        if (a == b)
            return a;
        for (int i = MAXDEPTH-1; i >= 0; i--) {
            if (dp[i][a] != dp[i][b]) {
                a = dp[i][a];
                b = dp[i][b];
            }
        }
        return dp[0][a];
    }

    private static void DFS(int node, int depth_t) {
        if (depth[node] != -1)
            return;

        depth[node] = depth_t;
        for (int next : list[node]) {
            if (depth[next] != -1)
                continue;
            dp[0][next] = node;
            for (int i = 1; i < MAXDEPTH; i++) {
                if(dp[i - 1][next] == 0) break;
                dp[i][next] = dp[i - 1][dp[i - 1][next]];
            }

            DFS(next, depth_t + 1);
        }
    }
    
    private static void BFS(){

        Queue<Integer> q = new LinkedList<>();
        q.add(START);
        depth[START] = 1;

        while(!q.isEmpty()){

            int current = q.poll();

            for(int next : list[current]){
                if(depth[next] == 0){ // 방문을 아직 하지 않은 경우.
                    depth[next] = depth[current] + 1;

                    // depth로 Ancestor 설정
                    dp[next][0] = current;
                    current = dp[current][0];
                    for(int i=1; i<MAXDEPTH; i++){
                        if(dp[current][i] == 0)break; // 루트 노드를 넘어가면 끝 .
                        dp[next][i] = dp[current][i-1];
                        current = dp[current][i-1];
                    }
                }
            }
        }
    }
}
