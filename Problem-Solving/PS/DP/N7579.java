package PS;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class N7579 {

    private static int N, M;
    private static int[] memory, cost;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        int result = Integer.MAX_VALUE;
        memory = new int[N];
        cost = new int[N];

        temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)
            memory[i] = Integer.parseInt(temp[i]);

        temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)
            cost[i] = Integer.parseInt(temp[i]);

//        for(int i=0;i<N;i++){
        for(int i=0;i<1;i++){
            result = Math.min(BFS(i), result);
        }

        System.out.println(result);


    }

    private static int BFS(int start){

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, memory[start], cost[start]));

        int min = Integer.MAX_VALUE;
        while(!q.isEmpty()){

            Node current = q.poll();
            for(int i=0; i<N;i++){
                if(i == current.num)continue;

                if(current.memory + memory[i] >= M){
                    min = Math.min(min, current.cost + cost[i]);
                }else{
                    q.add(new Node(i, current.memory + memory[i], current.cost + cost[i]));
                }
            }
        }
        return min;
    }

    private static class Node{

        int num, memory, cost;
        private Node(int num, int memory, int cost){
            this.num = num;
            this.memory = memory;
            this.cost = cost;
        }
    }


}
