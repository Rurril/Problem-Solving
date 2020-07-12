package PS;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class N2146 {

    private static int[][] map;
    private static boolean [][] isVisited;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static final int MAX = 10000;
    private static int min = MAX;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        isVisited = new boolean[n][n];

        for(int i=0;i<n;i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!isVisited[i][j] && map[i][j] == 1)BFS(i, j);
            }
        }

        System.out.println(min);
    }
    private static void BFS(int y, int x){

        int[][] check = new int[n][n];
        for(int i=0;i<n;i++) Arrays.fill(check[i], MAX);
//        System.out.println(y + " " + x);

        Queue<Node> q = new LinkedList<>();
        Queue<Node> next = new LinkedList<>();
        q.add(new Node(y, x));
        check[y][x] = 0;
        isVisited[y][x] = true;

        while(!q.isEmpty()){

            Node current = q.poll();
            next.add(current);

            for(int i=0;i<4;i++){
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if(ny >= n || nx >= n || ny < 0 || nx < 0 || check[ny][nx] == 0)continue;
                if(map[ny][nx] == 0)continue;

                check[ny][nx] = 0;
                isVisited[ny][nx] = true;
                q.add(new Node(ny, nx));
            }
        }

//        int time = 0;
        while(!next.isEmpty()){

            int size = next.size();
//            time++;
            while(size-- > 0){
                Node current = next.poll();

                for(int i=0;i<4;i++){
                    int ny = current.y + dy[i];
                    int nx = current.x + dx[i];


                    if(ny >= n || nx >= n || ny < 0 || nx < 0)continue;
                    if(check[ny][nx] == 0)continue; // 가장 먼저 제거
                    if(check[current.y][current.x] + 1 >= check[ny][nx])continue;
                    check[ny][nx] = check[current.y][current.x] + 1;

                    if(map[ny][nx] == 1){
                        min = Math.min(min, check[ny][nx] -1);
                        continue;
                    }else{
                        next.add(new Node(ny, nx));
                    }
                }
            }
        }

//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++){
//                System.out.print(check[i][j]+ " ");
//            }
//            System.out.println();
//        }
//        System.out.println(min);


    }

    static class Node{

        int y, x;

        Node(int y, int x){
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}
