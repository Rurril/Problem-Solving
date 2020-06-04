import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class N14003 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        String[] temp = br.readLine().split(" ");
        int[] arr = new int[n];
        int[] lisArray = new int[n];
        int[] lisIndex = new int[n];
        int[] preIndex = new int[n];
        boolean[] checked = new boolean[n];
        for(int i=0;i<n;i++)arr[i] = Integer.parseInt(temp[i]);

        int size = 0;
        for(int i=0;i<n;i++){

            int index = Arrays.binarySearch(lisArray, 0, size, arr[i])*-1 - 1;
            if(index < 0)
                index = index*-1 -1; // 같은 곳을 가리키는 경우 그대로 덮어 씌우는 것
            lisArray[index] = arr[i];

            if(index == size)size++;

            lisIndex[index] = i;
            if(index == 0)preIndex[i] = -1;
            else preIndex[i] = lisIndex[index-1];

        }

        ArrayList<Integer> res = new ArrayList();

        int tempIndex = lisIndex[size-1];
        while(tempIndex != -1){
            res.add(arr[tempIndex]);
            tempIndex = preIndex[tempIndex];
        }

        System.out.println(res.size());
        for(int i=res.size()-1; i >= 0; i--){
            System.out.print(res.get(i) + " ");
        }



    }
}
