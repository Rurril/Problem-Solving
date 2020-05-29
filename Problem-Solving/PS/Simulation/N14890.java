package PS;
import java.io.*;
public class N14890 {

    private static int n,l;
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        l = Integer.parseInt(temp[1]);
        map = new int[n][n];

        for(int i=0;i<n;i++){
            temp = br.readLine().split(" ");
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

//        System.out.println(hasRoadHorizontal(1));
//        System.out.println(hasRoadVertical(1));
        int cnt =0;
        for(int i=0;i<n;i++){
            if(hasRoadVertical(i)){
                cnt++;
//                System.out.println("vertical : " + i);
            }
            if(hasRoadHorizontal(i)){
                cnt++;
//                System.out.println("horizontal : " + i);
            }
        }
        System.out.println(cnt);

    }

    private static boolean hasRoadHorizontal(int row){
        boolean[] isChecked = new boolean[n];
        int height = map[row][0];
        for(int i=1;i<n;i++){
//            System.out.println(height);
            if(map[row][i] == height)continue;
            else if(map[row][i] + 1 == height){ // 1칸 낮아지는 경우.
                height = map[row][i];
                for(int j=0;j<l;j++){// 경사로 설치
//                    System.out.println("test1 " + i + " " + j);
                    if(i+j >= n)return false;
                    if(isChecked[i+j])return false;
                    isChecked[i+j] = true;
                    if(height != map[row][i+j])return false;
                }
            }else if(map[row][i] - 1 == height){ // 1칸 높아지는 경우
                height = map[row][i];
                for(int j=1;j<=l;j++){
//                    System.out.println("test2 " + i + " " + j);
                    if(i-j < 0)return false;
                    if(isChecked[i-j])return false;
                    isChecked[i-j] = true;
                    if(height-1 != map[row][i-j])return false;
                }
            }else
                return false;
        }
        return true;
    }

    private static boolean hasRoadVertical(int col){
        boolean[] isChecked = new boolean[n];
        int height = map[0][col];
        for(int i=1;i<n;i++){
            if(map[i][col] == height)continue;
            else if(map[i][col] + 1 == height){ // 1칸 낮아지는 경우.
                height = map[i][col];
                for(int j=0;j<l;j++){
                    if(i+j >= n)return false;
                    if(isChecked[i+j])return false;
                    isChecked[i+j] = true;
                    if(height != map[i+j][col])return false;
                }
            }else if(map[i][col] - 1 == height){ // 1칸 높아지는 경우
                height = map[i][col];
                for(int j=1;j<=l;j++){
                    if(i-j < 0)return false;
                    if(isChecked[i-j])return false;
                    isChecked[i-j] = true;
                    if(height-1 != map[i-j][col])return false;
                }
            }else
                return false;
        }
        return true;
    }
}
