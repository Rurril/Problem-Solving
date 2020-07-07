import java.io.*;
public class N4358_sub {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        int cnt = 0;

        while (br.ready()) {
            trie.insert(br.readLine());
            cnt += 1;
        }

        trie.print(cnt);

    }

    static class Trie{

        StringBuilder sb;
        private final int MAX = 128;
        Node root;

        class Node{
            Node[] next;
            boolean isFinished;
            int count;

            public Node(){
                this.next = new Node[MAX];
                this.isFinished = false;
                this.count = 0;
            }
        }

        Trie(){
            root = new Node();
            sb = new StringBuilder();
        }

        void insert(String str){
            int len = str.length();
            Node cur = root;

            for(int i=0;i<len;i++){
                int index = str.charAt(i);

                if(cur.next[index] == null){
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
            }
            cur.count++;
            cur.isFinished = true;
        }

        void print(int cnt){
            check(root, "", cnt);
            System.out.println(sb.toString());
        }

        void check(Node cur, String name, int cnt){
            if(cur.isFinished){
                String ratio = String.format("%.4f", 100 * ((double) cur.count/cnt));
                sb.append(name + " " + ratio + "\n");
            }

            for(int i=0;i<MAX;i++){
                if(cur.next[i] != null){
                    char c = (char) i;
                    check(cur.next[i], name + c, cnt);
                }
            }
        }
    }
}
