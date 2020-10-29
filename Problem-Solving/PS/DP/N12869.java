package PS;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N12869 {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] scvs = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scvs[i]=Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(scvs[0],scvs[1],scvs[2]));
    }

    public static int bfs(int s1, int s2, int s3) {
        int [][] damage = {{1, 3, 9}, {1, 9, 3} , {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
        Queue<int[]> q = new LinkedList<int[]>();
        boolean isVisited[][][] = new boolean[61][61][61];
        q.add(new int[] {s1, s2, s3, 0});
        isVisited[s1][s2][s3] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];
            int time = cur[3];

            if(a == 0 && b == 0 && c == 0) {
                return time;
            }

            for(int i = 0 ; i < damage.length ; i++) {
                int na = a-damage[i][0] < 0 ? 0 : a-damage[i][0];
                int nb = b-damage[i][1] < 0 ? 0 : b-damage[i][1];
                int nc = c-damage[i][2] < 0 ? 0 : c-damage[i][2];

                if(isVisited[na][nb][nc]) {
                    continue;
                }
                isVisited[na][nb][nc] = true;
                q.add(new int[] {na, nb, nc, time+1});
            }

        }
        return -1;
    }
}
