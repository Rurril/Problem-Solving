import java.io.*;
import java.util.*;

public class N16562 {

    private static int N, M, K;
    private static int[] money;
    private static boolean[] isFriend;
    private static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        list = new ArrayList[N+1];
        money = new int[N+1];
        isFriend = new boolean[N+1];
        temp = br.readLine().split(" ");

        for(int i=0;i<N;i++){
            list[i+1] = new ArrayList();
            money[i+1] = Integer.parseInt(temp[i]);
        }

        for(int i=0;i<M;i++){
            temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            list[s].add(e);
            list[e].add(s);
        }

        long totalMoney = 0;
        for(int i=1;i<=N;i++){
            if(!isFriend[i])totalMoney += BFS(i);
        }


        if(totalMoney <= K)System.out.println(totalMoney);
        else System.out.println("Oh no");
        // 돈이 부족하다면 oh no

    }

    private static long BFS(int number){
        Queue<Integer> q = new LinkedList<>();
        q.add(number);
        isFriend[number] = true;
        long minValue = 10_000_001;

        while(!q.isEmpty()){
            int current = q.poll();
            minValue = Math.min(minValue, money[current]);
            for(int next : list[current]){

                if(!isFriend[next]){
                    isFriend[next] = true;
                    q.add(next);
                }
            }
        }

        return minValue;
    }

    static class Edge{
        int s, e;

        Edge(int s, int e){
            this.s = s;
            this.e = e;
        }


    }
}

