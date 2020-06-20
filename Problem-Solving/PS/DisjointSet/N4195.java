package PS;
import org.w3c.dom.xpath.XPathResult;

import java.io.*;
import java.util.HashMap;

public class N4195 {

    private static int[] parent;
    private static int[] depth;
    private static int[] relation;
    private static final int MAX = 200001;
    private static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){
            int n = Integer.parseInt(br.readLine());
            int idx = 1;
            HashMap<String, Integer> relations = new HashMap<>();
            parent = new int[MAX];
            depth = new int[MAX];
            relation = new int[MAX];

            for(int i=1;i<MAX;i++){
                parent[i] = i;
                relation[i] = 1;
            }

            for(int i=0;i<n;i++){
                String[] people = br.readLine().split(" ");

                if(!relations.containsKey(people[0]))
                    relations.put(people[0], idx++);

                if(!relations.containsKey(people[1]))
                    relations.put(people[1], idx++);

                int aIdx = relations.get(people[0]);
                int bIdx = relations.get(people[1]);

                union(aIdx, bIdx);
            }
        }
        bw.flush();


    }

    private static void union(int a, int b) throws IOException {
        a = find(a);
        b = find(b);

        if(a == b){
            bw.write(relation[b]  + "\n");
            return;
        }

        if(depth[a] > depth[b]){ // b를 더 깊은 것을 놓는다.
            int temp = b;
            b = a;
            a = temp;
        }

        parent[a] = b;
        relation[b] += relation[a];
        bw.write(relation[b]  + "\n");

        if(depth[a] == depth[b])depth[b]++;
    }

    private static int find(int a){
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }
}
