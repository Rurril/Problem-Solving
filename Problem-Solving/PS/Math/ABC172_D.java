package PS;
import java.io.*;
public class ABC172_D {

    private static int N;
    private static long[] divisors = new long[10000001];

    private static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());



        for (int i = 1; i <= 10000000; i++) {
            for (int j = 1; j <= 10000000 / i; j++) {
                if(i*j > 10000000)break;
                divisors[i * j]++;
            }
        }

        for(int i=1;i<=N;i++){
            result += divisors[i] * i;
        }
        System.out.println(result);


    }
}
