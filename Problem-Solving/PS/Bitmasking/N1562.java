import java.io.*;
public class N1562 {

    private static long count = 0;
    private static int[] d = {1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        if(N < 10)System.out.println(0);
        else if(N == 10)System.out.println(1);
        else{

            for(int i=1;i<10;i++){

                int bitmask = (1<<i);
                DFS(N-1, i, bitmask);

            }

            System.out.println(count);
        }

    }

    private static void DFS(int depth, int next, int bitmask){
//        System.out.println(depth);
        if(depth == 0){
            if(bitmask == 0x03ff)count++;
            return;
        }

        for(int i=0;i<2;i++){
            int dn = next +d[i];

            if(dn < 0 || dn > 9)continue;

            if((bitmask & (1<<dn)) == 0){ // i 값을 가지고 있지 않다는 것을 의미.
                bitmask += (1<<dn);
                DFS(depth-1, dn, bitmask);
                bitmask -= (1<<dn);
            }else{
                DFS(depth-1, dn, bitmask);
            }
        }



    }
}
