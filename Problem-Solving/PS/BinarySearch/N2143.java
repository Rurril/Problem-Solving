import java.io.*;
public class N2143 {

    private static int T;
    private static int[] aSum,bSum;
    private static int[] aArr, bArr;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        int aLen = Integer.parseInt(br.readLine());
        aSum = new int[aLen + 1];
        aArr = new int[aLen + 1];
        String[] temp = br.readLine().split(" ");
        for(int i=0;i<aLen;i++){
            aArr[i+1] = Integer.parseInt(temp[i]);
            aSum[i+1] = aSum[i] + aArr[i+1];
        }

        int bLen = Integer.parseInt(br.readLine());
        bSum = new int[bLen + 1];
        bArr = new int[bLen + 1];
        temp = br.readLine().split(" ");
        for(int i=0;i<bLen;i++){
            bArr[i+1] = Integer.parseInt(temp[i]);
            bSum[i+1] = bSum[i] + bArr[i+1];
        }


        int cnt = 0;

        int aStart = 1;
        int aEnd = 1;
        int bStart = 1;
        int bEnd = 1;

        while(true){

            int aPart = aSum[aEnd] - aSum[aStart-1];


            while(bEnd <= bLen){


                // T >= 0 이라고할 떄
                int bPart = bSum[bEnd] - bSum[bStart-1];

                if(bStart == bEnd){
                    bEnd++;
                }

                if(aPart + bPart == T){ // 값을 작게해야한다.
                    cnt++;
                    bEnd++;
                }else if(aPart + bPart > T){
                    bStart++;
                }else if(aPart + bPart < T){
                    bEnd++;
                }

            }

            bStart = 1;
            bEnd = 1;


        }
    }
}
