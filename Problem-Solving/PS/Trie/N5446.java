package PS;
import java.io.*;
public class N5446 {

    private static final int MAX = 128;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0){

            cnt = 0;

            Trie forDelete = new Trie();
            Trie except = new Trie();

            int n = Integer.parseInt(br.readLine());
            for(int i=0;i<n;i++){
                String word = br.readLine();
                forDelete.insert(word);
            }
            int m = Integer.parseInt(br.readLine());
            for(int i=0;i<m;i++){
                String word = br.readLine();
                except.insert(word);
            }

            if(m == 0) {
                System.out.println(1);
                continue;
            }

            TrieNode node = forDelete.root;
            for(int i=0;i<MAX;i++){
                if(node.getChild(i) != null)search(""+(char)i, node.getChild(i), except);
            }

            System.out.println(cnt);

        }
    }

    private static void search(String word, TrieNode node, Trie except){

//        System.out.println(word + " " + cnt);
        // 첫 시작은 root로 넣지말고, 하나의 단어를 넣고 시작하도록 하자.
        if(!except.check(word)){ // 삭제를 하고 더 이상 진행하지 않아도 됨.
            cnt++;
            return;
        }

        if(node.isFinished())cnt++;

        for(int i=0;i<MAX;i++){

            if(node.getChild(i) != null){
                word += (char) i;
                search(word, node.getChild(i), except);
                word = word.substring(0, word.length()-1);
            }
        }
    }

    static class Trie{

        private TrieNode root;

        Trie(){
            root = new TrieNode();
        }

        private void insert(String word){

            char[] arr = word.toCharArray();
            TrieNode current = root;
            for(char a : arr){

                int num = a;
                if(current.getChild(num) == null){
                    current.setChild(num);
                }
                current.setHaveChild(true);
                current = current.getChild(num);
            }

            current.setFinished(true);
        }

        private boolean check(String word){

            char[] arr = word.toCharArray();
            TrieNode current = root;
            for(char a : arr){

                int num = a;
                if(current.getChild(num) == null){
                    return false; // word를 가지고 있지 않다.
                    // 여기서 false가 나온다면, 그 아래부분은 모두 삭제하고 +1 해주면 됨.
                }
                current = current.getChild(num);
            }

            return true;
        }


    }

    static class TrieNode{

        private TrieNode[] children;
        private boolean isFinished;
        private boolean haveChild;

        TrieNode(){
            children = new TrieNode[MAX];
            isFinished = false;
            haveChild = false;
        }

        private TrieNode getChild(int num){
            return children[num];
        }

        private void setChild(int num){
            children[num] = new TrieNode();
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void setFinished(boolean finished) {
            isFinished = finished;
        }

        public boolean isHaveChild() {
            return haveChild;
        }

        public void setHaveChild(boolean haveChild) {
            this.haveChild = haveChild;
        }
    }
}
