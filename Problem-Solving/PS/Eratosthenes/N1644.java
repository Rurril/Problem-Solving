import java.io.*;
import java.util.ArrayList;

public class N1644 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int target = Integer.parseInt(br.readLine());

        ArrayList<Integer> decimal = new ArrayList();
        ArrayList<Integer> preSum = new ArrayList();
        int cnt = 0;
        boolean[] check = new boolean[4_000_001];

        for(int i=2;i<=4_000_000; i++){
            for(int j=2; i*j <= 4_000_000; j++){
                check[i*j] = true;
            }
        }

        for(int i=2; i<=4_000_000; i++){
            if(!check[i])decimal.add(i);
        }
        preSum.add(0);

        for(int i=0;i<decimal.size();i++){
            preSum.add(preSum.get(i) + decimal.get(i));
        }

        int left = 1;
        int right = 1;
        while(true){

            if(left > right) break;
            if(right == preSum.size())break;

            int tSum = preSum.get(right) - preSum.get(left-1);

            if(tSum < target)right++;
            else if(tSum > target)left++;
            else {
                cnt++;
                left++;
            }
        }
        System.out.println(cnt);
    }
}
