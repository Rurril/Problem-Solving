import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class N9202 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static char[][] board;
    private static boolean[][] check;
    private static Set<String> set;
    private static Trie trie;
    private static int[] dy = {1, 1, 1, 0 , 0, -1, -1, -1};
    private static int[] dx = {1, -1, 0, -1 , 1, -1, 0, 1};
    private static int[] score = {0, 0, 1, 1, 2, 3, 5, 11};

    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());

        trie = new Trie();

        while(N-- > 0){
            String word = br.readLine();

            trie.insert(word);
        }

        br.readLine();

        int nBoard = Integer.parseInt(br.readLine());

        for(int i=0;i<nBoard;i++){

            find();
            print();
            if(i == nBoard-1)break;


            br.readLine();
        }
        bw.flush();

    }

    private static void print() throws IOException {
        int point = 0;
        int size = set.size();
        String longest = "";

        for(String word : set){

            point += score[word.length()-1];

            if(longest.length() < word.length())longest = word;

        }
        ArrayList<String> list = new ArrayList<>();

        for(String word : set){
            if(word.length() == longest.length()) list.add(word);
        }

        Collections.sort(list);

        bw.write(Integer.toString(point) + " " + list.get(0) + " " + Integer.toString(size));
        bw.newLine();
    }

    private static void find() throws IOException {
        board = new char[4][4];
        check = new boolean[4][4];
        set = new HashSet<>();

        for(int j=0;j<4;j++){
            char[] arr = br.readLine().toCharArray();
            for(int k=0;k<4;k++){
                board[j][k] = arr[k];
            }
        }

        for(int j=0;j<4;j++){
            for(int k=0;k<4;k++){
                check[j][k] = true;
                DFS(j, k, "" + board[j][k], 1);
                check[j][k] = false;
            }
        }

//        System.out.println(set);
    }

    private static void DFS(int y, int x, String word, int cnt){

        if(cnt > 8)return;

        if(!trie.check(word)){
            return;
        };


        for(int i=0;i<8;i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= 4 || nx >= 4 || ny < 0 || nx < 0)continue;
            if(check[ny][nx])continue;

            word += board[ny][nx];
            check[ny][nx] = true;
            DFS(ny, nx, word, cnt+1);
            word = word.substring(0, word.length()-1);
            check[ny][nx] = false;
        }

    }

    static class Trie{

        private TrieNode root;

        Trie(){
            root = new TrieNode();
        }

        private boolean check(String word){

            int length = word.length();
            TrieNode current = root;
            for(int i=0;i<length;i++){

                int order = word.charAt(i) - 'A'; // A = 0 , Z = 25

                if(current.children[order] == null){
                    return false; // 존재하지 않는 단어
                }

                current = current.children[order];
            }

            if(current.isFinished())set.add(word);

            if(current.isHaveChild())return true; // 더 확인해봐야 하는 경우
            else return false;

        }

        private void insert(String word){

            int length = word.length();
            TrieNode current = root;
            for(int i=0;i<length;i++){

                int order = word.charAt(i) - 'A'; // A = 0 , Z = 25

                if(current.children[order] == null){
                    current.children[order] = new TrieNode();
                }
                current.setHaveChild(true);
                current = current.children[order];
            }

            current.setFinished(true);
//            current.setLength(length);
        }

    }

    static class TrieNode{

        private TrieNode[] children;
        private boolean isFinished;
        private boolean haveChild;
        private int length;

        TrieNode(){
            children = new TrieNode[26];
            isFinished = false;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public void setFinished(boolean finished) {
            isFinished = finished;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }


        public boolean isHaveChild() {
            return haveChild;
        }

        public void setHaveChild(boolean haveChild) {
            this.haveChild = haveChild;
        }
    }
}

