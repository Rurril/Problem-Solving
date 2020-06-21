package PS;
import java.io.*;
public class RedScarf {

    private static int[] arr;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)arr[i+1] = Integer.parseInt(temp[i]);

        int t = 0;
        for(int i=1;i<=N;i++){
            t ^= arr[i];
        }

        for(int i=1;i<=N;i++){
            System.out.print((t ^ arr[i]) + " ");
        }

    }

    private static int solution(int except, int start, int end){
        if(start == end){
            if(except == start)return 0;
            else return arr[start];
        }
        int mid = (start+end)/2;
//        System.out.println(mid);
        return (solution(except, start, mid) ^ solution(except, mid+1, end));

    }
}
