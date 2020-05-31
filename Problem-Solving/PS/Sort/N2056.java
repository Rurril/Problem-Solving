package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N2056 {

    private static int N;
    private static int[] preWorks, times, sum;
    private static ArrayList<Integer>[] links;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N  = Integer.parseInt(br.readLine());

        links = new ArrayList[N+1];
        for(int i=1;i<=N;i++)links[i] = new ArrayList<>();
        times = new int[N+1];
        sum = new int[N+1];
        preWorks = new int[N+1];

        String[] temp;
        for(int i=1;i<=N;i++){
            temp = br.readLine().split(" ");
            int time = Integer.parseInt(temp[0]);
            times[i] = time;
            sum[i] = time;

            int preWork = Integer.parseInt(temp[1]);

            for(int j=1; j<=preWork;j++){
                links[Integer.parseInt(temp[j + 1])].add(i);
                preWorks[i]++;
            }
        }
        System.out.println(topologicalSort());
    }

    private static int topologicalSort(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i <= N; i++){
            if(preWorks[i] == 0)q.add(i); // 선행 작업이 없다는 경우.
        }

        while(!q.isEmpty()){

            int current = q.poll();
            for(int next : links[current]){
                sum[next] = Math.max(sum[next], times[next] + sum[current]);
                if(--preWorks[next] == 0){ // 선행 작업을 하나 끝낸 것을 처리해주고, 더 이상 선행작업이 없다면 queue에 넣는다.
                    q.add(next);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=N; i++)max = Math.max(sum[i], max);

        return max;
    }
}
