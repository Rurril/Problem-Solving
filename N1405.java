import java.io.*;
public class N1405 {

    private static int N;
    private static double[] possibilities = new double[4];
    private static double simple = 0;
    private static boolean[][] map = new boolean[30][30]; // 15,15에서 시작한다고 해도 29까지밖에 못감.
    private static int[] dy = {0, 0, 1, -1};
    private static int[] dx = {1, -1, 0, 0}; // 주어지는건 e, w, s, n

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        for(int i=0;i<4;i++){
            possibilities[i] = Double.parseDouble(temp[i+1])/100;
        }

        map[15][15] = true;
        DFS(1, 0, 15, 15);
        System.out.println(simple);
    }

    private static void DFS(double pos, int count, int y, int x){

        if(count == N){
//            System.out.println(y + " " + x + " " +simple);
            simple += pos;
            return;
        }

        for(int i=0;i<4;i++){

            int ny = y + dy[i];
            int nx = x + dx[i];

            if(map[ny][nx]) {
                continue;
            }else{

                map[ny][nx] = true;
                DFS(pos * possibilities[i], count+1, ny, nx);
                map[ny][nx] = false;
            }
        }

    }
}
