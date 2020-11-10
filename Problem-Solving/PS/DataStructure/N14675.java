package PS;
import java.io.*;
import java.util.ArrayList;

public class N14675 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        int[] nodes = new int[N+1];
        for(int i=0;i<N-1;i++){
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);
            nodes[s]++;
            nodes[e]++;
        }

        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String[] temp = br.readLine().split(" ");
            int command = Integer.parseInt(temp[0]);
            int number = Integer.parseInt(temp[1]);
            if(command == 1){
                if(nodes[number] > 1)bw.write("yes");
                else bw.write("no");
            }else if(command == 2){
                bw.write("yes");
            }else{
                System.out.println("ERROR");
            }

            if(i < N-1)bw.newLine();
        }

        bw.flush();



    }

}
