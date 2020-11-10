package PS;
import java.io.*;
public class N1747 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        while(true) {
            if(isPrimeNumber(N) && isPalindrome(N)) {
                System.out.println(N);
                return;
            }
            N++;
        }
    }

    private static boolean isPrimeNumber(int n) {
        if (n == 1) return false;

        for(int i=2; i*i<=n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }

    private static boolean isPalindrome(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int s = 0, e = arr.length-1;

        while(s < e) {
            if(arr[s] != arr[e]) return false;
            s++; e--;
        }
        return true;
    }
}
