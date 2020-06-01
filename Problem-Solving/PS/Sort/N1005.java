import java.io.*;
import java.util.*;
public class N1005 {

    private static int[] times, preOrder, sum;
    private static ArrayList<Integer>[] orders;
    private static int N,K;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]); // 건물의 개수
            K = Integer.parseInt(temp[1]);

            times = new int[N + 1];
            preOrder = new int[N + 1];
            sum = new int[N + 1];
            orders = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++)
                orders[i] = new ArrayList();
            temp = br.readLine().split(" ");

            for (int j = 0; j< N; j++)
                sum[j + 1] = times[j + 1] = Integer.parseInt(temp[j]);


            for (int j = 0; j < K; j++) {
                temp = br.readLine().split(" ");
                int first = Integer.parseInt(temp[0]);
                int second = Integer.parseInt(temp[1]);

                preOrder[second]++;
                orders[first].add(second);
            }
            topologicalSort();
            int finalBuilding = Integer.parseInt(br.readLine());

            bw.write(sum[finalBuilding] + "\n");
        }
        bw.flush();
    }

    private static void topologicalSort(){

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(preOrder[i] == 0)q.add(i);
        }

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next : orders[current]){
                sum[next] = Math.max(sum[next], times[next] + sum[current]);
                if(--preOrder[next] == 0){
                    q.add(next);
                }
            }

        }

    }
}


