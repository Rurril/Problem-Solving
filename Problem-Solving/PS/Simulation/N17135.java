package PS;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class N17135 {

    private static int N,M,D;
    private static int[][] map;
    private static int[][] t_map;
    private static int[] dy = {0, -1, 0};
    private static int[] dx = {-1, 0, 1};
    private static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]); // 행
        M = Integer.parseInt(temp[1]); // 렬
        D = Integer.parseInt(temp[2]); // 공격 거리 제한
        map = new int[N+1][M]; // N번째 행은 성이 있는 곳.

        for(int i=0;i<N;i++){ // 맵 설정
            temp = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }
        // 궁수를 3명 배치

        for(int i=0;i<M;i++){//012 013 014 ... 0 1 M-1
            for(int j=i+1; j<M;j++){
                for(int k=j+1;k<M;k++){
                    // i, j, k는 궁수의 열 위치
                    max = Math.max(max, simulation(i,j,k));
                }
            }
        }
//        max = simulation(0,2,4);

        System.out.println(max);



    }

    private static int simulation(int one, int two, int three){

        t_map = new int[N+1][M];
        int enemyCnt = 0;
        Archer[] archers = new Archer[3];
        archers[0] = new Archer(N, one);
        archers[1] = new Archer(N, two);
        archers[2] = new Archer(N, three);

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                t_map[i][j] = map[i][j];
            }
        }

        for(int i=0;i<N;i++){
            enemyCnt += turn(archers);
        }



//        System.out.println(one + " " + two + " " + three);
        return enemyCnt;
    }

    private static int turn(Archer[] archers){

        int enemyCnt = 0;
        boolean[] isDead = new boolean[3];
        for(int i=0;i<archers.length;i++){

            boolean flag = findEnemy(archers[i]);
            if(flag){
                // TODO : archer 위치에 있는 enyme를 없애기 -- 근데 한번에 없애야됨.
                isDead[i] = true;
            }
        }

        for(int i=0;i<3;i++){
            if(isDead[i]){
                int y = archers[i].enemyY;
                int x = archers[i].enemyX;
                if(t_map[y][x] == 1){
                    t_map[y][x] = 0;
                    enemyCnt++;
                }
            }
        }
        enemyMove();
//        System.out.println("num of enemy : "  + enemyCnt);
//        printTMap();
        return enemyCnt;
    }

    private static void printTMap(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(t_map[i][j] + " " );
            }
            System.out.println();
        }
    }

    private static void enemyMove(){


        for(int i=N-1;i>0;i--) {
            for(int j=0;j<M;j++){
                t_map[i][j] = t_map[i-1][j];
            }
        }
        for(int j=0;j<M;j++){
            t_map[0][j] = 0;
        }

    }

    private static boolean findEnemy(Archer archer){

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(archer.y, archer.x));
        boolean[][] isVisited = new boolean[N+1][M];
        isVisited[archer.y][archer.x] = true;
        int dist = 0;
        while(!q.isEmpty()){

            dist++;
            if(dist > D)break;

            for(int size = q.size(); size >0; size--){
                Node current = q.poll();

                for(int i=0;i<3;i++){
                    int ny = current.y + dy[i];
                    int nx = current.x + dx[i];

                    if(ny > N || ny < 0 || nx >= M || nx < 0 || isVisited[ny][nx])continue;
                    isVisited[ny][nx] = true;

                    if(t_map[ny][nx] == 1){
                        archer.enemyY = ny;
                        archer.enemyX = nx;
                        return true;
                    }
                    q.add(new Node(ny, nx));
                }
            }
        }
        return false;
    }

    private static class Archer{
        int y,x;
        int enemyY, enemyX;
        Archer(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    private static class Node{
        int y, x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
