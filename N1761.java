import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N1761 {

    private static int[] depth;
    private static int[][][] parents;
    private static ArrayList<Edge>[] edges;
    private static int N;
    private static final int K = 16;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        depth = new int[N+1];
        parents = new int[N+1][K][2];
        edges = new ArrayList[N+1];
        for(int i=1;i<=N;i++)edges[i] = new ArrayList();

        for(int i=1;i<N;i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);
            int volume = Integer.parseInt(temp[2]);

            edges[a].add(new Edge(b, volume));
            edges[b].add(new Edge(a, volume));
        }

        BFS();
        setParent();

//        for(int i=1; i<=N;i++){
//            for(int j=0; j<K;j++){
//                System.out.print(parents[i][j][0] + " " + parents[i][j][1] + " / ");
//            }System.out.println();
//        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]);
            int b = Integer.parseInt(temp[1]);

            bw.write(LCA(a, b) + "\n");
        }
        bw.flush();

    }

    private static int LCA(int a, int b){

        int length = 0;

        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }// a를 더 깊은 것으로 설정

        // 높이를 맞춰준다.
        int diff = depth[a] - depth[b];
        int jump = 0;
        while(diff > 0){
            if(diff % 2 == 1){
                length += parents[a][jump][1];
                a = parents[a][jump][0];
            }
            diff /= 2;
            jump++;
        }

        if(a == b){
            return length;
        }else{

            for(int i=K-1; i >=0; i--){
                if(parents[a][i][0] != parents[b][i][0]){
                    length += parents[a][i][1] + parents[b][i][1];
                    a = parents[a][i][0];
                    b = parents[b][i][0];
                }
            }

            length += parents[a][0][1] + parents[b][0][1];
            return length;
        }
    }

    private static void setParent(){

        for(int i=1; i<K; i++){
            for(int j=1; j<=N; j++){
                parents[j][i][0] = parents[parents[j][i-1][0]][i-1][0];
                if(parents[j][i][0] == 0)continue;
                parents[j][i][1] = parents[j][i-1][1] + parents[parents[j][i-1][0]][i-1][1]; // 부모와의 길이 측정
            }
        }
    }

    private static void BFS(){

        int start = 1;
        depth[start] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){

            int current = q.poll();

            for(Edge next : edges[current]){
                if(depth[next.end] == 0){

                    depth[next.end] = depth[current] + 1;
                    parents[next.end][0][0] = current; // parent을 나타냄
                    parents[next.end][0][1] = next.volume; // parent와의 거리를 나타냄.
                    q.add(next.end);
                }
            }

        }
    }

    private static class Edge{
        int end;
        int volume;
        private Edge(int end, int volume){
            this.end = end;
            this.volume = volume;
        }

    }
}
