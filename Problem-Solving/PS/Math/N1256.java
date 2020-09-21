package PS;
import java.io.*;
public class N1256 {

    private static int N,M;
    private static long K;
    private static final long MAX = 1_000_000_000;
    private static long[][] pascal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);



        pascal = new long[201][101];
        pascal[0][0] = 1;
        for(int i=1;i<=200;i++){

            pascal[i][0] = 1;
            for(int j=1;j<=100;j++){
                if(j > i)break;
                pascal[i][j] = Math.min(MAX, pascal[i-1][j-1] + pascal[i-1][j]);
            }
        }

//        for(int i=0;i<=30;i++){
//            for(int j=0;j<=30;j++){
//                System.out.print(pascal[i][j] + " ");
//            }
//            System.out.println();
//        }

        findSolution(N, M, K, "");

//        System.out.println(pascal[N+M][M]);
//        for(int i=1;i<=pascal[N+M][M];i++){
//            findSolution(N,M,i,"");
//        }


    }
    private static void findSolution(int n, int m, long k, String word){

//        System.out.println(n + " " + m + " " + k + " " + word);
        if(n == 0 && m == 0){
            System.out.println(word);
            return;
        }

        if(m < 0){
            System.out.println(-1);
            return;
        }
//        if(k==0) {
//            System.out.println(word);
//            return;
//        }
        long pos = pascal[n+m-1][m];
//        System.out.println(pos);


//        if(k < 0)return;

        if(pos == MAX || pos >= k){ // 경우의 수가 K보다 크므로, a를 앞에다가 놓아야 되는 경우라는 뜻
            findSolution(n-1, m, k, word+"a");
        }else{
            findSolution(n, m-1, k-pos, word+"z");
        }
    }

}
