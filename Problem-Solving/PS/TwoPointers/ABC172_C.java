package PS;
import java.io.*;

public class ABC172_C {

    private static long[] sumA;
    private static long[] sumB;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int a = Integer.parseInt(temp[0]);
        int b = Integer.parseInt(temp[1]);
        int time = Integer.parseInt(temp[2]);

        sumA = new long[a+1];
        sumB = new long[b+1];



        temp = br.readLine().split(" ");
        for(int i=1;i<=a;i++){
            sumA[i] = sumA[i-1] + Long.parseLong(temp[i-1]);

        }

        temp = br.readLine().split(" ");
        for(int i=1; i<=b;i++){
            sumB[i] = sumB[i-1] + Long.parseLong(temp[i-1]);
        }


        int j = b;
        for(int i=0;i<=a;i++){

            System.out.println(i + " " + j);
            if(sumA[i] > time)break;

            while(sumA[i] + sumB[j] > time)j--;

            max = Math.max(max, i+j);

        }
        System.out.println(max);

    }


}
