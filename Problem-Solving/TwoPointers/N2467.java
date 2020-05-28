package PS;
import javax.print.DocFlavor;
import java.io.*;
public class N2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<n;i++)arr[i] = Integer.parseInt(temp[i]);

        int start = 0;
        int end = arr.length-1;
        int max = 0;
        int min = 0;
        int result = 2_000_000_001;
        while(true){
            int liquid = arr[start] + arr[end];
//            System.out.println(start + " " + end + " max and min : " + max + " " + min);
            if(Math.abs(liquid) < result){
                result = Math.abs(liquid);
                max = arr[end];
                min = arr[start];
            }

            if(liquid > 0){
                end--;
            }else if(liquid < 0){
                start++;
            }else{
                //liquid == 0
                break;
            }

            if(start == end)break;
        }

        System.out.println(min +  " " + max);
    }
}
