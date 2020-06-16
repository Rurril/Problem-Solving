import java.io.*;
public class N3117 {

    private static int N,K,M;
    private static int[] students;
    private static int[][] table;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        K = Integer.parseInt(temp[1]);
        M = Integer.parseInt(temp[2]);

        students = new int[N+1];
        table = new int[K+1][30];

        temp = br.readLine().split(" ");
        for(int i=1;i<=N;i++)students[i] = Integer.parseInt(temp[i-1]);
        temp = br.readLine().split(" ");
        for(int i=1;i<=K;i++)table[i][0] = Integer.parseInt(temp[i-1]);

        setTable();
        for(int i=1;i<=N;i++){
            System.out.print(findValue(i) + " ");
        }
    }

    private static int findValue(int number){
        int video = students[number];
        int tM = M-1;
        int cnt = 0;

        while(tM > 0){
            if(tM % 2 == 1){
                video = table[video][cnt];
            }
            tM /= 2;
            cnt++;
        }
        return video;
    }

    private static void setTable(){

        for(int i=1;i<30;i++){
            for(int j=1; j<=K; j++){
                table[j][i] = table[table[j][i-1]][i-1];
            }
        }
    }
}
