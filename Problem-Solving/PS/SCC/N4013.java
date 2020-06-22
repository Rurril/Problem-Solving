import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class N4013 {

    private static int N, M;
    private static ArrayList<Integer>[] edges;
    private static ArrayList<Integer>[] revEdges;
    private static int[] money;
    private static int[] tMoney;
    private static int[] groupMoney;
    private static int[] isCycle;
    private static boolean[] restaurant;
    private static boolean[] isChecked;
    private static boolean[] finalCheck;
    private static int start;
    private static int idx = 1;
    private static Stack<Integer> st = new Stack();
    private static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);

        edges = new ArrayList[N+1];
        revEdges = new ArrayList[N+1];
        money = new int[N+1];
        tMoney = new int[N+1];
        groupMoney = new int[N+1];
        restaurant = new boolean[N+1];
        isChecked = new boolean[N+1];
        isCycle = new int[N+1];
        finalCheck = new boolean[N+1];

        for(int i=1;i<=N;i++){
            edges[i] = new ArrayList();
            revEdges[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);

            edges[start].add(end);
            revEdges[end].add(start);
        }

        for(int i=1;i<=N;i++)
            money[i] = Integer.parseInt(br.readLine());

        temp = br.readLine().split(" ");

        start = Integer.parseInt(temp[0]);
        int restaurants = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        for(int i=0;i<restaurants;i++){
            restaurant[Integer.parseInt(temp[i])] = true;
        }

        for(int i=1;i<=N;i++){
            if(!isChecked[i])DFS(start);
        }

        while(!st.isEmpty()){

            int current = st.pop(); // 처음으로 뽑히는 요소가 시작지점. 가장 영향을 많이 줄 수 있는 시작점.
            q.add(current);

            // @TODO 한번 뽑혀서 가는 과정에서 각 요소에 최댓 값을 저장해야한다.
            if(isCycle[current] == 0){
                SCC(current);
                idx++;
            }
        }
//        for(int i=1;i<=N;i++)System.out.print(isCycle[i] + " ");
//        System.out.println();

//        topologicalSort(start, groupMoney[isCycle[start]]);

//        while(!q.isEmpty()){
//            int current = q.poll();
//
//            if(!finalCheck[current]){
        test(start, groupMoney[isCycle[start]]);
//            }
//        }

//        for(int i=1;i<=N;i++)System.out.print(tMoney[i] + " ");
//        System.out.println();

        int max = 0;
        for(int i=1;i<=N;i++){
            if(restaurant[i])max = Math.max(max, tMoney[i]);
        }
        System.out.println(max);
    }

    private static void topologicalSort(int number, int value){
        tMoney[number] = Math.max(tMoney[number], value);

        for(int next : edges[number]){

        }
    }

    private static void test(int number, int value){
        finalCheck[number] = true;
//        System.out.println(number + " " + value);
        tMoney[number] = Math.max(tMoney[number], value);

        for(int next : edges[number]){

            if(!finalCheck[next] && isCycle[number] == isCycle[next]){
                test(next, tMoney[number]);
            }else if(isCycle[number] != isCycle[next]){
                test(next, tMoney[number] + groupMoney[isCycle[next]]);
            }
        }
    }

    private static void SCC(int number){
        isCycle[number] = idx;
        groupMoney[idx] += money[number];
        for(int next : revEdges[number]){
            if(!isChecked[next])continue;
            if(isCycle[next] == 0)SCC(next);
        }
    }

    private static void DFS(int number){

        isChecked[number] = true;

        for(int next : edges[number]){
            if(!isChecked[next])DFS(next);
        }
        st.push(number);
    }
}
