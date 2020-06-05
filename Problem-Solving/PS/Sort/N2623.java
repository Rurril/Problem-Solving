
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class N2623 {
    private static int N;
    private static int[] preOrder;
    private static ArrayList<Integer>[] order;
    private static BufferedWriter bw;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        int pd = Integer.parseInt(temp[1]);
        preOrder = new int[N+1];
        order = new ArrayList[N+1];
        for(int i=1;i<=N;i++)order[i] = new ArrayList();
        for(int i=0;i<pd;i++){
            temp = br.readLine().split(" ");
            int num = Integer.parseInt(temp[0]);
            for(int j=2;j<=num;j++){
                int first = Integer.parseInt(temp[j-1]);
                int second = Integer.parseInt(temp[j]);
                preOrder[second]++;
                order[first].add(second);
            }
        }

        if(topologicalSort()){
            bw.write(sb.toString());
            bw.flush();
        }else{
            bw.write("0");
            bw.flush();
        }

    }

    private static boolean topologicalSort() throws IOException {

        Queue<Integer> q = new LinkedList<>();
        boolean[] isFinished = new boolean[N+1];
        for(int i=1;i<=N;i++){
            if(preOrder[i] == 0){
                q.add(i);
                isFinished[i] = true;
            }
        }

        if(q.size() == 0)return false;

        while(!q.isEmpty()){

            int current = q.poll();
            sb.append(current + "\n");
            for(int next : order[current]){
                if(!isFinished[next]){
                    if(--preOrder[next] == 0){
                        q.add(next);
                        isFinished[next] = true;
                    }
                }else{
                    return false;
                }
            }
        }

        for(int i=1;i<=N;i++)if(!isFinished[i])return false;

        return true;
    }
}