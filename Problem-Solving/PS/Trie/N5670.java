
import java.io.*;
public class N5670 {

    private static final int SIZE = 26;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        while (br.ready()) {
//        while(true){
            int N = Integer.parseInt(br.readLine());

//            if (N == 0) break;

            Trie trie = new Trie();

            String[] str = new String[N];

            for (int i = 0; i < N; i++) {
                str[i] = br.readLine();
                trie.insert(str[i]);
            }

            for (int i = 0; i < SIZE; i++) {
                if (trie.getRoot().getChild(i) != null) {
                    trie.check(trie.getRoot().getChild(i), 1);
                }
            }

            System.out.printf("%.2f", (double) cnt / N);
            System.out.println();

            cnt = 0;

        }
    }
    static class Trie{

        class TrieNode{

            private TrieNode[] children = new TrieNode[SIZE];
            private boolean isFinished;
            private int nChild = 0;

            TrieNode(){
                isFinished = false;
                for(int i=0;i<SIZE;i++){
                    children[i] = null;
                }
            }

            private void addChild(int child){
                this.children[child] = new TrieNode();
                this.nChild++;
            }

            private int getNChild(){
                return this.nChild;
            }

            private TrieNode getChild(int number){
                return children[number];
            }

            private boolean isFinished(){
                return isFinished;
            }
        }

        private TrieNode root;

        Trie(){
            root = new TrieNode();
        }

        private TrieNode getRoot(){return this.root;}

        private int toNumber(char a){ // 이렇게 만들어서 사용하는 방법이 있네. 깔끔하게
            return a - 'a';
        }

        private void insert(String key){

            int length = key.length();
            TrieNode current = root;

            for(int i=0;i<length;i++){

                int next = toNumber(key.charAt(i));

                if(current.children[next] == null){
                    current.addChild(next);
                }

                current = current.getChild(next);
            }

            current.isFinished = true;
        }

        private void check(TrieNode node, int ret){

            if(node.isFinished()){
                cnt += ret;
            }

            if(node.getNChild() >= 2){
                ret++;
            }

            if(node.isFinished() && node.getNChild() == 1){ // 히든케이스
                ret++;
            }

            for(int i=0;i<SIZE; i++){
                if(node.getChild(i) != null){
                    check(node.getChild(i), ret);
                }
            }
        }
    }
}
