import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class N11437 {

    private static int N, M;
    private static int[] parent, depth;
    private static LinkedList<Integer>[] list;
    private static final int START = 1; // 루트는 1번
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new LinkedList[N+1];
        parent = new int[N+1];
        depth = new int[N+1];

        for(int i=1;i<=N;i++)list[i] = new LinkedList();

        //간선의 정보 입력
        for(int i=0; i<N-1; i++){
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            list[x].add(y);
            list[y].add(x);
        }

        BFS(); // 깊이와 부모에 대한 정보를 BFS를 통해서 입력

        M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            String[] temp = br.readLine().split(" ");
            int x = Integer.parseInt(temp[0]);
            int y = Integer.parseInt(temp[1]);

            // 깊이가 더 깊은 쪽을 y로 설정한다.
            if(depth[x] > depth[y]){
                int tmp = x;
                x = y;
                y = tmp;
            }
            // 같은 부모가 될때까지 올라간다
            while(depth[x] != depth[y]){
                y = parent[y];
            }

            if(x == y){
                bw.write(x + "\n");
            }else{

                while(parent[x] != parent[y]){
                    x = parent[x];
                    y = parent[y];
                }
                bw.write(parent[x] + "\n");
            }


        }
        bw.flush();

    }

    private static void BFS(){ // BFS를 통해서 부모와, 깊이에 대한 정보를 입력
        Queue<Integer> q = new LinkedList<>();
        q.add(START);
        parent[START] = START;
        depth[START] = START;

        while(!q.isEmpty()){

            int current = q.poll();

            for(int next : list[current]){
                if(parent[next] == 0){
                    q.add(next);
                    parent[next] = current;
                    depth[next] = depth[current] + 1;
                }
            }
        }
    }
}
