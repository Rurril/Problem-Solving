package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class N1922 {

    private static int N;
    private static int[] parent;
    private static int result = 0;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i=1;i<=N;i++)parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();


        for(int i=0;i<M;i++){
            String[] temp = br.readLine().split(" ");
            int com1 = Integer.parseInt(temp[0]);
            int com2 = Integer.parseInt(temp[1]);
            int volume = Integer.parseInt(temp[2]);

            pq.add(new Edge(com1, com2, volume));
        }


        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            int a = find(edge.start);
            int b = find(edge.end);

            if(a == b)continue;

            union(a,b);
            result += edge.volume;
        }
        System.out.println(result);

    }

    private static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot){
            parent[a] = b;
        }else return;
    }

    private static int find(int node){
        if(node == parent[node])return node;
        return parent[node] = find(parent[node]);
    }

    private static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int volume;
        Edge(int start, int end, int volume){
            this.start = start;
            this.end = end;
            this.volume = volume;
        }

        @Override
        public int compareTo(Edge o) {
            return this.volume - o.volume;
        }
    }

}
