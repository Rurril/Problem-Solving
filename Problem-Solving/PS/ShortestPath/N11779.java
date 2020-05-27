package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class N11779 {
    private static final long INF = 20_000_000_000l;
    private static int n,e;
    private static long[] dist;
    private static boolean[] isVisited;
    private static int[] way;
    private static ArrayList<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        e = Integer.parseInt(br.readLine());

        way = new int[n+1];
        dist = new long[n+1];
        isVisited = new boolean[n+1];
        edges = new ArrayList[n+1];
        for(int i=1;i<=n;i++)edges[i] = new ArrayList<>();

        String[] temp;
        for(int i=0;i<e;i++){
            temp = br.readLine().split(" ");
            edges[Integer.parseInt(temp[0])].add(new Edge(Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }
        temp = br.readLine().split(" ");
        int start = Integer.parseInt(temp[0]);
        int end = Integer.parseInt(temp[1]);
        Dijkstra(start, end);
    }

    private static void Dijkstra(int start, int end){
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int currentNode = edge.end;
            long currentWeight = edge.weight;

            if(isVisited[currentNode])continue;
            isVisited[currentNode] = true;
//            System.out.println(currentNode  + " " + currentWeight);
            for(int i=0; i < edges[currentNode].size();i++){
                int nextNode = edges[currentNode].get(i).end;
                long nextWeight = edges[currentNode].get(i).weight;
                if(!isVisited[nextNode] && dist[nextNode] > currentWeight + nextWeight){
                    dist[nextNode] = currentWeight + nextWeight;
                    pq.add(new Edge(nextNode, dist[nextNode]));
                    way[nextNode] = currentNode;
                }
            }
        }

        System.out.println(dist[end]);
        ArrayList<Integer> pass = new ArrayList<>();

        int next = end;

        while(true){
            pass.add(next);
            if(next == start)break;
            next = way[next];
        }

        System.out.println(pass.size());
        for(int i=pass.size()-1;i>=0;i--) System.out.print(pass.get(i) + " ");
        System.out.println();

    }

    private static class Edge implements Comparable<Edge>{

        int end;
        long weight;
        Edge(int end, long weight){
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight > 0 ? 1 : -1;
        }
    }
}
