package PS;
import java.io.*;
public class N1799 {

    private static int N;
    private static int[][] map;

    private static int[] dy = {1, 1, -1, -1};
    private static int[] dx = {1, -1, -1, 1};
    private static int blackCnt = 0;
    private static int whiteCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        boolean[][] bishop1 = new boolean[N][N];
        boolean[][] bishop2 = new boolean[N][N];
        for(int i=0;i<N;i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(temp[j]);// 놓을 수 있으면 1 아니면 0
            }
        }


        BacktrackingBlack(0, 0, 0, bishop1);
        BacktrackingWhite(0, 1, 0, bishop2);


//            map[i/N][i%N] = 0; // 더 이상 놓을 수 없는 자리
        System.out.println(blackCnt + whiteCnt);
    }


    private static void BacktrackingBlack(int y, int x, int count, boolean[][] bishop){

        blackCnt = Math.max(count, blackCnt);

        if(x >= N) {
            y += 1;
            x = (y%2 == 1)?1:0;
        }

        if(y >= N)return;

        if(isValid(y, x, bishop)){

            bishop[y][x] = true;
            BacktrackingBlack(y, x+2, count+1, bishop);
            bishop[y][x] = false;
        }
        // 놓지 않고 진행.
        BacktrackingBlack(y, x+2, count, bishop);
    }

    private static void BacktrackingWhite(int y, int x, int count, boolean[][] bishop){

        whiteCnt = Math.max(count, whiteCnt);

        if(x >= N) {
            y += 1;
            x = (y%2 == 1)?0:1;
        }

        if(y >= N)return;

        if(isValid(y, x, bishop)){
            bishop[y][x] = true;
            BacktrackingWhite(y, x+2, count+1, bishop);
            bishop[y][x] = false;
        }
        // 놓지 않고 진행.
        BacktrackingWhite(y, x+2, count, bishop);
    }

    private static boolean isValid(int y, int x, boolean[][] bishop){

        if(map[y][x] == 0)return false;

        for(int i=0;i<4;i++){
            int ty = y;
            int tx = x;
            while(true){
                ty += dy[i];
                tx += dx[i];
//                if(map[ty][tx] == 0)
                if(ty >=N || tx >= N || ty < 0 || tx < 0)break; // map을 벗어나면 끝
                if(bishop[ty][tx])return false;

            }
        }

        return true;
    }
}
