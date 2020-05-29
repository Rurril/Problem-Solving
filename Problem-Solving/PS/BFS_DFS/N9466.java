package PS;
import java.io.*;
public class N9466 {

    static int[] arr;
    static boolean[] visit, done;
    static int cnt;

    private static void dfs(int v) {
        if(visit[v]) { // 방문했는데 또 온 경우라면, cycle이 있기에 가능한 것.
            done[v] = true;
//            System.out.println(v);
            cnt++;
        }else visit[v] = true;

        if(!done[arr[v]]) dfs(arr[v]);
        visit[v] = false;
        done[v] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            cnt = 0;
            arr = new int[n+1];
            visit = new boolean[n+1];
            done = new boolean[n+1];
            String[] temp = br.readLine().split(" " );
            for(int i = 1; i <= n; i++) arr[i] = Integer.parseInt(temp[i-1]);
            for(int i = 1; i <= n; i++) if(!done[i]) dfs(i);

            bw.write(n - cnt + "\n");
        }
        bw.flush();
    }


}
