import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Stack;

public class N6543 {

    private static int N,M;
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static ArrayList<Integer>[] edges;
    private static ArrayList<Integer>[] revEdges;
    private static Stack<Integer> st = new Stack<>();
    private static boolean[] isChecked;
    private static boolean[] isSink;
    private static int[] numSCC;

    private static int idx = 1;

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){

            String[] temp = br.readLine().split(" ");

            if(temp.length == 1)break;

            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);

            setUp();

            for(int i=1;i<=N;i++)
                if(!isChecked[i])DFS(i);

            while(!st.isEmpty()){

                int current = st.pop();
                if(numSCC[current] == 0){
                    SCC(current);
                    idx++;
                }
            }


            for(int i=1;i<=N;i++){
                if(!isSink[numSCC[i]])continue; // 이미 싱크가 아닌걸로 판별될 때
                sinkTest(i);
            }

            for(int i=1;i<=N;i++){
                if(isSink[numSCC[i]])bw.write(i + " ");
            }
            bw.newLine();
        }

        bw.flush();
    }

    private static void sinkTest(int number){

        for(int next : edges[number]){

            if(numSCC[next] != numSCC[number]){ // 다른 SCC로 나가는 것이 있다면?
                isSink[numSCC[number]] = false;
            }
        }
    }

    private static void SCC(int number){
        numSCC[number] = idx;

        for(int next : revEdges[number]){
            if(numSCC[next] == 0){
                SCC(next);
            }
        }
    }

    private static void DFS(int number){
        isChecked[number] = true;

        for(int next : edges[number]){
            if(!isChecked[next])
                DFS(next);
        }

        st.push(number);
    }

    private static void setUp() throws IOException {

        edges = new ArrayList[N+1];
        revEdges = new ArrayList[N+1];
        isChecked = new boolean[N+1];
        isSink = new boolean[N+1];
        numSCC = new int[N+1];

        idx = 1;

        for(int i=1;i<=N;i++){
            edges[i] = new ArrayList();
            revEdges[i] = new ArrayList();
            isSink[i] = true;
        }

        String[] temp = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            int start = Integer.parseInt(temp[2*i]);
            int end = Integer.parseInt(temp[2*i + 1]);

            edges[start].add(end);
            revEdges[end].add(start);
        }


    }
}
