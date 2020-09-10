package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N2234 {

    private static int N,M; // N이 가로 M이 세로
    private static int[][] map;
    private static int[][] room;
    private static int[] dy = {0, -1, 0, 1}; // 서, 북, 동, 남
    private static int[] dx = {-1, 0, 1, 0};
    private static int roomCnt = 1;
    private static ArrayList<Integer> rooms = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        map = new int[M][N];
        room = new int[M][N];
        rooms.add(0);

        for(int i=0;i<M;i++){
            temp = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 방 별로 유니온 한 뒤에 방을 하나의 노드로 하는 그래프를 만들어서, 두 개의 노드를 합칠 수 있을 때의 최댓값을 구하자.
        int maxSize = 0;
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(room[i][j] == 0){
                    maxSize = Math.max(maxSize, BFS(i,j));
//                    printMap();
//                    System.out.println();
                }
            }
        }
        roomCnt--;



        System.out.println(roomCnt);
        System.out.println(maxSize);
        System.out.println(checkUnion());
    }

    private static int checkUnion(){

        boolean[][] isVisited = new boolean[M][N];
        isVisited[0][0] = true;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0));
        int max = 0;

        while(!q.isEmpty()){
            Node current = q.poll();

            for(int i=0;i<4;i++){
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if(ny >= M || ny < 0 || nx >= N || nx < 0 || isVisited[ny][nx])continue;

                isVisited[ny][nx] = true;
                int roomNumber1 = room[current.y][current.x];
                int roomNumber2 = room[ny][nx];

                if(roomNumber1 != roomNumber2){
                    max = Math.max(max, rooms.get(roomNumber1) + rooms.get(roomNumber2));
                }

                q.add(new Node(ny,nx));
            }
        }
        return max;
    }
    private static void printMap(){
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                System.out.print(room[i][j] + " " );
            }
            System.out.println();
        }
    }

    private static int BFS(int y, int x){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(y, x));
        room[y][x] = roomCnt;
        int roomSize = 0;
        while(!q.isEmpty()){
            Node current = q.poll();
            roomSize++;
//            printMap();
            int bitmask = map[current.y][current.x];
            for(int i=0;i<4;i++) {
                Node next = isOpen(current.y, current.x, bitmask, i);
//                System.out.println(next);
                if(next != null) q.add(next);
            }
        }

        roomCnt++;
        rooms.add(roomSize);
        return roomSize;
    }

    private static Node isOpen(int y, int x, int bitmask, int direction){

        if(((bitmask & (1 << direction)) == 0)){ // 해당 방향이 열려있다면.
//            System.out.println(bitmask + " "+ (bitmask >direction) + " "  + direction);
            int ny = y + dy[direction];
            int nx = x + dx[direction];

            if(ny >= M || ny < 0 || nx >= N || nx < 0 || room[ny][nx] != 0)return null; // 맵을 벗어나는 경우 && 방이 이미 이루어져있을 경우.

            room[ny][nx] = roomCnt;
            return new Node(ny, nx);
        }else{ // 해당 방향이 닫혀있다면
            return null;
        }
    }

    private static class Node{
        int y,x;
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
