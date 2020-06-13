package PS;
import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class N6497 {

    private static int N,M;
    private static int total;
    private static int[] parent;
    private static PriorityQueue<Edge> edges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            total = 0;
            if(N == 0 && M == 0)break;

            parent = new int[N+1];
            for(int i=1;i<=N;i++)parent[i] = i; // 자기자신의 부모는 자기로 설정

            for(int i=0;i<M;i++){
                temp = br.readLine().split(" ");
                int s = Integer.parseInt(temp[0]);
                int e = Integer.parseInt(temp[1]);
                int v = Integer.parseInt(temp[2]);

                edges.add(new Edge(s, e, v));
                total += v;
            }

            Kruskal();
        }

    }
    private static void Kruskal(){

        int result = 0;
        while(!edges.isEmpty()){
            Edge edge = edges.poll();
//            System.out.println(edge.s + " " + edge.e +  " " + edge.v);

            int a = find(edge.s);
            int b = find(edge.e);
            if(a == b)continue; // 사이클을 이룬다는 뜻

            union(a , b);
            result += edge.v;
//            System.out.println(result);
        }
        System.out.println(total - result);
    }

    private static int find(int n){
        if(parent[n] == n)return n;
        return parent[n] = find(parent[n]);
    }

    private static void union(int a, int b){

        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)return;
        // 두 개의 부모가 같다는 것은, 사이클을 이루는 것이라는 것
        parent[a] = b;
    }

    static class Edge implements Comparable<Edge> {
        int s, e, v;

        Edge(int s, int e, int v){
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Edge o) {
            return this.v - o.v;
        }
    }
}
