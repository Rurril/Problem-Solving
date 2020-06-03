import java.util.*;
public class N1647 {
    static int n, m, ans, x, y, cost;
    static int[] parent = new int[100001];
    static List<Edge> a = new ArrayList<>();
    static int find(int x){
        if(x==parent[x]) return x;
        return parent[x]=find(parent[x]);
    }
    static void merge(int x, int y, int cost){
        x = find(x);
        y = find(y);
        if(x==y) return;
        parent[x] = y;
        ans += cost;
        --n;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        for(int i=1; i<=n; i++)
            parent[i] = i;
        for(int i=0; i<m; i++){
            x = in.nextInt();
            y = in.nextInt();
            cost = in.nextInt();
            a.add(new Edge(x,y,cost));
        }
        Collections.sort(a);
        for(Edge e : a){
            if(n==2) break;
            merge(e.x, e.y, e.cost);
        }
        System.out.println(ans);
        in.close();
    }
    static class Edge implements Comparable<Edge>{
        int x, y, cost;
        public Edge(int x, int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}


