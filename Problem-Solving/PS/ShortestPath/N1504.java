package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class N1504 {

    private static final int INF = 300_000_000;
    private static int n,e;
    private static int[] dist;
    private static boolean[] check;
    private static ArrayList<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        e = Integer.parseInt(temp[1]);
        edges = new ArrayList[n+1];
        dist = new int[n+1];
        check = new boolean[n+1];

        for(int i=1;i<=n;i++)edges[i] = new ArrayList<>();

        for(int i=0;i<e;i++){
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            // 방향이 없는 그래프이므로 양 방향을 넣어준다.
            edges[start].add(new Edge(end, weight));
            edges[end].add(new Edge(start, weight));
        }

        temp = br.readLine().split(" ");
        int required1 = Integer.parseInt(temp[0]);
        int required2 = Integer.parseInt(temp[1]);

        int res = solve(required1, required2);
        System.out.println(res);


    }

    private static int solve(int required1, int required2){
        int result1 = 0;
        int result2 = 0;

        // 각각의 필수 경로를 구해서 최솟값을 결과값으로 설정한다.
        result1 += dijkstra(1, required1) + dijkstra(required1, required2) + dijkstra(required2, n);
        result2 += dijkstra(1, required2) + dijkstra(required2, required1) + dijkstra(required1, n);

        if(result1 >= INF && result2 >= INF)return -1;
        else return Math.min(result1, result2);
    }

    private static int dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        Arrays.fill(check, false);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int curNode = edge.e;
            int curWeight = edge.v;

            if(check[curNode])continue; // 방문한 노드라면 제외
            check[curNode] = true;

            for(int i=0; i < edges[curNode].size(); i++){
                int nNode = edges[curNode].get(i).e;
                int nWeight = edges[curNode].get(i).v;
                // 방문하지 않았고 && 기존의 계산된 거리보다 새로운 거리가 작을 경우
                if(!check[nNode] && dist[nNode] > curWeight + nWeight){
                    dist[nNode] = curWeight + nWeight;
                    pq.add(new Edge(nNode, dist[nNode]));
                }
            }
        }
        return dist[end];
    }

    // PQ에 넣기 위해서 Comparable을 상속해주고, compareTo를 구현해준다.
    static class Edge implements Comparable<Edge>{

        int e, v;
        Edge(int e, int v){
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return this.v - o.v;
        }
    }

}
