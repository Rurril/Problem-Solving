import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class N1516 {

    private static int N;
    private static int[] preWork;
    private static int[] results;
    private static int[] times;
    private static ArrayList<Integer>[] next;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        preWork = new int[N+1];
        results = new int[N+1];
        times = new int[N+1];
        next = new ArrayList[N+1];
        for(int i=1;i<=N;i++)next[i] = new ArrayList();

        for(int i=1;i<=N;i++){
            String[] temp = br.readLine().split(" ");
            int time = Integer.parseInt(temp[0]);
            times[i] = time;

            for(int j=1; j<=temp.length-2; j++){
                int before = Integer.parseInt(temp[j]);
                next[before].add(i);
                preWork[i]++;
            }
        }

        topologicalSort();

    }

    private static void topologicalSort(){

        PriorityQueue<Building> pq = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N+1];
        for(int i=1;i<=N;i++)if(preWork[i] == 0){
            pq.add(new Building(i, times[i]));
            isVisited[i] = true;
        }

        while(!pq.isEmpty()){

            Building current = pq.poll();
            results[current.number] = current.time;

            for(int next : next[current.number]){

                if(isVisited[next])continue;

                if(--preWork[next] == 0){

                    isVisited[next] = true;
                    pq.add(new Building(next, times[next] + current.time));
                }
            }
        }

        for(int i=1;i<=N;i++)System.out.println(results[i]);
    }

    static class Building implements Comparable<Building>{
        int number;
        int time;
        Building(int number, int time){
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(Building o) {
            return this.time - o.time;
        }
    }
}
