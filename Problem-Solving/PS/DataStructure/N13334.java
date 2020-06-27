import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class N13334 {

    private static int N, distant, max;
    private static ArrayList<Edge> edges = new ArrayList<Edge>();
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        max = 0;
        for(int i=0;i<N;i++){
            String[] temp = br.readLine().split(" ");

            // TODO : 라인 받아서 작성
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);

            if(end < start){
                int tmp = start;
                start = end;
                end = tmp;
            }
            edges.add(new Edge(start, end));
        }

        distant = Integer.parseInt(br.readLine());

        edges.sort(null);


        for(Edge edge : edges){
            pq.offer(edge.start);

            while(!pq.isEmpty() && pq.peek() < edge.end - distant)
                pq.poll();

//            System.out.println(pq);
            max = Math.max(max, pq.size());
        }

        System.out.println(max);
    }

    private static class Edge implements Comparable<Edge>{
        int start, end;

        @Override
        public String toString() {
            return start + " / " + end;
        }

        Edge(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.end == o.end)return this.start - o.start;
            return this.end - o.end;
        }


    }
}
