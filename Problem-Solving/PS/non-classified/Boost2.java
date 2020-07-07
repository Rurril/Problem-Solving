import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Boost2 {

    public static void main(String[] args) throws IOException{

        int[] arrayA = {2,3,4,3,5};//
//        int[] arrayA = {1,2,3,2};
//        int[] arrayB = {1,3};
        int[] arrayB = {1,6,7};
        int[] result = new int[5];
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        int pointer = 0;
        A.add(arrayA[0]);
        for(int i=1;i<arrayA.length;i++){
            if(A.get(pointer) == arrayA[i]){
                continue;
            }else{
                A.add(arrayA[i]);
                pointer++;
            }
        }




        pointer = 0;
        B.add(arrayB[0]);
        for(int i=1;i<arrayB.length;i++){
            if(B.get(pointer) == arrayB[i]){
                continue;
            }else{
                B.add(arrayB[i]);
                pointer++;
            }
        }

        System.out.println(A);
        System.out.println(B);

        result[0] = A.size();
        result[1] = B.size();

        int[] arrA = new int[A.size()];
        for(int i=0;i<A.size();i++){
            arrA[i] = A.get(i);
        }
        int[] arrB = new int[B.size()];
        for(int i=0;i<B.size();i++){
            arrB[i] = B.get(i);
        }


        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(sum(arrA, arrB).length);
        System.out.println(complement(arrA, arrB).length);
        System.out.println(intersect(arrA, arrB).length);

    }

    public static void toString(int[] arr){

        System.out.print("[");
        System.out.println(arr[0]);
        for(int i=1;i<arr.length;i++){
            System.out.print(", "+ arr[i]);
        }
        System.out.println("]");

    }

    public static int[] complement(int[] A, int[] B){

        ArrayList<Integer> temp = new ArrayList<>();

        int pointerA = 0;
        int pointerB = 0;
        while(true){

            if(pointerA >= A.length || pointerB >= B.length)break;

            if(A[pointerA] < B[pointerB]){
                temp.add(A[pointerA]);
                pointerA++;
            }else if(A[pointerA] > B[pointerB]){
                pointerB++;
            }else{

                pointerA++;
                pointerB++;
            }
        }

        int[] result = new int[temp.size()];
        for(int i=0;i<result.length;i++){
            result[i] = temp.get(i);
        }
        return result;
    }

    public static int[] intersect(int[] A, int[] B){

        ArrayList<Integer> temp = new ArrayList<>();

        int pointerA = 0;
        int pointerB = 0;
        while(true){

            if(pointerA >= A.length || pointerB >= B.length)break;

            if(A[pointerA] < B[pointerB]){
                pointerA++;
            }else if(A[pointerA] > B[pointerB]){
                pointerB++;
            }else{
                temp.add(A[pointerA]);
                pointerA++;
                pointerB++;
            }
        }

        int[] result = new int[temp.size()];
        for(int i=0;i<result.length;i++){
            result[i] = temp.get(i);
        }
        return result;
    }


    public static int[] sum(int[] A, int[] B){

        ArrayList<Integer> res = new ArrayList();


        for(int next : A){
            res.add(next);
        }
        for(int next : B){
            res.add(next);
        }

        res.sort(null);


        int pointer = 0;
        while(true){


            if(res.get(pointer) == res.get(pointer+1)){
                res.remove(pointer);
            }else{
                pointer++;
            }

            if(pointer == res.size()-1)break;
        }



        int[] result = new int[res.size()];
        for(int i=0;i<result.length;i++){
            result[i] = res.get(i);

        }

        return result;



    }
}

