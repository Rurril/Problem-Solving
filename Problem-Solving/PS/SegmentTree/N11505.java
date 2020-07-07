package PS;
import java.io.*;
import java.util.Arrays;

public class N11505 {

    private static int N,M,K;
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        long[] arr = new long[N];
        for(int i=0;i<N;i++)
            arr[i] = Long.parseLong(br.readLine());

        SegementTree segementTree = new SegementTree(arr);


        for(int i=0;i<M+K;i++){
            temp = br.readLine().split(" ");
            int command = Integer.parseInt(temp[0]);
            int start = Integer.parseInt(temp[1]);
            int end = Integer.parseInt(temp[2]);

            if(command == 1){
                // 변경
                segementTree.update(0, arr.length-1, 1, start-1, end);
            }else{
                // 출력
                long ret = segementTree.getValue(start-1, end-1, 1, 0, arr.length-1);
                bw.write(ret + "\n");
            }

            System.out.println(segementTree.toString());
        }

        bw.flush();
    }

    static class SegementTree{

        long[] segmentArr;
        long[] arr;
        long height;

        SegementTree(long[] arr){
            this.height = (long)Math.ceil(Math.log(arr.length) / Math.log(2));
            segmentArr = new long[(int)Math.pow(2, height+1)];
            this.arr = arr;

            init(0, arr.length-1, 1);
        }

        private long init(int start, int end, int node){

            int mid = (start + end)/2;
            if(start == end)
                return segmentArr[node] = arr[start];

            return segmentArr[node] = (init(start, mid, node*2) * init(mid+1, end, node*2 + 1))%MOD;
        }

        private long getValue(int left, int right, int node, int leftNode, int rightNode){

            if(right < leftNode || left > rightNode)return 1;

            if(rightNode <= right && leftNode >= left){
                return segmentArr[node];
            }

            int mid = (leftNode + rightNode)/2;

            return (getValue(left, right, node*2, leftNode, mid) * getValue(left, right, node*2 + 1, mid+1, right))%MOD;
        }

        private long update(int leftNode, int rightNode, int node, int location, int value){

            if(leftNode > location || rightNode < location)return segmentArr[node];

            if(leftNode == rightNode)
                return segmentArr[node] = value;

            int mid = (leftNode + rightNode)/2;

            return segmentArr[node] = (update(leftNode, mid, node*2, location, value) * update(mid+1, rightNode, node*2+1, location, value))%MOD;

        }

        @Override
        public String toString() {
            return "SegementTree{" +
                    "segmentArr=" + Arrays.toString(segmentArr) +
                    '}';
        }
    }
}
