import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class N16724 {

    private static int N, M;
    private static char[][] map;
    private static int[][] group;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static int idx = 1;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        map = new char[N][M];
        group = new int[N][M];

        for(int i=0;i<N;i++){
            char[] arr = br.readLine().toCharArray();
            for(int j=0;j<M;j++){
                map[i][j] = arr[j];
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(group[i][j] == 0)BFS(i, j);
            }
        }

        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                result = Math.max(result, group[i][j]);
//                System.out.print(group[i][j] + " ");
            }
//            System.out.println();
        }

        System.out.println(result);

    }

    private static void BFS(int y, int x){

        Queue<Location> q = new LinkedList<>();
        Queue<Location> res = new LinkedList<>();
        q.add(new Location(y, x));
        int index = idx;
        group[y][x] = index;

        while(!q.isEmpty()){
            Location current = q.poll();
            res.add(current);


            int[] next = getNext(current.y, current.x);
            int ny = next[0];
            int nx = next[1];


            if(group[ny][nx] == 0){
                group[ny][nx] = idx;
                q.add(new Location(ny, nx));
            }
            else{
                index = group[ny][nx];
            }
        }

        for(Location location : res){
            group[location.y][location.x] = index;
        }

        if(idx == index)idx++;






    }

    private static int[] getNext(int y, int x){
        int ny, nx;

        if(map[y][x] == 'D'){
            ny = y + 1;
            nx = x;
        }else if(map[y][x] == 'U'){
            ny = y - 1;
            nx = x;
        }else if(map[y][x] == 'L'){
            ny = y;
            nx = x - 1;
        }else if(map[y][x] == 'R'){
            ny = y;
            nx = x + 1;
        }else{
            ny = 0;
            nx = 0;
            System.out.println("ERROR");
        }
        int[] res = new int[2];
        res[0] = ny; res[1] = nx;
        return res;
    }

    static class Location{
        @Override
        public String toString() {
            return "Location{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }

        int y, x;
        Location(int y, int x){
            this.y = y;
            this.x = x;
        }

    }
}

