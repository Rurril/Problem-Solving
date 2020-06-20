package PS;
import kotlin.PreconditionsKt;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class N2150 {

    private static int V, E, cnt;
    private static int[] visited;
    private static boolean[] checked;
    private static Stack<Integer> st;
    private static ArrayList<Integer>[] con;
    private static ArrayList<Integer>[] revcon;
    private static ArrayList<Integer> ans;
    private static ArrayList<ArrayList<Integer>> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        V = Integer.parseInt(temp[0]);
        E = Integer.parseInt(temp[1]);

        con = new ArrayList[V+1];
        revcon = new ArrayList[V+1];
        visited = new int[V+1];
        cnt = 1;

        for(int v = 1; v <= V; v++){
            con[v] = new ArrayList<Integer>();
            revcon[v] = new ArrayList<Integer>();
        }

        for(int e = 1; e <= E; e++){
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);

            con[start].add(end);
            con[end].add(start);
        }

        st = new Stack<Integer>();
        for(int i=1; i<=V; i++){
            if(visited[i] == 0)dfs(i);
        }

        checked = new boolean[V+1];
        answer = new ArrayList<ArrayList<Integer>>();

        while(!st.isEmpty()){
            ans = new ArrayList<Integer>();
            int current = st.pop();
            if(!checked[current]){
                SCC(current);
                if(ans.size() > 0){
                    ans.sort(null);
                    answer.add(ans);
                }
            }
        }

        Collections.sort(answer, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        System.out.println(answer.size());

        for(ArrayList<Integer> list : answer){
            for(Integer number : list){
                System.out.print(number + " ");
            }
            System.out.println(-1);
        }

    }

    private static void SCC(int number){
        ans.add(number);
        checked[number] = true;
        for(int j=0; j< revcon[number].size(); j++){
            int n = revcon[number].get(j);
            if(!checked[n])
                SCC(n);
        }
    }

    private static void dfs(int number){
        visited[number] = cnt++;
        for(int i=0; i < con[number].size(); i++){
            int n = con[number].get(i);
            if(visited[n] == 0)
                dfs(n);
        }
        st.push(number);
    }
}
