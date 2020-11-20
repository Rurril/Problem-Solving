package PS;
import java.io.*;
import java.util.*;

public class N1092 {

    private static int N, M;
    private static ArrayList<Integer> weight = new ArrayList();
    private static ArrayList<Integer> constraint = new ArrayList();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 크래인이 N대, 1분에 박스를 하나씩 배에 실을 수 있다.
        // 각 크래인은 무게 제한이 있다.
        // 모든 박스를 배로 옮기는 시간 최솟값

        setUp();
        
        if(constraint.get(0) < weight.get(0)) System.out.println(-1);
        else{
            int time = 0;
            while(!weight.isEmpty()){
                int cranePointer = 0;
                for(int i=0;i<weight.size();i++){
                    if(cranePointer == constraint.size()) break; // 박스를 옮길 수 없을 경우 탈출.
                    if(constraint.get(cranePointer) >= weight.get(i)){
                        weight.remove(i);
                        cranePointer++;
                        i--;
                    }
                }
                time++;
            }
            System.out.println(time);
        }
    }
    private static void sol2(){

        if(weight.get(0) > constraint.get(constraint.size()-1)) System.out.println(-1);
        else{
            int time = 0;
            int index = 0;

            while(true){


//                System.out.println(cWeight + " " + maxWeight);
                for(int i=0; i<N; i++){

                    if(index == M)break;

                    int cWeight = weight.get(index);
                    int maxWeight = constraint.get(i);

                    if(cWeight <= maxWeight){
                        index++;
                    }
                }

                if(index == M)break;

                time++;
            }

            System.out.println(time+1);

        }
    }

    private static void sol1(){
        int cranePointer = 0;
        int time = 0;
        int index = 0;
        while(index < M){

            System.out.println(index + " " + cranePointer);

            if(constraint.get(cranePointer) >= weight.get(index)){ // 옮길 수 있다면
//                System.out.println();
//                System.out.println(weight.get(index));
                System.out.println(constraint.get(cranePointer) + "가 " + weight.get(index) + "를옮김");
                index++;
                cranePointer++;
            }else{
                cranePointer++;
            }

            if(index % N == 0 && index != 0){
                System.out.println("다 옮김");
                time++;
            }

            if(cranePointer >= N)cranePointer = 0;

        }

        System.out.println(time);
    }

    private static void setUp() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<N;i++){
            constraint.add(Integer.parseInt(temp[i]));
        }

        M = Integer.parseInt(br.readLine());
        temp = br.readLine().split(" ");
        for(int i=0;i<M;i++){
            weight.add(Integer.parseInt(temp[i]));
        }

        weight.sort(Comparator.reverseOrder());
        constraint.sort(Comparator.reverseOrder());
    }
}

