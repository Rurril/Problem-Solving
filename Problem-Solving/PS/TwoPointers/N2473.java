package PS;
import java.io.*;
import java.util.Arrays;

public class N2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<N;i++)arr[i] = Long.parseLong(temp[i]);
        Arrays.sort(arr);

        long res = 3_000_000_000l;
        int finalFirst = 0;
        int finalSecond = 0;
        int finalThird = 0;

        loop:
        for(int i=0;i<N-1;i++){
            long value = arr[i];
            int l = i+1;
            int r = N-1;
            while(true){

                if(l == r)break;

                long total = value + arr[l] + arr[r];

                if(Math.abs(total) < res){
                    finalFirst = i;
                    finalSecond = l;
                    finalThird = r;
                    res = Math.abs(total);
                }

                if(total > 0) r--;
                else if(total < 0) l++;
                else if(total == 0) break loop;

            }
        }
        System.out.println(arr[finalFirst] + " " + arr[finalSecond] + " " + arr[finalThird]);

    }
}
