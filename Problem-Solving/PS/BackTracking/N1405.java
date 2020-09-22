import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class N1405 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int numberOfVotes = Integer.parseInt(br.readLine());
        String[] votes = br.readLine().split(" ");
        Student[] students = new Student[100];
        ArrayList<Student> frame = new ArrayList<>();
        for(int i=0;i<100;i++)students[i] = (new Student(0, i+1, 0));


        for(int i=0;i<numberOfVotes;i++){
            int number = Integer.parseInt(votes[i]);
            boolean check = false;

            for(Student student : frame){
                if(number == student.getIdx()){
                    students[number-1].vote();
                    check = true;
                    break;
                }
            }

            if(!check){ // 틀에 걸려있지 않은 경우
                if(frame.size() != N){
                    students[number-1].vote();
//                    frame.add(number);
                }else{ // 꽉 찼을 경우.

                }
            }




        }

        Arrays.sort(students);


    }

    static class Student implements Comparable<Student>{

        private int votes;
        private int idx;
        private int lastVotes;

        Student(int votes, int idx, int lastVotes){
            this.votes = votes;
            this.idx = idx;
            this.lastVotes = lastVotes;
        }

        private void vote(){
            this.votes++;
        }

        private int getIdx(){
            return this.idx;
        }

        private void setLastVotes(int number){
            this.lastVotes = number;
        }


        @Override
        public int compareTo(Student s) {
            if(this.votes == s.votes)return s.lastVotes - this.lastVotes;
            else return s.votes - this.votes;
        }

        @Override
        public String  toString() {
            return "Student{" +
                    "votes=" + votes +
                    ", idx=" + idx +
                    ", lastVotes=" + lastVotes +
                    '}';
        }
    }
}
