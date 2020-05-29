package PS;
import java.io.*;
public class N2042_sub {

    private static final int QUERY_CHANGE = 1;
    private static final int QUERY_RANGESUM = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int nQuery = Integer.parseInt(temp[1]);
        nQuery += Integer.parseInt(temp[2]);

        long[] arr = new long[N];
        for(int i=0; i<N; i++)
            arr[i] = Long.parseLong(br.readLine());

        SegmentTree segmentTree = new SegmentTree(arr, N);

        while (nQuery-- > 0) {

            temp = br.readLine().split(" ");

            int queryType = Integer.parseInt(temp[0]);
            int idx1 = Integer.parseInt(temp[1]);
            long idx2 = Integer.parseInt(temp[2]);

            if (queryType == QUERY_CHANGE) {
                segmentTree.update(idx1 - 1, idx2, 1, 0, N - 1);
            }

            else if (queryType == QUERY_RANGESUM) {
                bw.write(segmentTree.query(idx1 - 1, (int)(idx2 - 1), 1, 0, N - 1) + "\n");
            }

        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static class SegmentTree{
        private long[] segmentArr;

        private SegmentTree(long[] arr, int n){
            int height = (int) Math.ceil(Math.log(n) / Math.log(2)); // 트리의 높이를 구한다.
            int segmentSize = (int) Math.pow(2, height) * 2; // 크기는 2^height -1 만큼 필요하다.
            segmentArr = new long[segmentSize];

            init(arr, 0, n-1, 1);
        }

        // node를 root로 하는 서브트리를 초기화하고, 이 구간의 최소치를 반환한다.
        private long init(long[] arr, int left, int right, int node){

            if(left == right)
                return segmentArr[node] = arr[left];

            int mid = (left + right)/2;

            return segmentArr[node] = init(arr, left, mid, node*2) + init(arr, mid+1, right, node * 2 + 1);
        }

        private long query(int left, int right, int node, int nodeLeft, int nodeRight){

            // 두 구간이 곂치지 않는 경우
            if(left > nodeRight || right < nodeLeft)return 0;

            // 노드 구간이 완전히 속하는 경우
            if(left <= nodeLeft && right >= nodeRight) return segmentArr[node];

            int mid = (nodeLeft + nodeRight)/2;

            return query(left, right, node*2, nodeLeft, mid) + query(left, right, (node * 2)+1, mid + 1, nodeRight);
        }

        private long update(int index, long newValue, int node, int nodeLeft, int nodeRight){

            // Node 구간에 포함되지 않는 경우
            if(index < nodeLeft || index > nodeRight) return segmentArr[node];

            // Node 구간에 포함되는 경우 : Leaf 노드인 경우
            if(nodeLeft == nodeRight) return segmentArr[node] = newValue;

            int mid = (nodeLeft + nodeRight)/2;

            return segmentArr[node] = update(index, newValue, node * 2, nodeLeft, mid) + update(index, newValue, (node * 2) + 1, mid + 1, nodeRight);

        }
    }
}
