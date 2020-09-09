package PS;
import java.io.*;
public class N2023 {

    private static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        DFS(1, 2);
        DFS(1, 3);
        DFS(1, 5);
        DFS(1, 7);

        bw.flush();
        bw.close();
    }

    private static void DFS(int depth, int number) throws IOException {

        if(depth == N){
            bw.write(number + "\n");
            return;
        }

        int[] lists = {1,3,5,7,9};
        for(int i : lists){
            int next = number*10 + i;
            if(isPrime(next)){ // 소수일 때
                DFS(depth+1, next);
            }
        }
    }

    private static boolean isPrime(int number){

        if(number == 1)return false;

        int root = (int)Math.sqrt(number);

        for(int i=2;i<=root;i++){
            if(number % i == 0)return false;
        }

        return true;
    }
}
