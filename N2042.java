package PS;
import java.io.*;
public class N2042 {
    private static int n,m,k;
    private static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        k = Integer.parseInt(temp[2]);

        arr = new long[n+1];
        for(int i=0;i<5;i++)arr[i+1] = Long.parseLong(br.readLine());

        for(int i=0;i<m+k;i++){
            temp = br.readLine().split(" ");

            int command = Integer.parseInt(temp[0]);

            // 참조값을 자동으로 바뀌게 연산을 해야하나...?
            if(command == 1){ // 바꾸는 연산
                // 바꾸는 것은 크게 어려운 연산은 아닌데
                int order = Integer.parseInt(temp[1]);
                long number = Long.parseLong(temp[2]);
            }else if(command ==2){ // 출력하는 연산

                // 부분합을 구하는 연산은 미리 구해놓지 않는이상 힘든데 
                int start = Integer.parseInt(temp[1]);
                int end = Integer.parseInt(temp[2]);
            }
        }


    }
}
