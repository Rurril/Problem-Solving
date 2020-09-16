import java.io.*;
public class N7570 {

    private static int N;
    private static int[] students;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //방법: 줄 서있는 어린이 중 한 명을 선택하여 제일 앞이나 제일 뒤로 보낸다.
        N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        students = new int[1_000_001];
        for(int i=0;i<N;i++)students[i+1] = Integer.parseInt(temp[i]);

        // TODO : 줄을 세울 때 앞이나 뒤로 보내는 어린이 수의 최솟값을 찾는 것이다.

        // 증가하는 부분수열
        // 1 5 2 3 4  -- 체크 만약 2/N보다 작은 수열을 이룬다면 맨 앞으로 보낸다.
        // 차례대로 앞으로 보낸다.
        // 4 3 1 2 5 6 -- 아 가장 연속증가하는 부분수열의 크기에서 전체 크기를 뺴면 되는 거네...
        // 3 1 2 5 6 4

        int[] dp = new int[N+1];

        for(int i=1;i<=N;i++){
            dp[students[i]] = dp[students[i]-1]+1;
        }

        int max = 0;
        for(int i=1;i<=N;i++){
            max = Math.max(max, dp[i]);
        }
        if(N == 1)System.out.println(0);
        else System.out.println(N - max);
    }
}
