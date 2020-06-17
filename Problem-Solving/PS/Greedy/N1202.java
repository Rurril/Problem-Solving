import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class N1202 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        int N = Integer.parseInt(temp[0]);
        int K = Integer.parseInt(temp[1]);

        Jewel[] jewels = new Jewel[N];
        int[] bags = new int[K];

        for(int i=0;i<N;i++){
            temp = br.readLine().split(" ");
            jewels[i] = new Jewel(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        for(int i=0;i<K;i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels);
        Arrays.sort(bags);

        long result = 0;
        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<K;i++){

            while(j < N && jewels[j].weight <= bags[i]){
                pq.add(-jewels[j].value);
                j++;
            }

            if(!pq.isEmpty())result += pq.poll(); // 비어있지 않다면, 가장 높은 값을 더한다.
        }


        System.out.println(-result);
    }

    static class Jewel implements Comparable<Jewel>{

        int weight, value;
        Jewel(int weight, int value){
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            if(this.weight == o.weight)return -1*(this.value - o.value); // 무게가 같다면, 가치순으로 내림차순
            return this.weight - o.weight; // 무게 순으로 오름차순
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }
}
