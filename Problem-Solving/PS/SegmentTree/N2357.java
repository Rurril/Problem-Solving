package PS;
import java.io.*;
public class N2357 {

    private static int N,M;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        long[] arr = new long[N];
        for(int i=0;i<N;i++)
            arr[i] = Long.parseLong(br.readLine());

        SegmentTree segmentTree = new SegmentTree(arr, N);

        for(int i=0;i<M;i++){
            temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]);
            int e = Integer.parseInt(temp[1]);

            long max = segmentTree.findMax(s-1, e-1, 1, 0, N-1);
            long min = segmentTree.findMin(s-1, e-1, 1, 0, N-1);
            bw.write(min + " " + max + "\n");
        }
        bw.flush();
    }

    private static class SegmentTree{
        private long[] minSegmentArr;
        private long[] maxSegmentArr;
        private int segmentSize;
        private SegmentTree(long[] arr, int n){
            int height = (int)Math.ceil((Math.log(n) / Math.log(2)));
            segmentSize = (int) Math.pow(2, height) * 2;
            minSegmentArr = new long[segmentSize];
            maxSegmentArr = new long[segmentSize];

            minInit(arr, 0, n-1, 1); // Root node 의 인덱스는 1로 시작한다.
            maxInit(arr, 0, n-1, 1);
        }

        private void printSegment(){
            for(int i=1;i<segmentSize;i++) System.out.print(minSegmentArr[i] + " ");
            System.out.println();
        }

        private long minInit(long[] arr, int left, int right, int node){

            int mid = (left + right)/2;
            if(left == right)
                return minSegmentArr[node] = arr[left];

            return minSegmentArr[node] = Math.min(minInit(arr, left, mid, node*2) , minInit(arr, mid+1, right, node*2 + 1));
        }

        private long maxInit(long[] arr, int left, int right, int node){

            int mid = (left + right)/2;
            if(left == right)
                return maxSegmentArr[node] = arr[left];

            return maxSegmentArr[node] = Math.max(maxInit(arr, left, mid, node*2) , maxInit(arr, mid+1, right, node*2 + 1));
        }

        private long findMax(int left, int right, int node, int nodeLeft, int nodeRight){

            // 두 구간이 곂치지 않는 경우
            if(left > nodeRight || right < nodeLeft)return 0;

            int mid = (nodeLeft + nodeRight) / 2;

            if(left <= nodeLeft && right >= nodeRight)
                return maxSegmentArr[node];

            return Math.max(findMax(left, right, node*2, nodeLeft, mid) , findMax(left, right, node*2 + 1, mid + 1, nodeRight));
        }

        private long findMin(int left, int right, int node, int nodeLeft, int nodeRight){

            // 두 구간이 곂치지 않는 경우
            if(left > nodeRight || right < nodeLeft)return Long.MAX_VALUE;

            int mid = (nodeLeft + nodeRight) / 2;

            // 두 구간이 완전히 속하는 경우
            if(left <= nodeLeft && right >= nodeRight)
                return minSegmentArr[node];

            return Math.min(findMin(left, right, node*2, nodeLeft, mid) , findMin(left, right, node*2 + 1, mid + 1, nodeRight));
        }
    }
}
