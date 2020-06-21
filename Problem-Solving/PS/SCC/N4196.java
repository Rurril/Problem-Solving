package PS;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class N4196 {

    private static int N,E;
    private static boolean[] isVisited; // for DFS
    private static boolean[] isChecked; // for SCC
    private static ArrayList<Integer>[] edges;
    private static Stack<Integer> st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0){
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            E = Integer.parseInt(temp[1]);

            edges = new ArrayList[N+1];
            isChecked = new boolean[N+1];
            isVisited = new boolean[N+1];
            st = new Stack();

            for(int i=1;i<=N;i++)
                edges[i] = new ArrayList();


            for(int i=0; i<E;i++){
                temp= br.readLine().split(" " );
                int a = Integer.parseInt(temp[0]);
                int b = Integer.parseInt(temp[1]);

                edges[a].add(b);
            }

            for(int i=1; i<=N;i++){
                if(!isVisited[i])DFS(i);
            }

            int cnt = 0;
            while(!st.isEmpty()){
                int current = st.pop();

                if(!isChecked[current]){
                    SCC(current);
                    cnt++;
                }
            }

            bw.write(cnt + "\n");
        }
        bw.flush();
    }

    private static void SCC(int number){

        isChecked[number] = true;
        for(int next : edges[number]){
            if(!isChecked[next])SCC(next);
        }
    }

    private static void DFS(int number){

        isVisited[number] = true;
        for(int next : edges[number]){
            if(!isVisited[next])DFS(next);
        }
        st.push(number);
    }
}
