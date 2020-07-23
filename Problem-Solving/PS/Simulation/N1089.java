package PS;
import java.io.*;
import java.util.ArrayList;

public class N1089 {

    private static int N;
    private static char[][][] floors = new char[10][5][3];
    private static char[][][] map;
    private static char[][] temp;
    private static long total = 0;
    private static int divide = 1;
    private static ArrayList<Integer>[] possibility;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        possibility = new ArrayList[N];
        temp = new char[5][4*N-1];
        map = new char[N][5][3];

        for(int i=0;i<N;i++)possibility[i] = new ArrayList<>();
        for(int i=0;i<5;i++){
            char[] arr = br.readLine().toCharArray();
            for(int j=0;j<arr.length;j++){
                temp[i][j] = arr[j];
            }
        }

        setLight();
        setMap();


        for(int i=0;i<N;i++){
            check(i);
        }

        for(int i=0;i<N;i++){
            // TODO : 모든 경우의 수를 찾아서 숫자를 만들어서 더하면 끝 !
//            System.out.println(possibility[i]);
            divide *= possibility[i].size();

        }
        getValue(0, "");
        double result = (double)total/divide;

        System.out.printf("%.5f", result);
    }

    private static void getValue(int index, String number){

        if(index == N){
//            System.out.println(total);
            total += Integer.parseInt(number);
            return;
        }

        for(int next : possibility[index]){
//            System.out.println(next);
            String num = Integer.toString(next);
            getValue(index+1, number + num);
        }
    }

    private static void check(int number){
        for(int i=0;i<10;i++) {
            if (isSame(number, i)) possibility[number].add(i);
        }
    }

    private static boolean isSame(int number, int index){

        for(int i=0;i<5;i++){
            for(int j=0;j<3;j++){
                if(map[number][i][j] == '#' && floors[index][i][j] == '.')return false;
            }
        }

        return true;
    }

    private static void setMap(){

        for(int i=0;i<N;i++){

            int start = 4 * i;

            for(int j=0;j<5;j++){
                for(int k=0;k<3;k++){
                    map[i][j][k] = temp[j][start + k];
                }
            }
        }

//        for(int k=0;k<N;k++){
//            for(int i=0;i<5;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(map[k][i][j] +  " " );
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
    }

    private static void setLight(){

        floors[0] = new char[][]{
                {'#', '#', '#' },
                {'#', '.', '#' },
                {'#', '.', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
        };
        floors[1] = new char[][]{
                {'.', '.', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
        };
        floors[2] = new char[][]{
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'#', '#', '#' },
                {'#', '.', '.' },
                {'#', '#', '#' },
        };
        floors[3] = new char[][]{
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'#', '#', '#' },
        };
        floors[4] = new char[][]{
                {'#', '.', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
        };
        floors[5] = new char[][]{
                {'#', '#', '#' },
                {'#', '.', '.' },
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'#', '#', '#' },
        };
        floors[6] = new char[][]{
                {'#', '#', '#' },
                {'#', '.', '.' },
                {'#', '#', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
        };
        floors[7] = new char[][]{
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
                {'.', '.', '#' },
        };
        floors[8] = new char[][]{
                {'#', '#', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
        };
        floors[9] = new char[][]{
                {'#', '#', '#' },
                {'#', '.', '#' },
                {'#', '#', '#' },
                {'.', '.', '#' },
                {'#', '#', '#' },
        };

//        for(int k=0;k<10;k++){
//            for(int i=0;i<5;i++){
//                for(int j=0;j<3;j++){
//                    System.out.print(floors[k][i][j] +  " " );
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

    }


}
