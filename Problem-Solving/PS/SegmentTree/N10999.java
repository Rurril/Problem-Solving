import java.io.*;
import java.net.Inet4Address;

public class N10999 {

    private static int N,M,K;
    private static long[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        K = Integer.parseInt(temp[2]);

        arr = new long[N];

        for(int i=0;i<N;i++)
            arr[i] = Long.parseLong(br.readLine());


        SegmentTree segmentTree = new SegmentTree(arr);
//        segmentTree.printTree();

        for(int i=0;i<M+K;i++){
            temp = br.readLine().split(" ");

            int command = Integer.parseInt(temp[0]);

            if(command == 1){
                // 구간 합 바꾸는 연산
                int l = Integer.parseInt(temp[1])-1;
                int r = Integer.parseInt(temp[2])-1;
                long val = Long.parseLong(temp[3]);

                segmentTree.update_ragne(0, arr.length-1, 1, l, r, val);
//                segmentTree.printTree();
//                segmentTree.printLazy();
            }else if(command ==2){
                // 출력하는 연산
                int l = Integer.parseInt(temp[1])-1;
                int r = Integer.parseInt(temp[2])-1;

                bw.write(segmentTree.sum(0, arr.length-1, 1, l, r) + "\n");
//                System.out.println("sum");
//                segmentTree.printTree();
//                segmentTree.printLazy();
            }
        }

        bw.flush();

    }

    private static class SegmentTree{

        long[] segArr;
        long[] lazy;
        long[] arr;
        int segSize;

        SegmentTree(long[] arr){
            int height = (int) Math.ceil(Math.log(arr.length)/Math.log(2));
            segSize = (int)Math.pow(2, height+1);

            this.arr = arr;
            segArr = new long[segSize];
            lazy = new long[segSize];

            init(0, arr.length-1, 1);
        }

        private void printTree(){
            for(int i=1; i<segSize;i++)System.out.print(segArr[i] + " ");
            System.out.println();
        }

        private void printLazy(){
            for(int i=1; i<segSize;i++)System.out.print(lazy[i] + " " );
            System.out.println();
        }


        private long init(int left, int right, int node){

            int mid = (left+right)/2;
            if(left == right)
                return segArr[node] = arr[left];

            return segArr[node] = init(left, mid, node*2) + init(mid+1, right, node*2+1);
        }

        private long sum(int left, int right, int node, int leftNode, int rightNode){
            update_lazy(left, right, node);

            if(left > rightNode || right < leftNode)return 0;
            if(leftNode <= left && right <= rightNode){
                return segArr[node];
            }

            int mid = (left + right) / 2;

            return sum(left, mid, node*2, leftNode, rightNode) + sum(mid+1, right, node*2 +1, leftNode, rightNode);
        }

        private long update_ragne(int left, int right, int node, int leftNode, int rightNode, long val){
            update_lazy(left, right, node);

            // 구간들을 업데이트 해주어야 하므로, 원하지 않은 구간이어도 위의 부모가 구간합을 제대로 구하기 위해서 계산
            if(left > rightNode || right < leftNode)return segArr[node];


            if(leftNode <= left && right <= rightNode){
                if(left != right){ // 한 개의 개체가 아니라 구간이므로 아래에도 전파하기 위해서
                    // 자식 구간에서 update_lazy가 제대로 적용되는 것을 알 수 있음.
                    lazy[node*2] += val;
                    lazy[node*2 + 1] += val;
                }
                return segArr[node] += (right-left+1)*val;
            }

            int mid = (left+right)/2;

            return segArr[node] = update_ragne(left, mid, node*2, leftNode, rightNode, val) + update_ragne(mid+1, right, node*2 + 1, leftNode, rightNode, val);
        }

        private void update_lazy(int left, int right, int node){
            if(lazy[node] == 0)return;
            segArr[node] += lazy[node] * (right - left + 1);
            if(left != right){
                lazy[node*2] += lazy[node];
                lazy[node*2+1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
}
