package PS;
import java.io.*;
public class OneQuadrillionAndOneDalmatians {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long number = Long.parseLong(br.readLine());
        long temp = 0;
        long cnt = 1;
        String name = "";
        while(true){
            temp = number;
            number -= Math.pow(26, cnt);

            if(number <= 0){

                number = temp-1;
                cnt--;

                if(cnt == 0){
                    long alphabat = number%26;
                    char part = (char) (alphabat+97);
                    name += part;
                    break;
                }

                while(cnt > 0){


                    long tempNum = (long)Math.pow(26, cnt);
                    long alphabat = number/tempNum; // 0이라면 그냥 a
                    char part = (char) (alphabat+97);
                    number -= (tempNum)*alphabat;


                    name += part;

                    if(cnt ==1){
                        alphabat = number%26;
                        part = (char) (alphabat+97);
                        name += part;
                    }

                    cnt--;
                }

                break;
            }
            cnt++;
        }
        System.out.println(name);
    }
}
