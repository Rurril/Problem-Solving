package PS;
import java.io.*;
public class N17435 {

    private static int M, Q;
    private static int[][] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        M = Integer.parseInt(br.readLine());
        table = new int[M+1][20];

        String[] temp = br.readLine().split(" ");
        for(int i=1;i<=M;i++)table[i][0] = Integer.parseInt(temp[i-1]);

        sparseTable();

        Q = Integer.parseInt(br.readLine());
        for(int i=0;i<Q;i++){
            temp =br.readLine().split(" ");
            int n  = Integer.parseInt(temp[0]);
            int x  = Integer.parseInt(temp[1]);

            bw.write(findValue(n, x) + "\n");
        }
        bw.flush();
    }
    private static int findValue(int n, int x){

        int k = 0;
        while (n >= 1) {
            if (n%2 == 1) {
                x = table[x][k];
            }
            n /= 2;
            k++;
        }

        return x;
    }

    private static void sparseTable(){

        for(int j=1;j<=19;j++){
            for(int i=1;i<=M;i++){
                table[i][j] = table[table[i][j-1]][j-1];
//                System.out.print(table[i][j] + " ");
            }
//            System.out.println();
        }
    }
}
