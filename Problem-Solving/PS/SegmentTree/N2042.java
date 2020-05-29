package PS;
import java.io.*;
public class N2042 {
    private static int n,m,k;
    private static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        m = Integer.parseInt(temp[1]);
        k = Integer.parseInt(temp[2]);

        PenwickTree tree = new PenwickTree(n);
        arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = Long.parseLong(br.readLine());
            tree.update(i, arr[i]);
        }

        for(int i=0;i<m+k;i++){
            temp = br.readLine().split(" ");

            int command = Integer.parseInt(temp[0]);

            // 참조값을 자동으로 바뀌게 연산을 해야하나...?
            if(command == 1){ // 바꾸는 연산
                int order = Integer.parseInt(temp[1]);
                long number = Long.parseLong(temp[2]);
                tree.update(order-1, number-arr[order-1]);
            }else if(command ==2){ // 출력하는 연산
                int start = Integer.parseInt(temp[1]);
                int end = Integer.parseInt(temp[2]);
                bw.write((tree.sum(end-1) - tree.sum((start-2))) + "\n");
            }
        }
        bw.flush();


    }

    private static class PenwickTree{
        long[] tree;

        private PenwickTree(int n){
            this.tree = new long[n+1];
        }

        private void update(int idx, long val){
            idx++;
            while(idx < tree.length){
                tree[idx] += val;
                idx += (idx & -idx);
            }
        }

        private long sum(int n){
            n++;
            long res = 0;
            while(n > 0){
                res += tree[n];
                n &= (n-1);
            }
            return res;
        }


    }

}
