import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class N3977 {

    private static int N, M, s, c, f, r;
    private static ArrayList<Integer>[] move;
    private static Stack<Integer> st = new Stack<>();
    private static int[] disc;
    private static int[] indegree;
    private static int[] scc;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0){
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            r = f = s = c = 0;

            move = new ArrayList[N+1];
            disc = new int[N+1];
            scc = new int[N+1];
            indegree = new int[N+1];

            for(int i=0;i<N;i++)move[i] = new ArrayList();

            for(int i=0;i<M;i++){
                temp = br.readLine().split(" ");
                int start = Integer.parseInt(temp[0]);
                int end = Integer.parseInt(temp[1]);
                move[start].add(end);
            }

            for(int i=0;i<N;i++){
                if(disc[i] == 0)DFS(i);
            }

            for(int i=0;i<N;i++){
                System.out.print(disc[i] + " ");
            }System.out.println();

            for(int i=0;i<N;i++){
                System.out.print(scc[i] + " ");
            }System.out.println();

            for(int i=0; i<N;i++){
                for(int next : move[i]){
                    if(scc[i] == scc[next])continue;
                    indegree[scc[next]]++;
                }
            }

            for(int i=1; i<= s; i++){
                if(indegree[i] == 0){
                    r = i;
                    f++;
                }
            }

            if(f >1){
                bw.write("Confused\n");
            }else{
                for(int i=0;i<N;i++){
                    if(scc[i] == r)bw.write(i + "\n");
                }
            }
            bw.newLine();

            if(tc > 0) {
                String end = br.readLine();
            }
        }

        bw.flush();
    }

    private static int DFS(int number){
        disc[number] = ++c;
        st.push(number);

        int ret = disc[number];

        for(int next : move[number]){
            if(disc[next] == 0)
                ret = Math.min(ret, DFS(next));
            else if(scc[next] == 0)
                ret = Math.min(ret, disc[next]);
        }

        if(ret == disc[number]){
            s++;
            while(true){

                int v = st.pop();
                scc[v] = s;
                if(v == number)break;
            }
        }
        return ret;
    }
}
