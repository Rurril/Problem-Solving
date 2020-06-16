import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N3176 {

    private static int N, K;
    private static final int START = 1;
    private static final int MAX = 1_000_001;
    private static int[][][] parents;
    private static int[] depth;
    private static BufferedWriter bw;
    private static ArrayList<Edge>[] edges;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        parents = new int[N+1][17][3]; // 마지막 차원은 0 은 parent 1 은 최소거리 2는 최대거리
        edges = new ArrayList[N+1];
        depth = new int[N+1];
        for(int i=1;i<=N;i++)edges[i] = new ArrayList<>();

        for(int i=1;i<=N;i++){
            for(int j=1; j<17; j++){
                parents[i][j][1] = MAX;
            }
        }

        for(int i=0;i<N-1;i++){
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            int v = Integer.parseInt(temp[2]);
            edges[s].add(new Edge(e, v));
            edges[e].add(new Edge(s, v));
        }

        BFS();
        setTable();

        K = Integer.parseInt(br.readLine());

        for(int i=0;i<K;i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            LCA(a, b);
        }
        bw.flush();
    }

    private static void LCA(int a, int b) throws IOException {

        int min = MAX;
        int max = 0;
        if(depth[a] < depth[b]){ // 깊은쪽을 a로 설정한다.
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b]; // 차이만큼 단계를 올려야 한다.
        int cnt = 0;
        while(diff > 0){

            if(diff % 2 == 1){
                min = Math.min(min, parents[a][cnt][1]);
                max = Math.max(max, parents[a][cnt][2]);
                a = parents[a][cnt][0];
            }
            cnt++;
            diff /= 2;
        } // 반복문이 끝나면, a와 b는 같은 위치에 올라와 있게 되는 것

        if(a == b){
            bw.write(min + " " + max + "\n");
        }else{

            for(int i=16; i>=0; i--){
                if(parents[a][i][0] != parents[b][i][0]){ // 다른 부분이 나오면 점프

                    int minA = parents[a][i][1];
                    int minB = parents[b][i][1];
                    int maxA = parents[a][i][2];
                    int maxB = parents[b][i][2];
                    min = Math.min(minB, Math.min(min , minA));
                    max = Math.max(maxB, Math.max(max , maxA));
                    a = parents[a][i][0];
                    b = parents[b][i][0];
                }
            }

            // 마지막 바로 위 조상만을 남겨뒀으므로 점프

            int minA = parents[a][0][1];
            int minB = parents[b][0][1];
            int maxA = parents[a][0][2];
            int maxB = parents[b][0][2];
            min = Math.min(minB, Math.min(min , minA));
            max = Math.max(maxB, Math.max(max , maxA));

            bw.write(min + " " + max + "\n");
        }
    }

    private static void setTable(){
        for(int j=1; j<17; j++){
            for(int i=1; i<=N; i++){
                parents[i][j][0] = parents[parents[i][j-1][0]][j-1][0];
                parents[i][j][1] = Math.min(parents[i][j-1][1], parents[parents[i][j-1][0]][j-1][1]);
                parents[i][j][2] = Math.max(parents[i][j-1][2], parents[parents[i][j-1][0]][j-1][2]);
            }
        }
    }

    private static void BFS(){
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N+1];
        q.add(START);
        isVisited[START] = true;
        depth[START] = 1;

        while(!q.isEmpty()){
            int current = q.poll();

            for(Edge next : edges[current]){
                int nextE = next.e;
                int nextV = next.v;

                if(isVisited[nextE])continue;
                isVisited[nextE] = true;

                depth[nextE] = depth[current] + 1;
                parents[nextE][0][0] = current;
                parents[nextE][0][1] = nextV; // 최소거리
                parents[nextE][0][2] = nextV; // 최대거리
                q.add(nextE);
            }
        }
    }

    static class Edge{
        int e, v;
        Edge(int e, int v){
            this.e = e;
            this.v = v;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "e=" + e +
                    ", v=" + v +
                    '}';
        }
    }
}
