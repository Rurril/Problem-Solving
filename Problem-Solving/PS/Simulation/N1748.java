package PS;
import java.io.*;
public class N1748 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int cnt =1;
        long result = 0;
        int temp = 9;

        while(N > 0){
            temp = 9*(int)Math.pow(10, cnt-1);

            if(N - temp > 0){
                result += cnt * temp;
                cnt++;
                N -= temp;
            }else{
                result += cnt * N;
                break;
            }

        }

        System.out.println(result);
    }
}
