package PS;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class N9328 {

    private static int h, w;
    private static char[][] map;
    private static ArrayList<Integer> sideX;
    private static ArrayList<Integer> sideY;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static HashSet<Integer> set;
    private static int document;
    private static Queue<Node> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){

            document = 0;
            sideY = new ArrayList<>();
            sideX = new ArrayList<>();
            set = new HashSet<>();
            q = new LinkedList<>();


            String[] temp = br.readLine().split(" ");
            h = Integer.parseInt(temp[0]);
            w = Integer.parseInt(temp[1]);
            map = new char[h][w];

            for(int i=0;i<h;i++){
                temp = br.readLine().split("");
                for(int j=0;j<w;j++){
                    map[i][j] = temp[j].charAt(0);
                }
            }

            setSide();

            char[] keys = br.readLine().toCharArray();
            if(keys[0] != '0'){
                for(char key : keys) {
                    int k = key;
                    set.add(k);
                }
            }

            boolean flag = true;
            while(flag)
                flag = BFS();


            bw.write(document +"\n");
        }
        bw.flush();

    }


    private static boolean BFS(){

        boolean[][] isVisited = new boolean[h][w];

        int keySize = set.size();
        setting();

        while(!q.isEmpty()){

            Node current = q.poll();
            if(isVisited[current.y][current.x])continue; // 중복해서 방문했을 수도 있으므로
            isVisited[current.y][current.x] = true;

//            System.out.println(current.y + " " +current.x);

            for(int i=0;i<4;i++){
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if(ny >= h || nx >= w || ny < 0 || nx < 0)continue;
                if(map[ny][nx] != '*' && !isVisited[ny][nx]){ // 벽이 아니고 방문하지 않은 경우만
                    if(map[ny][nx] == '$'){
                        q.add(new Node(ny, nx));
                        map[ny][nx] = '.';
                        document++;
                    }else if(map[ny][nx] == '.')q.add(new Node(ny, nx));
                    else{ // 문 혹은 열쇠로 이루어진 경우
                        int t = map[ny][nx];
                        if(t >= 97 && t <=122){ // 소문자 = 열쇠
                            set.add(t);
                            map[ny][nx] = '.';
                            q.add(new Node(ny, nx));
                        }else if(t >= 65 && t <= 90){
                            if(set.contains(t + 32)){ // 열쇠가 없으면 못여는 것
                                map[ny][nx] = '.';
                                q.add(new Node(ny, nx));
                            }
                        }
                    }

                }

            }
        }
        if(keySize == set.size())return false; // 새로운 키를 얻지 못해서 더 이상 열수가 없다는 것을 의미 : 다시 확인하는 의미가 없음.
        else return true;


    }
    private static void setSide(){
        for(int i=0;i<w;i++){
            sideY.add(0); sideX.add(i);
            sideY.add(h-1); sideX.add(i);
        }

        for(int i=1;i<h-1;i++){
            sideY.add(i); sideX.add(0);
            sideY.add(i); sideX.add(w-1);
        }
    }

    private static void setting(){
        // 빈칸이거나, 문이거나

        for(int i=0;i<sideX.size();i++){
            int ny = sideY.get(i);
            int nx = sideX.get(i);

            if(map[ny][nx] != '*'){
                if(map[ny][nx] == '$'){
                    q.add(new Node(ny, nx));
                    map[ny][nx] = '.';
                    document++;
                }else if(map[ny][nx] == '.')q.add(new Node(ny, nx));
                else{
                    int t = map[ny][nx];
                    if(t >= 97 && t <=122){ // 소문자 = 열쇠
                        set.add(t);
                        map[ny][nx] = '.';
                        q.add(new Node(ny, nx));
                    }else if(t >= 65 && t <= 90){
                        if(set.contains(t + 32)){ // 열쇠가 없으면 못여는 것
                            map[ny][nx] = '.';
                            q.add(new Node(ny, nx));
                        }
                    }
                }
            }
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
