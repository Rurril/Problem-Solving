import java.io.*;
import java.util.regex.Pattern;
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String sound = new BufferedReader(new InputStreamReader(System.in)).readLine();
		if(Pattern.matches("(100+1+|01)+", sound)) System.out.println("SUBMARINE");
		else System.out.println("NOISE");
    }
}
