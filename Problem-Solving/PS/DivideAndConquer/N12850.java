import java.io.*;
public class N12850 {

    private static long[][] map = new long[8][8];
    private static int N;
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0 - 1 ,2 
        // 1 - 0, 2, 3
        // 2 - 0, 1, 3, 4
        // 3 - 1, 2, 4, 5
        // 4 - 2, 3, 5, 7
        // 5 - 3, 4, 6
        // 6 - 5, 7
        // 7 - 4, 6
        N = Integer.parseInt(br.readLine());
        map[0][1] = 1; map[0][2] = 1;
        map[1][0] = 1; map[1][2] = 1; map[1][3] = 1;
        map[2][0] = 1; map[2][1] = 1; map[2][3] = 1; map[2][5] = 1;
        map[3][1] = 1; map[3][2] = 1; map[3][4] = 1; map[3][5] = 1;
        map[4][3] = 1; map[4][5] = 1; map[4][6] = 1;
        map[5][2] = 1; map[5][3] = 1; map[5][4] = 1; map[5][7] = 1;
        map[6][4] = 1; map[6][7] = 1;
        map[7][5] = 1; map[7][6] = 1;


        map = pow(map, N);
        System.out.println(map[0][0]);


        
    }

    private static long[][] pow(long[][] arr, int n){
        if(n==1){
            return arr;
        }else{

            long[][] res = mult(arr, arr);

            if(n%2 == 1){
                return mult(arr, pow(res, n/2));
            }else{
                return pow(res, n/2);
            }

        }
    }

    private static long[][] mult(long[][] arr1, long[][] arr2){
        long[][] res = new long[8][8];

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                for(int k=0;k<8;k++){
                    res[i][j] = (res[i][j] + arr1[i][k] * arr2[k][j])%MOD;
                }
            }
        }

        return res;
    }

}
