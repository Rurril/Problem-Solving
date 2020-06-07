package PS;
import java.io.*;
import java.util.Queue;

public class N6549 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String[] temp = br.readLine().split(" ");
            int length = Integer.parseInt(temp[0]);

            if(length == 0)break;

            long[] arr = new long[length];
            for(int i=0;i<length;i++)arr[i]= Long.parseLong(temp[i+1]);

            SegmentTree segmentTree = new SegmentTree(length, arr);

//            segmentTree.printTree();
            bw.write(segmentTree.getMaxArea(0, arr.length-1) + "\n");
        }
        bw.flush();
    }

    private static class SegmentTree{
        int[] segmentTreeArr;
        long[] valueArr;
        int segmentSize;

        SegmentTree(int N, long[] arr){
            int height = (int)Math.ceil(Math.log(N) / Math.log(2));
            segmentSize = (int)Math.pow(2, height+1);
            segmentTreeArr = new int[segmentSize];
            valueArr = new long[N];

            for(int i=0;i<N;i++)
                valueArr[i] = arr[i];

            init(1, 0, N-1);
        }

        private void printTree(){
            for(int i=1;i<segmentSize;i++){
                System.out.print(segmentTreeArr[i] + " ");
            }
            System.out.println();
        }

        private int init(int node, int left, int right){

            int mid = (left + right) / 2;
            if(left == right) // 최소 높이가 있는 인덱스를 적어준다.
                return segmentTreeArr[node] = left;

            int leftIndex = init(node*2, left, mid);
            int rightIndex = init(node*2+1, mid+1, right);
            return segmentTreeArr[node] =
                    valueArr[leftIndex] < valueArr[rightIndex] ? leftIndex : rightIndex;
        }

        private int Query(int left, int right, int node, int leftNode, int rightNode){ // left, right 를 기준으로 최솟값을 같는 인덱스를 리턴해주는 기능

            int mid = (left + right)/2;

            if(right < leftNode || left > rightNode) // 얻고자하는 범위를 벗어난 것
                return -1;


            if(left >= leftNode && right <= rightNode)
                return segmentTreeArr[node]; // 올바른 인덱스를 리턴해준다.

            //인덱스에서 value값을 얻어서 더 작은 것을 리턴해준다.
            int leftIndex = Query(left, mid, node*2, leftNode, rightNode);
            int rightIndex = Query(mid+1, right, node*2+1, leftNode, rightNode);
            if(leftIndex == -1)return rightIndex;
            else if(rightIndex == -1)return leftIndex;
            else return valueArr[leftIndex] < valueArr[rightIndex] ? leftIndex : rightIndex;
        }

        private long getMaxArea(int left, int right){

            int index = Query(0, valueArr.length-1, 1, left, right);

            long area = valueArr[index]*(long)(right - left + 1);

            if(left <= index-1){
                long leftArea = getMaxArea(left, index-1);
                area = Math.max(area, leftArea);
            }

            if(index+1 <= right){
                long rightArea = getMaxArea(index+1, right);
                area = Math.max(area, rightArea);
            }
            return area;
        }
    }
}
