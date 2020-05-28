package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class N1325 {
    private static int n,m;
    private static ArrayList<Integer>[] edges;
    private static int[] results;
    private static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        results = new int[n+1];
        edges = new ArrayList[n+1];
        for(int i=1;i<=n;i++)edges[i] = new ArrayList<>();

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
        }

        int max = 0;
        for(int i=1;i<=n;i++){
            check = new boolean[n+1];
            DFS(i);
        }

        for(int i=1;i<=n;i++){
            max = Math.max(max, results[i]);
        }

        for(int i = 1 ; i <= n ; i++){

            if(results[i]==max){
                System.out.print(i+" ");
            }
        }
    }

    private static void DFS(int v){
        check[v]=true;
        for(int next : edges[v]){
            if(!check[next]){
                DFS(next);
                results[next]++;
            }
        }
    }
}
