import java.io.*;
public class N11444 {

    private static long N;
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Long.parseLong(br.readLine());

        long[][] matrix = {
                {1, 1},
                {1, 0}
        };

        matrix = pow(matrix, N);
        bw.write(matrix[0][1]+"\n");
        bw.flush();

    }

    private static long[][] pow(long[][] matrix, long n){

        if(n == 1){
            return matrix;
        }else{
            long[][] result = mult(matrix, matrix);

            if(n % 2 == 1){
                return mult(matrix, pow(result, n/2));
            }else{
                return pow(result, n/2);
            }
        }
    }

    private static long[][] mult(long[][] arr1, long[][] arr2){

        long[][] result = new long[2][2];

        result[0][0] = (arr1[0][0] * arr2[0][0] + arr1[0][1] * arr2[1][0])%MOD;
        result[0][1] = (arr1[0][0] * arr2[0][1] + arr1[0][1] * arr2[1][1])%MOD;
        result[1][0] = (arr1[1][0] * arr2[0][0] + arr1[1][1] * arr2[1][0])%MOD;
        result[1][1] = (arr1[1][0] * arr2[0][1] + arr1[1][1] * arr2[1][1])%MOD;

        return result;
    }
}
