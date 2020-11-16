import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class N1339 {

    private static String[] words;
    private static HashMap<Character, Integer> hashMap = new HashMap();
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        words = new String[n];

        for(int i=0;i<n;i++){
            char[] word = br.readLine().toCharArray();

            for(int j=0;j<word.length;j++){
                char key = word[j];
                int num = (int) Math.pow(10, word.length-1-j);
                if(hashMap.containsKey(key)){
                    num += hashMap.get(key);
                }
                hashMap.put(key, num);
            }
        }
        ArrayList<Integer> list = new ArrayList();
        for(char key : hashMap.keySet()){
            list.add(hashMap.get(key));
        }

        list.sort(Comparator.reverseOrder());

        int cnt = 9;
        int res = 0;
        for(int number : list){
            res += number*(cnt--);
        }
        System.out.println(res);
//        System.out.println(hashMap.toString());
//        System.out.println(list.toString());

    }
}
