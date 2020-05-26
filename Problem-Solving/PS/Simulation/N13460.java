package PS;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
public class N13460 {
    final static int CNT = 10;
    static int n,m;
    static int[] dy = {1, -1, 0, 0}; // 위, 아래, 오른쪽, 왼쪽 순서
    static int[] dx = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][][][] isVisited;
    static Node hole;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        map = new char[n][m];
        isVisited = new boolean[n][m][n][m]; // 앞의 2차원이 빨간 구슬, 뒤의 2차원이 파란 구슬.
        char[] arr;
        Node red = null;
        Node blue = null;
        for(int i=0;i<n;i++){
            arr = br.readLine().toCharArray();
            for(int j=0;j<m;j++){
                map[i][j] = arr[j];

                if(map[i][j] == 'B')blue = new Node(i , j);
                else if(map[i][j] == 'R') red = new Node(i , j);
                else if(map[i][j] == 'O') hole = new Node(i , j);
            }
        }

        // 조건
        // 1. 10번 이하로 움직이고, 초과할 경우 불가능한 것으로 -1을 리턴
        // 2. 빨구, 파구 두개를 동시에 움직여야한다. --> 누굴 먼저 움직여야 하는지 판단.
        // 3. 어떻게 중복된 위치를 제거할 것인지 판단. --> 빨구와 파구 동시에 생각해야함.
        Pair start = new Pair(red.y, red.x , blue.y, blue.x);
        isVisited[red.y][red.x][blue.y][blue.x] = true;
        System.out.println(BFS(start));

    }

    public static int BFS(Pair start){

        Queue<Pair> q = new LinkedList();
        q.add(start);

        int result = -1;

        roop:
        for(int i=0;i<CNT+1;i++){

            int size = q.size();

            for(int j=0;j<size;j++){

                Pair pair = q.poll();
//                System.out.println(pair.ry + " " + pair.rx + " " + pair.by + " " + pair.bx);
                if(pair.by == hole.y && pair.bx == hole.x)continue; // 잘못된 경우.

                if(pair.ry == hole.y && pair.rx == hole.x){ // 끝난 경우.
                    result = i;
                    break roop;
                }

                if(i==10)continue; // 10번 돌리고 마지막으로 확인 후 끝낸다.

                for(int k=0;k<4;k++){ // 4방향으로 이동.
                    Pair next = Roll(pair, k);

                    if(!isVisited[next.ry][next.rx][next.by][next.bx]){
                        q.add(next);

                        isVisited[next.ry][next.rx][next.by][next.bx] = true;
                    }
                }
            }
        }

        return result;
    }

    public static Pair Roll(Pair pair, int direction){
        // 위, 아래, 오른쪽, 왼쪽 순서 0,1,2,3
        int nry = pair.ry;
        int nrx = pair.rx;
        int nby = pair.by;
        int nbx = pair.bx;
        int RCnt = 0;
        int BCnt = 0;
        // 빨간색 구슬을 먼저 굴린다.

        while(true){
            nry += dy[direction];
            nrx += dx[direction];

            if(nry >= n || nrx >= m || nry < 0 || nrx < 0)break;
            if(map[nry][nrx] == '#')break;// 벽에 막히거나 도착한다면 종료한다.
            if(map[nry][nrx] == 'O'){
                nry += dy[direction];
                nrx += dx[direction];
                break;
            }
            RCnt++;
        }
        // 파란색 구슬을 굴린다.
        while(true){
            nby += dy[direction];
            nbx += dx[direction];

            if(nby >= n || nbx >= m || nby < 0 || nbx < 0)break;
            if(map[nby][nbx] == '#')break;
            if(map[nby][nbx] == 'O'){
                nby += dy[direction];
                nbx += dx[direction];
                break;
            }
            BCnt++;
        }

        nry -= dy[direction];
        nrx -= dx[direction];
        nby -= dy[direction];
        nbx -= dx[direction];

        if(nry == nby && nrx == nbx){

            if(nry == hole.y && nrx == hole.x){ // 둘다 구멍에 빠졌다면.
                return new Pair(nry, nrx, nby, nbx);
            }else{
                // 위치가 같다면, 더 움직인 쪽을 한칸 반대로 움직인다.
                if(BCnt > RCnt){
                    nby -= dy[direction];
                    nbx -= dx[direction];
                }else if(BCnt < RCnt){
                    nry -= dy[direction];
                    nrx -= dx[direction];
                }else System.out.println("Same count error");
            }

        }
        return new Pair(nry, nrx, nby, nbx);


    }

    static class Node{

        int y,x;
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    static class Pair{
        int ry, rx;
        int by, bx;
        Pair(int ry, int rx, int by, int bx){
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
        }
    }
}
