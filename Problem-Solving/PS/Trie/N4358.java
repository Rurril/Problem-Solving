import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class N4358 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<String, Integer> words = new HashMap<>();


        String line;
        int cnt = 0;
        while((line = br.readLine()) != null){

//            if(line.equals("0"))break;

            if(words.containsKey(line)){
                words.replace(line, words.get(line)+1);
            }else{
                words.put(line, 1);
            }
            cnt++;
        }

        TreeMap<String, Integer> treeMap = new TreeMap<>(words);

        for(String key : treeMap.keySet()){

            bw.write(key + " ");
            double ratio = (treeMap.get(key)*100) / (double)cnt;
            String k = String.format("%.4f", ratio);

            bw.write(k + "\n");
        }

        bw.flush();



    }

    static class Trie{


    }
}
