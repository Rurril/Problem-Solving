    package PS;
    import java.io.*;
    import java.util.Arrays;

    public class N10868 {

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

            SegmentTree segmentTree = new SegmentTree(N, arr);
    //        segmentTree.printSegment();
            for(int i=0;i<M;i++){
                temp = br.readLine().split(" ");
                int start = Integer.parseInt(temp[0]);
                int end= Integer.parseInt(temp[1]);

                long min = segmentTree.minQuery(start-1, end-1, 1, 0, N-1);
                bw.write(min + "\n");
            }
            bw.flush();
        }

        private static class SegmentTree{
            long[] segmentTreeArr;
            int segmentSize;

            SegmentTree(int N, long[] arr){
                int height = (int) Math.ceil(Math.log(N) / Math.log(2));
                segmentSize = (int) Math.pow(2, height+1);
                segmentTreeArr = new long[segmentSize];

                init(arr, 0, N-1, 1);

            }
            private void printSegment(){
                for(int i=1;i<segmentSize;i++) System.out.print(segmentTreeArr[i] + " ");
                System.out.println();
            }

            private long minQuery(int left, int right, int node, int nodeLeft, int nodeRight){

                // 두 구간이 곂치지 않는 경우
                if(left > nodeRight || right < nodeLeft)return Long.MAX_VALUE;

                int mid = (nodeLeft + nodeRight) / 2;

                // 두 구간이 완전히 속하는 경우
                if(left <= nodeLeft && right >= nodeRight)
                    return segmentTreeArr[node];

                return Math.min(minQuery(left, right, node*2, nodeLeft, mid) , minQuery(left, right, node*2 + 1, mid + 1, nodeRight));
            }

            private long init(long[] arr, int left, int right, int node){

                int mid = (left+right)/2;
                if(left == right)
                    return segmentTreeArr[node] = arr[left];
                return segmentTreeArr[node] = Math.min(init(arr, left, mid, node*2), init(arr, mid+1, right, node*2+1));
            }
        }
    }
