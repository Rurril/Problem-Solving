import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class N1600 {

    private static int K, W, H;
    private static int[][] map;
    private static boolean[][][] isVisited;
    private static int[] horseJumpY = {2, 2, -2, -2, 1, 1, -1, -1};
    private static int[] horseJumpX = {1, -1, 1, -1, 2, -2, 2, -2};
    private static int[] jumpY = {1, -1, 0, 0};
    private static int[] jumpX = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        W = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[1]);

        map = new int[H][W];
        isVisited = new boolean[H][W][K+1];
        isVisited[0][0][0] = true;

        for(int i=0;i<H;i++){
            temp = br.readLine().split(" ");
            for(int j=0;j<W;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        int cnt = move();
        System.out.println(cnt);


    }

    private static int move(){

        Queue<Location> q = new LinkedList<>();
        q.add(new Location(0, 0, 0, 0));

        int cnt = 0;

        while(!q.isEmpty()){

            Location current = q.poll();
            if(current.y == H-1 && current.x == W-1){
                return current.time;
            }

            for(int j=0; j<4; j++){
                int ny = current.y + jumpY[j];
                int nx = current.x + jumpX[j];

                if(ny >= H || ny < 0 || nx >= W || nx < 0 || map[ny][nx] == 1 || isVisited[ny][nx][current.count])continue;

                isVisited[ny][nx][current.count] = true;
                q.add(new Location(ny, nx, current.time+1, current.count));
            }

            if(current.count == K)continue;

            for(int j=0;j<8;j++){
                int ny = current.y + horseJumpY[j];
                int nx = current.x + horseJumpX[j];

                if(ny >= H || ny < 0 || nx >= W || nx < 0 || map[ny][nx] == 1 || isVisited[ny][nx][current.count+1])continue;

                isVisited[ny][nx][current.count+1] = true;
                q.add(new Location(ny, nx, current.time+1, current.count+1));
            }

        }

        return -1;
    }

    private static class Location{
        int y, x, time, count;

        Location(int y, int x, int time, int count){
            this.y = y;
            this.x = x;
            this.time = time;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "y=" + y +
                    ", x=" + x +
                    ", count=" + count +
                    '}';
        }
    }
}
