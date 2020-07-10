import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
public class N1795 {
    private static int R,C;
    private static int[][][] map;
    private static int[] dy = {2, 2, 1, 1, -2, -2, -1, -1};
    private static int[] dx = {1, -1, 2, -2, 1, -1, 2, -2};

    private static Queue<StartPoint> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 각 체스말 당, 전체 탐색으로 수행한다고...?
        String[] temp = br.readLine().split(" ");
        R = Integer.parseInt(temp[0]);
        C = Integer.parseInt(temp[1]);

        for(int i=0;i<R;i++){
            char[] arr = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                if(arr[j] != '.'){
                    int num = arr[j] - '0';
                    queue.add(new StartPoint(i, j, num));
                }
            }
        }

        int size = queue.size();
        map = new int[R][C][size];
        for(int i=0;i<size;i++){
            BFS(queue.poll(), i);
        }

        int result = Integer.MAX_VALUE;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){

                int move = 0;
                boolean flag = true;
                for(int k=0; k<size; k++){

                    if(map[i][j][k] < 0){
                        flag = false;
                        break;
                    }
                    move += map[i][j][k];
                }

                if(flag)result = Math.min(result, move);
            }
        }

        if(result == Integer.MAX_VALUE)System.out.println(-1);
        else System.out.println(result);
    }
    private static void BFS(StartPoint sp, int index){

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                map[i][j][index] = -1;
            }
        }

        int number = sp.number;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sp.y, sp.x));
        map[sp.y][sp.x][index] = 0;

        int cnt = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                Node current = q.poll();
                for(int j=0;j<8;j++){
                    int ny = current.y + dy[j];
                    int nx = current.x + dx[j];

                    if(ny >= R || nx >= C || ny < 0 || nx < 0 || map[ny][nx][index] >= 0)continue;
                    map[ny][nx][index] = (int)Math.ceil(cnt / (double)number);
                    q.add(new Node(ny, nx));
                }
            }
            cnt++;

        }
//        System.out.println("number : " + index);
//
//        for(int i=0;i<R;i++){
//            for(int j=0;j<C;j++){
//                System.out.print(map[i][j][index] + " ");
//            }System.out.println();
//        }
    }


    static class StartPoint{
        @Override
        public String toString() {
            return "StartPoint{" +
                    "y=" + y +
                    ", x=" + x +
                    ", number=" + number +
                    '}';
        }

        int y, x;
        int number;
        StartPoint(int y, int x, int number){
            this.y = y;
            this.x = x;
            this.number = number;
        }
    }
    static class Node{
        int y, x;

        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}