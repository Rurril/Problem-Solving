package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N1939 {

    private static int N,M, start, end;
    private static ArrayList<Bridge>[] bridges;

    public static void main(String[] args) throws IOException {

        setUp();
        // TODO : start 에서 end로 화물을 보내는 것.

        long left = 0;
        long right = 1_000_000_000L;
        long result = 0;
        while(left <= right){
            long mid = (left+right)/2;
            boolean flag = isPossible(start, end, mid);
//            System.out.println(left + " " + right + " " + mid + " " + flag);
            if(flag){ // 가능하다면 줄이고
                left = mid+1;
                result = Math.max(result, mid);
            }else{
                right = mid-1;
            }
        }
        System.out.println(result);

    }

    private static boolean isPossible(int start, int end, long weight){

        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[N+1];
        q.add(start);
        isVisited[start] = true;


        while(!q.isEmpty()){
            int current = q.poll();

            if(current == end)return true;

            for(Bridge bridge : bridges[current]){
                int next = bridge.end;
                int maxWeight = bridge.weight;
                if(isVisited[next] || weight > maxWeight)continue;

                q.add(next);
                isVisited[next] = true;
            }
        }

        return false;


    }

    private static class Bridge{

        int start, end, weight;

        Bridge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }

    private static void setUp() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]); // 섬의 개수
        M = Integer.parseInt(temp[1]); // 다리의 개수
        bridges = new ArrayList[N+1];
        for(int i=1;i<=N;i++)bridges[i] = new ArrayList();

        for(int i=0;i<M;i++){
            temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]); // A to B, 중량 제한이 C인 것
            int b = Integer.parseInt(temp[1]);
            int c = Integer.parseInt(temp[2]);
            bridges[a].add(new Bridge(a,b,c));
            bridges[b].add(new Bridge(b,a,c));
        }

        temp = br.readLine().split(" ");
        start = Integer.parseInt(temp[0]);
        end = Integer.parseInt(temp[1]);
    }
}
