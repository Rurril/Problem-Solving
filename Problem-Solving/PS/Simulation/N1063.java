import java.io.*;
public class N1063 {

    private static int[][] map = new int[8][8];
    private static int sx, sy, kx, ky;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        R : 한 칸 오른쪽으로
//        L : 한 칸 왼쪽으로
//        B : 한 칸 아래로
//        T : 한 칸 위로
//        RT : 오른쪽 위 대각선으로
//        LT : 왼쪽 위 대각선으로
//        RB : 오른쪽 아래 대각선으로
//        LB : 왼쪽 아래 대각선으로

        String[] temp = br.readLine().split(" ");

        char[] king = temp[0].toCharArray();
        char[] stone = temp[1].toCharArray();


        sx = stone[0] - 65;
        sy = stone[1] - 49;
        map[sy][sx] = 2;

        kx = king[0] - 65;
        ky = king[1] - 49;
        map[ky][kx] = 1;


        int command = Integer.parseInt(temp[2]);

        for(int i=0;i<command;i++){
            String cmd = br.readLine();
            move(cmd);
        }

        king[0] = (char)(kx + 65);
        king[1] = (char)(ky + 49);
        stone[0] = (char)(sx + 65);
        stone[1] = (char)(sy + 49);
        System.out.println(king[0]+""+king[1]);
        System.out.println(stone[0]+""+stone[1]);



    }

    private static void printMap(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.print(map[i][j] + " " );
            }System.out.println();
        }
    }


    private static void move(String cmd){

        int[] d = getNext(cmd);

        if(d == null){
            System.err.println("ERROR");
            return;
        }

        int dy = ky + d[0];
        int dx = kx + d[1];

        // 킹이 맵 바깥으로 나가거나, 킹이 돌을 밀거나 - 그래서 돌이 바깥으로 나가거나
        if(dy >= 8 || dx >= 8 || dy < 0 || dx < 0)return;

        if(dy == sy && dx == sx){
            if(sy + d[0] >= 8 || sx + d[1] >= 8 || sy + d[0] < 0 || sx + d[1] < 0)return;

            map[sy][sx] = 0;
            map[ky][kx] = 0;
            sy += d[0];
            sx += d[1];
            ky = dy;
            kx = dx;
            map[sy][sx] = 2;
            map[ky][kx] = 1;
        }else{
            map[ky][kx] = 0;
            ky = dy;
            kx = dx;
            map[ky][kx] = 1;
        }


    }

    private static int[] getNext(String cmd){
        int[] ret = new int[2];
        if(cmd.equals("R")){ // {0, 1}
            ret[0] = 0; ret[1] = 1;
            return ret;
        }else if(cmd.equals("L")){ // {0, -1}
            ret[0] = 0; ret[1] = -1;
            return ret;
        }else if(cmd.equals("B")){ // {-1, 0}
            ret[0] = -1; ret[1] = 0;
            return ret;
        }else if(cmd.equals("T")){ // {1, 0}
            ret[0] = 1; ret[1] = 0;
            return ret;
        }else if(cmd.equals("RT")){ // 1, 1
            ret[0] = 1; ret[1] = 1;
            return ret;
        }else if(cmd.equals("LT")){ // 1, -1
            ret[0] = 1; ret[1] = -1;
            return ret;
        }else if(cmd.equals("RB")){ // -1, 1
            ret[0] = -1; ret[1] = 1;
            return ret;
        }else if(cmd.equals("LB")){ // -1, -1
            ret[0] = -1; ret[1] = -1;
            return ret;
        }else{
            System.err.println("ERROR");
            return null;
        }
    }
}
