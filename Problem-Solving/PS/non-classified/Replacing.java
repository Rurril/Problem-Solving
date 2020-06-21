package PS;
import java.io.*;
public class Replacing {

    private static int[] arr = new int[100001];
    private static int N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        String[] temp = br.readLine().split(" ");
        for(int i=0;i<temp.length;i++){
            arr[Integer.parseInt(temp[i])]++;
        }

        long sum = 0;
        for(int i=1;i<=100000;i++){
            sum += i*arr[i];
        }

        Q = Integer.parseInt(br.readLine());
        while(Q-- > 0){
            temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);

            arr[to] += arr[from];
            sum += (long)to*(long)arr[from];
            sum -= (long)from*(long)arr[from];
            arr[from] = 0;
            bw.write(sum + "\n");
        }
        bw.flush();



    }
}
