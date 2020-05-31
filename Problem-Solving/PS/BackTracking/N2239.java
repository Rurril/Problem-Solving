package PS;
import java.io.*;


public class N2239 {
    private static int[][] map = new int[9][9];
    private static boolean end = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<9;i++){
            String[] temp = br.readLine().split("");
            for(int j=0;j<9;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        dfs(0);
    }

    private static void dfs(int num){

        if(num == 81){
            end = true;
            printMap();
        }

        if(end)return;

        int row = num/9;
        int col = num%9;

        if(map[row][col] != 0)dfs(num+1);
        else{
            for(int k=1; k<=9; k++){
                if(isPossible(row, col, k)){
                    map[row][col] = k;
                    dfs(num+1);
                    map[row][col] = 0;
                }
            }
        }



    }

    private static boolean isPossible(int y, int x, int num){

        for(int i=0;i<9;i++){
            if(map[y][i] == num)return false;
            if(map[i][x] == num)return false;
        }

        int yDivision = y/3;
        int xDivision = x/3;

        for(int i=yDivision*3; i<yDivision*3+3; i++){
            for(int j=xDivision*3; j<xDivision*3+3; j++){
                if(map[i][j] == num)return false;
            }
        }

        return true;
    }


    private static void printMap(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
