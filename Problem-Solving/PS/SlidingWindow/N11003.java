package PS;
import java.io.*;
import java.util.ArrayDeque;


public class N11003 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int L = Integer.parseInt(temp[1]);
        int[] arr = new int[N];
        temp = br.readLine().split(" ");

        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(temp[i]);


        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for(int i=0;i<N;i++){

            while(!deque.isEmpty() && deque.peekFirst() <= i - L){
//                System.out.println("pollFirst : " + (i-L));
                deque.pollFirst();
            }
            while(!deque.isEmpty() && arr[deque.peekLast()] > arr[i]){
//                System.out.println("pollLast : " + (arr[deque.peekFirst()]));
                deque.pollLast();
            }

            deque.add(i);
//            System.out.println(deque);
            bw.write(arr[deque.peekFirst()] + " ");
        }
        bw.flush();

    }
}
