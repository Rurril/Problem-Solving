import java.io.*;
import java.util.Arrays;
import java.util.Comparator;


public class N2568 {

    private static int N;
    private static Line[] list;
    private static int[][] dp;
    private static int MAX = 500001;
    private static boolean[] checked;
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        list = new Line[N];
        checked = new boolean[N];
        dp = new int[N+1][2];

        for(int i=1; i<=N; i++)Arrays.fill(dp[i], MAX);
        for(int i = 0; i<N; i++){
            String[] temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);
            list[i] = new Line(from, to);
        }

        Arrays.sort(list, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });

        LIS();
        bw.flush();
    }

    private static void LIS() throws IOException {
        int[] lis = new int[N];
        int[] lisIndex = new int[N];
        int[] preIndex = new int[N];
        int size = 0, temp;

        for (int i = 0; i < N; ++i) {
            temp = -(Arrays.binarySearch(lis, 0, size, list[i].end)) - 1;
            if (temp < 0)
                temp = -(temp) - 1; // 같은 숫자를 찾을 경우는 패스
            lis[temp] = list[i].end;
            lisIndex[temp] = i;
            // 자신의 위치가 0(첫번째)이면 이전은 없고, 자신의 위치가 0이 아니라면 현재 lis 구성의 바로 직전이 이전됨.
            preIndex[i] = (temp == 0) ? -1 : lisIndex[temp - 1];

            if (temp == size)
                ++size;
        }
        bw.write((N - size) + "\n");
        // 경로 출력을 위한 코드
        int cur = lisIndex[size - 1];
        while (cur != -1) {
            checked[cur] = true;
            cur = preIndex[cur];
        }
        for (int i = 0; i < N; i++) {
            if (!checked[i])
                bw.write(list[i].start + "\n");
        }
    }

    private static class Line {
        int start, end;
        private Line(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
