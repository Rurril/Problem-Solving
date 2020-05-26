package PS;
import java.io.*;
import java.util.PriorityQueue;

public class N1197 {

    static int n,m;
    static int[] parent;
    static PriorityQueue<edge> pq = new PriorityQueue<edge>();
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);

        parent = new int[n+1];

        for(int i=0;i<n+1;i++) parent[i] = i;

        for(int i=0;i<m;i++){
            temp = br.readLine().split(" ");
            pq.add(new edge(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
        }

        for(int i=0;i<m;i++){
            edge edge = pq.poll();

            int a = find(edge.s);
            int b = find(edge.e);

            if(a==b)continue; // 부모가 같다는 것은 사이클을 이루게 하는 간선이라는 것이므로 제외.
            union(a , b);
            result += edge.v;
        }

        System.out.println(result);

    }

    // 크루스칼의 기본 union - find 알고리즘
    public static int find(int a){
        if(a == parent[a])return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }

    public static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot != bRoot){
            parent[aRoot] = b;
        }else{
            return;
        }
    }


    static class edge implements Comparable<edge>{
        int s,e,v;

        edge(int s, int e, int v){
            this.s = s;
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(edge o) {
            return o.v >= this.v ? -1 : 1;
        }
    }
}
