package PS;
import java.io.*;
public class N3780 {

    private static int N;
    private static int[] parent;
    private static int[] length;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0){
            N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            length = new int[N+1];
            for(int i=1;i<=N;i++){
                parent[i] = i; // 부모가 누구인가. 1 : 부모까지의 거리
                length[i] = 0;
            }

            while(true){
                String[] temp = br.readLine().split(" ");

//                for(int i=1;i<=N;i++) System.out.print(length[i] + " ");
//                System.out.println();

                if(temp[0].equals("O"))break;
                else if(temp[0].equals("E")){
                    int number = Integer.parseInt(temp[1]);
                    find(number);
                    bw.write(length[number] + "\n");
                }else if(temp[0].equals("I")){
                    int a = Integer.parseInt(temp[1]);
                    int b = Integer.parseInt(temp[2]);

                    union(a, b);
                }

            }

        }
        bw.flush();
    }

    private static int find(int a){

        if(a == parent[a])return a;
        else {
            int next = find(parent[a]);
            length[a] += length[parent[a]];
//            System.out.println("test" + parent[a][1]);
            return parent[a] = next;
        }
    }

    private static void union(int to, int from){

        length[to] = Math.abs(to - from)%1000;
        parent[to] = from;
    }
}
