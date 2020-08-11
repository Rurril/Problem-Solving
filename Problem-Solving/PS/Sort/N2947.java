package PS;
import java.io.*;


public class N2947  {

    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        arr = new int[5];
        for(int i=0;i<5;i++)arr[i] = Integer.parseInt(temp[i]);

        while(true){
            if(arr[0] > arr[1]){
                int tmp = arr[1];
                arr[1] = arr[0];
                arr[0] = tmp;
                print();
            }

            if(arr[1] > arr[2]){
                int tmp = arr[2];
                arr[2] = arr[1];
                arr[1] = tmp;
                print();
            }

            if(arr[2] > arr[3]){
                int tmp = arr[3];
                arr[3] = arr[2];
                arr[2] = tmp;
                print();
            }

            if(arr[3] > arr[4]){
                int tmp = arr[4];
                arr[4] = arr[3];
                arr[3] = tmp;
                print();
            }

            if(check())break;
        }



    }

    private static boolean check(){
        for(int i=0;i<5;i++){
            if(arr[i] != i+1)return false;
        }
        return true;
    }

    private static void print(){
        for(int i=0;i<5;i++) System.out.print(arr[i] + " ");
        System.out.println();
    }


}

