package PS;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class N12100 {

    private static int[][] map;
    private static int N;
    private static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            String[] temp = br.readLine().split(" ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 2048 게임을 바탕으로하여 최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록을 출력한다.
        move(0);
        System.out.println(max);

    }

    private static void move(int count){

        if(count == 5){
            max = Math.max(max, getMax());
            return;
        }

        count++;
//        System.out.println(count);

        int[][] tMap = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tMap[i][j] = map[i][j];
            }
        }

        // 상하좌우로 이동하는 기능을 구현해야 한다.

        moveLeft();
        move(count);
        resetMap(tMap);

        moveRight();
        move(count);
        resetMap(tMap);

        moveTop();
        move(count);
        resetMap(tMap);

        moveBottom();
        move(count);
        resetMap(tMap);



    }

    private static int getMax(){
        int tMax = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(tMax < map[i][j])tMax = map[i][j];
            }
        }
        return tMax;
    }

    private static void moveLeft(){

        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == 0)continue;
                q.add(map[i][j]);
                map[i][j] = 0;
            }

            int j = 0;
            while(!q.isEmpty()){
                int current = q.poll();

                if(map[i][j] == 0){ // 비어있는 경우
                    map[i][j] = current;
                }else if(map[i][j] == current){ // 위치한 것이 같은 값인 경우
                    map[i][j] += current;
                    j++;
                }else{ // 다른 값이 들어가 있는 경우
                    map[i][j+1] = current;
                    j++;
                }
            }
        }
    }

    private static void moveRight(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=N-1;j>=0;j--){
                if(map[i][j] == 0)continue;
                q.add(map[i][j]);
                map[i][j] = 0;
            }

            int j = N-1;
            while(!q.isEmpty()){
                int current = q.poll();

                if(map[i][j] == 0){ // 비어있는 경우
                    map[i][j] = current;
                }else if(map[i][j] == current){ // 위치한 것이 같은 값인 경우
                    map[i][j] += current;
                    j--;
                }else{ // 다른 값이 들어가 있는 경우
                    map[i][j-1] = current;
                    j--;
                }
            }
        }
    }

    private static void moveTop(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[j][i] == 0)continue;
                q.add(map[j][i]);
                map[j][i] = 0;
            }

            int j = 0;
            while(!q.isEmpty()){
                int current = q.poll();

                if(map[j][i] == 0){ // 비어있는 경우
                    map[j][i] = current;
                }else if(map[j][i] == current){ // 위치한 것이 같은 값인 경우
                    map[j][i] += current;
                    j++;
                }else{ // 다른 값이 들어가 있는 경우
                    map[j+1][i] = current;
                    j++;
                }
            }
        }
    }

    private static void moveBottom(){
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=N-1;j>=0;j--){
                if(map[j][i] == 0)continue;
                q.add(map[j][i]);
                map[j][i] = 0;
            }

            int j = N-1;
            while(!q.isEmpty()){
                int current = q.poll();

                if(map[j][i] == 0){ // 비어있는 경우
                    map[j][i] = current;
                }else if(map[j][i] == current){ // 위치한 것이 같은 값인 경우
                    map[j][i] += current;
                    j--;
                }else{ // 다른 값이 들어가 있는 경우
                    map[j-1][i] = current;
                    j--;
                }
            }
        }
    }

    private static void resetMap(int[][] tMap){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = tMap[i][j];
            }
        }
    }

    private static void printMap(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
