package PS;
import java.io.*;
public class N15684 {

    private static int N, H, M;
    private static int[][] map;
    private static int min = 4;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        H = Integer.parseInt(temp[1]);
        M = Integer.parseInt(temp[2]);

        map = new int[M+1][N+1];

        for(int i=0;i<H;i++){
            temp = br.readLine().split(" ");
            int height = Integer.parseInt(temp[0]);
            int number = Integer.parseInt(temp[1]);

            map[height][number] = 1; // 오른쪽으로 이어짐
            map[height][number+1] = 2; // 왼쪽으로 이어짐
        }

        DFS(0, 1);
        if(min == 4) System.out.println(-1);
        else System.out.println(min);
    }

    private static void DFS(int cnt, int number){
        if(cnt > 3 || cnt > min){
            return;
        }

        if(simulation()){
            min = cnt;
            return;
        }else{

            for(int i = number; i <= M*(N-1); i++){
                int y = (i-1)/(N-1) + 1;
                int x = (i-1)%(N-1) + 1;
                if(map[y][x] == 0 && map[y][x+1] == 0){
                    map[y][x] = 1;
                    map[y][x+1] = 2;
                    DFS(cnt+1, i+1);
                    map[y][x] = 0;
                    map[y][x+1] = 0;
                }
            }
        }
    }

    private static boolean simulation(){

        for(int i=1;i<=N;i++){

            int number = i;
            for(int j=1;j<=M;j++){
                if(map[j][number] == 1)number++;
                else if(map[j][number] == 2)number--;
            }

            if(number != i)return false;
        }
        return true;
    }
}
