package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N11438_sub {

    private static int K = 0;
    private static int N;
    private static int[][] parent;
    private static int[] depth;
    private static ArrayList<Integer>[] adList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

//        K max값 구하기
        for(int i = N; i > 0; i/=2, K++);

        depth = new int[N+1];
        parent = new int[K+1][N+1];
        adList = new ArrayList[N+1];
        for(int i=1; i<=N;i++){
            adList[i] = new ArrayList();
            depth[i] = -1;
        }

        // 데이터 입력, 간선노드 만들기
        for(int i=1; i<N; i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            adList[a].add(b);
            adList[b].add(a);
        }

        //depth랑 parent 배열 채우기
        BFS();
        setParent();

        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            bw.write(LCA(a, b) + "\n");
        }
        bw.flush();
    }

    private static int LCA(int a, int b){
        //depth 가 a가 더 낮으면 더 깊은것으로 swap
        if(depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        //높이 차이 계산
        int diff = depth[a] - depth[b];
        //ex) diff = 11 // 11 = 2^3 + 2^1 + 2^0
        int k = 0;
        while (diff >= 1) {
            if (diff%2 == 1) {
                a = parent[k][a];
            }
            diff /= 2;
            k++;
        }

        //위로 올라가 b와 동일한 값이 나오면 a는 LCA 임
        if (a == b) {
            return a;
        }

        //남은 부분은 남은 값으로 점프
        for (k = K-1; k > -1; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        return parent[0][a];


    }

    private static void setParent(){
        for(int i=1; i<=K; i++){
            for(int j=1; j<=N; j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }
    }

    private static void BFS(){
        int start = 1;

        Queue<Integer> q = new LinkedList();
        q.add(start);

        //최초 시작점의 부모는 0
        parent[0][start] = 0;

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next : adList[current]){
                if(next != parent[0][current]){ // 부모방향으로 갈 수 없음.
                    depth[next] = depth[current] + 1;

                    parent[0][next] = current;
                    q.add(next);
                }
            }
        }
    }
}
